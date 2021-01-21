package mx.pliis.afiliacion.persistencia.hibernate.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mx.pliis.afiliacion.persistencia.hibernate.entity.SuscripcionEntity;

public interface SuscripcionEntityRepository extends JpaRepository<SuscripcionEntity, Integer>{
	Optional<SuscripcionEntity> findByidMiembroAndidPlanAndidPagoAndactivo(Integer idMiembro, Integer idPlan, Integer idPago);
	
	@Query("SELECT s "
			+ "FROM SuscripcionEntity s "
			+ "WHERE s.suscripcionEntityPK.idMiembro = :idMiembro "
			+ "ORDER BY fecha_fin desc")
	Optional<List<SuscripcionEntity>> getSuscripcionesByidMiembro(
		@Param("idMiembro") Integer idMiembro);
	
	Optional<List<SuscripcionEntity>> findByactivo();
	
	Optional<List<SuscripcionEntity>> findByidMiembroAndActivo(Integer idMiembro, Boolean activo);
	
	@Query("SELECT s "
			+ "FROM SuscripcionEntity s "
			+ "WHERE s.suscripcionEntityPK.idMiembro = :idMiembro "
			+ "	and s.activo = :activo "
			+ "ORDER BY fecha_fin desc")
	Optional<List<SuscripcionEntity>> getSuscripcionesByIdMiembroAndActivo(
		@Param("idMiembro") Integer idMiembro, 
		@Param("activo") Boolean activo);
}
