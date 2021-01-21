package mx.pliis.afiliacion.persistencia.hibernate.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.pliis.afiliacion.persistencia.hibernate.entity.AfiliadoEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.FamiliarEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.VinculoFamiliarEntity;

public interface VinculoFamiliarEntityRepository extends JpaRepository<VinculoFamiliarEntity, Integer> {
	Optional<List<VinculoFamiliarEntity>> findByIdFamiliar(FamiliarEntity IdFamiliar);

	Optional<List<VinculoFamiliarEntity>> findByIdAfiliadoAndActivo(AfiliadoEntity idAfiliado, Boolean activo);
}
