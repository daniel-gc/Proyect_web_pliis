package mx.pliis.afiliacion.persistencia.hibernate.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.pliis.afiliacion.persistencia.hibernate.entity.FamiliarEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.UsuarioEntity;

@Repository
public interface FamiliarEntityRepository extends JpaRepository<FamiliarEntity, Integer> {
	Optional<FamiliarEntity> findByIdFamiliar(Integer idFamiliar);
	
	Optional<FamiliarEntity> findByIdUsuario(UsuarioEntity idUsuario);
}
