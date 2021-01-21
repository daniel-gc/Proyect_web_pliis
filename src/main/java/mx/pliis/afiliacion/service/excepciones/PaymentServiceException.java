package mx.pliis.afiliacion.service.excepciones;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper = true)
public class PaymentServiceException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private String category;

	@Getter
	@Setter
	private String requestId;

	@Getter
	@Setter
	private Integer errorCode;
	
	@Getter
	@Setter
	private boolean isRequestSent = false;

	public PaymentServiceException() {
	}

	public PaymentServiceException(String message) {
		super(message);
	}

	public PaymentServiceException(Throwable cause) {
		super(cause);
	}

	public PaymentServiceException(String message, Throwable cause) {
		super(message, cause);
	}

}
