package mx.pliis.afiliacion.utils.afiliacion;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.pliis.afiliacion.dto.NuevoAfiliadoDTO;
import mx.pliis.afiliacion.persistencia.hibernate.entity.AfiliadoEntity;
import mx.pliis.afiliacion.persistencia.hibernate.repository.EstadoCivilEntityRepository;
import mx.pliis.afiliacion.persistencia.hibernate.repository.NacionalidadEntityRepository;
import mx.pliis.afiliacion.persistencia.hibernate.repository.SexoEntityRepository;

@Component
public class AfiliadoUtil {

	@Autowired
	private EstadoCivilEntityRepository estadoCivilRepository;
	@Autowired
	private NacionalidadEntityRepository nacionalidadRepository;
	@Autowired
	private SexoEntityRepository sexoRepository;

	public AfiliadoUtil() {
	}

	public void copyFromDTOtoEntity(NuevoAfiliadoDTO dto, AfiliadoEntity ent) {
		ent.setApellidoMaterno(dto.getApellidoMaterno());
		ent.setApellidoPaterno(dto.getApellidoPaterno());
		ent.setContrato(dto.getContrato());
		ent.setCurp(dto.getCurp());
		ent.setDeseaAfiliarse(dto.getDeseaAfiliarse());
		ent.setDireccionDomicilio(dto.getDireccionDomicilio());
		ent.setEsIdCentroTrabajo(dto.getEsIdCentroTrabajo());
		ent.setEsIdSindicato(dto.getEsIdSindicato());
		ent.setFechaAfiliacion(dto.getFechaAfiliacion());
		ent.setFechaBaja(null);
		ent.setFechaNacimiento(dto.getFechaNacimiento());
		ent.setFechaRegistro(dto.getFechaRegistro());
		ent.setFechaIngresoEmpresa(dto.getFechaIngresoEmpresa());
		ent.setFotoAfiliado(dto.getFotoAfiliado());
		ent.setFotoCredencial(dto.getFotoCredencial());
		ent.setIdEstadoCivil(estadoCivilRepository.findById(dto.getIdEstadoCivil()).get());
		ent.setIdNacionalidad(nacionalidadRepository.findById(dto.getIdNacionalidad()).get());
		ent.setIdSexo(sexoRepository.findById(dto.getIdSexo()).get());
		ent.setLugarNacimiento(dto.getLugarNacimiento());
		ent.setNombrePuestoTrabajo(dto.getNombrePuestoTrabajo());
		ent.setNombres(dto.getNombres());
		ent.setNombreSindicato(dto.getNombreSindicato());
		ent.setRfc(dto.getRfc());
		ent.setSalarioMensualBruto(dto.getSalarioMensualBruto());
		ent.setSalarioMensualNeto(dto.getSalarioMensualNeto());
		ent.setVinculoFamiliarEntityCollection(null);
		ent.setIdFacebook(dto.getIdFacebook());
		ent.setIdWhatsapp(dto.getIdWhatsapp());
	}

	public void copyFromEntityToDTO(NuevoAfiliadoDTO dto, AfiliadoEntity ent) {

		try {
			BeanUtils.copyProperties(dto, ent);
			
			dto.setArqIdUsuario(ent.getIdUsuario().getArqIdUsuario());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
