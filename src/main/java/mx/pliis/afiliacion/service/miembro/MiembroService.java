package mx.pliis.afiliacion.service.miembro;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import mx.pliis.afiliacion.dto.NuevoMiembroDTO;
import mx.pliis.afiliacion.dto.SuscripcionDTO;

public interface MiembroService {
	Integer nuevoMiembro(NuevoMiembroDTO nuevoMiembroDTO);
	
	boolean deleteMiembroById(Integer idMiembro);
	
	public Boolean generaSuscripcion(Integer arqIdUsuario, Integer idPlan, Integer idPago);
	
	public Boolean deleteSuscripcion(Integer arqIdUsuario, Integer idPlan, Integer idPago);
	
	public List<SuscripcionDTO> getHistorialPagos(Integer arqIdUsuario);
	
	public Map<String, List<Integer>> validarSuscripciones();
	
	public List<Integer> validarPagoSuscripciones();
	
	public SuscripcionDTO validarSuscripcionActiva(Integer arqIdUsuario);
	
	public NuevoMiembroDTO getMiembroPorIdArq(@NotNull Integer arqIdUsuario);
}
