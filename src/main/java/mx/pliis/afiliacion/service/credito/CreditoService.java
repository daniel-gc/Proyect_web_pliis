package mx.pliis.afiliacion.service.credito;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import mx.pliis.afiliacion.dto.ArchivoCreditoDTO;
import mx.pliis.afiliacion.dto.ContactoCreditoDTO;
import mx.pliis.afiliacion.dto.NuevoMiembroDTO;
import mx.pliis.afiliacion.dto.ObservacionCreditoDTO;
import mx.pliis.afiliacion.dto.SolicitudCreditoDTO;
import mx.pliis.afiliacion.dto.SuscripcionDTO;

public interface CreditoService {
	public Integer generarSolicitudCredito(SolicitudCreditoDTO solicitudCreditoDTO);
	
	public Integer agregarContactoCredito(ContactoCreditoDTO contactoCreditoDTO);
	
	public Integer agregarArchivoCredito(ArchivoCreditoDTO archivoCreditoDTO);
	
	public List<SolicitudCreditoDTO> consultaSolicitudesCredito(String tipoUsuario, Integer arqIdUsuario);
	
	public Integer agregarObservacionCredito(ObservacionCreditoDTO observacionCreditoDTO);
	
	public SolicitudCreditoDTO actualizaEstatusCredito(Integer idCredito, Integer idEstatusCredito);
	
	public SolicitudCreditoDTO actualizaSolicitudCredito(SolicitudCreditoDTO solicitudCreditoDTO);
	
	public ContactoCreditoDTO actualizaContactoCredito(ContactoCreditoDTO contactoCreditoDTO);
	
	public ArchivoCreditoDTO actualizaArchivoCredito(ArchivoCreditoDTO archivoCreditoDTO);
	
	public List<SolicitudCreditoDTO> consultaSolicitudesCreditoConcentrado();
	
	public Boolean deleteSolicitudCredito(Integer idCredito);
	
	public Boolean deleteContactosCredito(Integer idCredito);
	
	public Boolean deleteArchivosCredito(Integer idCredito);
	
	public Boolean marcarComoEnviadasSolicitudesCredito(String listasolicitudes);
}
