package mx.pliis.afiliacion.persistencia.hibernate.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.pliis.afiliacion.persistencia.hibernate.entity.AfiliadoEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.UsuarioEntity;

@Repository
public interface AfiliadoEntityRepository extends JpaRepository<AfiliadoEntity, Integer> {
	boolean existsByRfc(String rfc);

	boolean existsByCurp(String curp);

	boolean existsByIdFacebook(String idFacebook);

	boolean existsByIdWhatsapp(String idWhatsapp);

	Optional<AfiliadoEntity> findByIdFacebook(String idFacebook);

	Optional<AfiliadoEntity> findByIdWhatsapp(String idWhatsapp);

	@Query("	SELECT afil.idUsuario.arqIdUsuario" + "	FROM AfiliadoEntity afil "
			+ "	WHERE afil.idAfiliado =:idAfiliado ")
	Integer getIdArqByIdAfiliado(@Param("idAfiliado") Integer idAfiliado);

	Optional<AfiliadoEntity> findByIdUsuario(UsuarioEntity idUsuario);
	
	@Query("	SELECT afil" + "	FROM AfiliadoEntity afil "
			+ "	WHERE afil.idUsuario.arqIdUsuario =:idUsuarioArq ")
	Optional<AfiliadoEntity> findPorIdUsuarioArq(Integer idUsuarioArq);
	
	Optional<AfiliadoEntity> findByCurp(String curp);

}
