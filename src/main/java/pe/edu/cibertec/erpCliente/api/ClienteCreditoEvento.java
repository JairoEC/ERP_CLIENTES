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
import pe.edu.cibertec.erpCliente.api.request.ClienteCreditoEventoRequestDto;
import pe.edu.cibertec.erpCliente.api.response.ClienteCreditoEventoResponseDto;
import pe.edu.cibertec.erpCliente.service.ClienteCreditoEventoService;

@Slf4j
@RestController
@RequestMapping("/api/credito-eventos")
@RequiredArgsConstructor
public class ClienteCreditoEvento {
	
	private final ClienteCreditoEventoService service;
	
	//-- CREAR --
	@PostMapping
	public ResponseEntity<ClienteCreditoEventoResponseDto> crear(@Valid @RequestBody ClienteCreditoEventoRequestDto request,
			                                        UriComponentsBuilder uri) {
		log.info("POST /api/credito-eventos");
		
		ClienteCreditoEventoResponseDto eventoGuardadoDto = service.crear(request);
		URI location = uri.path("/api/credito-eventos/{id}")
				          .buildAndExpand(eventoGuardadoDto.getEventoId())
				          .toUri();
		return ResponseEntity.created(location).body(eventoGuardadoDto);
	}
	
	//-- OBTENER POR ID --
	@GetMapping("/{id}")
	public ResponseEntity<ClienteCreditoEventoResponseDto> obtener(@PathVariable Long id) {
		log.info("GET /api/credito-eventos/{}", id);
		ClienteCreditoEventoResponseDto eventoBuscadoDto = service.obtener(id);
		return ResponseEntity.ok(eventoBuscadoDto);
	}
	
	//-- LISTAR TODOS --
	
	@GetMapping
	public ResponseEntity<List<ClienteCreditoEventoResponseDto>> listar() {
		log.info("GET /api/credito-eventos");
		List<ClienteCreditoEventoResponseDto> listaEventosDto = service.listar();
		return ResponseEntity.ok(listaEventosDto);
	}
	
	// -- ACTUALIZAR --
	@PutMapping("/{id}")
	public ResponseEntity<ClienteCreditoEventoResponseDto> actualizar(@PathVariable Long id,
			@Valid @RequestBody ClienteCreditoEventoRequestDto request) {
		log.info("PUT /api/credito-eventos/{}", id);
		ClienteCreditoEventoResponseDto eventoActualizadoDto = service.actualizar(id, request);
		return ResponseEntity.ok(eventoActualizadoDto);
	}
	
	//-- ELIMINAR --
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Long id) {
		log.info("DELETE /api/credito-eventos/{}", id);
		service.eliminar(id);
		return ResponseEntity.noContent().build();
	}

}
