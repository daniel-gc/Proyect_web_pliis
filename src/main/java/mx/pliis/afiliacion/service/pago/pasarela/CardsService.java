package mx.pliis.afiliacion.service.pago.pasarela;

import java.util.Date;
import java.util.List;

import mx.openpay.client.Card;
import mx.pliis.afiliacion.service.excepciones.PaymentServiceException;

public interface CardsService {

	/**
	 * Create a customer card on OpenPay platform <code>
	 * 	{
	 *   	"token_id":"tokgslwpdcrkhlgxqi9a",
	 *   	"device_session_id":"8VIoXj0hN5dswYHQ9X1mVCiB72M7FY9o"
	 *	}'
	 * </code>
	 * 
	 * @param opCustomerId
	 * @param tokenId
	 * @param deviceSessionId
	 * @return
	 * @throws PaymentServiceException
	 */
	public Card createCustomerCard(String opCustomerId, String tokenId, String deviceSessionId) throws PaymentServiceException;

	/**
	 * Delete a customer card on OpenPat platform
	 * 
	 * @param opCustomerId
	 * @param opCardId
	 * @throws PaymentServiceException
	 */
	public void deleteCustomerCard(String opCustomerId, String opCardId) throws PaymentServiceException;

	/**
	 * Search the cards associated to the customer
	 * 
	 * @param creation    Same as creation date. Format yyyy-mm-dd
	 * @param creationGte After the creation date. Format yyyy-mm-dd
	 * @param creationLte Before the creation date. Format yyyy-mm-dd
	 * @param offset      Number of records to skip at the beginning, default 0.
	 * @param limit       Number of required records, default 10.
	 * @return
	 * @throws PaymentServiceException
	 */
	public List<Card> searchCustomerCards(String opCustomerId, Date creation, Date creationGte, Date creationLte,
			Integer offset, Integer limit) throws PaymentServiceException;
}
