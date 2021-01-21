package mx.pliis.afiliacion.service.catalogo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.pliis.afiliacion.dto.EstadoCivilDTO;
import mx.pliis.afiliacion.dto.EstatusCreditoDTO;
import mx.pliis.afiliacion.dto.MontoCreditoDTO;
import mx.pliis.afiliacion.dto.NacionalidadDTO;
import mx.pliis.afiliacion.dto.PlanDTO;
import mx.pliis.afiliacion.dto.RelacionFamiliarDTO;
import mx.pliis.afiliacion.dto.SexoDTO;
import mx.pliis.afiliacion.dto.TipoArchivoDTO;
import mx.pliis.afiliacion.persistencia.hibernate.entity.EstadoCivilEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.EstatusCreditoEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.MontoCreditoEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.NacionalidadEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.PlanEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.RelacionFamiliarEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.SexoEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.TipoArchivoEntity;
import mx.pliis.afiliacion.persistencia.hibernate.repository.EstadoCivilEntityRepository;
import mx.pliis.afiliacion.persistencia.hibernate.repository.EstatusCreditoEntityRepository;
import mx.pliis.afiliacion.persistencia.hibernate.repository.MontoCreditoEntityRepository;
import mx.pliis.afiliacion.persistencia.hibernate.repository.NacionalidadEntityRepository;
import mx.pliis.afiliacion.persistencia.hibernate.repository.PlanEntityRepository;
import mx.pliis.afiliacion.persistencia.hibernate.repository.RelacionFamiliarEntityRepository;
import mx.pliis.afiliacion.persistencia.hibernate.repository.SexoEntityRepository;
import mx.pliis.afiliacion.persistencia.hibernate.repository.TipoArchivoEntityRepository;
import mx.pliis.afiliacion.utils.afiliacion.CatalogoUtil;
import mx.pliis.afiliacion.utils.credito.CreditoUtil;

@Service
public class CatalogoServiceImpl implements CatalogoService {
	@Autowired
	private SexoEntityRepository sexoRepository;
	@Autowired
	private EstadoCivilEntityRepository estadoCivilRepository;
	@Autowired
	private NacionalidadEntityRepository nacionalidadRepository;
	@Autowired
	private RelacionFamiliarEntityRepository relacionFamiliarEntityRepository;
	@Autowired
	private PlanEntityRepository planEntityRepository;
	@Autowired
	private MontoCreditoEntityRepository montoCreditoEntityRepository;
	@Autowired
	private TipoArchivoEntityRepository tipoArchivoEntityRepository;
	@Autowired
	private EstatusCreditoEntityRepository estatusCreditoEntityRepository;
	@Autowired
	private CatalogoUtil catalogoUtil;
	@Autowired
	private CreditoUtil creditoUtil;
	
	@Override
	@Transactional(readOnly = true)
	public List<SexoDTO> getAllSexos() {
		List<SexoDTO> listaRet = new ArrayList<>();
		List<SexoEntity> listaEnt = sexoRepository.findAll();

		catalogoUtil.copyFromSexoEntityListToDTO(listaEnt, listaRet);

		return listaRet;
	}

	@Override
	@Transactional(readOnly = true)
	public List<NacionalidadDTO> getAllNacionalidades() {
		List<NacionalidadDTO> listaRet = new ArrayList<>();
		List<NacionalidadEntity> listaEnt = nacionalidadRepository.findAll();

		catalogoUtil.copyFromNacionalidadEntityListToDTO(listaEnt, listaRet);

		return listaRet;
	}

	@Override
	@Transactional(readOnly = true)
	public List<EstadoCivilDTO> getAllEstadosCiviles() {
		List<EstadoCivilDTO> listaRet = new ArrayList<>();
		List<EstadoCivilEntity> listaEnt = estadoCivilRepository.findAll();

		catalogoUtil.copyFromEstadoCivilEntityListToDTO(listaEnt, listaRet);

		return listaRet;
	}

	@Override
	public List<RelacionFamiliarDTO> getAllRelacionesFamiliares(Boolean incluida) {
		List<RelacionFamiliarDTO> listaRet = new ArrayList<>();
		Optional<List<RelacionFamiliarEntity>> listaEnt = relacionFamiliarEntityRepository.findByIncluida(incluida);

		listaEnt.ifPresent(l -> {
			catalogoUtil.copyFromRelacionFamiliarEntityListToDTO(l, listaRet);
		});
//		

		return listaRet;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<PlanDTO> getAllPlanes() {
		List<PlanDTO> listaRet = new ArrayList<>();
		List<PlanEntity> listaEnt = planEntityRepository.findAll();

		catalogoUtil.copyFromPlanEntityListToDTO(listaEnt, listaRet);

		return listaRet;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MontoCreditoDTO> getAllMontosCredito() {
		List<MontoCreditoDTO> listaRet = new ArrayList<>();
		List<MontoCreditoEntity> listaEnt = montoCreditoEntityRepository.findAll();

		creditoUtil.copyFromMontoCreditoEntityListToDTO(listaEnt, listaRet);

		return listaRet;
	}

	@Override
	@Transactional(readOnly = true)
	public List<TipoArchivoDTO> getAllTipoArchivo() {
		List<TipoArchivoDTO> listaRet = new ArrayList<>();
		List<TipoArchivoEntity> listaEnt = tipoArchivoEntityRepository.findAll();

		creditoUtil.copyFromTipoArchivoEntityListToDTO(listaEnt, listaRet);

		return listaRet;
	}

	@Override
	@Transactional(readOnly = true)
	public List<EstatusCreditoDTO> getAllEstatusCredito() {
		List<EstatusCreditoDTO> listaRet = new ArrayList<>();
		List<EstatusCreditoEntity> listaEnt = estatusCreditoEntityRepository.findAll();

		creditoUtil.copyFromEstatusCreditoEntityListToDTO(listaEnt, listaRet);

		return listaRet;
	}
}
