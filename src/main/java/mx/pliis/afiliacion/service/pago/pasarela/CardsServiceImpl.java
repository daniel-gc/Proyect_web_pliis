package mx.pliis.afiliacion.service.pago.pasarela;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import mx.openpay.client.Card;
import mx.openpay.client.core.OpenpayAPI;
import mx.openpay.client.exceptions.OpenpayServiceException;
import mx.openpay.client.exceptions.ServiceUnavailableException;
import mx.openpay.client.utils.SearchParams;
import mx.pliis.afiliacion.service.excepciones.PaymentServiceException;
import mx.pliis.afiliacion.utils.pago.PagoUtil;

@Service
@Log4j2
public class CardsServiceImpl implements CardsService {

	@Autowired
	private OpenpayAPI openpayAPI;

	@Autowired
	private PagoUtil pagoUtil;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Card createCustomerCard(String opCustomerId, String tokenId, String deviceSessionId)
			throws PaymentServiceException {
		log.info("Creating card request for customer {}", opCustomerId);
		Card card = new Card();
		card.setTokenId(tokenId);
		card.setDeviceSessionId(deviceSessionId);
		log.info("Card request to Openpay {}", card);

		try {
			card = this.openpayAPI.cards().create(opCustomerId, card);
		} catch (OpenpayServiceException | ServiceUnavailableException e) {
			log.error("An error occurred while creating a card...", e);
			throw this.pagoUtil.throwByOpenpayException(e);
		}
		log.info("Card created successfully");
		return card;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteCustomerCard(String opCustomerId, String opCardId) throws PaymentServiceException {
		log.info("Deleting card {} for customer {}", opCardId, opCustomerId);
		try {
			this.openpayAPI.cards().delete(opCustomerId, opCardId);
		} catch (ServiceUnavailableException | OpenpayServiceException e) {
			log.error("An error occurred while deleting a card...", e);
			throw this.pagoUtil.throwByOpenpayException(e);
		}
		log.info("Card deleted successfully");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Card> searchCustomerCards(String opCustomerId, Date creation, Date creationGte, Date creationLte,
			Integer offset, Integer limit) throws PaymentServiceException {
		log.info("Searching cards for customer {}", opCustomerId);
		SearchParams params = this.pagoUtil.createSearchParams(creation, creationGte, creationLte, offset, limit);
		log.info("Search request to Openpay {}", params);
		
		List<Card> cardList = null;
		try {
			cardList = openpayAPI.cards().list(opCustomerId, params);
		} catch (ServiceUnavailableException | OpenpayServiceException e) {
			log.error("An error occurred while getting a cards...", e);
			throw this.pagoUtil.throwByOpenpayException(e);
		}
		log.info("List of cards successfully obtained");
		return cardList;
	}

}
