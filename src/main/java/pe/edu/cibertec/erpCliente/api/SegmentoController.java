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
import pe.edu.cibertec.erpCliente.api.request.SegmentoRequestDto;
import pe.edu.cibertec.erpCliente.api.response.SegmentoResponseDto;
import pe.edu.cibertec.erpCliente.service.SegmentoService;

@Slf4j
@RestController
@RequestMapping("/api/segmentos")
@RequiredArgsConstructor
public class SegmentoController {
	
	private final SegmentoService service;
	
	//-- CREAR --
	@PostMapping
	public ResponseEntity<SegmentoResponseDto> crear(@Valid @RequestBody SegmentoRequestDto request,
			                                        UriComponentsBuilder uri) {
		log.info("POST /api/segmentos");
		
		SegmentoResponseDto segmentoGuardadoDto = service.crear(request);
		URI location = uri.path("/api/segmentos/{id}")
				          .buildAndExpand(segmentoGuardadoDto.getSegmentoId())
				          .toUri();
		return ResponseEntity.created(location).body(segmentoGuardadoDto);
	}
	
	//-- OBTENER POR ID --
	@GetMapping("/{id}")
	public ResponseEntity<SegmentoResponseDto> obtener(@PathVariable Short id) {
		log.info("GET /api/segmentos/{}", id);
		SegmentoResponseDto segmentoBuscadoDto = service.obtener(id);
		return ResponseEntity.ok(segmentoBuscadoDto);
	}
	
	
	//-- LISTAR TODOS --
	@GetMapping
	public ResponseEntity<List<SegmentoResponseDto>> listar() {
		log.info("GET /api/segmentos");
		List<SegmentoResponseDto> listaSegmentosDto = service.listar();
		return ResponseEntity.ok(listaSegmentosDto);
	}
	
	//-- ACTUALIZAR --
	
	@PutMapping("/{id}")
	public ResponseEntity<SegmentoResponseDto> actualizar(@PathVariable Short id,
			                              @Valid @RequestBody SegmentoRequestDto request) {
		log.info("PUT /api/segmentos/{}", id);
		SegmentoResponseDto segmentoActualizadoDto = service.actualizar(id, request);
		return ResponseEntity.ok(segmentoActualizadoDto);
	}
	
	//-- ELIMINAR --
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Short id) {
		log.info("DELETE /api/segmentos/{}", id);
		service.eliminar(id);
		return ResponseEntity.noContent().build();
	}

}
