package mx.pliis.afiliacion.utils.credito;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.pliis.afiliacion.dto.ArchivoCreditoDTO;
import mx.pliis.afiliacion.dto.ContactoCreditoDTO;
import mx.pliis.afiliacion.dto.EstatusCreditoDTO;
import mx.pliis.afiliacion.dto.MontoCreditoDTO;
import mx.pliis.afiliacion.dto.NuevoMiembroDTO;
import mx.pliis.afiliacion.dto.ObservacionCreditoDTO;
import mx.pliis.afiliacion.dto.SolicitudCreditoDTO;
import mx.pliis.afiliacion.dto.TipoArchivoDTO;
import mx.pliis.afiliacion.persistencia.hibernate.entity.ArchivoCreditoEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.ContactoCreditoEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.CreditoEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.EstatusCreditoEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.MiembroEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.MontoCreditoEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.ObservacionCreditoEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.TipoArchivoEntity;
import mx.pliis.afiliacion.persistencia.hibernate.repository.EstadoCivilEntityRepository;
import mx.pliis.afiliacion.persistencia.hibernate.repository.EstatusCreditoEntityRepository;
import mx.pliis.afiliacion.persistencia.hibernate.repository.MontoCreditoEntityRepository;
import mx.pliis.afiliacion.persistencia.hibernate.repository.NacionalidadEntityRepository;
import mx.pliis.afiliacion.persistencia.hibernate.repository.SexoEntityRepository;
import mx.pliis.afiliacion.persistencia.hibernate.repository.TipoArchivoEntityRepository;

@Component
public class CreditoUtil {
	
	@Autowired
	private MontoCreditoEntityRepository montoCreditoEntityRepository;
	@Autowired
	private EstatusCreditoEntityRepository estatusCreditoEntityRepository;
	@Autowired
	private TipoArchivoEntityRepository tipoArchivoEntityRepository;
	
	public CreditoUtil() {
	}

	public void copyFromCreditoDTOtoEntity(SolicitudCreditoDTO dto, CreditoEntity ent) {
		ent.setIdCredito(dto.getIdCredito());
		ent.setEsAfiliado(dto.getEsAfiliado());
		ent.setEsMiembro(dto.getEsMiembro());
	    ent.setNombre(dto.getNombre());
		ent.setApellidoPaterno(dto.getApellidoPaterno());
	    ent.setApellidoMaterno(dto.getApellidoMaterno());
	    ent.setFechaNacimiento(dto.getFechaNacimiento());
		ent.setTelefono(dto.getTelefono());
	    ent.setEmail(dto.getEmail());
	    ent.setEmpresa(dto.getEmpresa());
	    ent.setNumeroEmpleado(dto.getNumeroEmpleado());
	    ent.setFechaIngresoCia(dto.getFechaIngresoCia());
		ent.setSalarioMensualBruto(dto.getSalarioMensualBruto());
		ent.setSalarioMensualNeto(dto.getSalarioMensualNeto());
		ent.setIdEstatusCredito(estatusCreditoEntityRepository.findById(dto.getIdEstatusCredito()).get());
		ent.setIdMontoCredito(montoCreditoEntityRepository.findById(dto.getIdMontoCredito()).get());
		ent.setFechaSolicitud(dto.getFechaSolicitud());
		ent.setFechaConclucion(dto.getFechaConclucion());
		ent.setOrigenSolicitud(dto.getOrigenSolicitud());
		ent.setEnviado(dto.getEnviado());
	}
	
	public void copyFromCreditoEntityListToDTO(List<CreditoEntity> ents,
			List<SolicitudCreditoDTO> dtos) {
		ents.forEach(ent -> {
			var dto = new SolicitudCreditoDTO();
			copyFromCreditoEntityToDTO(ent, dto);
			dtos.add(dto);
		});
	}
	
