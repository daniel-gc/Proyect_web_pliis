package mx.pliis.afiliacion.service.credito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;
import mx.pliis.afiliacion.dto.ArchivoCreditoDTO;
import mx.pliis.afiliacion.dto.ColorPlanDTO;
import mx.pliis.afiliacion.dto.ContactoCreditoDTO;
//import lombok.RequiredArgsConstructor;
import mx.pliis.afiliacion.dto.NuevoMiembroDTO;
import mx.pliis.afiliacion.dto.ObservacionCreditoDTO;
import mx.pliis.afiliacion.dto.SolicitudCreditoDTO;
import mx.pliis.afiliacion.dto.SuscripcionDTO;
import mx.pliis.afiliacion.persistencia.hibernate.entity.ArchivoCreditoEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.ContactoCreditoEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.CreditoEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.EstatusCreditoEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.MiembroEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.ObservacionCreditoEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.PagosEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.PlanEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.SuscripcionEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.UsuarioEntity;
import mx.pliis.afiliacion.persistencia.hibernate.repository.ArchivoCreditoEntityRepository;
import mx.pliis.afiliacion.persistencia.hibernate.repository.ContactoCreditoEntityRepository;
import mx.pliis.afiliacion.persistencia.hibernate.repository.CreditoEntityRepository;
import mx.pliis.afiliacion.persistencia.hibernate.repository.EstatusCreditoEntityRepository;
import mx.pliis.afiliacion.persistencia.hibernate.repository.MiembroEntityRepository;
import mx.pliis.afiliacion.persistencia.hibernate.repository.ObservacionCreditoEntityRepository;
import mx.pliis.afiliacion.persistencia.hibernate.repository.PagosEntityRepository;
import mx.pliis.afiliacion.persistencia.hibernate.repository.PlanEntityRepository;
import mx.pliis.afiliacion.persistencia.hibernate.repository.SuscripcionEntityRepository;
import mx.pliis.afiliacion.persistencia.hibernate.repository.UsuarioEntityRepository;
import mx.pliis.afiliacion.utils.afiliacion.CatalogoUtil;
import mx.pliis.afiliacion.utils.credito.CreditoUtil;
import mx.pliis.afiliacion.utils.miembro.MiembroUtil;

@Service
@Log4j2
public class CreditoServiceImpl implements CreditoService {
	@Autowired
	private UsuarioEntityRepository usuarioRepository;
	@Autowired
	private CreditoEntityRepository creditoEntityRepository;
	@Autowired
	private ContactoCreditoEntityRepository contactoCreditoEntityRepository;
	@Autowired
	private ArchivoCreditoEntityRepository archivoCreditoEntityRepository;
	@Autowired
	private ObservacionCreditoEntityRepository observacionCreditoEntityRepository;
	@Autowired
	private EstatusCreditoEntityRepository estatusCreditoEntityRepository;
	@Autowired
	private CreditoUtil creditoUtil;
	@Autowired
	private CatalogoUtil catalogoUtil;

	@Override
	@Transactional(readOnly = false)
	public Integer generarSolicitudCredito(SolicitudCreditoDTO solicitudCreditoDTO) {
		var creditoEntity = new CreditoEntity();
		LocalDate fechaActual = LocalDate.now();

		Optional<UsuarioEntity> opcionalUsuarioEntity = usuarioRepository
				.findByArqIdUsuario(solicitudCreditoDTO.getArqIdUsuario());
		UsuarioEntity usuarioEntity = opcionalUsuarioEntity.get();

		creditoUtil.copyFromCreditoDTOtoEntity(solicitudCreditoDTO, creditoEntity);
		creditoEntity.setFechaSolicitud(fechaActual);
		creditoEntity.setIdUsuario(usuarioEntity);
		creditoEntity.setActivo(true);

		creditoEntity = creditoEntityRepository.save(creditoEntity);
		return creditoEntity.getIdCredito();
	}

	@Override
	@Transactional(readOnly = false)
	public Integer agregarContactoCredito(ContactoCreditoDTO contactoCreditoDTO) {
		var contactoCreditoEntity = new ContactoCreditoEntity();

		Optional<CreditoEntity> opcionalCreditoEntity = creditoEntityRepository
				.findById(contactoCreditoDTO.getIdCredito());
		CreditoEntity creditoEntity = opcionalCreditoEntity.get();

		creditoUtil.copyFromContactoCreditoDTOtoEntity(contactoCreditoDTO, contactoCreditoEntity);
		contactoCreditoEntity.setIdCredito(creditoEntity);
		contactoCreditoEntity.setActivo(true);

		contactoCreditoEntity = contactoCreditoEntityRepository.save(contactoCreditoEntity);
		return contactoCreditoEntity.getIdContactoCredito();
	}

