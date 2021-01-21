package mx.pliis.afiliacion.service.afiliado;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mx.pliis.afiliacion.dto.NuevoAfiliadoDTO;
import mx.pliis.afiliacion.persistencia.hibernate.entity.AfiliadoEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.UsuarioEntity;
import mx.pliis.afiliacion.persistencia.hibernate.repository.AfiliadoEntityRepository;
import mx.pliis.afiliacion.persistencia.hibernate.repository.FamiliarEntityRepository;
import mx.pliis.afiliacion.persistencia.hibernate.repository.UsuarioEntityRepository;
import mx.pliis.afiliacion.persistencia.hibernate.repository.VinculoFamiliarEntityRepository;
import mx.pliis.afiliacion.service.miembro.MiembroServiceImpl;
import mx.pliis.afiliacion.utils.afiliacion.AfiliadoUtil;

@Service
@RequiredArgsConstructor
@Log4j2
public class AfiliadoServiceImpl implements AfiliadoService {
	@Autowired
	private AfiliadoUtil afiliadoUtil;
	@Autowired
	private AfiliadoEntityRepository afiliadoEntityRepository;
	@Autowired
	private UsuarioEntityRepository usuarioRepository;
	private final FamiliarEntityRepository familiarEntityRepository;
	private final VinculoFamiliarEntityRepository vinculoFamiliarEntityRepository;

	@Override
	@Transactional(readOnly = false)
	public Integer nuevoAfiliado(NuevoAfiliadoDTO nuevoAfiliadoDTO) {
		var afiliadoEntity = new AfiliadoEntity();
		var usuarioEntity = new UsuarioEntity();

		afiliadoUtil.copyFromDTOtoEntity(nuevoAfiliadoDTO, afiliadoEntity);

		usuarioEntity.setArqIdUsuario(nuevoAfiliadoDTO.getArqIdUsuario());
		usuarioEntity.setTelefono(nuevoAfiliadoDTO.getTelefono());
		usuarioEntity = usuarioRepository.save(usuarioEntity);

		afiliadoEntity.setIdUsuario(usuarioEntity);
		afiliadoEntity = afiliadoEntityRepository.save(afiliadoEntity);

		return afiliadoEntity.getIdAfiliado();
	}

	@Override
	@Transactional(readOnly = false)
	public boolean deleteAfiliadoById(Integer idAfiliado) {
		Optional<AfiliadoEntity> afiliadoOptional = afiliadoEntityRepository.findById(idAfiliado);

//		Consumer<? super AfiliadoEntity> actionDelete = val -> {
//			UsuarioEntity usuarioEntity = val.getIdUsuario();
//			afiliadoRepository.delete(val);
//			usuarioRepository.delete(usuarioEntity);
//		};
//		afiliadoOptional.ifPresent(actionDelete);

		if (afiliadoOptional.isPresent()) {
			UsuarioEntity usuarioEntity = afiliadoOptional.get().getIdUsuario();
			afiliadoEntityRepository.delete(afiliadoOptional.get());
			usuarioRepository.delete(usuarioEntity);
			return true;
		}
		return false;
	}

