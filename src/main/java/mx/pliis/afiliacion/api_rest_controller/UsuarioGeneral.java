package mx.pliis.afiliacion.api_rest_controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/general")
@CrossOrigin(origins = "*")
@Log4j2
public class UsuarioGeneral {

	@PutMapping("cambiar_passw")
	public ResponseEntity<Boolean> cambiarContrasena() {
		return null;

	}

}
