package mx.pliis.afiliacion.service.pago;

import java.math.BigDecimal;

import mx.pliis.afiliacion.persistencia.hibernate.entity.PagosEntity;

public interface PagosService {

	/**
	 * Create a PENDING PAY before to send the charge to OpenPay 
	 * @param idUsuario
	 * @param description
	 * @param monto
	 * @return
	 */
	PagosEntity createPendingCharge(Integer idUsuario, String description, BigDecimal monto, Integer idPlan);
	
	void updatePendingCharge(PagosEntity pendingCharge);
}
