package mx.pliis.afiliacion.utils.afiliacion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import mx.pliis.afiliacion.dto.ColorPlanDTO;
import mx.pliis.afiliacion.dto.EstadoCivilDTO;
import mx.pliis.afiliacion.dto.EstatusCreditoDTO;
import mx.pliis.afiliacion.dto.MiembroDTO;
import mx.pliis.afiliacion.dto.MontoCreditoDTO;
import mx.pliis.afiliacion.dto.NacionalidadDTO;
import mx.pliis.afiliacion.dto.PagoDTO;
import mx.pliis.afiliacion.dto.PlanDTO;
import mx.pliis.afiliacion.dto.RelacionFamiliarDTO;
import mx.pliis.afiliacion.dto.SexoDTO;
import mx.pliis.afiliacion.dto.SuscripcionDTO;
import mx.pliis.afiliacion.dto.TipoArchivoDTO;
import mx.pliis.afiliacion.persistencia.hibernate.entity.ColorPlanEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.EstadoCivilEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.EstatusCreditoEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.MiembroEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.MontoCreditoEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.NacionalidadEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.PagosEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.PlanEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.RelacionFamiliarEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.SexoEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.SuscripcionEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.TipoArchivoEntity;

@Component
public class CatalogoUtil {

	public CatalogoUtil() {
	}

	public void copyFromSexoEntityListToDTO(List<SexoEntity> ents, List<SexoDTO> dtos) {
		ents.forEach(ent -> {
			var dto = new SexoDTO();
			copyFromSexoEntityToDTO(ent, dto);
			dtos.add(dto);
		});
	}

	public void copyFromSexoEntityToDTO(SexoEntity ent, SexoDTO dto) {
		dto.setIdSexo(ent.getIdSexo());
		dto.setNombre(ent.getNombre());
	}

	public void copyFromEstadoCivilEntityListToDTO(List<EstadoCivilEntity> ents, List<EstadoCivilDTO> dtos) {
		ents.forEach(ent -> {
			var dto = new EstadoCivilDTO();
			copyFromEstadoCivilEntityToDTO(ent, dto);
			dtos.add(dto);
		});
	}

	public void copyFromEstadoCivilEntityToDTO(EstadoCivilEntity ent, EstadoCivilDTO dto) {
		dto.setIdEstadoCivil(ent.getIdEstadoCivil());
		dto.setNombre(ent.getNombre());
	}

	public void copyFromNacionalidadEntityListToDTO(List<NacionalidadEntity> ents, List<NacionalidadDTO> dtos) {
		ents.forEach(ent -> {
			var dto = new NacionalidadDTO();
			copyFromNacionalidadEntityToDTO(ent, dto);
			dtos.add(dto);
		});
	}

	public void copyFromNacionalidadEntityToDTO(NacionalidadEntity ent, NacionalidadDTO dto) {
		dto.setIdNacionalidad(ent.getIdNacionalidad());
		dto.setNombre(ent.getNombre());
	}
	
	public void copyFromRelacionFamiliarEntityToDTO(RelacionFamiliarEntity ent, RelacionFamiliarDTO dto) {
		dto.setIdRelacionFamiliar(ent.getIdRelacionFamiliar());
		dto.setNombre(ent.getNombre());
	}

	public void copyFromRelacionFamiliarEntityListToDTO(List<RelacionFamiliarEntity> ents,
			List<RelacionFamiliarDTO> dtos) {
		ents.forEach(ent -> {
			var dto = new RelacionFamiliarDTO();
			copyFromRelacionFamiliarEntityToDTO(ent, dto);
			dtos.add(dto);
		});
	}
	
	public void copyFromPlanEntityListToDTO(List<PlanEntity> ents, List<PlanDTO> dtos) {
		ents.forEach(ent -> {
			var dto = new PlanDTO();
			copyFromPlanEntityToDTO(ent, dto);
			dtos.add(dto);
		});
	}

	public void copyFromPlanEntityToDTO(PlanEntity ent, PlanDTO dto) {
		dto.setIdPlan(ent.getIdPlan());
		dto.setNombre(ent.getNombre());
		dto.setMonto(ent.getMonto());
		dto.setDescripcion(ent.getDescripcion());
		dto.setMoneda(ent.getMoneda());
		dto.setDuracion(ent.getDuracion());
		dto.setUnidadDuracion(ent.getUnidad_duracion());
		dto.setTiempoPrueba(ent.getTiempo_prueba());
		
		List<ColorPlanDTO> listaRet = new ArrayList<>();
		Collection<ColorPlanEntity> listaEnt = ent.getColorPlanEntityCollection();
		copyFromColorPlanEntityListToDTO(listaEnt,listaRet);
		dto.setListaColorPlanDTO(listaRet);
	}
	
	public void copyFromColorPlanEntityListToDTO(Collection<ColorPlanEntity> ents, List<ColorPlanDTO> dtos) {
		ents.forEach(ent -> {
			var dto = new ColorPlanDTO();
			copyFromColorPlanEntityToDTO(ent, dto);
			dtos.add(dto);
		});
	}
	
