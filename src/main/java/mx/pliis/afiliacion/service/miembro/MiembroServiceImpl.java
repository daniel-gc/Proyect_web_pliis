package mx.pliis.afiliacion.service.miembro;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;
import mx.pliis.afiliacion.dto.ColorPlanDTO;
//import lombok.RequiredArgsConstructor;
import mx.pliis.afiliacion.dto.NuevoMiembroDTO;
import mx.pliis.afiliacion.dto.SuscripcionDTO;
import mx.pliis.afiliacion.persistencia.hibernate.entity.MiembroEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.PagosEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.PlanEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.SuscripcionEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.UsuarioEntity;
import mx.pliis.afiliacion.persistencia.hibernate.repository.MiembroEntityRepository;
import mx.pliis.afiliacion.persistencia.hibernate.repository.PagosEntityRepository;
import mx.pliis.afiliacion.persistencia.hibernate.repository.PlanEntityRepository;
import mx.pliis.afiliacion.persistencia.hibernate.repository.SuscripcionEntityRepository;
import mx.pliis.afiliacion.persistencia.hibernate.repository.UsuarioEntityRepository;
import mx.pliis.afiliacion.utils.afiliacion.CatalogoUtil;
import mx.pliis.afiliacion.utils.miembro.MiembroUtil;


@Service
@Log4j2
public class MiembroServiceImpl implements MiembroService{
	@Autowired
	private MiembroUtil miembroUtil;
	@Autowired
	private MiembroEntityRepository miembroEntityRepository;
	@Autowired
	private UsuarioEntityRepository usuarioRepository;
	@Autowired
	private PlanEntityRepository planEntityRepository;
	@Autowired
	private SuscripcionEntityRepository suscripcionEntityRepository;
	@Autowired
	private PagosEntityRepository pagosEntityRepository;
	@Autowired
	private CatalogoUtil catalogoUtil;
	
	@Override
	@Transactional(readOnly = false)
	public Integer nuevoMiembro(NuevoMiembroDTO nuevoMiembroDTO) {
		var miembroEntity = new MiembroEntity();
		var usuarioEntity = new UsuarioEntity();
		
		miembroUtil.copyFromDTOtoEntity(nuevoMiembroDTO, miembroEntity);
		usuarioEntity.setNombres(
				nuevoMiembroDTO.getApellidoPaterno() + " " +
				nuevoMiembroDTO.getApellidoMaterno() + " " +
				nuevoMiembroDTO.getNombres());
		usuarioEntity.setArqIdUsuario(nuevoMiembroDTO.getArqIdUsuario());
		usuarioEntity.setTelefono(nuevoMiembroDTO.getTelefono());
		usuarioEntity = usuarioRepository.save(usuarioEntity);
		
		miembroEntity.setIdUsuario(usuarioEntity);
		miembroEntity = miembroEntityRepository.save(miembroEntity);
		
		return miembroEntity.getIdMiembro();
	}
	
	@Override
	@Transactional(readOnly = false)
	public boolean deleteMiembroById(Integer idMiembro) {
		Optional<MiembroEntity> miembroOptional = miembroEntityRepository.findById(idMiembro);
		if (miembroOptional.isPresent()) {
			UsuarioEntity usuarioEntity = miembroOptional.get().getIdUsuario();
			miembroEntityRepository.delete(miembroOptional.get());
			usuarioRepository.delete(usuarioEntity);
			return true;
		}
		return false;
	}
	
	@Override
	@Transactional(readOnly = false)
	public Boolean generaSuscripcion(Integer arqIdUsuario, Integer idPlan, Integer idPago) {
		Optional<UsuarioEntity> opcionalUsuarioEntity = usuarioRepository.findByArqIdUsuario(arqIdUsuario);
		UsuarioEntity usuarioEntity = opcionalUsuarioEntity.get();
		Optional<MiembroEntity> opcionalMiembroEntity= miembroEntityRepository.findByIdUsuario(usuarioEntity);
		MiembroEntity miembroEntity = opcionalMiembroEntity.get();
		Optional<PlanEntity> opcionalPlanEntity = planEntityRepository.findById(idPlan);
		PlanEntity planEntity = opcionalPlanEntity.get();
		Optional<PagosEntity> opcionalPagosEntity = pagosEntityRepository.findById(idPago);
		PagosEntity pagosEntity = opcionalPagosEntity.get();
		
		if (!miembroEntity.getSuscripcionEntityCollection().isEmpty()) {
			deshabilitarSuscripcionesActualesActivas(miembroEntity.getSuscripcionEntityCollection());
		}
		
		LocalDate fechaInicio = LocalDate.now();
		LocalDate fechaFin = calculaFechaFinSuscripcion(fechaInicio,planEntity.getDuracion(), planEntity.getUnidad_duracion());
		
		var suscripcionEntity = new SuscripcionEntity(planEntity.getIdPlan(), miembroEntity.getIdMiembro(), pagosEntity.getIdPago());
		suscripcionEntity.setPlanEntity(planEntity);
		suscripcionEntity.setMiembroEntity(miembroEntity);
		suscripcionEntity.setPagoEntity(pagosEntity);
		suscripcionEntity.setFechaInicio(fechaInicio);
		suscripcionEntity.setFechaFin(fechaFin);
		suscripcionEntity.setActivo(true);
		suscripcionEntity = suscripcionEntityRepository.save(suscripcionEntity);
		return true;
	}
	
