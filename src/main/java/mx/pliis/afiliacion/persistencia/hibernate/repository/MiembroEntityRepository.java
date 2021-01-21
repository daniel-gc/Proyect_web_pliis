package mx.pliis.afiliacion.persistencia.hibernate.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.pliis.afiliacion.persistencia.hibernate.entity.MiembroEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.UsuarioEntity;

@Repository
public interface MiembroEntityRepository extends JpaRepository<MiembroEntity, Integer> {
	boolean existsByRfc(String rfc);

	boolean existsByCurp(String curp);

	boolean existsByIdFacebook(String idFacebook);

	boolean existsByIdWhatsapp(String idWhatsapp);

	Optional<MiembroEntity> findByIdFacebook(String idFacebook);

	Optional<MiembroEntity> findByIdWhatsapp(String idWhatsapp);

	@Query("SELECT miembro.idUsuario.arqIdUsuario "
			+ "FROM MiembroEntity miembro "
			+ "WHERE miembro.idMiembro =:idMiembro ")
	Integer getIdArqByIdMiembro(@Param("idMiembro") Integer idMiembro);

	Optional<MiembroEntity> findByIdUsuario(UsuarioEntity idUsuario);

}