	public void copyFromCreditoEntityToDTO(CreditoEntity ent, SolicitudCreditoDTO dto) {
		dto.setIdCredito(ent.getIdCredito());
		dto.setIdUsuario(ent.getIdUsuario().getIdUsuario());
		dto.setArqIdUsuario(ent.getIdUsuario().getArqIdUsuario());
		dto.setEsAfiliado(ent.getEsAfiliado());
		dto.setEsMiembro(ent.getEsMiembro());
		dto.setNombre(ent.getNombre());
		dto.setApellidoPaterno(ent.getApellidoPaterno());
		dto.setApellidoMaterno(ent.getApellidoMaterno());
		dto.setFechaNacimiento(ent.getFechaNacimiento());
		dto.setTelefono(ent.getTelefono());
		dto.setEmail(ent.getEmail());
		dto.setEmpresa(ent.getEmpresa());
		dto.setNumeroEmpleado(ent.getNumeroEmpleado());
		dto.setFechaIngresoCia(ent.getFechaIngresoCia());
		dto.setSalarioMensualBruto(ent.getSalarioMensualBruto());
		dto.setSalarioMensualNeto(ent.getSalarioMensualNeto());
		dto.setFechaSolicitud(ent.getFechaSolicitud());
		dto.setFechaConclucion(ent.getFechaConclucion());
		dto.setOrigenSolicitud(ent.getOrigenSolicitud());
		dto.setEnviado(ent.getEnviado());
		dto.setFechaConclucion(ent.getFechaConclucion());
		
		dto.setIdEstatusCredito(ent.getIdEstatusCredito().getIdEstatusCredito());
		EstatusCreditoDTO estatusCreditoDTO = new EstatusCreditoDTO();
		copyFromEstatusCreditoEntityToDTO(ent.getIdEstatusCredito(), estatusCreditoDTO);
		dto.setEstatusCreditoDTO(estatusCreditoDTO);
		
		dto.setIdMontoCredito(ent.getIdMontoCredito().getIdMontoCredito());
		MontoCreditoDTO montoCreditoDTO = new MontoCreditoDTO();
		copyFromMontoCreditoEntityToDTO(ent.getIdMontoCredito(), montoCreditoDTO);
		dto.setMontoCreditoDTO(montoCreditoDTO);
		
		List<ContactoCreditoDTO> listaRetCC = new ArrayList<>();
		Collection<ContactoCreditoEntity> listaEntCC = ent.getContactoCreditoEntityCollection();
		copyFromContactoCreditoEntityListToDTO(listaEntCC,listaRetCC);
		dto.setListaContactoCreditoDTO(listaRetCC);
		
		List<ArchivoCreditoDTO> listaRetAC = new ArrayList<>();
		Collection<ArchivoCreditoEntity> listaEntAC = ent.getArchivoCreditoEntityCollection();
		copyFromArchivoCreditoEntityListToDTO(listaEntAC,listaRetAC);
		dto.setListaArchivoCreditoDTO(listaRetAC);
		
		List<ObservacionCreditoDTO> listaRetOC = new ArrayList<>();
		Collection<ObservacionCreditoEntity> listaEntOC = ent.getObservacionCreditoEntity();
		copyFromObservacionCreditoActivasEntityListToDTO(listaEntOC, listaRetOC);
		dto.setListaObservacionCreditoDTO(listaRetOC);
		
	}

	public void copyFromContactoCreditoEntityListToDTO(Collection<ContactoCreditoEntity> ents,
			List<ContactoCreditoDTO> dtos) {
		ents.forEach(ent -> {
			var dto = new ContactoCreditoDTO();
			copyFromContactoCreditoEntitytoDTO(ent, dto);
			dtos.add(dto);
		});
	}
	
	public void copyFromContactoCreditoEntitytoDTO(ContactoCreditoEntity ent, ContactoCreditoDTO dto) {
		dto.setIdContactoCredito(ent.getIdContactoCredito());
		dto.setIdCredito(ent.getIdCredito().getIdCredito());
		dto.setNombre(ent.getNombre());
		dto.setApellidoPaterno(ent.getApellidoPaterno());
		dto.setApellidoMaterno(ent.getApellidoMaterno());
		dto.setTelefono(ent.getTelefono());
		dto.setEmail(ent.getEmail());
		dto.setParentesco(ent.getParentesco());
	}
	
	public void copyFromContactoCreditoDTOtoEntity(ContactoCreditoDTO dto, ContactoCreditoEntity ent) {
		ent.setIdContactoCredito(dto.getIdContactoCredito());
		ent.setNombre(dto.getNombre());
		ent.setApellidoPaterno(dto.getApellidoPaterno());
	    ent.setApellidoMaterno(dto.getApellidoMaterno());
	    ent.setTelefono(dto.getTelefono());
	    ent.setEmail(dto.getEmail());
	    ent.setParentesco(dto.getParentesco());
	}

	public void copyFromArchivoCreditoEntityListToDTO(Collection<ArchivoCreditoEntity> ents,
			List<ArchivoCreditoDTO> dtos) {
		ents.forEach(ent -> {
			var dto = new ArchivoCreditoDTO();
			copyFromArchivoCreditoEntitytoDTO(ent, dto);
			dtos.add(dto);
		});
	}
	
	public void copyFromArchivoCreditoEntitytoDTO(ArchivoCreditoEntity ent, ArchivoCreditoDTO dto) {
		dto.setIdArchivoCredito(ent.getIdArchivoCredito());
		dto.setIdCredito(ent.getIdCredito().getIdCredito());
		dto.setNombre(ent.getNombre());
		dto.setExtencion(ent.getExtencion());
		dto.setArchivo(ent.getArchivo());
		dto.setIdTipoArchivo(ent.getIdTipoArchivo().getIdTipoArchivo());
		
		TipoArchivoDTO tipoArchivoDTO = new TipoArchivoDTO();
		copyFromTipoArchivoEntityToDTO(ent.getIdTipoArchivo(), tipoArchivoDTO);
		dto.setTipoArchivoDTO(tipoArchivoDTO);
	}

