package mx.pliis.afiliacion.dto.pago;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CardDTO {
	
	private Integer usuarioArqId;

	@NotBlank(message = "El token id es requerido para crear la tarjeta")
	private String tokenId;
	
	@NotBlank(message = "El identificador del dispositivo es requerido para crear la tarjeta")
	private String deviceSessionId;
	
}
