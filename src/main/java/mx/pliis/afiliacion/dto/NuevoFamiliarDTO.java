package mx.pliis.afiliacion.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class NuevoFamiliarDTO {

	@NotEmpty
	@Size(min = 1, max = 500)
	private String nombres;
	@NotEmpty
	@Size(min = 1, max = 100)
	private String apellidoPaterno;
	@Size(min = 1, max = 100)
	private String apellidoMaterno;
	@Size(max = 100)
	@NotEmpty
	@Email
	private String email;
	private LocalDate fechaNacimiento;
	private Integer idSexo;
	@NotNull
	private Integer idAfiliado;
	@NotNull
	private Integer idRelacionFamiliar;
//	Agregados para la tabla Usuarios:
	@NotNull
	private String telefono;
	@NotNull
	private Integer arqIdUsuario;
}
