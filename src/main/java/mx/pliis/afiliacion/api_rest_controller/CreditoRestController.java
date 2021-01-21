package mx.pliis.afiliacion.api_rest_controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import mx.pliis.afiliacion.dto.ArchivoCreditoDTO;
import mx.pliis.afiliacion.dto.ContactoCreditoDTO;
import mx.pliis.afiliacion.dto.MensajeDTO;
import mx.pliis.afiliacion.dto.ObservacionCreditoDTO;
import mx.pliis.afiliacion.dto.SolicitudCreditoDTO;
import mx.pliis.afiliacion.dto.SuscripcionDTO;
import mx.pliis.afiliacion.service.credito.CreditoService;
import mx.pliis.afiliacion.service.miembro.MiembroService;

@RestController
@RequestMapping("/credito")
@CrossOrigin(origins = "*")
@Log4j2
public class CreditoRestController {
	@Autowired
	private CreditoService creditoService;
	
	@PostMapping("/generarSolicitudCredito")
	public ResponseEntity<?> generarSolicitudCredito(@RequestBody @Valid SolicitudCreditoDTO solicitudCreditoDTO){
		Integer ret = Integer.MIN_VALUE;
		
		try {
			ret = creditoService.generarSolicitudCredito(solicitudCreditoDTO);
		} catch (Exception e) {
			MensajeDTO msg = new MensajeDTO("Ocurrió un error salvando la información: " + e.getLocalizedMessage());
			log.error(e.getLocalizedMessage());
			return new ResponseEntity<>(msg, HttpStatus.PRECONDITION_FAILED);
		}
		return new ResponseEntity<>(ret, HttpStatus.CREATED);
	}
	
	@PostMapping("/agregarContactoCredito")
	public ResponseEntity<?> agregarContactoCredito(@RequestBody @Valid ContactoCreditoDTO contactoCreditoDTO){
		Integer ret = Integer.MIN_VALUE;
		
		try {
			ret = creditoService.agregarContactoCredito(contactoCreditoDTO);
		} catch (Exception e) {
			MensajeDTO msg = new MensajeDTO("Ocurrió un error salvando la información: " + e.getLocalizedMessage());
			log.error(e.getLocalizedMessage());
			return new ResponseEntity<>(msg, HttpStatus.PRECONDITION_FAILED);
		}
		return new ResponseEntity<>(ret, HttpStatus.CREATED);
	}
	
	@PostMapping("/agregarArchivoCredito")
	public ResponseEntity<?> agregarArchivoCredito(@RequestBody @Valid ArchivoCreditoDTO archivoCreditoDTO){
		Integer ret = Integer.MIN_VALUE;
		
		try {
			ret = creditoService.agregarArchivoCredito(archivoCreditoDTO);
		} catch (Exception e) {
			MensajeDTO msg = new MensajeDTO("Ocurrió un error salvando la información: " + e.getLocalizedMessage());
			log.error(e.getLocalizedMessage());
			return new ResponseEntity<>(msg, HttpStatus.PRECONDITION_FAILED);
		}
		return new ResponseEntity<>(ret, HttpStatus.CREATED);
	}
	
	@GetMapping("/consultaSolicitudesCredito")
	public ResponseEntity<List<SolicitudCreditoDTO>> consultaSolicitudesCredito(
			@RequestParam("tipoUsuario") String tipoUsuario,
			@RequestParam("arqIdUsuario") Integer arqIdUsuario
		){
		var dtoList = creditoService.consultaSolicitudesCredito(tipoUsuario, arqIdUsuario);
		return new ResponseEntity<>(dtoList, HttpStatus.OK);
	}
	
	@PostMapping("/agregarObservacionCredito")
	public ResponseEntity<?> agregarObservacionCredito(@RequestBody @Valid ObservacionCreditoDTO observacionCreditoDTO){
		Integer ret = Integer.MIN_VALUE;
		try {
			ret = creditoService.agregarObservacionCredito(observacionCreditoDTO);
		} catch (Exception e) {
			MensajeDTO msg = new MensajeDTO("Ocurrió un error salvando la información: " + e.getLocalizedMessage());
			log.error(e.getLocalizedMessage());
			return new ResponseEntity<>(msg, HttpStatus.PRECONDITION_FAILED);
		}
		return new ResponseEntity<>(ret, HttpStatus.CREATED);
	}
	
	@PostMapping("/actualizaEstatusCredito")
	public ResponseEntity<?> actualizaEstatusCredito(
			@RequestParam("idCredito") Integer idCredito,
			@RequestParam("idEstatusCredito") Integer idEstatusCredito
	){
		SolicitudCreditoDTO solicitudCreditoDTO = null;
		try {
			solicitudCreditoDTO = creditoService.actualizaEstatusCredito(idCredito, idEstatusCredito);
		} catch (Exception e) {
			MensajeDTO msg = new MensajeDTO("Ocurrió un error salvando la información: " + e.getLocalizedMessage());
			log.error(e.getLocalizedMessage());
			return new ResponseEntity<>(msg, HttpStatus.PRECONDITION_FAILED);
		}
		return new ResponseEntity<>(solicitudCreditoDTO, HttpStatus.OK);
	}
	
