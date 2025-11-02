package pe.edu.cibertec.erpCliente.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.sql.Delete;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.erpCliente.api.request.ClienteRequestDto;
import pe.edu.cibertec.erpCliente.api.response.ClienteResponseDto;
import pe.edu.cibertec.erpCliente.entity.Cliente;
import pe.edu.cibertec.erpCliente.service.ClienteService;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService service;

    @PostMapping
    public ResponseEntity<ClienteResponseDto> crear(@Valid @RequestBody ClienteRequestDto request){
        //log.info("POST /api/clientes");
        ClienteResponseDto saved = service.crear(request);
        return ResponseEntity
                .created(URI.create("/api/clientes/"+saved.getClienteId()))
                .body(saved);
    }
    @GetMapping("/{id}")
    public ClienteResponseDto obtener(@PathVariable Integer id){
        return service.obtener(id);
    }

    @PutMapping("/{id}")
    public ClienteResponseDto actualizar(@PathVariable Integer id,
                                               @Valid @RequestBody ClienteRequestDto request){
        return service.actualizar(id,request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id){
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<ClienteResponseDto> listar(){
        log.info("GET /api/clientes Listar clientes");
        return service.listar();
    }
}
