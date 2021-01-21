package mx.pliis.afiliacion.persistencia.hibernate.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.pliis.afiliacion.persistencia.hibernate.entity.CreditoEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.SuscripcionEntity;

@Repository
public interface CreditoEntityRepository extends JpaRepository<CreditoEntity, Integer> {
	@Query("SELECT c "
			+ "FROM CreditoEntity c "
			+ "WHERE c.idEstatusCredito.idEstatusCredito IN (:estatusCredito) "
			+ "	and c.activo = :activo "
			+ "ORDER BY "
			+ "	fecha_solicitud asc, "
			+ "	c.idEstatusCredito.idEstatusCredito asc"
	)
	Optional<List<CreditoEntity>> getSolicitudesCreditoByIdEstatusCreditoAndActivoForSC( 
		@Param("estatusCredito")List<Integer> estatusCredito,
		@Param("activo") Boolean activo);
	
	@Query("SELECT c "
			+ "FROM CreditoEntity c "
			+ "WHERE c.idUsuario.idUsuario = :idUsuario "
			+ "	and c.activo = :activo "
			+ "ORDER BY "
			+ "	fechaSolicitud asc, "
			+ "	c.idEstatusCredito.idEstatusCredito asc"
	)
	Optional<List<CreditoEntity>> getSolicitudesCreditoByIdUsuarioAndActivoForASUE( 
		@Param("idUsuario") Integer idUsuario,
		@Param("activo") Boolean activo);
	
	@Query("SELECT c "
			+ "FROM CreditoEntity c "
			+ "WHERE "
			+ "	c.idEstatusCredito.idEstatusCredito IN (:estatusCredito) "
			+ "	and enviado = false "
			+ "	and c.activo = :activo "
			+ "ORDER BY "
			+ "	fecha_solicitud asc"
	)
	Optional<List<CreditoEntity>> getSolicitudesCreditoByIdEstatusCreditoAndActivoConcentrado( 
		@Param("estatusCredito")List<Integer> estatusCredito,
		@Param("activo") Boolean activo);
}
