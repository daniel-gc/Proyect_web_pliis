package mx.pliis.afiliacion.dto;

import lombok.Data;

@Data
public class MensajeDTO {
	private String mensaje;

	public MensajeDTO() {
	}

	public MensajeDTO(String mensaje) {
		this.mensaje = mensaje;
	}

}
