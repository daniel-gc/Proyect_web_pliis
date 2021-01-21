package mx.pliis.afiliacion.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ArchivoCreditoDTO {
	
	private Integer idArchivoCredito;
	private Integer idCredito;
	private String nombre;
	private String extencion;
	private byte[] archivo;
	private Integer idTipoArchivo;
	private TipoArchivoDTO tipoArchivoDTO;
}
