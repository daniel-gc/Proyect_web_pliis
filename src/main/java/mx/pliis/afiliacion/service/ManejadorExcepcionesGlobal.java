package mx.pliis.afiliacion.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.log4j.Log4j2;
import mx.openpay.client.exceptions.OpenpayServiceException;
import mx.openpay.client.exceptions.ServiceUnavailableException;
import mx.pliis.afiliacion.service.excepciones.InvalidDataException;
import mx.pliis.afiliacion.service.excepciones.PaymentServiceException;

//@ControllerAdvice
@RestControllerAdvice
@Log4j2
public class ManejadorExcepcionesGlobal extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("LocalDateTime", LocalDateTime.now());
		body.put("Status", status.value());

		// Get all errors
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());

		headers.set("Mensaje", errors.toString());

		body.put("Mensaje", errors);

		return new ResponseEntity<>(null, headers, status);
	}

	@ExceptionHandler(value = IllegalArgumentException.class)
	protected ResponseEntity<Object> handleConflict(IllegalArgumentException ex, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("LocalDateTime", LocalDateTime.now());
		body.put("Mensaje", ex.getLocalizedMessage());

//		return new ResponseEntity<>(body, new HttpHeaders(), status);
		return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.CONFLICT, request);
//		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
	
	@ExceptionHandler(value = PaymentServiceException.class)
	protected ResponseEntity<Object> handleServiceException(final PaymentServiceException ex, final WebRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("LocalDateTime", LocalDateTime.now());
		body.put("Mensaje", ex.getLocalizedMessage());
		
		Map<String, Object> payErrors = new HashMap<>();
		if (ex.getCause() instanceof OpenpayServiceException) {
			OpenpayServiceException cause = (OpenpayServiceException) ex.getCause();
			log.info("Request ID with Exception {}", cause.getRequestId());
			
			payErrors.put("description", cause.getDescription());
			payErrors.put("error_code", cause.getErrorCode().toString());
			payErrors.put("category", cause.getCategory());
			payErrors.put("request_id", cause.getRequestId());
			body.put("PayErrors", payErrors);
		} else if(ex.getCause() instanceof ServiceUnavailableException) {
			ServiceUnavailableException cause = (ServiceUnavailableException) ex.getCause();
			payErrors.put("description", cause.getMessage());
			payErrors.put("sent", ex.isRequestSent());
			body.put("PayErros", payErrors);
		} else if(ex.getCause() instanceof InvalidDataException) {
			InvalidDataException cause = (InvalidDataException) ex.getCause();
			payErrors.put("description", cause.getMessage());
			status = HttpStatus.NOT_FOUND;
		} else {
			payErrors.put("description", ex.getMessage());
		}
		
		return handleExceptionInternal(ex, body, new HttpHeaders(), status, request);
	}

}
