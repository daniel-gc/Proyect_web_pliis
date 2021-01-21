package mx.pliis.afiliacion.utils.pago;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import java.util.Date;

import javax.net.ssl.SSLException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.conn.ConnectTimeoutException;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;
import mx.openpay.client.Customer;
import mx.openpay.client.core.requests.transactions.CreateCardChargeParams;
import mx.openpay.client.exceptions.OpenpayServiceException;
import mx.openpay.client.exceptions.ServiceUnavailableException;
import mx.openpay.client.utils.SearchParams;
import mx.pliis.afiliacion.service.excepciones.InvalidDataException;
import mx.pliis.afiliacion.service.excepciones.PaymentServiceException;

@Component
@Log4j2
public class PagoUtil {

	private static final int NOT_FOUND_INDEX = -1;
	
	/**
	 * Create a customer with minimum information
	 * 
	 * @param customer
	 * @param name
	 * @param lastName
	 * @param email
	 * @param phoneNumber
	 * @param id
	 */
	public void createCustomer(final Customer customer, String name, String lastName, String email, String phoneNumber,
			Integer id)
			throws InvalidDataException {
		log.info("Creating customer request to Openpay...");

		if (StringUtils.isBlank(name) || StringUtils.isBlank(email)) {
			throw new InvalidDataException("El nombre y email son requeridos para Openpay");
		}

		customer.setName(name);
		customer.setLastName(lastName);
		customer.setEmail(email);
		customer.setPhoneNumber(phoneNumber);
		customer.setRequiresAccount(false);
		customer.setExternalId(id.toString());

		log.info("Customer request to Openpay {}", customer);
	}

	/**
	 * Create a charge request to send to OpenPay platform
	 * 
	 * @param charge
	 * @param opCardId
	 * @param amount
	 * @param description
	 * @param orderId
	 * @param deviceSessionId
	 * @throws InvalidDataException
	 */
	public void createCharge(final CreateCardChargeParams charge, String opCardId, BigDecimal amount,
			String description, String orderId, String deviceSessionId) throws InvalidDataException {
		log.info("Creating charge request to Openpay...");

		if (StringUtils.isBlank(opCardId)) {
			throw new InvalidDataException("La tarjeta es requerida");
		}

		if (amount == null || amount.compareTo(BigDecimal.ZERO) != 1) {
			throw new InvalidDataException("El monto debe ser valido y mayor a cero");
		}

		if (StringUtils.isBlank(description)) {
			throw new InvalidDataException("La descripción es requerida");
		}

		if (StringUtils.isBlank(orderId)) {
			log.warn(
					"Es recomendable incluír un identificador unico, por si ocurre un error de comunicación y poder confirmarlo mediante este dato");
		}

		if (StringUtils.isBlank(deviceSessionId)) {
			throw new InvalidDataException("El device session id es requerido");
		}

		charge.cardId(opCardId);
		charge.amount(amount);
		charge.description(description);
		charge.orderId(orderId);
		charge.deviceSessionId(deviceSessionId);

		log.info("Charge request to Openpay {}", charge);
	}

	/**
	 * Create a Search filter to get a list data from OpenPay
	 * 
	 * @param creation
	 * @param creationGte
	 * @param creationLte
	 * @param offset
	 * @param limit
	 * @return
	 */
	public SearchParams createSearchParams(Date creation, Date creationGte, Date creationLte, Integer offset,
			Integer limit) {
		SearchParams request = new SearchParams();
		if (creation != null) {
			request.creation(creation);
		}

		if (creationGte != null) {
			request.creationGte(creationGte);
		}

		if (creationLte != null) {
			request.creationLte(creationLte);
		}

		if (offset != null) {
			request.offset(offset);
		}

		if (limit != null) {
			request.limit(limit);
		}

		return request;
	}

	public PaymentServiceException throwByOpenpayException(final Exception e) {
		PaymentServiceException exception = null;
		if (e instanceof OpenpayServiceException) {
			exception = new PaymentServiceException(((OpenpayServiceException) e).getDescription(), e);
			exception.setCategory(((OpenpayServiceException) e).getCategory());
			exception.setRequestId(((OpenpayServiceException) e).getRequestId());
			exception.setErrorCode(((OpenpayServiceException) e).getErrorCode());
		} else if (e instanceof ServiceUnavailableException) {
			exception = new PaymentServiceException(e.getMessage(), e);
			if(e.getCause() instanceof IOException) {
				IOException cause = (IOException) e.getCause();
				//True si se envío pero no se pudo leer una respuesta
				exception.setRequestSent(this.isRequestSent(cause));
			}
		}

		return exception;
	}

	private boolean isRequestSent(final IOException e) {
		return ExceptionUtils.indexOfType(e, ProtocolException.class) == NOT_FOUND_INDEX
				&& ExceptionUtils.indexOfThrowable(e, SocketException.class) == NOT_FOUND_INDEX
				&& ExceptionUtils.indexOfType(e, UnknownHostException.class) == NOT_FOUND_INDEX
				&& ExceptionUtils.indexOfThrowable(e, MalformedURLException.class) == NOT_FOUND_INDEX
				&& ExceptionUtils.indexOfType(e, UnknownServiceException.class) == NOT_FOUND_INDEX
				&& ExceptionUtils.indexOfThrowable(e, SSLException.class) == NOT_FOUND_INDEX
				&& ExceptionUtils.indexOfType(e, ConnectTimeoutException.class) == NOT_FOUND_INDEX
				&& !(ExceptionUtils.indexOfType(e, SocketTimeoutException.class) == 0
						&& e.getMessage().contains("Connection timed out"));
	}

	/*
	 * Register client only with minimum data private Address getAddress(String
	 * street, String number, String suburb, String zipCode, String city, String
	 * country) { if(StringUtils.isBlank(street) || StringUtils.isBlank(zipCode) ||
	 * StringUtils.isBlank(city) || StringUtils.isBlank(country)) {
	 * log.warn("The address information is incomplete"); return null; }
	 * 
	 * Address address = new Address(); street = street + " " +
	 * StringUtils.defaultString(number); address.setLine1(street);
	 * address.setLine3(StringUtils.defaultString(suburb));
	 * address.setPostalCode(zipCode); address.setCity(city);
	 * address.setState("POR DEFINIR"); address.setCountryCode("MX");
	 * 
	 * return address; }
	 */
}