	public void copyFromArchivoCreditoDTOtoEntity(ArchivoCreditoDTO dto, ArchivoCreditoEntity ent) {
		ent.setIdArchivoCredito(dto.getIdArchivoCredito());
		ent.setNombre(dto.getNombre());
		ent.setExtencion(dto.getExtencion());
	    ent.setArchivo(dto.getArchivo());
	    ent.setIdTipoArchivo(tipoArchivoEntityRepository.findById(dto.getIdTipoArchivo()).get());
	}
	
	public void copyFromMontoCreditoEntityListToDTO(List<MontoCreditoEntity> ents, List<MontoCreditoDTO> dtos) {
		ents.forEach(ent -> {
			var dto = new MontoCreditoDTO();
			copyFromMontoCreditoEntityToDTO(ent, dto);
			dtos.add(dto);
		});
	}
	
	public void copyFromMontoCreditoEntityToDTO(MontoCreditoEntity ent, MontoCreditoDTO dto) {
		dto.setIdMontoCredito(ent.getIdMontoCredito());
		dto.setMonto(ent.getMonto());
		dto.setCuotaRecuperacion(ent.getCuotaRecuperacion());
		dto.setTotal(ent.getTotal());
		dto.setValidoDesde(ent.getValidoDesde());
		dto.setValidoHasta(ent.getValidoHasta());
		dto.setIngresoNeto(ent.getIngresoNeto());
		dto.setDescuento(ent.getDescuento());
	}

	public void copyFromTipoArchivoEntityListToDTO(List<TipoArchivoEntity> ents, List<TipoArchivoDTO> dtos) {
		ents.forEach(ent -> {
			var dto = new TipoArchivoDTO();
			copyFromTipoArchivoEntityToDTO(ent, dto);
			dtos.add(dto);
		});
	}
	
	public void copyFromTipoArchivoEntityToDTO(TipoArchivoEntity ent, TipoArchivoDTO dto) {
		dto.setIdTipoArchivo(ent.getIdTipoArchivo());
		dto.setDescripcion(ent.getDescripcion());
	}

	public void copyFromEstatusCreditoEntityListToDTO(List<EstatusCreditoEntity> ents, List<EstatusCreditoDTO> dtos) {
		ents.forEach(ent -> {
			var dto = new EstatusCreditoDTO();
			copyFromEstatusCreditoEntityToDTO(ent, dto);
			dtos.add(dto);
		});
	}
	
	public void copyFromEstatusCreditoEntityToDTO(EstatusCreditoEntity ent, EstatusCreditoDTO dto) {
		dto.setIdEstatusCredito(ent.getIdEstatusCredito());
		dto.setDescripcion(ent.getDescripcion());
	}
	
	public void copyFromObservacionCreditoEntityListToDTO(Collection<ObservacionCreditoEntity> ents, List<ObservacionCreditoDTO> dtos) {
		ents.forEach(ent -> {
			var dto = new ObservacionCreditoDTO();
			copyFromObservacionCreditoEntityToDTO(ent, dto);
			dtos.add(dto);
		});
	}
	
	public void copyFromObservacionCreditoActivasEntityListToDTO(Collection<ObservacionCreditoEntity> ents, List<ObservacionCreditoDTO> dtos) {
		ents.forEach(ent -> {
			var dto = new ObservacionCreditoDTO();
			copyFromObservacionCreditoEntityToDTO(ent, dto);
			if(ent.getActivo()) {
				dtos.add(dto);
			}
		});
	}
	
	public void copyFromObservacionCreditoEntityToDTO(ObservacionCreditoEntity ent, ObservacionCreditoDTO dto) {
		dto.setIdObservacionCredito(ent.getIdObservacionCredito());
		dto.setIdCredito(ent.getIdCredito().getIdCredito());
		dto.setIdUsuario(ent.getIdUsuario().getIdUsuario());
		dto.setObservacion(ent.getObservacion());
		dto.setIdEstatusCredito(ent.getIdEstatusCredito().getIdEstatusCredito());
		EstatusCreditoDTO estatusCreditoDTO = new EstatusCreditoDTO();
		copyFromEstatusCreditoEntityToDTO(ent.getIdEstatusCredito(), estatusCreditoDTO);
		dto.setEstatusCreditoDTO(estatusCreditoDTO);
	}
	
	public void copyFromObservacionCreditoDTOtoEntity(ObservacionCreditoDTO dto, ObservacionCreditoEntity ent) {
	    ent.setIdEstatusCredito(estatusCreditoEntityRepository.findById(dto.getIdEstatusCredito()).get());
	    ent.setObservacion(dto.getObservacion());
	}
}
