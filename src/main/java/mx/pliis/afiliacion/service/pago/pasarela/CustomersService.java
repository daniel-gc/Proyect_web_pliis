package mx.pliis.afiliacion.service.pago.pasarela;

import mx.openpay.client.Customer;
import mx.pliis.afiliacion.service.excepciones.InvalidDataException;
import mx.pliis.afiliacion.service.excepciones.PaymentServiceException;

public interface CustomersService {

	/**
	 * Create a new customer on OpenPay. The id attribute must be save in DB.
	 * <code>
	 * {
	 *	    "name": "Required",
	 *	    "last_name": "Optional",
	 *	    "external_id": "Optional",
	 *	    "email": "Required",
	 *	    "address": {Optional
	 *	        "city": "Required",
	 *	        "state": "Required",
	 *	        "line1": "Required",
	 *	        "postal_code": "Required",
	 *	        "line2": "Optional",
	 *	        "line3": "Optional",
	 *	    },
	 *	    "phone_number":"Optional",
	 *	    "requires_account": false,
	 *	}
	 * </code>
	 * @param name
	 * @param lastName
	 * @param email
	 * @param phoneNumber
	 * @param id
	 * @return
	 * @throws InvalidDataException 
	 * @throws PaymentServiceException 
	 */
	public Customer createCustomer(String name, String lastName, String email,
			String phoneNumber, Integer id) throws InvalidDataException, PaymentServiceException;
	
	/**
	 * Delete an exist customer.
	 * @param Openpay Customer Id
	 * @throws PaymentServiceException 
	 */
	public void deleteCustomer(String customerId) throws PaymentServiceException;
	
	/**
	 * Update an exist customer. Attributes to update
	 * <code>
	 * Example:
	 *	{
	 *	    "name": "Required",
	 *	    "email": "Required",
	 *      "last_name": "Optional",
	 *	    "address": {
	 *	        "city": "Required",
	 *	        "state": "Required",
	 *	        "line1": "Required",
	 *	        "postal_code": "Required",
	 *	        "line2": "Optional",
	 *	        "line3": "Optional",
	 *	        "country_code": "Required"
	 *	    },
	 *	    "phone_number": "Required"
	 *	}
	 * </code>
	 * @param customer
	 * @return
	 
	public Customer updateCustomer(Customer customer);*/
	
	/**
	 * 
	 * @param externalId
	 * @return
	 */
	public Customer findCustomerByExternalId(String externalId) throws PaymentServiceException;
}