	public void deshabilitarSuscripcionesActualesActivas(Collection<SuscripcionEntity> suscripcionEntityCollection){
		suscripcionEntityCollection.forEach(suscripcionEntity -> {
			suscripcionEntity.setActivo(false);
			suscripcionEntityRepository.save(suscripcionEntity);
		});
	}
	
	@Override
	@Transactional(readOnly = false)
	public Boolean deleteSuscripcion(Integer arqIdUsuario, Integer idPlan, Integer idPago) {
		Optional<UsuarioEntity> opcionalUsuarioEntity = usuarioRepository.findByArqIdUsuario(arqIdUsuario);
		UsuarioEntity usuarioEntity = opcionalUsuarioEntity.get();
		Optional<MiembroEntity> opcionalMiembroEntity= miembroEntityRepository.findByIdUsuario(usuarioEntity);
		MiembroEntity miembroEntity = opcionalMiembroEntity.get();
		Optional<PlanEntity> opcionalPlanEntity = planEntityRepository.findById(idPlan);
		PlanEntity planEntity = opcionalPlanEntity.get();
		Optional<PagosEntity> opcionalPagosEntity = pagosEntityRepository.findById(idPago);
		PagosEntity pagosEntity = opcionalPagosEntity.get();
		
		LocalDate fechaInicio = LocalDate.now();
		LocalDate fechaFin = calculaFechaFinSuscripcion(fechaInicio,planEntity.getDuracion(), planEntity.getUnidad_duracion());
		
		Optional<SuscripcionEntity> optionalSuscripcionEntity = suscripcionEntityRepository.findByidMiembroAndidPlanAndidPagoAndactivo(miembroEntity.getIdMiembro(), planEntity.getIdPlan(), pagosEntity.getIdPago()); 
		if (optionalSuscripcionEntity.isPresent()) {
			suscripcionEntityRepository.delete(optionalSuscripcionEntity.get());
			return true;
		}
		
		return false;
	}
	
	public LocalDate calculaFechaFinSuscripcion(LocalDate fechaInicio, Integer aumento, String unidadMedida) {
		switch (unidadMedida) {
			case "MES":
			break;
			case "AÑO":
				aumento = aumento * 12;
			break;
			default:
			break;
		}
		LocalDate fechaFin = fechaInicio.plusMonths(aumento);
		return fechaFin;
	}
	
	@Override
	@Transactional(readOnly = false)
	public List<SuscripcionDTO> getHistorialPagos(Integer arqIdUsuario){
		List<SuscripcionDTO> listaSuscripcionDTO = new ArrayList<>();
		
		Optional<UsuarioEntity> opcionalUsuarioEntity = usuarioRepository.findByArqIdUsuario(arqIdUsuario);
		if (opcionalUsuarioEntity.isEmpty()) {
			log.error("El usuario {} no existe.", arqIdUsuario);
			throw new IllegalArgumentException("El usuario espeficado no existe.");
		}
		UsuarioEntity usuarioEntity = opcionalUsuarioEntity.get();
		
		Optional<MiembroEntity> opcionalMiembroEntity = miembroEntityRepository.findByIdUsuario(usuarioEntity);
		if (opcionalMiembroEntity.isEmpty()) {
			log.error("No existe un registro de miembro para el usuario {}.", arqIdUsuario);
			throw new IllegalArgumentException("No existe un registro de miembro para el usuario espeficado.");
		}
		MiembroEntity miembroEntity = opcionalMiembroEntity.get();
		
		Optional<List<SuscripcionEntity>> optionalListaSuscripcionEntity = suscripcionEntityRepository.getSuscripcionesByidMiembro(miembroEntity.getIdMiembro());
		
		optionalListaSuscripcionEntity.ifPresent(l -> {
			catalogoUtil.copyFromSuscripcionEntityListToDTO(l, listaSuscripcionDTO);
		});
		
		
		return listaSuscripcionDTO;
	}
	
