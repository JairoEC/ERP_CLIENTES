package pe.edu.cibertec.erpCliente.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import pe.edu.cibertec.erpCliente.api.request.ClienteDireccionRequestDto;
import pe.edu.cibertec.erpCliente.api.response.ClienteDireccionResponseDto;
import pe.edu.cibertec.erpCliente.entity.Cliente;
import pe.edu.cibertec.erpCliente.entity.ClienteDireccion;
import pe.edu.cibertec.erpCliente.exception.BusinessException;
import pe.edu.cibertec.erpCliente.mapper.ClienteDireccionMapper;
import pe.edu.cibertec.erpCliente.repository.ClienteDireccionRepository;
import pe.edu.cibertec.erpCliente.repository.ClienteRepository;
import pe.edu.cibertec.erpCliente.service.ClienteDireccionService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ClienteDireccionServiceImpl implements ClienteDireccionService {

    private final ClienteDireccionRepository clienteDireccionRepo;
    private final ClienteRepository clienteRepo;
    private final ClienteDireccionMapper mapper;
    private final EntityManager entityManager;
    
    @Override
    public ClienteDireccionResponseDto crear(ClienteDireccionRequestDto request) {
        log.info("Creando dirección para clienteId={}", request.getClienteId());

        Cliente cliente = clienteRepo.findById(request.getClienteId())
				.orElseThrow(() -> new BusinessException("Cliente no encontrado con id: " + request.getClienteId()));
        
       
        ClienteDireccion direccion = mapper.toEntity(request);
        
        direccion.setCliente(cliente);
        direccion.setActivo(true); 

     
        ClienteDireccion guardado = clienteDireccionRepo.save(direccion);
        log.debug("Dirección creada id={}", guardado.getId());

        return mapper.toResponseDto(guardado);
    }

    @Override
    public ClienteDireccionResponseDto actualizar(Long id, ClienteDireccionRequestDto request) {
        log.info("Actualizando dirección id={}", id);

        ClienteDireccion direccionActual = clienteDireccionRepo.findById(id)
                .orElseThrow(() -> new BusinessException("Dirección no encontrada con id: " + id));

        
        mapper.updateEntityFromDto(request, direccionActual);

        ClienteDireccion actualizado = clienteDireccionRepo.save(direccionActual);
        log.debug("Dirección actualizada id={}", actualizado.getId());
        entityManager.flush();
        entityManager.refresh(actualizado);
                
        return mapper.toResponseDto(actualizado);
        
    }

    //--  ELIMINAR  --//
    @Override
    public void eliminar(Long id) {
        log.info("Eliminando dirección id={}", id);

        ClienteDireccion direccion = clienteDireccionRepo.findById(id)
                .orElseThrow(() -> new BusinessException("Dirección no encontrada con id: " + id));

        clienteDireccionRepo.delete(direccion);
    }

    //--  OBTENER  --//
    @Override
    @Transactional(readOnly = true)
    public ClienteDireccionResponseDto obtener(Long id) {
        log.info("Obteniendo dirección id={}", id);

        ClienteDireccion direccion = clienteDireccionRepo.findById(id)
                .orElseThrow(() -> new BusinessException("Dirección no encontrada con id: " + id));

        return mapper.toResponseDto(direccion);
    }

    //-- LISTAR POR CLIENTE --//
    @Override
    @Transactional(readOnly = true)
    public List<ClienteDireccionResponseDto> listarPorCliente(Long clienteId) {
        log.info("Listando direcciones por clienteId={}", clienteId);
        List<ClienteDireccion> direcciones = clienteDireccionRepo.findByCliente_ClienteId(clienteId);
        return direcciones.stream().map(mapper::toResponseDto).toList();
    }

    //-- LISTAR TODOS --//
    @Override
    @Transactional(readOnly = true)
    public List<ClienteDireccionResponseDto> listar() {
        log.info("Listando todas las direcciones");
        return clienteDireccionRepo.findAll().stream()
                .map(mapper::toResponseDto)
                .toList();
    }


}
