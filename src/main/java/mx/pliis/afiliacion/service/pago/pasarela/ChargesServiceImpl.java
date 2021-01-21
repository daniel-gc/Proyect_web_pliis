package mx.pliis.afiliacion.service.pago.pasarela;

import static mx.pliis.afiliacion.enums.PagoEstatus.FAILED;
import static mx.pliis.afiliacion.enums.PagoEstatus.SUCCESS;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;
import mx.openpay.client.Charge;
import mx.openpay.client.Customer;
import mx.openpay.client.core.OpenpayAPI;
import mx.openpay.client.core.requests.transactions.CreateCardChargeParams;
import mx.openpay.client.core.requests.transactions.RefundParams;
import mx.openpay.client.exceptions.OpenpayServiceException;
import mx.openpay.client.exceptions.ServiceUnavailableException;
import mx.openpay.client.utils.SearchParams;
import mx.pliis.afiliacion.dto.pago.ChargeDTO;
import mx.pliis.afiliacion.dto.pago.CustomerDTO;
import mx.pliis.afiliacion.dto.pago.webhook.TransactionNotification;
import mx.pliis.afiliacion.dto.pago.webhook.WebhookNotification;
import mx.pliis.afiliacion.persistencia.hibernate.entity.PagosEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.UsuarioEntity;
import mx.pliis.afiliacion.persistencia.hibernate.repository.PagosEntityRepository;
import mx.pliis.afiliacion.persistencia.hibernate.repository.UsuarioEntityRepository;
import mx.pliis.afiliacion.service.excepciones.InvalidDataException;
import mx.pliis.afiliacion.service.excepciones.PaymentServiceException;
import mx.pliis.afiliacion.service.pago.PagosService;
import mx.pliis.afiliacion.utils.pago.PagoUtil;

@Service
@Log4j2
public class ChargesServiceImpl implements ChargesService {

	@Autowired
	private OpenpayAPI openpayAPI;

	@Autowired
	private PagoUtil pagoUtil;

	@Autowired
	private CustomersService customersService;

	@Autowired
	private PagosService pagosService;

	@Autowired
	private UsuarioEntityRepository usuarioEntityRepository;

	@Autowired
	private PagosEntityRepository pagosEntityRepository;

