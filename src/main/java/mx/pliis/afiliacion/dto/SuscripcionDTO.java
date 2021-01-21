package mx.pliis.afiliacion.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SuscripcionDTO {
	private PlanDTO planDTO;
	private MiembroDTO miembroDTO;
	private PagoDTO pagoDTO;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private Boolean activo;
}
