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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import mx.pliis.afiliacion.dto.MensajeDTO;
import mx.pliis.afiliacion.dto.NuevoMiembroDTO;
import mx.pliis.afiliacion.dto.SuscripcionDTO;
import mx.pliis.afiliacion.service.miembro.MiembroService;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
@Log4j2
public class MiembroRestController {
	@Autowired
	private MiembroService miembroService;
	
	@PostMapping("/nuevoMiembro")
	public ResponseEntity<?> createMiembro(@RequestBody @Valid NuevoMiembroDTO nuevoMiembroDTO){
		Integer ret = Integer.MIN_VALUE;
		
		try {
			ret = miembroService.nuevoMiembro(nuevoMiembroDTO);
		} catch (Exception e) {
			MensajeDTO msg = new MensajeDTO("Ocurrió un error salvando la información: " + e.getLocalizedMessage());
			log.error(e.getLocalizedMessage());
			return new ResponseEntity<>(msg, HttpStatus.PRECONDITION_FAILED);
		}
		return new ResponseEntity<>(ret, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deleteMiembro")
	public ResponseEntity<?> deleteMiembroById(@RequestParam("idMiembro") Integer idMiembro) {
		boolean ret = false;
		
		try {
			ret = miembroService.deleteMiembroById(idMiembro);
		} catch (Exception e) {
			MensajeDTO msg = new MensajeDTO("Ocurrió un error borrando al miembro");
			log.error(e.getLocalizedMessage());
			return new ResponseEntity<>(msg, HttpStatus.PRECONDITION_FAILED);
		}

		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@PostMapping("/generaSuscripcion")
	public ResponseEntity<?> generaSuscripcion(
			@RequestParam("arqIdUsuario") Integer arqIdUsuario,
			@RequestParam("idPlan") Integer idPlan,
			@RequestParam("idPago") Integer idPago
		) {
		boolean ret = false;
		try {
			ret = miembroService.generaSuscripcion(arqIdUsuario, idPlan, idPago);
		} catch (Exception e) {
			MensajeDTO msg = new MensajeDTO("Ocurrió un error salvando la información: " + e.getLocalizedMessage());
			log.error(e.getLocalizedMessage());
			return new ResponseEntity<>(msg, HttpStatus.PRECONDITION_FAILED);
		}

		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteSuscripcion")
	public ResponseEntity<?> deleteSuscripcion(
			@RequestParam("arqIdUsuario") Integer arqIdUsuario,
			@RequestParam("idPlan") Integer idPlan,
			@RequestParam("idPago") Integer idPago
		) {
		boolean ret = false;
		try {
			ret = miembroService.deleteSuscripcion(arqIdUsuario, idPlan, idPago);
		} catch (Exception e) {
			MensajeDTO msg = new MensajeDTO("Ocurrió un error borrando la suscripcion");
			log.error(e.getLocalizedMessage());
			return new ResponseEntity<>(msg, HttpStatus.PRECONDITION_FAILED);
		}

		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@GetMapping("/getHistorialPagos")
	public ResponseEntity<List<SuscripcionDTO>> getHistorialPagos(
			@RequestParam("arqIdUsuario") Integer arqIdUsuario
		){
		var dtoList = miembroService.getHistorialPagos(arqIdUsuario);
		return new ResponseEntity<>(dtoList, HttpStatus.OK);
	}
	
	@PostMapping("/validarSuscripciones")
	public ResponseEntity<?> validarSuscripciones(){
		try {
			var res = miembroService.validarSuscripciones();
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			MensajeDTO msg = new MensajeDTO("Ocurrió un error desactivando las suscripciones del dia");
			log.error(e.getLocalizedMessage());
			return new ResponseEntity<>(msg, HttpStatus.PRECONDITION_FAILED);
		}
	}
	
	@GetMapping("/validarSuscripcionActiva")
	public ResponseEntity<?> validarSuscripcionActiva(
			@RequestParam("arqIdUsuario") Integer arqIdUsuario
	){
		var suscripcionDTO = miembroService.validarSuscripcionActiva(arqIdUsuario);
		return new ResponseEntity<>(suscripcionDTO, HttpStatus.OK);
	}
	
	@GetMapping("/miembroPorArqIdUsuario")
	public ResponseEntity<NuevoMiembroDTO> getMiembroPorIdArq(
			@NotNull @RequestParam(value = "arqIdUsuario", required = false) Integer arqIdUsuario) {
		NuevoMiembroDTO ret;
		HttpHeaders responseHeaders = new HttpHeaders();
		HttpStatus status = HttpStatus.OK;
		
		try {
			ret = miembroService.getMiembroPorIdArq(arqIdUsuario);
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
