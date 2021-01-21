package mx.pliis.afiliacion.service.pago.pasarela;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import mx.openpay.client.Customer;
import mx.openpay.client.core.OpenpayAPI;
import mx.openpay.client.exceptions.OpenpayServiceException;
import mx.openpay.client.exceptions.ServiceUnavailableException;
import mx.openpay.client.utils.SearchParams;
import mx.pliis.afiliacion.service.excepciones.InvalidDataException;
import mx.pliis.afiliacion.service.excepciones.PaymentServiceException;
import mx.pliis.afiliacion.utils.pago.PagoUtil;

@Service
@Log4j2
public class CustomersServiceImpl implements CustomersService {

	@Autowired
	private OpenpayAPI openpayAPI;

	@Autowired
	private PagoUtil pagoUtil;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Customer createCustomer(String name, String lastName, String email, String phoneNumber, Integer id)
			throws InvalidDataException, PaymentServiceException {
		Customer request = new Customer();
		this.pagoUtil.createCustomer(request, name, lastName, email, phoneNumber, id);

		try {
			request = openpayAPI.customers().create(request);
		} catch (ServiceUnavailableException | OpenpayServiceException e) {
			log.error("An error occurred while creating customer...", e);
			throw this.pagoUtil.throwByOpenpayException(e);
		}
		log.info("Customer created successfully");
		return request;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteCustomer(String customerId) throws PaymentServiceException {
		log.info("Deleting customer {}", customerId);
		try {
			openpayAPI.customers().delete(customerId);
		} catch (ServiceUnavailableException | OpenpayServiceException e) {
			log.error("An error occurred while deleting customer...", e);
			throw this.pagoUtil.throwByOpenpayException(e);
		}
		log.info("Customer deleted successfully");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Customer findCustomerByExternalId(String externalId) throws PaymentServiceException {
		log.info("Searching customer with external id {}", externalId);
		SearchParams request = new SearchParams();
		request.orderId(externalId);
		request.limit(1);
		try {
			List<Customer> customers = this.openpayAPI.customers().list(request);
			if(customers != null && !customers.isEmpty()) {
				return customers.get(0);
			}
			return null;
		} catch (ServiceUnavailableException | OpenpayServiceException e) {
			log.error("An error occurred while searching customer...", e);
			throw this.pagoUtil.throwByOpenpayException(e);
		}
	}
}
