package mx.pliis.afiliacion.dto.pago;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDTO {

	@NotBlank(message = "El nombre es requerido")
	@Size(max = 100, message = "El nombre debe tener entre 1 y 100 caracteres")
	private String name;
	
	@Size(max = 100, message = "El apellido debe tener entre 1 y 100 caracteres")
	private String lastName;
	
	@NotBlank(message = "El email es requerido")
	@Email(message = "El email debe ser valido")
	@Size(max = 100, message = "El email debe tener entre 1 y 100 caracteres")
	private String email;
	
	@Size(max = 100, message = "El teléfono debe tener entre 1 y 100 caracteres")
	private String phoneNumber;
}
