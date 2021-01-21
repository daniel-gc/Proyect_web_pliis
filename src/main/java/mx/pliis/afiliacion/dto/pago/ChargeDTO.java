package mx.pliis.afiliacion.dto.pago;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChargeDTO {

	@NotNull(message = "El identificador de usuario (ARQ) es requerido")
	private Integer usuarioArqId;
	
	@NotNull(message = "El identificador del plan es requerido")
	private Integer idPlan;
	
	@NotBlank(message = "El email del usuario (ARQ) es requerido")
	@Email(message = "El email es invalido")
	private String email;
	
	@NotBlank(message = "El token de la tarjeta (OP) es requerido")
	private String tokenId;
	
	@NotBlank(message = "El identificador del dispositivo (OP) es requerido")
	private String deviceSessionId;
	
	@NotNull(message = "El monto a cobrar (OP) es requerido")
	@Digits(integer = 10, fraction = 2, message = "El monto a cobrar tiene un formato invalido")
	@DecimalMin(value = "0.0", inclusive = false, message = "El monto a cobrar debe ser mayor a 0.00")
	private BigDecimal amount;
	
	/** The plan name + The buy date */
	@NotBlank(message = "La descripción (OP) es requerida")
	private String description;
	
	/** The ID of record on payments table 
	private String orderId;*/
}
