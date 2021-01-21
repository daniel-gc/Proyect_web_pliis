package mx.pliis.afiliacion.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import lombok.Data;

@Data
public class NuevoMiembroDTO {
	private Integer idMiembro;
	private String nombres;
	private String empresa;
	private String centroTrabajo;
	private String sindicato;
	private byte[] fotoCredencial;
	@NotEmpty(message = "El apellido paterno es obligatorio")
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String direccionDomicilio;
	private String rfc;
	private String curp;
	private String lugarNacimiento;
	private LocalDate fechaAfiliacion;
	private byte[] fotoMiembro;
	@NotNull(message = "Falta la fecha de nacimiento")
	@Past(message = "La fecha de nacimiento debe ser anterior")
	private LocalDate fechaNacimiento;
	private String contrato;
	private LocalDate fechaRegistro;
	private LocalDate fechaBaja;
	private String nombreSindicato;
	private String nombrePuestoTrabajo;
	@NotNull(message = "El salario neto es obligatorio")
	private BigDecimal salarioMensualNeto;
	private BigDecimal salarioMensualBruto;
	private Boolean deseaAfiliarse;
	private LocalDate fechaIngresoEmpresa;
	@NotNull(message = "Debe tener un estado civil")
	private Integer idEstadoCivil;
	private Integer idNacionalidad;
	private Integer idSexo;
//	ID de las dos redes sociales para el chat
	private String idFacebook;
	private String idWhatsapp;
//	Agregados para la tabla Usuarios:
	private String telefono;
	private Integer arqIdUsuario;
//	Dirección desglosada:
	private String calle;
	private String numero;
	private String colonia;
	private String alcaldia;
	private String ciudad;
	private String pais;

}
