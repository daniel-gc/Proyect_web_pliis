package mx.pliis.afiliacion.dto;

import java.util.List;

import lombok.Data;

@Data
public class PlanDTO {
	private Integer idPlan;
	private String idPlanOpenpay;
	private String nombre;
	private Double monto;
	private Integer centavos;
	private String descripcion;
	private String moneda;
	private Integer duracion;
	private String unidadDuracion;
	private Integer tiempoPrueba;
	private Boolean activo;
	private List<ColorPlanDTO> listaColorPlanDTO;
}