	@Transactional
	public Charge createCompleteCustomerCharge(final ChargeDTO chargeDTO) throws PaymentServiceException {
		UsuarioEntity usuario = usuarioEntityRepository.findByArqIdUsuario(chargeDTO.getUsuarioArqId()).orElse(null);
		if (usuario == null) {
			log.error("El usuario de arquitectura {} no existe", chargeDTO.getUsuarioArqId());
			throw new InvalidDataException("El usuario de arquitectura no fue encontrado");
		}

		String opCustomerId = usuario.getIdUsuarioOpenpay();
		if (StringUtils.isBlank(opCustomerId)) {
			Customer findCustomerOP = customersService.findCustomerByExternalId(usuario.getIdUsuario().toString());
			if(findCustomerOP == null) {
				log.info("El usuario no esta registrado en Openpay, se procede a registrar...");
				CustomerDTO newCustomer = new CustomerDTO();
				newCustomer.setName(usuario.getNombres());
				newCustomer.setPhoneNumber(usuario.getTelefono());
				newCustomer.setEmail(chargeDTO.getEmail());

				Customer opCustomer = this.customersService.createCustomer(usuario.getNombres(), null, chargeDTO.getEmail(),
						usuario.getTelefono(), usuario.getIdUsuario());
				opCustomerId = opCustomer.getId();
				usuario.setIdUsuarioOpenpay(opCustomerId);
			} else {
				opCustomerId = findCustomerOP.getId();
				usuario.setIdUsuarioOpenpay(opCustomerId);
			}
		}
		log.info("El usuario ya se encuentra registrado en Openpay, se procede a realizar el cargo...");

		PagosEntity bdPayment = pagosService.createPendingCharge(usuario.getIdUsuario(), chargeDTO.getDescription(),
				chargeDTO.getAmount(), chargeDTO.getIdPlan());
		String orderId = "ORD-" + bdPayment.getIdPago();

		Charge payment;
		try {
			payment = this.createCustomerCharge(opCustomerId, chargeDTO.getTokenId(), chargeDTO.getAmount(),
					chargeDTO.getDescription(), orderId, chargeDTO.getDeviceSessionId());
		} catch (InvalidDataException e) {
			throw new PaymentServiceException(e);
		} catch (ServiceUnavailableException | OpenpayServiceException e) {
			if (e instanceof OpenpayServiceException) {
				bdPayment.setStatus(FAILED);
				bdPayment.setErrorCode(((OpenpayServiceException) e).getErrorCode());
				bdPayment.setErrorMessage(((OpenpayServiceException) e).getDescription());
				this.pagosService.updatePendingCharge(bdPayment);
			}
			throw this.pagoUtil.throwByOpenpayException(e);
		}

		if ("COMPLETED".equalsIgnoreCase(payment.getStatus())) {
			bdPayment.setIdPagoOpenpay(payment.getId());
			bdPayment.setAuthorization(payment.getAuthorization());
			bdPayment.setStatus(SUCCESS);
		} else if ("FAILED".equalsIgnoreCase(payment.getStatus())) {
			bdPayment.setStatus(FAILED);
			bdPayment.setErrorMessage(payment.getErrorMessage());
		}
		this.pagosService.updatePendingCharge(bdPayment);
		return payment;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Charge createCustomerCharge(String opCustomerId, String opCardId, BigDecimal amount, String description,
			String orderId, String deviceSessionId)
			throws InvalidDataException, OpenpayServiceException, ServiceUnavailableException { // PaymentServiceException
		log.info("Creating charge request for customer {}", opCustomerId);
		CreateCardChargeParams request = new CreateCardChargeParams();
		this.pagoUtil.createCharge(request, opCardId, amount, description, orderId, deviceSessionId);
		log.info("Charge request to Openpay {}", request);

		Charge payment = null;

		try {
			payment = this.openpayAPI.charges().createCharge(opCustomerId, request);
		} catch (OpenpayServiceException | ServiceUnavailableException e) {
			log.error("An error occurred while creating a charge...", e);
			throw e;
		}
		log.info("Charge created successfully");
		return payment;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void confirmTransactionStatus(final WebhookNotification notification) throws InvalidDataException {
		TransactionNotification transaction = notification.getTransaction();
		Integer idPago = Integer.valueOf(StringUtils.remove(transaction.getOrderId(), "ORD-"));
		log.info("Verificando pago con ID {}", idPago);

		PagosEntity pago = this.pagosEntityRepository.findById(idPago).orElse(null);
		if (pago == null) {
			throw new InvalidDataException("No se encontro un pago asociado con el ORDER ID recibido");
		}

		String type = notification.getType();
		if ("verification".equalsIgnoreCase(type)) {
			log.info("Código de verificación para webhook: {}", notification.getVerificationCode());
		} else {
			String confirmStatus = transaction.getStatus();
			String currentStatus = pago.getStatus().name();
			if("SUCCESS".equalsIgnoreCase(currentStatus)) {
				currentStatus = "completed";
			}
			
			if (!currentStatus.equalsIgnoreCase(confirmStatus)) { //"PENDING".equalsIgnoreCase(pago.getStatus().name()) 
				if ("charge.succeeded".equalsIgnoreCase(type)) {
					pago.setStatus(SUCCESS);
					pago.setAuthorization(transaction.getAuthorization());
					pago.setIdPagoOpenpay(transaction.getId());
					this.pagosService.updatePendingCharge(pago);
				} else if ("charge.failed".equalsIgnoreCase(type)) {
					pago.setStatus(FAILED);
					pago.setErrorMessage(transaction.getErrorMessage());
					this.pagosService.updatePendingCharge(pago);
				} else {
					log.info("El evento {} enviado no es manejado por el sistema", type);
				}
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Charge createCustomerRefund(String opCustomerId, String description, BigDecimal amount)
			throws PaymentServiceException {
		log.info("Creating refund request for customer {}", opCustomerId);
		RefundParams request = new RefundParams();
		if (StringUtils.isNotBlank(description)) {
			request.description(description);
		}
		if (amount != null && amount.compareTo(BigDecimal.ZERO) == 1) {
			request.amount(amount);
		}
		log.info("Refund request to Openpay {}", request);
		Charge refund = null;

		try {
			refund = this.openpayAPI.charges().refund(opCustomerId, request);
		} catch (OpenpayServiceException | ServiceUnavailableException e) {
			log.error("An error occurred while creating a refund...", e);
			throw this.pagoUtil.throwByOpenpayException(e);
		}
		log.info("Refund created successfully");
		return refund;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Charge> searchCustomerCharges(String opCustomerId, Date creation, Date creationGte, Date creationLte,
			Integer offset, Integer limit) throws PaymentServiceException {
		log.info("Searching charges for customer {}", opCustomerId);
		SearchParams params = this.pagoUtil.createSearchParams(creation, creationGte, creationLte, offset, limit);
		log.info("Search request to Openpay {}", params);

		List<Charge> chargeList = null;
		try {
			chargeList = openpayAPI.charges().list(opCustomerId, params);
		} catch (ServiceUnavailableException | OpenpayServiceException e) {
			log.error("An error occurred while getting a charges...", e);
			throw this.pagoUtil.throwByOpenpayException(e);
		}
		log.info("List of charges successfully obtained");
		return chargeList;
	}
}
