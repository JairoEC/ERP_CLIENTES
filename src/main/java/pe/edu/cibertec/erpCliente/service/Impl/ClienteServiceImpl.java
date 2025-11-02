package pe.edu.cibertec.erpCliente.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.cibertec.erpCliente.api.request.ClienteRequestDto;
import pe.edu.cibertec.erpCliente.api.response.ClienteResponseDto;
import pe.edu.cibertec.erpCliente.entity.Cliente;
import pe.edu.cibertec.erpCliente.entity.Segmento;
import pe.edu.cibertec.erpCliente.entity.TipoDocumento;
import pe.edu.cibertec.erpCliente.exception.BusinessException;
import pe.edu.cibertec.erpCliente.exception.NotFoundException;
import pe.edu.cibertec.erpCliente.mapper.ClienteMapper;
import pe.edu.cibertec.erpCliente.repository.ClienteRepository;
import pe.edu.cibertec.erpCliente.repository.CondicionPagoRepository;
import pe.edu.cibertec.erpCliente.repository.SegmentoRepository;
import pe.edu.cibertec.erpCliente.repository.TipoDocumentoRepository;
import pe.edu.cibertec.erpCliente.service.ClienteService;
import pe.edu.cibertec.erpCliente.service.SegmentoService;

import javax.swing.text.Segment;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final TipoDocumentoRepository tipoDocumentoRepository;
    private final SegmentoRepository segmentoRepository;
    private final CondicionPagoRepository condicionPagoRepository;
    private final ClienteMapper mapper;

    @Override
    public ClienteResponseDto crear(ClienteRequestDto request) {
        log.info("Creando cliente dni= {}", request.getNumeroDocumento());

        if(clienteRepository.existsByNumeroDocumento(request.getNumeroDocumento())){
            throw new BusinessException("Ya existe un cliente con el dni: "+request.getNumeroDocumento());
        }
        if(request.getSegmento() == null){
            throw new BusinessException("Debe de indicar un tipo de segmento");
        }
        Segmento segm = segmentoRepository.findById(request.getSegmento())
                .orElseThrow(()-> new NotFoundException("Tipo de segmento no encontrado: "+request.getSegmento()));
        Cliente entity = mapper.toEntity(request);
        LocalDateTime ahora = LocalDateTime.now();
        entity.setCreadoEn(ahora);
        entity.setUltimaActualizacion(ahora);
        entity.setActivo(true);
        entity.setBloqueadoCredito(false);
        entity.setSegmento(segm);

        Cliente saved = clienteRepository.save(entity);
        log.debug("Cliente creado id= {}",saved.getClienteId());
        return mapper.toResponseDto(saved);
    }

    @Override
    public ClienteResponseDto actualizar(Integer id,ClienteRequestDto request) {
        log.info("Actualizando cliente dni = {}",request.getNumeroDocumento());
        Cliente actual = clienteRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Cliente no encontrado id: {}"+id));
        mapper.updateEntityFromDto(request, actual);
        Cliente saved = clienteRepository.save(actual);
        return mapper.toResponseDto(saved);
    }

    @Override
    public void eliminar(Integer idCli) {
        log.info("Eliminando cliente id= {}",idCli);
        Cliente c = clienteRepository.findById(idCli)
                .orElseThrow(()-> new NotFoundException("Cliente no encontrado: "+idCli));
        clienteRepository.delete(c);
        log.debug("Cliente eliminado id= {}",idCli);
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteResponseDto obtener(Integer idCli) {
        Cliente c = clienteRepository.findById(idCli)
                .orElseThrow(()-> new NotFoundException("Cliente no encontrado id: "+idCli));
        return mapper.toResponseDto(c);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClienteResponseDto> listar() {
        return clienteRepository.findAll().stream()
                .map(mapper::toResponseDto)
                .toList();
    }
}
