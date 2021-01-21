package mx.pliis.afiliacion.utils.miembro;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.pliis.afiliacion.dto.NuevoMiembroDTO;
import mx.pliis.afiliacion.persistencia.hibernate.entity.MiembroEntity;
import mx.pliis.afiliacion.persistencia.hibernate.repository.EstadoCivilEntityRepository;
import mx.pliis.afiliacion.persistencia.hibernate.repository.NacionalidadEntityRepository;
import mx.pliis.afiliacion.persistencia.hibernate.repository.SexoEntityRepository;

@Component
public class MiembroUtil {

	@Autowired
	private EstadoCivilEntityRepository estadoCivilRepository;
	@Autowired
	private NacionalidadEntityRepository nacionalidadRepository;
	@Autowired
	private SexoEntityRepository sexoRepository;

	public MiembroUtil() {
	}

	public void copyFromDTOtoEntity(NuevoMiembroDTO dto, MiembroEntity ent) {
		ent.setApellidoMaterno(dto.getApellidoMaterno());
		ent.setApellidoPaterno(dto.getApellidoPaterno());
		ent.setContrato(dto.getContrato());
		ent.setCurp(dto.getCurp());
		ent.setDeseaAfiliarse(dto.getDeseaAfiliarse());
		ent.setDireccionDomicilio(dto.getDireccionDomicilio());
		ent.setEmpresa(dto.getEmpresa());
		ent.setCentroTrabajo(dto.getCentroTrabajo());
		ent.setSindicato(dto.getSindicato());
		ent.setFechaAfiliacion(dto.getFechaAfiliacion());
		ent.setFechaBaja(null);
		ent.setFechaNacimiento(dto.getFechaNacimiento());
		ent.setFechaRegistro(dto.getFechaRegistro());
		ent.setFechaIngresoEmpresa(dto.getFechaIngresoEmpresa());
		ent.setFotoMiembro(dto.getFotoMiembro());
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
		//ent.setVinculoFamiliarEntityCollection(null);
		ent.setIdFacebook(dto.getIdFacebook());
		ent.setIdWhatsapp(dto.getIdWhatsapp());
		ent.setCalle(dto.getCalle());
		ent.setNumero(dto.getNumero());
		ent.setColonia(dto.getColonia());
		ent.setAlcaldia(dto.getAlcaldia());
		ent.setCiudad(dto.getCiudad());
		ent.setPais(dto.getPais());
	}

	public void copyFromEntityToDTO(NuevoMiembroDTO dto, MiembroEntity ent) {

		try {
			BeanUtils.copyProperties(dto, ent);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
