package mx.pliis.afiliacion.persistencia.hibernate.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.pliis.afiliacion.persistencia.hibernate.entity.UsuarioEntity;

@Repository
public interface UsuarioEntityRepository extends JpaRepository<UsuarioEntity, Integer> {
	UsuarioEntity findByTelefono(String telefono);

	Optional<UsuarioEntity> findByArqIdUsuario(Integer arqIdUsuario);

}
