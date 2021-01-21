package mx.pliis.afiliacion.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ObservacionCreditoDTO {
	
	private Integer idObservacionCredito;
	private Integer idCredito;
    private Integer idUsuario;
    private Integer arqIdUsuario;
	private Integer idEstatusCredito;
	private EstatusCreditoDTO estatusCreditoDTO;
	private String observacion;
	private LocalDate fechaObservacion;
}
