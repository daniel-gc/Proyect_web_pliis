package mx.pliis.afiliacion.service.familiar;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mx.pliis.afiliacion.dto.FamiliarDTO;
import mx.pliis.afiliacion.dto.NuevoAfiliadoDTO;
import mx.pliis.afiliacion.dto.NuevoFamiliarDTO;
import mx.pliis.afiliacion.persistencia.hibernate.entity.AfiliadoEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.FamiliarEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.RelacionFamiliarEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.SexoEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.UsuarioEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.VinculoFamiliarEntity;
import mx.pliis.afiliacion.persistencia.hibernate.repository.AfiliadoEntityRepository;
import mx.pliis.afiliacion.persistencia.hibernate.repository.FamiliarEntityRepository;
import mx.pliis.afiliacion.persistencia.hibernate.repository.RelacionFamiliarEntityRepository;
import mx.pliis.afiliacion.persistencia.hibernate.repository.SexoEntityRepository;
import mx.pliis.afiliacion.persistencia.hibernate.repository.UsuarioEntityRepository;
import mx.pliis.afiliacion.persistencia.hibernate.repository.VinculoFamiliarEntityRepository;
import mx.pliis.afiliacion.utils.afiliacion.AfiliadoUtil;
import mx.pliis.afiliacion.utils.familiar.FamiliarUtilComponent;

@Service
@RequiredArgsConstructor
@Log4j2
public class FamiliarServiceImpl implements FamiliarService {

	private final SexoEntityRepository sexoEntityRepository;
	private final RelacionFamiliarEntityRepository relacionFamiliarEntityRepository;
	private final AfiliadoEntityRepository afiliadoEntityRepository;
	private final UsuarioEntityRepository usuarioEntityRepository;
	private final FamiliarEntityRepository familiarEntityRepository;
	private final VinculoFamiliarEntityRepository vinculoFamiliarEntityRepository;
	private final FamiliarUtilComponent familiarUtilComponent;
	@Autowired
	private AfiliadoUtil afiliadoUtil;
	
	@Override
	@Transactional(readOnly = false)
	public Integer saveNuevoFamiliar(NuevoFamiliarDTO nuevoFamiliarDTO) {
		FamiliarEntity familiarEntity = copyDTOToEntity(nuevoFamiliarDTO);

		if (sexoEntityRepository.findById(nuevoFamiliarDTO.getIdSexo()).isPresent()) {
			SexoEntity sexoEntity = sexoEntityRepository.findById(nuevoFamiliarDTO.getIdSexo()).get();
			familiarEntity.setIdSexo(sexoEntity);
		} else {
			return -1;
		}

		RelacionFamiliarEntity relacionFamiliarEntity = null;
		if (relacionFamiliarEntityRepository.findById(nuevoFamiliarDTO.getIdRelacionFamiliar()).isPresent()) {
			relacionFamiliarEntity = relacionFamiliarEntityRepository.findById(nuevoFamiliarDTO.getIdRelacionFamiliar())
					.get();
		} else {
			return -1;
		}

		AfiliadoEntity afiliadoEntity = null;
		if (afiliadoEntityRepository.findById(nuevoFamiliarDTO.getIdAfiliado()).isPresent()) {
			afiliadoEntity = afiliadoEntityRepository.findById(nuevoFamiliarDTO.getIdAfiliado()).get();
		} else {
			return -1;
		}
		
		
		UsuarioEntity usuarioEntity = new UsuarioEntity();
		usuarioEntity.setNombres(nuevoFamiliarDTO.getApellidoPaterno() + nuevoFamiliarDTO.getApellidoPaterno()
				+ nuevoFamiliarDTO.getNombres());
		usuarioEntity.setArqIdUsuario(nuevoFamiliarDTO.getArqIdUsuario());
		usuarioEntity.setTelefono(nuevoFamiliarDTO.getTelefono());
		usuarioEntity.setActivo(true);
		usuarioEntity = usuarioEntityRepository.save(usuarioEntity);

		familiarEntity.setIdUsuario(usuarioEntity);
		familiarEntity = familiarEntityRepository.save(familiarEntity);

		VinculoFamiliarEntity vinculoFamiliarEntity = new VinculoFamiliarEntity();
		vinculoFamiliarEntity.setActivo(true);
		vinculoFamiliarEntity.setIdAfiliado(afiliadoEntity);
		vinculoFamiliarEntity.setIdFamiliar(familiarEntity);
		vinculoFamiliarEntity.setIdRelacionFamiliar(relacionFamiliarEntity);

//		TODO: Verificar cantidades de familiares permitidos por relación familiar
		vinculoFamiliarEntity = vinculoFamiliarEntityRepository.save(vinculoFamiliarEntity);

		return familiarEntity.getIdFamiliar();
	}

