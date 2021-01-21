package mx.pliis.afiliacion.api_rest_controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import mx.pliis.afiliacion.dto.EstadoCivilDTO;
import mx.pliis.afiliacion.dto.EstatusCreditoDTO;
import mx.pliis.afiliacion.dto.MensajeDTO;
import mx.pliis.afiliacion.dto.MontoCreditoDTO;
import mx.pliis.afiliacion.dto.NacionalidadDTO;
import mx.pliis.afiliacion.dto.PlanDTO;
import mx.pliis.afiliacion.dto.RelacionFamiliarDTO;
import mx.pliis.afiliacion.dto.SexoDTO;
import mx.pliis.afiliacion.dto.TipoArchivoDTO;
import mx.pliis.afiliacion.service.catalogo.CatalogoService;

@RestController
@RequestMapping("/catalogos")
@CrossOrigin(origins = "*")
@Log4j2
public class CatalogosRestController {
	@Autowired
	private CatalogoService catalogoService;

	@GetMapping("/sexos")
	public ResponseEntity<?> getAllSexos() {
		List<SexoDTO> sexosList = new ArrayList<>();

		try {
			sexosList = catalogoService.getAllSexos();
		} catch (Exception e) {
			MensajeDTO msg = new MensajeDTO(
					"Ocurrió un error recuperando la información de los catálogos: " + e.getLocalizedMessage());
			log.error(e.getLocalizedMessage());
			return new ResponseEntity<>(msg, HttpStatus.PRECONDITION_FAILED);
		}

		return new ResponseEntity<>(sexosList, HttpStatus.OK);

	}

	@GetMapping("/nacionalidades")
	public ResponseEntity<?> getAllNacionalidades() {
		List<NacionalidadDTO> retList = new ArrayList<>();

		try {
			retList = catalogoService.getAllNacionalidades();
		} catch (Exception e) {
			MensajeDTO msg = new MensajeDTO(
					"Ocurrió un error recuperando la información de los catálogos: " + e.getLocalizedMessage());
			log.error(e.getLocalizedMessage());
			return new ResponseEntity<>(msg, HttpStatus.PRECONDITION_FAILED);
		}

		return new ResponseEntity<>(retList, HttpStatus.OK);

	}

	@GetMapping("/estadosCiviles")
	public ResponseEntity<?> getAllEstadosCiviles() {
		List<EstadoCivilDTO> retList = new ArrayList<>();

		try {
			retList = catalogoService.getAllEstadosCiviles();
		} catch (Exception e) {
			MensajeDTO msg = new MensajeDTO(
					"Ocurrió un error recuperando la información de los catálogos: " + e.getLocalizedMessage());
			log.error(e.getLocalizedMessage());
			return new ResponseEntity<>(msg, HttpStatus.PRECONDITION_FAILED);
		}

		return new ResponseEntity<>(retList, HttpStatus.OK);

	}

	@GetMapping("/relacionesFamiliares")
	public ResponseEntity<List<RelacionFamiliarDTO>> getRelacionesFamiliares() {
		List<RelacionFamiliarDTO> retList = new ArrayList<>();

		try {
			retList = catalogoService.getAllRelacionesFamiliares(true);
		} catch (Exception e) {
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("Mensaje",
					"Ocurrió un error recuperando la información de los catálogos: " + e.getLocalizedMessage());
			log.error(e.getLocalizedMessage());
			return new ResponseEntity<>(new ArrayList<>(), responseHeaders,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(retList, HttpStatus.OK);

	}

	@RequestMapping(value = "/test2.xlsx", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	byte[] testDown() throws IOException, InvalidFormatException {
		Workbook workbook = new SXSSFWorkbook();
		Sheet sheet = workbook.createSheet();
		log.info("Comenzando a crear el excel");
		for (int i = 0; i < 200000; i++) {
			Row newRow = sheet.createRow(i);
			for (int j = 0; j < 100; j++) {
				newRow.createCell(j).setCellValue(Math.random());
			}
		}
		log.info("Terminado de crear el excel");
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		log.info("Inicio Write");
		workbook.write(os);
		log.info("Fin Write");
		byte[] bytes = os.toByteArray();
		return bytes;
	}
	
	@GetMapping("/planes")
	public ResponseEntity<?> getAllPlanes() {
		List<PlanDTO> planesList = new ArrayList<>();

		try {
			planesList = catalogoService.getAllPlanes();
		} catch (Exception e) {
			MensajeDTO msg = new MensajeDTO(
					"Ocurrió un error recuperando la información de los catálogos: " + e.getLocalizedMessage());
			log.error(e.getLocalizedMessage());
			return new ResponseEntity<>(msg, HttpStatus.PRECONDITION_FAILED);
		}
		
		return new ResponseEntity<>(planesList, HttpStatus.OK);
	}

	@GetMapping("/montosCredito")
	public ResponseEntity<?> getAllMontosCredito() {
		List<MontoCreditoDTO> montoCreditoList = new ArrayList<>();

		try {
			montoCreditoList = catalogoService.getAllMontosCredito();
		} catch (Exception e) {
			MensajeDTO msg = new MensajeDTO(
					"Ocurrió un error recuperando la información de los catálogos: " + e.getLocalizedMessage());
			log.error(e.getLocalizedMessage());
			return new ResponseEntity<>(msg, HttpStatus.PRECONDITION_FAILED);
		}
		
		return new ResponseEntity<>(montoCreditoList, HttpStatus.OK);
	}

	@GetMapping("/tipoArchivo")
	public ResponseEntity<?> getAllTipoArchivo() {
		List<TipoArchivoDTO> tipoArchivoList = new ArrayList<>();

		try {
			tipoArchivoList = catalogoService.getAllTipoArchivo();
		} catch (Exception e) {
			MensajeDTO msg = new MensajeDTO(
					"Ocurrió un error recuperando la información de los catálogos: " + e.getLocalizedMessage());
			log.error(e.getLocalizedMessage());
			return new ResponseEntity<>(msg, HttpStatus.PRECONDITION_FAILED);
		}
		
		return new ResponseEntity<>(tipoArchivoList, HttpStatus.OK);
	}

	@GetMapping("/estatusCredito")
	public ResponseEntity<?> getAllEstatusCredito() {
		List<EstatusCreditoDTO> estatusCreditoList = new ArrayList<>();

		try {
			estatusCreditoList = catalogoService.getAllEstatusCredito();
		} catch (Exception e) {
			MensajeDTO msg = new MensajeDTO(
					"Ocurrió un error recuperando la información de los catálogos: " + e.getLocalizedMessage());
			log.error(e.getLocalizedMessage());
			return new ResponseEntity<>(msg, HttpStatus.PRECONDITION_FAILED);
		}
		
		return new ResponseEntity<>(estatusCreditoList, HttpStatus.OK);
	}
}