	public void copyFromColorPlanEntityToDTO(ColorPlanEntity ent, ColorPlanDTO dto) {
		dto.setIdColorPlan(ent.getIdColorPlan());
		dto.setIdPlan(ent.getIdPlan());
		dto.setColorHex(ent.getColorHex());
		dto.setColorRGBA(ent.getColorRGBA());
		dto.setUsoPara(ent.getUsoPara());
		dto.setOrden(ent.getOrden());
	}
	
	public void copyFromSuscripcionEntityListToDTO(List<SuscripcionEntity> ents,
			List<SuscripcionDTO> dtos) {
		ents.forEach(ent -> {
			var dto = new SuscripcionDTO();
			copyFromSuscripcionEntityToDTO(ent, dto);
			dtos.add(dto);
		});
	}
	
	public void copyFromSuscripcionEntityToDTO(SuscripcionEntity ent, SuscripcionDTO dto) {
		var planDTO = new PlanDTO();
		PlanEntity planEntity = ent.getPlanEntity();
		this.copyFromPlanEntityToDTO(planEntity, planDTO);
		
		var miembroDTO = new MiembroDTO();
		MiembroEntity miembroEntity = ent.getMiembroEntity();
		this.copyFromMiembroEntityToDTO(miembroEntity, miembroDTO);
		
		var pagoDTO = new PagoDTO();
		PagosEntity pagosEntity = ent.getPagoEntity();
		this.copyFromPagosEntityToDTO(pagosEntity, pagoDTO);
		
		dto.setPlanDTO(planDTO);
		dto.setMiembroDTO(miembroDTO);
		dto.setPagoDTO(pagoDTO);
		dto.setFechaInicio(ent.getFechaInicio());
		dto.setFechaFin(ent.getFechaFin());
		dto.setActivo(ent.getActivo());
	}
	
	public void copyFromMiembroEntityToDTO(MiembroEntity ent, MiembroDTO dto) {
		dto.setIdMiembro(ent.getIdMiembro());
		dto.setNombres(ent.getNombres());
		dto.setEmpresa(ent.getEmpresa());
		dto.setCentroTrabajo(ent.getCentroTrabajo());
		dto.setSindicato(ent.getSindicato());
		dto.setFotoCredencial(ent.getFotoCredencial());
		dto.setApellidoPaterno(ent.getApellidoPaterno());
		dto.setApellidoMaterno(ent.getApellidoMaterno());
		dto.setDireccionDomicilio(ent.getDireccionDomicilio());
		dto.setRfc(ent.getRfc());
		dto.setCurp(ent.getCurp());
		dto.setLugarNacimiento(ent.getLugarNacimiento());
		dto.setFechaAfiliacion(ent.getFechaAfiliacion());
		dto.setFotoMiembro(ent.getFotoMiembro());
		dto.setFechaNacimiento(ent.getFechaNacimiento());
		dto.setContrato(ent.getContrato());
		dto.setFechaRegistro(ent.getFechaRegistro());
		dto.setFechaBaja(ent.getFechaBaja());
		dto.setNombreSindicato(ent.getNombreSindicato());
		dto.setNombrePuestoTrabajo(ent.getNombrePuestoTrabajo());
		dto.setSalarioMensualNeto(ent.getSalarioMensualNeto());
		dto.setSalarioMensualBruto(ent.getSalarioMensualBruto());
		dto.setDeseaAfiliarse(ent.getDeseaAfiliarse());
		dto.setFechaIngresoEmpresa(ent.getFechaIngresoEmpresa());
		dto.setIdEstadoCivil(ent.getIdEstadoCivil().getIdEstadoCivil());
		dto.setIdNacionalidad(ent.getIdNacionalidad().getIdNacionalidad());
		dto.setIdSexo(ent.getIdSexo().getIdSexo());
		dto.setIdFacebook(ent.getIdFacebook());
		dto.setIdWhatsapp(ent.getIdWhatsapp());
		dto.setTelefono(ent.getIdUsuario().getTelefono());
		dto.setArqIdUsuario(ent.getIdUsuario().getArqIdUsuario());
		dto.setCalle(ent.getCalle());
		dto.setNumero(ent.getNumero());
		dto.setColonia(ent.getColonia());
		dto.setAlcaldia(ent.getAlcaldia());
		dto.setCiudad(ent.getCiudad());
		dto.setPais(ent.getPais());
	}
	
	public void copyFromPagosEntityToDTO(PagosEntity ent, PagoDTO dto) {
		dto.setIdPago(ent.getIdPago());
		dto.setIdUsuario(ent.getIdUsuario());
		dto.setIdPagoOpenpay(ent.getIdPagoOpenpay());
		dto.setDescription(ent.getDescription());
		dto.setMonto(ent.getMonto());
		dto.setStatus(ent.getStatus().toString());
		dto.setFecha(ent.getFecha());
		dto.setTipo(ent.getTipo().toString());
		dto.setAuthorization(ent.getAuthorization());
		dto.setErrorCode(ent.getErrorCode());
		dto.setErrorMessage(ent.getErrorMessage());
	}
}