	private FamiliarEntity copyDTOToEntity(NuevoFamiliarDTO dto) {
		FamiliarEntity ent = new FamiliarEntity();

		ent.setApellidoMaterno(dto.getApellidoMaterno());
		ent.setApellidoPaterno(dto.getApellidoPaterno());
		ent.setEmail(dto.getEmail());
		ent.setFechaNacimiento(dto.getFechaNacimiento());
		ent.setNombres(dto.getNombres());

		return ent;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean deleteFamiliarById(Integer idFamiliar) {
		Optional<FamiliarEntity> familiarOptional = familiarEntityRepository.findById(idFamiliar);

		if (familiarOptional.isPresent()) {
			Optional<List<VinculoFamiliarEntity>> vinculosOptionalList = vinculoFamiliarEntityRepository
					.findByIdFamiliar(familiarOptional.get());
			vinculosOptionalList.ifPresent(vinculos -> {
				vinculos.forEach(vinculo -> {
					vinculoFamiliarEntityRepository.delete(vinculo);
				});
			});

			UsuarioEntity usuarioEntity = familiarOptional.get().getIdUsuario();
			familiarEntityRepository.delete(familiarOptional.get());
			usuarioEntityRepository.delete(usuarioEntity);

			return true;
		}
		return false;
	}

	@Override
	@Transactional(readOnly = true)
	public List<FamiliarDTO> getFamiliares(Integer arqIdUsuario) {
		var usEntOpt = this.usuarioEntityRepository.findByArqIdUsuario(arqIdUsuario);
		if (usEntOpt.isEmpty()) {
			log.error("El usuario con arqIdUsuario = {} no existe en la BD de afiliados.", arqIdUsuario);
			throw new IllegalArgumentException("El usuario especificado no existe en la BD de afiliados.");
		}
		var usuarioEntity = usEntOpt.get();

		var afiliadoEntityOpt = this.afiliadoEntityRepository.findByIdUsuario(usuarioEntity);
		if (afiliadoEntityOpt.isEmpty()) {
			log.error("El usuario con arqIdUsuario = {} no es un afiliado.", arqIdUsuario);
			throw new IllegalArgumentException("El usuario especificado no es un afiliado.");
		}

		var afiliadoEntity = afiliadoEntityOpt.get();

		var vinculosFamiliaresOpt = this.vinculoFamiliarEntityRepository
				.findByIdAfiliadoAndActivo(afiliadoEntity, Boolean.TRUE);

		if (vinculosFamiliaresOpt.isEmpty()) {
			return new ArrayList<>();
		}

		var vinculosFamiliares = vinculosFamiliaresOpt.get();
		var familiaresDTO = this.familiarUtilComponent.copyFromEntityList(vinculosFamiliares);

		return familiaresDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public Integer cancelarVinculoFamiliar(Integer idVinculoFamiliar) {
		var vinculoFamiliarEntityOpt = this.vinculoFamiliarEntityRepository.findById(idVinculoFamiliar);
		if (vinculoFamiliarEntityOpt.isEmpty()) {
			log.error("El vínculo familiar {} no existe.", idVinculoFamiliar);
			throw new IllegalArgumentException("El vínculo familiar espeficado no existe.");
		}

		var vinculoFamiliarEntity = vinculoFamiliarEntityOpt.get();

		vinculoFamiliarEntity.setActivo(Boolean.FALSE);

		vinculoFamiliarEntity.getIdFamiliar().getIdUsuario().setActivo(Boolean.FALSE);

		this.vinculoFamiliarEntityRepository.save(vinculoFamiliarEntity);

		return vinculoFamiliarEntity.getIdFamiliar().getIdUsuario().getArqIdUsuario();
	}
	
	@Override
	@Transactional(readOnly = false)
	public FamiliarDTO getFamiliarPorIdArq(@NotNull Integer arqIdUsuario) {
		Optional<UsuarioEntity> optionalUsuarioEntity = usuarioEntityRepository.findByArqIdUsuario(arqIdUsuario);
		
		if (optionalUsuarioEntity.isEmpty()) {
			log.error("El usuario {} no existe.", arqIdUsuario);
			throw new IllegalArgumentException("El usuario espeficado no existe.");
		}
		
		UsuarioEntity usuarioEntity = optionalUsuarioEntity.get();
		Optional<FamiliarEntity> optionalFamiliarEntity = familiarEntityRepository.findByIdUsuario(usuarioEntity);
		
		if (optionalFamiliarEntity.isEmpty()) {
			log.error("El afiliado {} no existe.", usuarioEntity.getIdUsuario());
			throw new IllegalArgumentException("El afiliado espeficado no existe.");
		}
		
		FamiliarEntity familiarEntity = optionalFamiliarEntity.get();
		FamiliarDTO familiarDTO = familiarUtilComponent.copyFromFamEntity(familiarEntity);
		
		return familiarDTO;
	}
	
	@Override
	@Transactional(readOnly = false)
	public NuevoAfiliadoDTO getAfiliadoPorIdFamiliar(@NotNull Integer idFamiliar) {
		//Optional<UsuarioEntity> optionalUsuarioEntity = 
		Optional<FamiliarEntity> optionalFamiliarEntity = familiarEntityRepository.findByIdFamiliar(idFamiliar);
		
		if (optionalFamiliarEntity.isEmpty()) {
			log.error("El familiar {} no existe.", idFamiliar);
			throw new IllegalArgumentException("El familiar espeficado no existe.");
		}
//		
//		UsuarioEntity usuarioEntity = optionalUsuarioEntity.get();
		FamiliarEntity familiarEntity =  optionalFamiliarEntity.get();
		Optional<List<VinculoFamiliarEntity>> optionalListVinculoFamiliarEntity = vinculoFamiliarEntityRepository.findByIdFamiliar(familiarEntity);
		
		if (optionalFamiliarEntity.isEmpty()) {
			log.error("No se encontro un vinculo con el familiar {}.", idFamiliar);
			throw new IllegalArgumentException("El vinculo con el familiar espeficado no existe.");
		}
		
		List<VinculoFamiliarEntity> listVinculoFamiliarEntity = optionalListVinculoFamiliarEntity.get();  
		
		if (!(listVinculoFamiliarEntity.size() > 0)) {
			log.error("No se encontro un vinculo con el familiar {}.", idFamiliar);
			throw new IllegalArgumentException("El vinculo con el familiar espeficado no existe.");
		}
		
		
		AfiliadoEntity afiliadoEntity = listVinculoFamiliarEntity.get(0).getIdAfiliado();
		NuevoAfiliadoDTO nuevoAfiliadoDTO = new NuevoAfiliadoDTO();
		afiliadoUtil.copyFromEntityToDTO(nuevoAfiliadoDTO, afiliadoEntity);
		
		return nuevoAfiliadoDTO;
	}
}
