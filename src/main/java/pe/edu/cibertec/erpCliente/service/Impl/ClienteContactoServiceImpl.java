package pe.edu.cibertec.erpCliente.service.Impl;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.cibertec.erpCliente.api.request.ClienteContactoRequestDto;
import pe.edu.cibertec.erpCliente.api.response.ClienteContactoResponseDto;
import pe.edu.cibertec.erpCliente.entity.CatEtapaContacto;
import pe.edu.cibertec.erpCliente.entity.CatOrigenContacto;
import pe.edu.cibertec.erpCliente.entity.ClienteContacto;
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


    @Override
    public ClienteContactoResponseDto crear(ClienteContactoRequestDto request) {
        log.info("Creando contacto para clienteId={}", request.getClienteId());

        var cliente = clienteRepo.findById(request.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + request.getClienteId()));

        ClienteContacto contacto = mapper.toEntity(request);
        contacto.setCliente(cliente);

        if (request.getEtapaContactoId() != null) {
            contacto.setEtapaContacto(
                    entityManager.getReference(CatEtapaContacto.class, request.getEtapaContactoId())
            );
        }

        if (request.getOrigenContactoId() != null) {
            contacto.setOrigenContacto(
                    entityManager.getReference(CatOrigenContacto.class, request.getOrigenContactoId())
            );
        }

        ClienteContacto guardado = clienteContactoRepo.save(contacto);
        log.debug("Contacto creado id={}", guardado.getId());

        entityManager.refresh(guardado);

        return mapper.toResponseDto(guardado);
    }


    @Override
    public ClienteContactoResponseDto actualizar(Long id, ClienteContactoRequestDto request) {
        log.info("Actualizando contacto id={}", id);

        ClienteContacto contactoActual = clienteContactoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Contacto no encontrado con id: " + id));

        mapper.updateEntityFromDto(request, contactoActual);

        if (request.getClienteId() != null) {
            var cliente = clienteRepo.findById(request.getClienteId())
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + request.getClienteId()));
            contactoActual.setCliente(cliente);
        }

        if (request.getEtapaContactoId() != null) {
            contactoActual.setEtapaContacto(
                    entityManager.getReference(CatEtapaContacto.class, request.getEtapaContactoId())
            );
        } else {
            contactoActual.setEtapaContacto(null);
        }

        if (request.getOrigenContactoId() != null) {
            contactoActual.setOrigenContacto(
                    entityManager.getReference(CatOrigenContacto.class, request.getOrigenContactoId())
            );
        } else {
            contactoActual.setOrigenContacto(null);
        }

        ClienteContacto actualizado = clienteContactoRepo.save(contactoActual);
        log.debug("Contacto actualizado id={}", actualizado.getId());

        entityManager.flush();

        entityManager.refresh(actualizado);

        return mapper.toResponseDto(actualizado);
    }

    @Override
    public void eliminar(Long id) {
        log.info("Eliminando contacto id={}", id);

        ClienteContacto contacto = clienteContactoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Contacto no encontrado con id: " + id));

        clienteContactoRepo.delete(contacto);
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteContactoResponseDto obtener(Long id) {
        log.info("Obteniendo contacto id={}", id);

        ClienteContacto contacto = clienteContactoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Contacto no encontrado con id: " + id));

        return mapper.toResponseDto(contacto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClienteContactoResponseDto> listarPorCliente(Integer clienteId) {
        log.info("Listando contactos por clienteId={}", clienteId);
        List<ClienteContacto> contactos = clienteContactoRepo.findByClienteClienteId(clienteId);
        return contactos.stream().map(mapper::toResponseDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClienteContactoResponseDto> listar() {
        log.info("Listando todos los contactos");
        return clienteContactoRepo.findAll().stream()
                .map(mapper::toResponseDto)
                .toList();
    }
}
