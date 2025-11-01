package pe.edu.cibertec.erpCliente.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pe.edu.cibertec.erpCliente.api.request.ClienteDireccionRequestDto;
import pe.edu.cibertec.erpCliente.api.response.ClienteDireccionResponseDto;
import pe.edu.cibertec.erpCliente.service.ClienteDireccionService;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/clientes-direccion")
@RequiredArgsConstructor
public class ClienteDireccionController {

    private final ClienteDireccionService service;

    @PostMapping
    public ResponseEntity<ClienteDireccionResponseDto> crear(
            @Valid @RequestBody ClienteDireccionRequestDto request,
            UriComponentsBuilder uri) {

        log.info("POST /api/clientes-direccion");
        ClienteDireccionResponseDto direccionGuardada = service.crear(request);

        URI location = uri.path("/api/clientes-direccion/{id}")
                .buildAndExpand(direccionGuardada.getId())
                .toUri();

        return ResponseEntity.created(location).body(direccionGuardada);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDireccionResponseDto> obtener(@PathVariable Long id) {
        log.info("GET /api/clientes-direccion/{}", id);
        ClienteDireccionResponseDto direccion = service.obtener(id);
        return ResponseEntity.ok(direccion);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDireccionResponseDto>> listar() {
        log.info("GET /api/clientes-direccion");
        List<ClienteDireccionResponseDto> lista = service.listar();
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDireccionResponseDto> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ClienteDireccionRequestDto request) {

        log.info("PUT /api/clientes-direccion/{}", id);
        ClienteDireccionResponseDto actualizado = service.actualizar(id, request);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable Long id) {
        log.info("DELETE /api/clientes-direccion/{}", id);
        service.eliminar(id);

        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Dirección eliminada correctamente");

        return ResponseEntity.ok(response);
    }
}
