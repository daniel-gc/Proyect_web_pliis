package mx.pliis.afiliacion.api_rest_controller;

import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import mx.pliis.afiliacion.dto.MensajeDTO;
import mx.pliis.afiliacion.dto.NuevoAfiliadoDTO;
import mx.pliis.afiliacion.service.afiliado.AfiliadoService;
import reactor.core.publisher.Mono;
import mx.pliis.afiliacion.utils.afiliacion.Mensaje;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
@Log4j2
public class AfiliadoRestController {
	@Autowired
	private AfiliadoService afiliadoService;

	@PostMapping("/nuevo")
	public ResponseEntity<?> createAfiliado(@RequestBody @Valid NuevoAfiliadoDTO nuevoAfiliado) {
		Integer ret = Integer.MIN_VALUE;

		try {
			ret = afiliadoService.nuevoAfiliado(nuevoAfiliado);
		} catch (Exception e) {
			MensajeDTO msg = new MensajeDTO("Ocurrió un error salvando la información: " + e.getLocalizedMessage());
			log.error(e.getLocalizedMessage());
			return new ResponseEntity<>(msg, HttpStatus.PRECONDITION_FAILED);
		}

		return new ResponseEntity<>(ret, HttpStatus.CREATED);

	}

	/**
	 * Para invocarse
	 * http://<hostname>:<portnumber>/afiliado?idAfiliado=<idAfiliado>
	 * 
	 * @param idAfiliado
	 * @return
	 */

	@DeleteMapping()
	public ResponseEntity<?> deleteAfiliadoById(@RequestParam("idAfiliado") Integer idAfiliado) {
		boolean ret = false;

		try {
			ret = afiliadoService.deleteAfiliadoById(idAfiliado);
		} catch (Exception e) {
			MensajeDTO msg = new MensajeDTO("Ocurrió un error borrando el afiliado");
			log.error(e.getLocalizedMessage());
			return new ResponseEntity<>(msg, HttpStatus.PRECONDITION_FAILED);
		}

		return new ResponseEntity<>(ret, HttpStatus.OK);
	}

	@GetMapping(value = "/existeRfc")
	public ResponseEntity<?> existeAfiliadoByRfc(@RequestParam("rfc") String rfc) {
		boolean ret = false;

		try {
			ret = afiliadoService.existeAfiliadoByRfc(rfc);
		} catch (Exception e) {
			MensajeDTO msg = new MensajeDTO("Ocurrió un error al buscar el rfc del afiliado");
			log.error(e.getLocalizedMessage());
			return new ResponseEntity<>(msg, HttpStatus.PRECONDITION_FAILED);
		}

		return new ResponseEntity<>(ret, HttpStatus.OK);
	}

	@GetMapping(value = "/existeCurp")
	public ResponseEntity<?> existeAfiliadoByCurp(@RequestParam("curp") String curp) {
		boolean ret = false;

		try {
			ret = afiliadoService.existeAfiliadoByCurp(curp);
		} catch (Exception e) {
			MensajeDTO msg = new MensajeDTO("Ocurrió un error al buscar el curp del afiliado");
			log.error(e.getLocalizedMessage());
			return new ResponseEntity<>(msg, HttpStatus.PRECONDITION_FAILED);
		}

		return new ResponseEntity<>(ret, HttpStatus.OK);
	}

