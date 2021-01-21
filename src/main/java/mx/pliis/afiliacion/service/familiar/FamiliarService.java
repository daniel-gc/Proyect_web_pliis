package mx.pliis.afiliacion.service.familiar;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import mx.pliis.afiliacion.dto.FamiliarDTO;
import mx.pliis.afiliacion.dto.NuevoAfiliadoDTO;
import mx.pliis.afiliacion.dto.NuevoFamiliarDTO;

public interface FamiliarService {

	Integer saveNuevoFamiliar(NuevoFamiliarDTO nuevoFamiliarDTO);

	boolean deleteFamiliarById(Integer idFamiliar);

	List<FamiliarDTO> getFamiliares(Integer arqIdUsuario);

	Integer cancelarVinculoFamiliar(Integer idVinculoFamiliar);
	
	FamiliarDTO getFamiliarPorIdArq(@NotNull Integer arqIdUsuario);
	
	NuevoAfiliadoDTO getAfiliadoPorIdFamiliar(@NotNull Integer idFamiliar);
}
