package pe.edu.cibertec.erpCliente.service.Impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pe.edu.cibertec.erpCliente.api.request.ClienteCreditoEventoRequestDto;
import pe.edu.cibertec.erpCliente.api.response.ClienteCreditoEventoResponseDto;
import pe.edu.cibertec.erpCliente.entity.Cliente;
import pe.edu.cibertec.erpCliente.entity.ClienteCreditoEvento;
import pe.edu.cibertec.erpCliente.exception.BusinessException;
import pe.edu.cibertec.erpCliente.mapper.ClienteCreditoEventoMapper;
import pe.edu.cibertec.erpCliente.repository.ClienteCreditoEventoRepository;
import pe.edu.cibertec.erpCliente.repository.ClienteRepository;
import pe.edu.cibertec.erpCliente.service.ClienteCreditoEventoService;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ClienteCreditoEventoServiceImpl implements ClienteCreditoEventoService {

	private final ClienteCreditoEventoRepository eventoRepository;
    private final ClienteRepository clienteRepository; // Necesitas este repo
    private final ClienteCreditoEventoMapper mapper;
    
	@Override
	public ClienteCreditoEventoResponseDto crear(ClienteCreditoEventoRequestDto request) {
		
	
		log.info("Creando evento de credito para el cliente: id='{}'", request.getClienteId());
		
		Cliente cliente = clienteRepository.findById(request.getClienteId())
				.orElseThrow(() -> new BusinessException("Cliente no encontrado con id: " + request.getClienteId()));
		
		// -- 2 -- MAPEO (Request DTO -> Entidad) ---
		ClienteCreditoEvento nuevoEvento = mapper.toEntity(request);
		
		// -- 3. Asignando campos que mapper ignoró ---
		nuevoEvento.setCliente(cliente); 
		nuevoEvento.setFecha(LocalDateTime.now());
		
		// -- 4. PERSISTENCIA (GUARDAR EN BD) ---
		ClienteCreditoEvento eventoGuardado = eventoRepository.save(nuevoEvento);
		
		// -- 5. MAPEO DE RESPUESTA (Entidad -> Response DTO) ---
		log.debug("Evento de credito creado id={}", eventoGuardado.getEventoId());
		return mapper.toResponseDto(eventoGuardado);
	}

	@Override
	public ClienteCreditoEventoResponseDto obtener(Long id) {
		log.info("Obteniendo evento de credito con id='{}'", id);
		return eventoRepository.findById(id)
				.map(mapper:: toResponseDto)
				.orElseThrow(() -> new BusinessException("Evento de credito no encontrado con id: " + id));
		
		
	}
	
	
	
	
	@Override
	public ClienteCreditoEventoResponseDto actualizar(Long id, ClienteCreditoEventoRequestDto request) {
		log.info("Actualizando evento de credito con id='{}'", id);
		
		ClienteCreditoEvento eventoExistente = eventoRepository.findById(id)
				.orElseThrow(() -> new BusinessException("Evento de credito no encontrado con id: " + id));
		
		// Actualizar los campos permitidos
		eventoExistente.setAccion(request.getAccion());
		eventoExistente.setDetalle(request.getDetalle());
		eventoExistente.setUsuario(request.getUsuario());
		
		// Guardar los cambios
		ClienteCreditoEvento eventoActualizado = eventoRepository.save(eventoExistente);
		
		log.debug("Evento de credito actualizado id={}", eventoActualizado.getEventoId());
		return mapper.toResponseDto(eventoActualizado);
	}

	
	
	@Override
	public void eliminar(Long id) {
		throw new BusinessException("La eliminación de eventos de crédito no está permitida'");

	}



	@Override
	public List<ClienteCreditoEventoResponseDto> listar() {
		log.info("Listando todos los eventos de credito");
		return eventoRepository.findAll().stream()
				.map(mapper::toResponseDto)
				.toList();
	}

}
