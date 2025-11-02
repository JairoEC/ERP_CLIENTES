package pe.edu.cibertec.erpCliente.service.Impl;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.cibertec.erpCliente.api.request.ClienteContactoRequestDto;
import pe.edu.cibertec.erpCliente.api.response.ClienteContactoResponseDto;
import pe.edu.cibertec.erpCliente.entity.ClienteContacto;
import pe.edu.cibertec.erpCliente.exception.BusinessException;
import pe.edu.cibertec.erpCliente.mapper.ClienteContactoMapper;
import pe.edu.cibertec.erpCliente.repository.ClienteContactoRepository;
import pe.edu.cibertec.erpCliente.repository.ClienteRepository;
import pe.edu.cibertec.erpCliente.service.ClienteContactoService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ClienteContactoServiceImpl implements ClienteContactoService {
    private final ClienteContactoRepository clienteContactoRepo;
    private final ClienteRepository clienteRepo;
    private final ClienteContactoMapper mapper;
    private final EntityManager entityManager;

    //--  CREAR  --//
    @Override
    public ClienteContactoResponseDto crear(ClienteContactoRequestDto request) {
        log.info("Creando contacto para clienteId={}", request.getClienteId());

        var cliente = clienteRepo.findById(request.getClienteId())
                .orElseThrow(() -> new BusinessException("Cliente no encontrado con id: " + request.getClienteId()));

        ClienteContacto contacto = mapper.toEntity(request);
        
        contacto.setCliente(cliente);
        contacto.setActivo(true);
  
        ClienteContacto guardado = clienteContactoRepo.save(contacto);
        log.debug("Contacto creado id={}", guardado.getContactoId());


        return mapper.toResponseDto(guardado);
    }

    //--  ACTUALIZAR  --//
    @Override
    public ClienteContactoResponseDto actualizar(Long id, ClienteContactoRequestDto request) {
        log.info("Actualizando contacto id={}", id);

        ClienteContacto contactoActual = clienteContactoRepo.findById(id)
                .orElseThrow(() -> new BusinessException("Contacto no encontrado con id: " + id));

        if(request.getEmail()!=null && !request.getEmail().isEmpty()) {
        	if (clienteContactoRepo.existsByClienteAndEmailAndContactoIdNot(
        			contactoActual.getCliente(), request.getEmail(), id)) {
                throw new BusinessException("El email '" + request.getEmail() + "' ya está en uso por otro contacto.");
            }
        }
        
   
        mapper.updateEntityFromDto(request, contactoActual);



        ClienteContacto actualizado = clienteContactoRepo.save(contactoActual);
        log.debug("Contacto actualizado id={}", actualizado.getContactoId());

        entityManager.flush();
        entityManager.refresh(actualizado);

        return mapper.toResponseDto(actualizado);
    }
    
    //--  ELIMINAR  --//

    @Override
    public void eliminar(Long id) {
        log.info("Eliminando contacto id={}", id);

        ClienteContacto contacto = clienteContactoRepo.findById(id)
                .orElseThrow(() -> new BusinessException("Contacto no encontrado con id: " + id));

     // Regla de negocio: No se puede borrar un contacto principal
        if (contacto.isEsPrincipal()) {
            throw new BusinessException("No se puede eliminar un contacto principal. Primero asigne otro contacto como principal.");
        }
        
  
        clienteContactoRepo.delete(contacto);
    }

    //--  OBTENER POR ID --//
    @Override
    @Transactional(readOnly = true)
    public ClienteContactoResponseDto obtener(Long id) {
        log.info("Obteniendo contacto id={}", id);

        ClienteContacto contacto = clienteContactoRepo.findById(id)
                .orElseThrow(() -> new BusinessException("Contacto no encontrado con id: " + id));

        return mapper.toResponseDto(contacto);
    }

    //--  LISTAR POR CLIENTE  --//
    @Override
    @Transactional(readOnly = true)
    public List<ClienteContactoResponseDto> listarPorCliente(Long clienteId) {
        log.info("Listando contactos por clienteId={}", clienteId);
        List<ClienteContacto> contactos = clienteContactoRepo.findByClienteClienteId(clienteId);
        return contactos.stream().map(mapper::toResponseDto).toList();
    }

    //--  LISTAR TODOS  --//
    @Override
    @Transactional(readOnly = true)
    public List<ClienteContactoResponseDto> listar() {
        log.info("Listando todos los contactos");
        return clienteContactoRepo.findAll().stream()
                .map(mapper::toResponseDto)
                .toList();
    }
}
