package mx.pliis.afiliacion.service.excepciones;

public class InternalErrorException extends PaymentServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InternalErrorException(String message) {
		super(message);
	}
	
	public InternalErrorException(String message, Throwable cause) {
		super(message, cause);
	}
}
