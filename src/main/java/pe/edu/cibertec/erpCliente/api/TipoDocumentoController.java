package pe.edu.cibertec.erpCliente.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.erpCliente.api.request.ClienteRequestDto;
import pe.edu.cibertec.erpCliente.api.request.TipoDocumentoRequestDto;
import pe.edu.cibertec.erpCliente.api.response.ClienteResponseDto;
import pe.edu.cibertec.erpCliente.api.response.TipoDocumentoResponseDto;
import pe.edu.cibertec.erpCliente.service.TipoDocumentoService;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/tipodocumento")
@RequiredArgsConstructor
public class TipoDocumentoController {
    private final TipoDocumentoService service;

    @PostMapping
    public ResponseEntity<TipoDocumentoResponseDto> crear(@RequestBody TipoDocumentoRequestDto rq){
        TipoDocumentoResponseDto saved = service.crear(rq);
        return ResponseEntity
                .created(URI.create("/api/tipodocumento"+saved.getCodigo()))
                .body(saved);
    }
    @GetMapping("/{id}")
    public TipoDocumentoResponseDto obtener(@PathVariable Short id){
        return service.obtener(id);
    }

    @PutMapping("/{id}")
    public TipoDocumentoResponseDto actualizar(@PathVariable Short id,
                                         @Valid @RequestBody TipoDocumentoRequestDto request){
        return service.actualizar(id,request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Short id){
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<TipoDocumentoResponseDto> listar(){
        log.info("GET /api/clientes Listar clientes");
        return service.listar();
    }
}
