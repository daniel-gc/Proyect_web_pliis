package mx.pliis.afiliacion.service.afiliado;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import mx.pliis.afiliacion.dto.NuevoAfiliadoDTO;

public interface AfiliadoService {
	Integer nuevoAfiliado(NuevoAfiliadoDTO nuevoAfiliadoDTO);

	boolean deleteAfiliadoById(Integer idAfiliado);

	boolean existeAfiliadoByCurp(String curp);

	boolean existeAfiliadoByRfc(String rfc);

	boolean existeAfiliadoByRedesSociales(Optional<String> idFacebookOpt, Optional<String> idWhatsappOpt);

	NuevoAfiliadoDTO getAfiliadoByRedesSociales(Optional<String> idFacebookOpt, Optional<String> idWhatsappOpt);

	Integer getIdArq(Integer idAfiliado);

	NuevoAfiliadoDTO getAfiliadoByIdAfiliado(@NotNull Integer idAfiliado);
	
	NuevoAfiliadoDTO getAfiliadoPorIdArq(@NotNull Integer arqIdUsuario);
	
	NuevoAfiliadoDTO getAfiliadoPorCurp(@NotNull String curp);
}
