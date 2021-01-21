package mx.pliis.afiliacion.dto;

import lombok.Data;

@Data
public class FamiliarDTO {

	private Integer idVinculoFamiliar;
	private Integer idFamiliar;
	private String nombres;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private RelacionFamiliarDTO relacionFamiliarDTO;
	private String email;

}
