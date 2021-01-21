package mx.pliis.afiliacion.service.excepciones;

public class InvalidDataException extends PaymentServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidDataException(String message) {
		super(message);
	}
}
