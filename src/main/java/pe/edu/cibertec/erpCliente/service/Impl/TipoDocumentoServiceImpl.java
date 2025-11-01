package pe.edu.cibertec.erpCliente.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.cibertec.erpCliente.api.request.TipoDocumentoRequestDto;
import pe.edu.cibertec.erpCliente.api.response.TipoDocumentoResponseDto;
import pe.edu.cibertec.erpCliente.entity.TipoDocumento;
import pe.edu.cibertec.erpCliente.mapper.TipoDocumentoMapper;
import pe.edu.cibertec.erpCliente.repository.TipoDocumentoRepository;
import pe.edu.cibertec.erpCliente.service.TipoDocumentoService;

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

        return null;
    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public TipoDocumentoResponseDto obtener(Short id) {
        return null;
    }

    @Override
    public TipoDocumentoResponseDto listar() {
        return null;
    }
}
