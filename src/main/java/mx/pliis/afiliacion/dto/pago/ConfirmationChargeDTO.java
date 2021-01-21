package mx.pliis.afiliacion.dto.pago;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class ConfirmationChargeDTO implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 5619300445012294182L;
	
	private Date operationDate;
	private String status;
	private Integer errorCode;
	private String errorMessage;
	private String authorization;
	private String orderId;
	private Integer idPago;
	private String transactionId;
}
