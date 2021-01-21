package mx.pliis.afiliacion.api_rest_controller;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.openpay.client.Charge;
import mx.pliis.afiliacion.dto.pago.ChargeDTO;
import mx.pliis.afiliacion.dto.pago.ConfirmationChargeDTO;
import mx.pliis.afiliacion.dto.pago.webhook.WebhookNotification;
import mx.pliis.afiliacion.service.excepciones.InvalidDataException;
import mx.pliis.afiliacion.service.excepciones.PaymentServiceException;
import mx.pliis.afiliacion.service.pago.pasarela.ChargesService;
import mx.pliis.afiliacion.service.pago.pasarela.CustomersService;

/**
 * Rest only use the createCharge, First create a customer and after create a
 * charge Not use store card methods for the moment.
 * 
 * @author jesus.gutierrez
 */
@RestController
@RequestMapping("/payments")
@CrossOrigin(origins = "*")
public class PagoRestController {

	@Autowired
	private ChargesService chargesService;

	/** Start Customer Methods
	@PostMapping("/create/customer")
	public ResponseEntity<String> createCustomer(@Valid @RequestBody CustomerDTO customerDTO)
			throws InvalidDataException, PaymentServiceException {
		this.customersService.createCustomer(customerDTO.getName(), customerDTO.getLastName(), customerDTO.getEmail(),
				customerDTO.getPhoneNumber());
		return new ResponseEntity<>("Openpay: Cliente creado correctamente", HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/customer/{customer}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("customer") @NotBlank String opCustomerId)
			throws PaymentServiceException {
		this.customersService.deleteCustomer(opCustomerId);
		return new ResponseEntity<>("Openpay: Cliente borrado correctamente", HttpStatus.OK);
	}
	Finish Customer Methods */

	/** Start Charge Methods */
	@PostMapping("/create/charge")
	public ResponseEntity<ConfirmationChargeDTO> createCharge(@Valid @RequestBody ChargeDTO chargeDTO) throws PaymentServiceException {
		Charge payment = this.chargesService.createCompleteCustomerCharge(chargeDTO);
		ConfirmationChargeDTO successPay = new ConfirmationChargeDTO();
		successPay.setAuthorization(payment.getAuthorization());
		successPay.setOrderId(payment.getOrderId());
		successPay.setErrorCode(payment.getErrorCode());
		successPay.setErrorMessage(payment.getErrorMessage());
		successPay.setOperationDate(payment.getOperationDate());
		successPay.setStatus(payment.getStatus());
		Integer idPago = Integer.valueOf(StringUtils.remove(payment.getOrderId(), "ORD-"));
		successPay.setIdPago(idPago);
		successPay.setTransactionId(payment.getId());
		
		return new ResponseEntity<>(successPay, HttpStatus.OK);
	}
	/** Finish Card Methods */
	
	/** Start WebHook Method */
	@PostMapping("/confirm/transaction/status")
	public ResponseEntity<String> confirmTransactionStaus(@RequestBody WebhookNotification notification) throws PaymentServiceException {
		try {
			this.chargesService.confirmTransactionStatus(notification);
		} catch (InvalidDataException e) {
			throw new PaymentServiceException(e);
		}
		return new ResponseEntity<>("[OK]", new HttpHeaders(), HttpStatus.OK);
	}
	/** Finish WebHook Method */
	
	
	/**
	 * Start Card Methods @PostMapping("/create/card") public ResponseEntity<String>
	 * createCard(@Valid @RequestBody CardDTO cardDTO) throws
	 * PaymentServiceException{ this.cardsService.createCustomerCard("PENDING",
	 * cardDTO.getTokenId(), cardDTO.getDeviceSessionId()); return new
	 * ResponseEntity<>("Openpay: Tarjeta creada correctamente", HttpStatus.OK); }
	 * 
	 * @DeleteMapping("/delete/customer/{customer}/card/{card}") public
	 * ResponseEntity<String> deleteCard(@PathVariable("customer") @NotBlank String
	 * opCustomerId, @PathVariable("card") @NotBlank String opCardId) throws
	 * PaymentServiceException{ this.cardsService.deleteCustomerCard(opCustomerId,
	 * opCardId); return new ResponseEntity<>("Openpay: Tarjeta borrada
	 * correctamente", HttpStatus.OK); }
	 * 
	 * @GetMapping("/search/customer/{customer}/cards") public ResponseEntity<?>
	 * getCustomerCards(@PathVariable("customer") @NotBlank String opCustomerId)
	 * throws PaymentServiceException {
	 * this.cardsService.searchCustomerCards(opCustomerId, null, null, null, null,
	 * null); //TODO Crear un DTO para regresar información especifica de la tarjeta
	 * return null; } Finish Card Methods
	 */
}
