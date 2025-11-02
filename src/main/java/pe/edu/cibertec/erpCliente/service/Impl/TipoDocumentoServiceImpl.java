package pe.edu.cibertec.erpCliente.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.cibertec.erpCliente.api.request.TipoDocumentoRequestDto;
import pe.edu.cibertec.erpCliente.api.response.TipoDocumentoResponseDto;
import pe.edu.cibertec.erpCliente.entity.TipoDocumento;
import pe.edu.cibertec.erpCliente.exception.NotFoundException;
import pe.edu.cibertec.erpCliente.mapper.TipoDocumentoMapper;
import pe.edu.cibertec.erpCliente.repository.TipoDocumentoRepository;
import pe.edu.cibertec.erpCliente.service.TipoDocumentoService;

import java.net.http.HttpResponse;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class TipoDocumentoServiceImpl implements TipoDocumentoService {

    private final TipoDocumentoRepository tipoDocumentoRepository;
    private final TipoDocumentoMapper mapper;

    @Override
    public TipoDocumentoResponseDto crear(TipoDocumentoRequestDto request) {
        log.info("Creando Tipo de documento: {}",request.getNombre());
        TipoDocumento entity = mapper.toEntity(request);
        TipoDocumento saved = tipoDocumentoRepository.save(entity);
        log.info("Tipo de documento guardado id: {}",saved.getTipoDocumentoId());
        return mapper.toResponseDto(saved);
    }

    @Override
    public TipoDocumentoResponseDto actualizar(Short id, TipoDocumentoRequestDto request) {
        log.info("Actualizando tipo de documento id: {}",id);
        TipoDocumento entity = mapper.toEntity(request);
        entity.setTipoDocumentoId(id);
        TipoDocumento saved = tipoDocumentoRepository.save(entity);
        return mapper.toResponseDto(saved);
    }

    @Override
    public void eliminar(Short id) {
        tipoDocumentoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public TipoDocumentoResponseDto obtener(Short id) {
        TipoDocumento entity = tipoDocumentoRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Documento no encontrado id: "+id));
        return mapper.toResponseDto(entity);
    }

    @Override
    public List<TipoDocumentoResponseDto> listar() {
        return tipoDocumentoRepository.findAll().stream()
                .map(mapper::toResponseDto)
                .toList();
    }
}
