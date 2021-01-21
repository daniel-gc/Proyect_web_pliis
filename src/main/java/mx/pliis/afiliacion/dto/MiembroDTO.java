package mx.pliis.afiliacion.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Data
public class MiembroDTO {
	private Integer idMiembro;
	private String nombres;
	private String empresa;
	private String centroTrabajo;
	private String sindicato;
	private byte[] fotoCredencial;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String direccionDomicilio;
	private String rfc;
	private String curp;
	private String lugarNacimiento;
	private LocalDate fechaAfiliacion;
	private byte[] fotoMiembro;
	private LocalDate fechaNacimiento;
	private String contrato;
	private LocalDate fechaRegistro;
	private LocalDate fechaBaja;
	private String nombreSindicato;
	private String nombrePuestoTrabajo;
	private BigDecimal salarioMensualNeto;
	private BigDecimal salarioMensualBruto;
	private Boolean deseaAfiliarse;
	private LocalDate fechaIngresoEmpresa;
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