	@PostMapping("/actualizaSolicitudCredito")
	public ResponseEntity<?> actualizaSolicitudCredito(@RequestBody @Valid SolicitudCreditoDTO solicitudCreditoDTO){
		try {
			solicitudCreditoDTO = creditoService.actualizaSolicitudCredito(solicitudCreditoDTO);
		} catch (Exception e) {
			MensajeDTO msg = new MensajeDTO("Ocurrió un error salvando la información: " + e.getLocalizedMessage());
			log.error(e.getLocalizedMessage());
			return new ResponseEntity<>(msg, HttpStatus.PRECONDITION_FAILED);
		}
		return new ResponseEntity<>(solicitudCreditoDTO, HttpStatus.OK);
	}
	
	@PostMapping("/actualizaContactoCredito")
	public ResponseEntity<?> actualizaContactoCredito(@RequestBody @Valid ContactoCreditoDTO contactoCreditoDTO){
		try {
			contactoCreditoDTO = creditoService.actualizaContactoCredito(contactoCreditoDTO);
		} catch (Exception e) {
			MensajeDTO msg = new MensajeDTO("Ocurrió un error salvando la información: " + e.getLocalizedMessage());
			log.error(e.getLocalizedMessage());
			return new ResponseEntity<>(msg, HttpStatus.PRECONDITION_FAILED);
		}
		return new ResponseEntity<>(contactoCreditoDTO, HttpStatus.OK);
	}
	
	@PostMapping("/actualizaArchivoCredito")
	public ResponseEntity<?> actualizaArchivoCredito(@RequestBody @Valid ArchivoCreditoDTO archivoCreditoDTO){
		try {
			archivoCreditoDTO = creditoService.actualizaArchivoCredito(archivoCreditoDTO);
		} catch (Exception e) {
			MensajeDTO msg = new MensajeDTO("Ocurrió un error salvando la información: " + e.getLocalizedMessage());
			log.error(e.getLocalizedMessage());
			return new ResponseEntity<>(msg, HttpStatus.PRECONDITION_FAILED);
		}
		return new ResponseEntity<>(archivoCreditoDTO, HttpStatus.OK);
	}
	
	@GetMapping("/consultaSolicitudesCreditoConcentrado")
	public ResponseEntity<List<SolicitudCreditoDTO>> consultaSolicitudesCreditoConcentrado(){
		var dtoList = creditoService.consultaSolicitudesCreditoConcentrado();
		return new ResponseEntity<>(dtoList, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteSolicitudCredito")
	public ResponseEntity<?> deleteSolicitudCredito(
			@RequestParam("idCredito") Integer idCredito
		) {
		boolean ret = false;
		try {
			ret = creditoService.deleteSolicitudCredito(idCredito);
		} catch (Exception e) {
			MensajeDTO msg = new MensajeDTO("Ocurrió un error borrando la solicitud de credito");
			log.error(e.getLocalizedMessage());
			return new ResponseEntity<>(msg, HttpStatus.PRECONDITION_FAILED);
		}
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteContactosCredito")
	public ResponseEntity<?> deleteContactosCredito(
			@RequestParam("idCredito") Integer idCredito
		) {
		boolean ret = false;
		try {
			ret = creditoService.deleteContactosCredito(idCredito);
		} catch (Exception e) {
			MensajeDTO msg = new MensajeDTO("Ocurrió un error borrando los contactos de la solicitud de credito");
			log.error(e.getLocalizedMessage());
			return new ResponseEntity<>(msg, HttpStatus.PRECONDITION_FAILED);
		}
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteArchivosCredito")
	public ResponseEntity<?> deleteArchivosCredito(
			@RequestParam("idCredito") Integer idCredito
		) {
		boolean ret = false;
		try {
			ret = creditoService.deleteArchivosCredito(idCredito);
		} catch (Exception e) {
			MensajeDTO msg = new MensajeDTO("Ocurrió un error borrando los archivos de la solicitud de credito");
			log.error(e.getLocalizedMessage());
			return new ResponseEntity<>(msg, HttpStatus.PRECONDITION_FAILED);
		}
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@PostMapping("/marcarComoEnviadasSolicitudesCredito")
	public ResponseEntity<?> marcarComoEnviadasSolicitudesCredito(
			@RequestParam("listasolicitudes") String listasolicitudes
		) {
		boolean ret = false;
		try {
			ret = creditoService.marcarComoEnviadasSolicitudesCredito(listasolicitudes);
		} catch (Exception e) {
			MensajeDTO msg = new MensajeDTO("Ocurrió un error borrando los archivos de la solicitud de credito");
			log.error(e.getLocalizedMessage());
			return new ResponseEntity<>(msg, HttpStatus.PRECONDITION_FAILED);
		}
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
}