	@Override
	@Transactional(readOnly = false)
	public Map<String, List<Integer>> validarSuscripciones(){
		Map<String, List<Integer>> listasUsuarios = new HashMap<String, List<Integer>>();
		List<Integer> listArqIdUsuDesactivados = new ArrayList<>();
		List<Integer> listArqIdUsuProximos = new ArrayList<>();
		
		LocalDate fechaActual = LocalDate.now();
		LocalDate fechaManana = fechaActual.plusDays(5);
		Optional<List<SuscripcionEntity>> optionalListaSuscripcionEntity = suscripcionEntityRepository.findByactivo();
		
		optionalListaSuscripcionEntity.ifPresent(l -> {
			List<SuscripcionEntity> listaSuscripcionEntity = l;
			
			listaSuscripcionEntity.forEach(ent -> {
				LocalDate fechaFinSuscrip = ent.getFechaFin();
				Integer arqIdUsuario = ent.getMiembroEntity().getIdUsuario().getArqIdUsuario();
				if (fechaActual.isEqual(fechaFinSuscrip) || fechaActual.isBefore(fechaFinSuscrip)) {
					ent.setActivo(false);
					listArqIdUsuDesactivados.add(arqIdUsuario);
					log.info("Se desactiva el acceso a PLIIS al usuario: {}", arqIdUsuario);
				} else if (fechaManana.isEqual(fechaFinSuscrip)) {
					listArqIdUsuProximos.add(arqIdUsuario);
				}
			});
		});
		
		listasUsuarios.put("listArqIdUsuDesactivados", listArqIdUsuDesactivados);
		listasUsuarios.put("listArqIdUsuProximos", listArqIdUsuProximos);
		
		return listasUsuarios;
	}
	
	@Override
	@Transactional(readOnly = false)
	public List<Integer> validarPagoSuscripciones(){
		List<Integer> listArqIdUsuCambio = new ArrayList<>();
		
		return listArqIdUsuCambio;
	}
	
	@Override
	@Transactional(readOnly = false)
	public SuscripcionDTO validarSuscripcionActiva(Integer arqIdUsuario){
		SuscripcionDTO suscripcionDTO = null;
		
		Optional<UsuarioEntity> opcionalUsuarioEntity = usuarioRepository.findByArqIdUsuario(arqIdUsuario);
		if (opcionalUsuarioEntity.isEmpty()) {
			log.error("El usuario {} no existe.", arqIdUsuario);
			throw new IllegalArgumentException("El usuario espeficado no existe.");
		}
		UsuarioEntity usuarioEntity = opcionalUsuarioEntity.get();
		
		Optional<MiembroEntity> opcionalMiembroEntity = miembroEntityRepository.findByIdUsuario(usuarioEntity);
		if (opcionalMiembroEntity.isEmpty()) {
			log.error("No existe un registro de miembro para el usuario {}.", arqIdUsuario);
			throw new IllegalArgumentException("No existe un registro de miembro para el usuario espeficado.");
		}
		MiembroEntity miembroEntity = opcionalMiembroEntity.get();
		
		Optional<List<SuscripcionEntity>> optionalListaSuscripcionEntity = suscripcionEntityRepository.findByidMiembroAndActivo(miembroEntity.getIdMiembro(), true);
		List<SuscripcionDTO> listaSuscripcionDTO = new ArrayList<>();
		optionalListaSuscripcionEntity.ifPresent(l -> {
			List<SuscripcionEntity> listaSuscripcionEntity = l;
			
			catalogoUtil.copyFromSuscripcionEntityListToDTO(listaSuscripcionEntity, listaSuscripcionDTO);
		});
		
		if (listaSuscripcionDTO.size() > 0) {
			suscripcionDTO = listaSuscripcionDTO.get(0);
		}
		
		return suscripcionDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public NuevoMiembroDTO getMiembroPorIdArq(@NotNull Integer arqIdUsuario) {
		Optional<UsuarioEntity> optionalUsuarioEntity = usuarioRepository.findByArqIdUsuario(arqIdUsuario);
		
		if (optionalUsuarioEntity.isEmpty()) {
			log.error("El usuario {} no existe.", arqIdUsuario);
			throw new IllegalArgumentException("El usuario espeficado no existe.");
		}
		
		UsuarioEntity usuarioEntity = optionalUsuarioEntity.get();
		Optional<MiembroEntity> opcionalMiembroEntity = miembroEntityRepository.findByIdUsuario(usuarioEntity);
		if (opcionalMiembroEntity.isEmpty()) {
			log.error("El miembro {} no existe.", usuarioEntity.getIdUsuario());
			throw new IllegalArgumentException("No existe un registro para el usuario espeficado.");
		}
		MiembroEntity miembroEntity = opcionalMiembroEntity.get();
		NuevoMiembroDTO nuevoMiembroDTO = new NuevoMiembroDTO();
		miembroUtil.copyFromEntityToDTO(nuevoMiembroDTO, miembroEntity);
		return nuevoMiembroDTO;
	}
}
