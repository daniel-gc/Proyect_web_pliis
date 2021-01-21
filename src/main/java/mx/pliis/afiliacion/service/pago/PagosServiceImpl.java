package mx.pliis.afiliacion.service.pago;

import static mx.pliis.afiliacion.enums.PagoEstatus.PENDING;
import static mx.pliis.afiliacion.enums.PagoTipo.CARGO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;
import mx.pliis.afiliacion.persistencia.hibernate.entity.PagosEntity;
import mx.pliis.afiliacion.persistencia.hibernate.repository.PagosEntityRepository;

@Service
@Log4j2
public class PagosServiceImpl implements PagosService {
	
	private static final ZoneId TIME_ZONE = ZoneId.of("America/Mexico_City");

	@Autowired
	private PagosEntityRepository pagosEntityRepository;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public PagosEntity createPendingCharge(Integer idUsuario, String description, BigDecimal monto, Integer idPlan) {
		log.info("Registrando un PAGO PENDIENTE en base de datos");
		PagosEntity pendingCharge = new PagosEntity();
		pendingCharge.setIdUsuario(idUsuario);
		pendingCharge.setDescription(description);
		pendingCharge.setMonto(monto);
		pendingCharge.setFecha(LocalDateTime.now().atZone(TIME_ZONE).toLocalDateTime());
		pendingCharge.setTipo(CARGO);
		pendingCharge.setStatus(PENDING);
		pendingCharge.setIdPlan(idPlan);
		
		pagosEntityRepository.save(pendingCharge);
		log.info("PAGO PENDIENTE registrafo en base de datos con ID {}", pendingCharge.getIdPago());
		return pendingCharge;
	}

	@Override
	@Transactional
	public void updatePendingCharge(PagosEntity pendingCharge) {
		this.pagosEntityRepository.save(pendingCharge);
	}

}
