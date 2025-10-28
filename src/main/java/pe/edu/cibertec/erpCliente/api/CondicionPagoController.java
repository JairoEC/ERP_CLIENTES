package pe.edu.cibertec.erpCliente.api;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pe.edu.cibertec.erpCliente.api.request.CondicionPagoRequestDto;
import pe.edu.cibertec.erpCliente.api.response.CondicionPagoResponseDto;
import pe.edu.cibertec.erpCliente.service.CondicionPagoService;


@Slf4j
@RestController
@RequestMapping("/api/condiciones-pago")
@RequiredArgsConstructor
public class CondicionPagoController {
	
	private final CondicionPagoService service;
	
	//-- CREAR --
	@PostMapping
	public ResponseEntity<CondicionPagoResponseDto> crear(@Valid @RequestBody CondicionPagoRequestDto request,
			                                        UriComponentsBuilder uri) {
		log.info("POST /api/condiciones-pago");
		
		CondicionPagoResponseDto condicionPagoGuardadoDto = service.crear(request);
		URI location = uri.path("/api/condiciones-pago/{id}")
				          .buildAndExpand(condicionPagoGuardadoDto.getCondicionPagoId())
				          .toUri();
		return ResponseEntity.created(location).body(condicionPagoGuardadoDto);
	}
	
	//-- OBTENER POR ID --
	@GetMapping("/{id}")
	public ResponseEntity<CondicionPagoResponseDto> obtener(@PathVariable Short id) {
		log.info("GET /api/condiciones-pago/{}", id);
		CondicionPagoResponseDto condicionPagoBuscadoDto = service.obtener(id);
		return ResponseEntity.ok(condicionPagoBuscadoDto);
	}
	
	
	//-- LISTAR TODOS --
	@GetMapping
	public ResponseEntity<List<CondicionPagoResponseDto>> listar() {
		log.info("GET /api/condiciones-pago");
		List<CondicionPagoResponseDto> listaCondicionPagosDto = service.listar();
		return ResponseEntity.ok(listaCondicionPagosDto);
	}
	
	//-- ACTUALIZAR --
	
	@PutMapping("/{id}")
	public ResponseEntity<CondicionPagoResponseDto> actualizar(@PathVariable Short id,
			                              @Valid @RequestBody CondicionPagoRequestDto request) {
		log.info("PUT /api/condiciones-pago/{}", id);
		CondicionPagoResponseDto condicionPagoActualizadoDto = service.actualizar(id, request);
		return ResponseEntity.ok(condicionPagoActualizadoDto);
	}
	
	//-- ELIMINAR --
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Short id) {
		log.info("DELETE /api/condiciones-pago/{}", id);
		service.eliminar(id);
		return ResponseEntity.noContent().build();
	}

}
