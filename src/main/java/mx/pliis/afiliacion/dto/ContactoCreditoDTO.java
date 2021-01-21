package mx.pliis.afiliacion.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ContactoCreditoDTO {
	
	private Integer idContactoCredito;
	private Integer idCredito;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
    private String telefono;
    private String email;
	private String parentesco;
}
