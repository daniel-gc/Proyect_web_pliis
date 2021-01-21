package mx.pliis.afiliacion.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class MontoCreditoDTO {
	
	private Integer idMontoCredito;
	private BigDecimal monto;
	private BigDecimal cuotaRecuperacion;
	private BigDecimal total;
	private Integer validoDesde;
	private Integer validoHasta;
	private BigDecimal ingresoNeto;
	private BigDecimal descuento;
}
