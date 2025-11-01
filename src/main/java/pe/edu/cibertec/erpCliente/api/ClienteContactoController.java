package pe.edu.cibertec.erpCliente.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pe.edu.cibertec.erpCliente.api.request.ClienteContactoRequestDto;
import pe.edu.cibertec.erpCliente.api.response.ClienteContactoResponseDto;
import pe.edu.cibertec.erpCliente.service.ClienteContactoService;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/clientes-contacto")
@RequiredArgsConstructor
public class ClienteContactoController {
    private final ClienteContactoService service;

    @PostMapping
    public ResponseEntity<ClienteContactoResponseDto> crear(
            @Valid @RequestBody ClienteContactoRequestDto request,
            UriComponentsBuilder uri) {

        log.info("POST /api/clientes-contacto");
        ClienteContactoResponseDto contactoGuardado = service.crear(request);

        URI location = uri.path("/api/clientes-contacto/{id}")
                .buildAndExpand(contactoGuardado.getId())
                .toUri();

        return ResponseEntity.created(location).body(contactoGuardado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteContactoResponseDto> obtener(@PathVariable Long id) {
        log.info("GET /api/clientes-contacto/{}", id);
        ClienteContactoResponseDto contacto = service.obtener(id);
        return ResponseEntity.ok(contacto);
    }

    @GetMapping
    public ResponseEntity<List<ClienteContactoResponseDto>> listar() {
        log.info("GET /api/clientes-contacto");
        List<ClienteContactoResponseDto> lista = service.listar();
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteContactoResponseDto> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ClienteContactoRequestDto request) {

        log.info("PUT /api/clientes-contacto/{}", id);
        ClienteContactoResponseDto actualizado = service.actualizar(id, request);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable Long id) {
        log.info("DELETE /api/clientes-contacto/{}", id);
        service.eliminar(id);
        
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Contacto eliminado correctamente");
        
        return ResponseEntity.ok(response);
    }

}
