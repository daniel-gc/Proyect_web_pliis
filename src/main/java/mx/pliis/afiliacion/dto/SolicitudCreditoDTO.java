package mx.pliis.afiliacion.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class SolicitudCreditoDTO {
	private Integer idCredito;
    private Integer idUsuario;
    private Integer arqIdUsuario;
    private Boolean esAfiliado;
    private Boolean esMiembro;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
	private LocalDate fechaNacimiento;
    private String telefono;
    private String email;
    private String empresa;
    private String numeroEmpleado;
	private LocalDate fechaIngresoCia;
	private BigDecimal salarioMensualNeto;
	private BigDecimal salarioMensualBruto;
    private Integer idMontoCredito;
    private MontoCreditoDTO montoCreditoDTO;
    private Integer idEstatusCredito;
    private EstatusCreditoDTO estatusCreditoDTO;
    private String tipoUsuario;
    private LocalDate fechaSolicitud;
    private LocalDate fechaConclucion;
    private Integer origenSolicitud;
    private Boolean enviado;
    
    private List<ContactoCreditoDTO> listaContactoCreditoDTO;
    private List<ArchivoCreditoDTO> listaArchivoCreditoDTO;
    private List<ObservacionCreditoDTO> listaObservacionCreditoDTO;
}
