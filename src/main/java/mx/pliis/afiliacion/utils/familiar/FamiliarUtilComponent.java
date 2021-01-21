package mx.pliis.afiliacion.utils.familiar;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mx.pliis.afiliacion.dto.FamiliarDTO;
import mx.pliis.afiliacion.dto.RelacionFamiliarDTO;
import mx.pliis.afiliacion.persistencia.hibernate.entity.FamiliarEntity;
import mx.pliis.afiliacion.persistencia.hibernate.entity.VinculoFamiliarEntity;

@Component
@Log4j2
@RequiredArgsConstructor
public class FamiliarUtilComponent {
	
	public FamiliarDTO copyFromFamEntity(FamiliarEntity ent) {
		FamiliarDTO dto = new FamiliarDTO();

		dto.setIdFamiliar(ent.getIdFamiliar());
		dto.setNombres(ent.getNombres());
		dto.setIdFamiliar(ent.getIdFamiliar());
		dto.setApellidoMaterno(ent.getApellidoMaterno());
		dto.setApellidoPaterno(ent.getApellidoPaterno());
		dto.setEmail(ent.getEmail());
		
		return dto;
	}
	
	public FamiliarDTO copyFromEntity(VinculoFamiliarEntity ent) {
		FamiliarDTO dto = new FamiliarDTO();

		dto.setIdVinculoFamiliar(ent.getIdVinculoFamiiliar());
		dto.setNombres(ent.getIdFamiliar().getNombres());
		dto.setIdFamiliar(ent.getIdFamiliar().getIdFamiliar());
		dto.setApellidoMaterno(ent.getIdFamiliar().getApellidoMaterno());
		dto.setApellidoPaterno(ent.getIdFamiliar().getApellidoPaterno());
		dto.setEmail(ent.getIdFamiliar().getEmail());

		RelacionFamiliarDTO relacionFamiliarDTO = new RelacionFamiliarDTO();
		relacionFamiliarDTO.setIdRelacionFamiliar(ent.getIdRelacionFamiliar().getIdRelacionFamiliar());
		relacionFamiliarDTO.setNombre(ent.getIdRelacionFamiliar().getNombre());

		dto.setRelacionFamiliarDTO(relacionFamiliarDTO);

		return dto;
	}

	public List<FamiliarDTO> copyFromEntityList(List<VinculoFamiliarEntity> entList) {
		var dtoList = new ArrayList<FamiliarDTO>();

		for (VinculoFamiliarEntity ent : entList) {
			var dto = this.copyFromEntity(ent);
			dtoList.add(dto);
		}

		return dtoList;
	}

}