	@Override
	@Transactional(readOnly = false)
	public Integer agregarArchivoCredito(ArchivoCreditoDTO archivoCreditoDTO) {
		var archivoCreditoEntity = new ArchivoCreditoEntity();

		Optional<CreditoEntity> opcionalCreditoEntity = creditoEntityRepository
				.findById(archivoCreditoDTO.getIdCredito());
		CreditoEntity creditoEntity = opcionalCreditoEntity.get();

		creditoUtil.copyFromArchivoCreditoDTOtoEntity(archivoCreditoDTO, archivoCreditoEntity);
		archivoCreditoEntity.setNombre(archivoCreditoEntity.getIdTipoArchivo().getDescripcion());
		archivoCreditoEntity.setIdCredito(creditoEntity);
		archivoCreditoEntity.setActivo(true);

		archivoCreditoEntity = archivoCreditoEntityRepository.save(archivoCreditoEntity);
		return archivoCreditoEntity.getIdArchivoCredito();
	}

	@Override
	@Transactional(readOnly = false)
	public List<SolicitudCreditoDTO> consultaSolicitudesCredito(String tipoUsuario, Integer arqIdUsuario) {
		List<SolicitudCreditoDTO> listaSolicitudCreditoDTO = new ArrayList<>();
		Optional<List<CreditoEntity>> optionalListaCreditoEntity = null;
		List<Integer> listaEstatusCredito = null;

		if (tipoUsuario.equals("AC")) {
			listaEstatusCredito = new ArrayList<>();
			listaEstatusCredito.add(1);
			listaEstatusCredito.add(5);

			optionalListaCreditoEntity = creditoEntityRepository
					.getSolicitudesCreditoByIdEstatusCreditoAndActivoForSC(listaEstatusCredito, true);
		} else if (tipoUsuario.equals("AA")) {
			listaEstatusCredito = new ArrayList<>();
			listaEstatusCredito.add(8);

			optionalListaCreditoEntity = creditoEntityRepository
					.getSolicitudesCreditoByIdEstatusCreditoAndActivoForSC(listaEstatusCredito, true);
		} else {
			Optional<UsuarioEntity> opcionalUsuarioEntity = usuarioRepository.findByArqIdUsuario(arqIdUsuario);
			if (opcionalUsuarioEntity.isEmpty()) {
				log.error("El usuario {} no existe.", arqIdUsuario);
				throw new IllegalArgumentException("El usuario espeficado no existe.");
			}
			UsuarioEntity usuarioEntity = opcionalUsuarioEntity.get();

			optionalListaCreditoEntity = creditoEntityRepository
					.getSolicitudesCreditoByIdUsuarioAndActivoForASUE(usuarioEntity.getIdUsuario(), true);
		}

		optionalListaCreditoEntity.ifPresent(l -> {
			creditoUtil.copyFromCreditoEntityListToDTO(l, listaSolicitudCreditoDTO);
		});

		return listaSolicitudCreditoDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public Integer agregarObservacionCredito(ObservacionCreditoDTO observacionCreditoDTO) {
		var observacionCreditoEntity = new ObservacionCreditoEntity();

		LocalDate fechaActual = LocalDate.now();
		Optional<UsuarioEntity> opcionalUsuarioEntity = usuarioRepository
				.findByArqIdUsuario(observacionCreditoDTO.getArqIdUsuario());
		if (opcionalUsuarioEntity.isEmpty()) {
			log.error("El usuario {} no existe.", observacionCreditoDTO.getArqIdUsuario());
			throw new IllegalArgumentException("El usuario espeficado no existe.");
		}
		UsuarioEntity usuarioEntity = opcionalUsuarioEntity.get();
		
		Optional<CreditoEntity> opcionalCreditoEntity = creditoEntityRepository
				.findById(observacionCreditoDTO.getIdCredito());
		CreditoEntity creditoEntity = opcionalCreditoEntity.get();
		
		if (!creditoEntity.getObservacionCreditoEntity().isEmpty()) {
			deshabilitarObservacionCreditoActivas(creditoEntity.getObservacionCreditoEntity());
		}
		
		creditoUtil.copyFromObservacionCreditoDTOtoEntity(observacionCreditoDTO, observacionCreditoEntity);
		observacionCreditoEntity.setIdCredito(creditoEntity);
		observacionCreditoEntity.setIdUsuario(usuarioEntity);
		observacionCreditoEntity.setFechaObservacion(fechaActual);
		observacionCreditoEntity.setActivo(true);

		observacionCreditoEntity = observacionCreditoEntityRepository.save(observacionCreditoEntity);
		
		return observacionCreditoEntity.getIdObservacionCredito();
	}
	
	public void deshabilitarObservacionCreditoActivas(Collection<ObservacionCreditoEntity> observacionCreditoEntityCollection){
		observacionCreditoEntityCollection.forEach(observacionCreditoEntity -> {
			observacionCreditoEntity.setActivo(false);
			observacionCreditoEntityRepository.save(observacionCreditoEntity);
		});
	}
	
	@Override
	@Transactional(readOnly = false)
	public SolicitudCreditoDTO actualizaEstatusCredito(Integer idCredito, Integer idEstatusCredito) {
		Optional<CreditoEntity> opcionalCreditoEntity = creditoEntityRepository
				.findById(idCredito);
		CreditoEntity creditoEntity = opcionalCreditoEntity.get();
		
		Optional<EstatusCreditoEntity> opcionalEstatusCreditoEntity = estatusCreditoEntityRepository
				.findById(idEstatusCredito);
		EstatusCreditoEntity estatusCreditoEntity = opcionalEstatusCreditoEntity.get();
		
		LocalDate fechaActual = LocalDate.now();
		
		SolicitudCreditoDTO solicitudCreditoDTO = null;
		try {
			if(idEstatusCredito == 7 || idEstatusCredito == 8) {
				creditoEntity.setFechaConclucion(fechaActual);
			}
			creditoEntity.setIdEstatusCredito(estatusCreditoEntity);
			creditoEntity = creditoEntityRepository.save(creditoEntity);
			
			solicitudCreditoDTO = new SolicitudCreditoDTO();
			creditoUtil.copyFromCreditoEntityToDTO(creditoEntity, solicitudCreditoDTO);
			
		} catch (Exception e) {
			return null;
		}
		return solicitudCreditoDTO;
	}
	
	@Override
	@Transactional(readOnly = false)
	public SolicitudCreditoDTO actualizaSolicitudCredito(SolicitudCreditoDTO solicitudCreditoDTO) {
		var creditoEntity = new CreditoEntity();
		LocalDate fechaActual = LocalDate.now();

		Optional<UsuarioEntity> opcionalUsuarioEntity = usuarioRepository
				.findByArqIdUsuario(solicitudCreditoDTO.getArqIdUsuario());
		UsuarioEntity usuarioEntity = opcionalUsuarioEntity.get();
		
		try {
			creditoUtil.copyFromCreditoDTOtoEntity(solicitudCreditoDTO, creditoEntity);
			creditoEntity.setIdUsuario(usuarioEntity);
			creditoEntity.setActivo(true);

			creditoEntity = creditoEntityRepository.save(creditoEntity);

			solicitudCreditoDTO = new SolicitudCreditoDTO();
			creditoUtil.copyFromCreditoEntityToDTO(creditoEntity, solicitudCreditoDTO);			
		} catch (Exception e) {
			return null;
		}
		return solicitudCreditoDTO;
	}
	
	@Override
	@Transactional(readOnly = false)
	public ContactoCreditoDTO actualizaContactoCredito(ContactoCreditoDTO contactoCreditoDTO) {
		var contactoCreditoEntity = new ContactoCreditoEntity();
		LocalDate fechaActual = LocalDate.now();
		
		Optional<CreditoEntity> opcionalCreditoEntity = creditoEntityRepository
				.findById(contactoCreditoDTO.getIdCredito());
		CreditoEntity creditoEntity = opcionalCreditoEntity.get();
		
		try {
			creditoUtil.copyFromContactoCreditoDTOtoEntity(contactoCreditoDTO, contactoCreditoEntity);
			contactoCreditoEntity.setIdCredito(creditoEntity);
			contactoCreditoEntity.setActivo(true);

			contactoCreditoEntity = contactoCreditoEntityRepository.save(contactoCreditoEntity);
			creditoUtil.copyFromContactoCreditoEntitytoDTO(contactoCreditoEntity, contactoCreditoDTO);			
		} catch (Exception e) {
			return null;
		}
		return contactoCreditoDTO;
	}
	
	@Override
	@Transactional(readOnly = false)
	public ArchivoCreditoDTO actualizaArchivoCredito(ArchivoCreditoDTO archivoCreditoDTO) {
		var archivoCreditoEntity = new ArchivoCreditoEntity();
		LocalDate fechaActual = LocalDate.now();
		
		Optional<CreditoEntity> opcionalCreditoEntity = creditoEntityRepository
				.findById(archivoCreditoDTO.getIdCredito());
		CreditoEntity creditoEntity = opcionalCreditoEntity.get();
		
		try {
			creditoUtil.copyFromArchivoCreditoDTOtoEntity(archivoCreditoDTO, archivoCreditoEntity);
			archivoCreditoEntity.setIdCredito(creditoEntity);
			archivoCreditoEntity.setActivo(true);

			archivoCreditoEntity = archivoCreditoEntityRepository.save(archivoCreditoEntity);
			creditoUtil.copyFromArchivoCreditoEntitytoDTO(archivoCreditoEntity, archivoCreditoDTO);			
		} catch (Exception e) {
			return null;
		}
		return archivoCreditoDTO;
	}
	
	@Override
	@Transactional(readOnly = false)
	public List<SolicitudCreditoDTO> consultaSolicitudesCreditoConcentrado() {
		List<SolicitudCreditoDTO> listaSolicitudCreditoDTO = new ArrayList<>();
		Optional<List<CreditoEntity>> optionalListaCreditoEntity = null;
		List<CreditoEntity> listaCreditoEntity = null;
		List<Integer> listaEstatusCredito = null;
		listaEstatusCredito = new ArrayList<>();
		listaEstatusCredito.add(8);
		
		optionalListaCreditoEntity = creditoEntityRepository
					.getSolicitudesCreditoByIdEstatusCreditoAndActivoConcentrado(listaEstatusCredito, true);

		if (optionalListaCreditoEntity.isEmpty()) {
			log.error("No se encontraron registros de solicitudes aprobadas pendientes de envio.");
			//throw new IllegalArgumentException("No se encontraron registros de solicitudes aprobadas pendientes de envio.");
			return null;
		} else {
			listaCreditoEntity = optionalListaCreditoEntity.get();
			
//			if (!listaCreditoEntity.isEmpty()) {
//				marcarComoEnviadasCredito(listaCreditoEntity);
//			}
			
			optionalListaCreditoEntity.ifPresent(l -> {
				creditoUtil.copyFromCreditoEntityListToDTO(l, listaSolicitudCreditoDTO);
			});
	
			return listaSolicitudCreditoDTO;
		}
	}
	
	@Override
	@Transactional(readOnly = false)
	public Boolean deleteSolicitudCredito(Integer idCredito) {
		Optional<CreditoEntity> opcionalCreditoEntity = creditoEntityRepository.findById(idCredito);
		
		if (opcionalCreditoEntity.isPresent()) {
			CreditoEntity creditoEntity = opcionalCreditoEntity.get();
			creditoEntityRepository.delete(creditoEntity);
			return true;
		}
		return false;
	}
	
	@Override
	@Transactional(readOnly = false)
	public Boolean deleteContactosCredito(Integer idCredito) {
		Optional<CreditoEntity> opcionalCreditoEntity = creditoEntityRepository.findById(idCredito);
		CreditoEntity creditoEntity = opcionalCreditoEntity.get();
		
		if (!creditoEntity.getContactoCreditoEntityCollection().isEmpty()) {
			Collection<ContactoCreditoEntity> colectionContactoCreditoEntity = creditoEntity.getContactoCreditoEntityCollection();
			for (ContactoCreditoEntity contactoCreditoEntity : colectionContactoCreditoEntity) {
				contactoCreditoEntityRepository.delete(contactoCreditoEntity);
			}
			return true;
		}
		return false;
	}
	
	// SolicitudCredito
	// ContactoCredito
	// ArchivoCredito
	@Override
	@Transactional(readOnly = false)
	public Boolean deleteArchivosCredito(Integer idCredito) {
		Optional<CreditoEntity> opcionalCreditoEntity = creditoEntityRepository.findById(idCredito);
		CreditoEntity creditoEntity = opcionalCreditoEntity.get();
		
		if (!creditoEntity.getArchivoCreditoEntityCollection().isEmpty()) {
			Collection<ArchivoCreditoEntity> colectionArchivoCreditoEntity = creditoEntity.getArchivoCreditoEntityCollection();
			for (ArchivoCreditoEntity archivoCreditoEntity : colectionArchivoCreditoEntity) {
				archivoCreditoEntityRepository.delete(archivoCreditoEntity);
			}
			return true;
		}
		return false;
	}
	
	@Override
	@Transactional(readOnly = false)
	public Boolean marcarComoEnviadasSolicitudesCredito(String listasolicitudes) {
		List<CreditoEntity> listaCreditoEntity = new ArrayList<CreditoEntity>();
		var listasolicitudesArray = listasolicitudes.split(",");
		
		for (String idCreditoSTR : listasolicitudesArray) {
			Integer idCredito = Integer.valueOf(idCreditoSTR);
			Optional<CreditoEntity> opcionalCreditoEntity = creditoEntityRepository.findById(idCredito);
			CreditoEntity creditoEntity = opcionalCreditoEntity.get();
			
			listaCreditoEntity.add(creditoEntity);
		}
		
		marcarComoEnviadasCredito(listaCreditoEntity);
		
		return true;
	}
	
	public void marcarComoEnviadasCredito(List<CreditoEntity> listaCreditoEntity){
		listaCreditoEntity.forEach(creditoEntity -> {
			creditoEntity.setEnviado(true);
			creditoEntityRepository.save(creditoEntity);
		});
	}
}
