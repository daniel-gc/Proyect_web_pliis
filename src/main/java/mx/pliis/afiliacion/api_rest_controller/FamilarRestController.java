package mx.pliis.afiliacion.api_rest_controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import mx.pliis.afiliacion.dto.FamiliarDTO;
import mx.pliis.afiliacion.dto.NuevoAfiliadoDTO;
import mx.pliis.afiliacion.dto.NuevoFamiliarDTO;
import mx.pliis.afiliacion.service.familiar.FamiliarService;

@RestController
@RequestMapping("/familiar")
@CrossOrigin(origins = "*")
@Log4j2
public class FamilarRestController {
	@Autowired
	private FamiliarService familiarService;

	@PostMapping(value = "/nuevo")
	public ResponseEntity<Integer> guardarNuevoFamiliar(@RequestBody @Valid NuevoFamiliarDTO nuevoFamiliarDTO) {
		Integer ret = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpStatus status = HttpStatus.OK;

		try {
			ret = familiarService.saveNuevoFamiliar(nuevoFamiliarDTO);
		} catch (Exception e) {
			responseHeaders.set("Mensaje", "Ocurrió un error al guardar el familiar: " + e.getLocalizedMessage());
			log.error(e.getLocalizedMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<>(null, responseHeaders, status);
		}

		return new ResponseEntity<>(ret, status);
	}

	@DeleteMapping
	public ResponseEntity<Boolean> deleteFamiliar(@RequestParam("idFamiliar") Integer idFamiliar) {
		Boolean ret = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpStatus status = HttpStatus.OK;

		try {
			ret = familiarService.deleteFamiliarById(idFamiliar);
		} catch (Exception e) {
			responseHeaders.set("Mensaje", "Ocurrió un error al borrar el familiar: " + e.getLocalizedMessage());
			log.error(e.getLocalizedMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<>(null, responseHeaders, status);
		}

		return new ResponseEntity<>(ret, status);
	}
	
	@GetMapping("/todos")
	public ResponseEntity<List<FamiliarDTO>> getFamiliares(@RequestParam("arqIdUsuario") Integer arqIdUsuario) {

		var dtoList = this.familiarService.getFamiliares(arqIdUsuario);

		return new ResponseEntity<>(dtoList, HttpStatus.OK);
	}

	@PutMapping("/cancelar")
	public ResponseEntity<Integer> cancelarVinculoFamiliar(
			@RequestParam("idVinculoFamiliar") Integer idVinculoFamiliar) {
		var ret = this.familiarService.cancelarVinculoFamiliar(idVinculoFamiliar);

		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@GetMapping("/afiliadoPorFamiliarIdArq")
	public ResponseEntity<NuevoAfiliadoDTO> getFamiliarPorIdArq(
			@NotNull @RequestParam(value = "arqIdUsuario", required = false) Integer arqIdUsuario) {
		NuevoAfiliadoDTO ret;
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpStatus status = HttpStatus.OK;
		
		try {
			FamiliarDTO familiarDTO = this.familiarService.getFamiliarPorIdArq(arqIdUsuario);
			ret = this.familiarService.getAfiliadoPorIdFamiliar(familiarDTO.getIdFamiliar());
		} catch (Exception e) {
			responseHeaders.set("Mensaje",
					"Ocurrió un error al buscar el id de arquitectura: " + e.getLocalizedMessage());
			log.error(e.getLocalizedMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<>(null, responseHeaders, status);
		}

		return new ResponseEntity<>(ret, status);
	}
}
