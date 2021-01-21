package mx.pliis.afiliacion.service.catalogo;

import java.util.List;

import mx.pliis.afiliacion.dto.EstadoCivilDTO;
import mx.pliis.afiliacion.dto.EstatusCreditoDTO;
import mx.pliis.afiliacion.dto.MontoCreditoDTO;
import mx.pliis.afiliacion.dto.NacionalidadDTO;
import mx.pliis.afiliacion.dto.PlanDTO;
import mx.pliis.afiliacion.dto.RelacionFamiliarDTO;
import mx.pliis.afiliacion.dto.SexoDTO;
import mx.pliis.afiliacion.dto.TipoArchivoDTO;

public interface CatalogoService {

	public List<SexoDTO> getAllSexos();

	public List<NacionalidadDTO> getAllNacionalidades();

	public List<EstadoCivilDTO> getAllEstadosCiviles();

	public List<RelacionFamiliarDTO> getAllRelacionesFamiliares(Boolean incluida);
	
	public List<PlanDTO> getAllPlanes();
	
	public List<MontoCreditoDTO> getAllMontosCredito();
	
	public List<TipoArchivoDTO> getAllTipoArchivo();
	
	public List<EstatusCreditoDTO> getAllEstatusCredito();
}