	@Override
	@Transactional(readOnly = true)
	public boolean existeAfiliadoByCurp(String curp) {
		return afiliadoEntityRepository.existsByCurp(curp);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean existeAfiliadoByRfc(String rfc) {
		return afiliadoEntityRepository.existsByRfc(rfc);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean existeAfiliadoByRedesSociales(Optional<String> idFacebookOpt, Optional<String> idWhatsappOpt) {

		return ((idFacebookOpt.isPresent() && afiliadoEntityRepository.existsByIdFacebook(idFacebookOpt.get()))
				|| (idWhatsappOpt.isPresent() && afiliadoEntityRepository.existsByIdWhatsapp(idWhatsappOpt.get())));

	}

	@Override
	@Transactional(readOnly = true)
	public NuevoAfiliadoDTO getAfiliadoByRedesSociales(Optional<String> idFacebookOpt, Optional<String> idWhatsappOpt) {
		if (idFacebookOpt.isPresent() && afiliadoEntityRepository.existsByIdFacebook(idFacebookOpt.get())) {
			Optional<AfiliadoEntity> optEnt = afiliadoEntityRepository.findByIdFacebook(idFacebookOpt.get());
			if (optEnt.isEmpty()) {
				return null;
			}
			AfiliadoEntity ent = optEnt.get();
			NuevoAfiliadoDTO dto = new NuevoAfiliadoDTO();
			afiliadoUtil.copyFromEntityToDTO(dto, ent);

			Optional<UsuarioEntity> us = usuarioRepository.findById(ent.getIdUsuario().getIdUsuario());
			if (us.isPresent()) {
				dto.setArqIdUsuario(us.get().getArqIdUsuario());
			}

			return dto;
		}

		if (idWhatsappOpt.isPresent() && afiliadoEntityRepository.existsByIdWhatsapp(idWhatsappOpt.get())) {
			Optional<AfiliadoEntity> optEnt = afiliadoEntityRepository.findByIdWhatsapp(idWhatsappOpt.get());
			if (optEnt.isEmpty()) {
				return null;
			}
			AfiliadoEntity ent = optEnt.get();
			NuevoAfiliadoDTO dto = new NuevoAfiliadoDTO();
			afiliadoUtil.copyFromEntityToDTO(dto, ent);

			Optional<UsuarioEntity> us = usuarioRepository.findById(ent.getIdUsuario().getIdUsuario());
			if (us.isPresent()) {
				dto.setArqIdUsuario(us.get().getArqIdUsuario());
			}
			return dto;
		}

		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Integer getIdArq(Integer idAfiliado) {
		return this.afiliadoEntityRepository.getIdArqByIdAfiliado(idAfiliado);
	}

	@Override
	@Transactional(readOnly = true)
	public NuevoAfiliadoDTO getAfiliadoByIdAfiliado(@NotNull Integer idAfiliado) {
		var afiliadoEntityOpt = this.afiliadoEntityRepository.findById(idAfiliado);

		if (afiliadoEntityOpt.isEmpty()) {
			throw new IllegalArgumentException("El afiliado especificado no existe");
		}
		var ent = afiliadoEntityOpt.get();
		var dto = new NuevoAfiliadoDTO();
		afiliadoUtil.copyFromEntityToDTO(dto, ent);
		return dto;
	}
	
	@Override
	@Transactional(readOnly = true)
	public NuevoAfiliadoDTO getAfiliadoPorIdArq(@NotNull Integer arqIdUsuario) {
		Optional<UsuarioEntity> optionalUsuarioEntity = usuarioRepository.findByArqIdUsuario(arqIdUsuario);
				
		if (optionalUsuarioEntity.isEmpty()) {
			log.error("El usuario {} no existe.", arqIdUsuario);
			throw new IllegalArgumentException("El usuario espeficado no existe.");
		}
		
		UsuarioEntity usuarioEntity = optionalUsuarioEntity.get();
		Optional<AfiliadoEntity> optionalAfiliadoEntity = afiliadoEntityRepository.findPorIdUsuarioArq(usuarioEntity.getArqIdUsuario());
		
		if (optionalAfiliadoEntity.isEmpty()) {
			log.error("El afiliado {} no existe.", usuarioEntity.getIdUsuario());
			throw new IllegalArgumentException("El afiliado espeficado no existe.");
		}
		
		AfiliadoEntity afiliadoEntity = optionalAfiliadoEntity.get();
		NuevoAfiliadoDTO nuevoAfiliadoDTO = new NuevoAfiliadoDTO();
		afiliadoUtil.copyFromEntityToDTO(nuevoAfiliadoDTO, afiliadoEntity);
		
		return nuevoAfiliadoDTO;
	}

	@Override
	@Transactional(readOnly = true)
	public NuevoAfiliadoDTO getAfiliadoPorCurp(@NotNull String curp) {

		var optionalAfiliado = afiliadoEntityRepository.findByCurp(curp);
		
		if(optionalAfiliado.isPresent()) {
			var afiliadoEntity = optionalAfiliado.get();
			NuevoAfiliadoDTO nuevoAfiliadoDTO = new NuevoAfiliadoDTO();
			afiliadoUtil.copyFromEntityToDTO(nuevoAfiliadoDTO, afiliadoEntity);
			return  nuevoAfiliadoDTO;
		}
		
		return null;
	}
}