	@GetMapping(value = "/existeRedSocial")
	public ResponseEntity<Boolean> existeAfiliadoByRedesSociales(
			@RequestParam(value = "idFacebook", required = false) String idFacebook,
			@RequestParam(value = "idWhatsapp", required = false) String idWhatsapp) {
		boolean ret = false;
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpStatus status = HttpStatus.OK;

		if (idWhatsapp == null && idFacebook == null) {
			responseHeaders.set("Mensaje", "Ambos campos no pueden ser nulos");
			return new ResponseEntity<>(false, responseHeaders, HttpStatus.BAD_REQUEST);
		}

		Optional<String> idFacebookOpt = Optional.ofNullable(idFacebook);
		Optional<String> idWhatsappOpt = Optional.ofNullable(idWhatsapp);
		try {
			ret = afiliadoService.existeAfiliadoByRedesSociales(idFacebookOpt, idWhatsappOpt);
		} catch (Exception e) {
			responseHeaders.set("Mensaje", "Ocurrió un error al buscar la persona: " + e.getLocalizedMessage());
			log.error(e.getLocalizedMessage());
			return new ResponseEntity<>(false, responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(ret, status);
	}

	@GetMapping(value = "/afiliadoPorRedSocial")
	public ResponseEntity<NuevoAfiliadoDTO> getAfiliadoByRedesSociales(
			@RequestParam(value = "idFacebook", required = false) String idFacebook,
			@RequestParam(value = "idWhatsapp", required = false) String idWhatsapp) {
		NuevoAfiliadoDTO ret;
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpStatus status = HttpStatus.OK;

		if (idWhatsapp == null && idFacebook == null) {
			responseHeaders.set("Mensaje", "Ambos campos no pueden ser nulos");
			status = HttpStatus.BAD_REQUEST;
			return new ResponseEntity<>(null, responseHeaders, status);
		}

		Optional<String> idFacebookOpt = Optional.ofNullable(idFacebook);
		Optional<String> idWhatsappOpt = Optional.ofNullable(idWhatsapp);

		try {
			ret = afiliadoService.getAfiliadoByRedesSociales(idFacebookOpt, idWhatsappOpt);
		} catch (Exception e) {
			responseHeaders.set("Mensaje", "Ocurrió un error al buscar la persona: " + e.getLocalizedMessage());
			log.error(e.getLocalizedMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<>(null, responseHeaders, status);
		}

		return new ResponseEntity<>(ret, status);
	}

	@GetMapping(value = "/afiliadoPorIdAfiliado")
	public ResponseEntity<NuevoAfiliadoDTO> getAfiliadoByIdAfiliado(
			@NotNull @RequestParam(value = "idAfiliado", required = false) Integer idAfiliado) {
		NuevoAfiliadoDTO ret;

		ret = afiliadoService.getAfiliadoByIdAfiliado(idAfiliado);

		return new ResponseEntity<>(ret, HttpStatus.OK);
	}

	@GetMapping(value = "/idArq")
	public ResponseEntity<Integer> getIdArqByIdAfiliado(
			@RequestParam(name = "idAfiliado", required = true) Integer idAfiliado) {
		Integer ret;
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpStatus status = HttpStatus.OK;

		try {
			ret = afiliadoService.getIdArq(idAfiliado);
		} catch (Exception e) {
			responseHeaders.set("Mensaje",
					"Ocurrió un error al buscar el id de arquitectura: " + e.getLocalizedMessage());
			log.error(e.getLocalizedMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<>(null, responseHeaders, status);
		}

		return new ResponseEntity<>(ret, status);
	}

	@GetMapping("/borrame")
	public Mono<String> getEmployeeById() {
		return Mono.just("Hola mundo");
	}
	
	@GetMapping("/afiliadoPorArqIdUsuario")
	public ResponseEntity<NuevoAfiliadoDTO> getAfiliadoPorIdArq(
			@NotNull @RequestParam(value = "arqIdUsuario", required = false) Integer arqIdUsuario) {
		NuevoAfiliadoDTO ret =null;
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpStatus status = HttpStatus.OK;
		
		try {
			ret = afiliadoService.getAfiliadoPorIdArq(arqIdUsuario);
		} catch (Exception e) {
			responseHeaders.set("Mensaje",
					"Ocurrió un error al buscar el id de arquitectura: " + e.getLocalizedMessage());
			log.error(e.getLocalizedMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			return new ResponseEntity<>(null, responseHeaders, status);
		}

		return new ResponseEntity<>(ret, status);
	}
	
	@GetMapping("/afiliadoPorCurp")
	public ResponseEntity<?> getAfiliadoPorCurp(
			@NotNull @RequestParam(value = "curp") String curp) {
		NuevoAfiliadoDTO ret =null;
		HttpHeaders responseHeaders = new HttpHeaders();		
		try {
			ret = afiliadoService.getAfiliadoPorCurp(curp);
			
			if(ret != null) 
				return new ResponseEntity<>(ret, HttpStatus.OK);
			
			return new ResponseEntity<>(new Mensaje("No se encontro el afiliado por la curp ingresada"), HttpStatus.NOT_FOUND);
			

		} catch (Exception e) {
			responseHeaders.set("Mensaje",
					"Ocurrió un error al buscar el id de arquitectura: " + e.getLocalizedMessage());
			log.error(e.getLocalizedMessage());
			return new ResponseEntity<>(null, responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} 
	
}
