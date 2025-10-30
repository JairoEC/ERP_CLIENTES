package pe.edu.cibertec.erpCliente.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pe.edu.cibertec.erpCliente.api.request.CondicionPagoRequestDto;
import pe.edu.cibertec.erpCliente.api.response.CondicionPagoResponseDto;
import pe.edu.cibertec.erpCliente.entity.CondicionPago;
import pe.edu.cibertec.erpCliente.exception.BusinessException;
import pe.edu.cibertec.erpCliente.mapper.CondicionPagoMapper;
import pe.edu.cibertec.erpCliente.repository.CondicionPagoRepository;
import pe.edu.cibertec.erpCliente.repository.ClienteRepository;
import pe.edu.cibertec.erpCliente.service.CondicionPagoService;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CondicionPagoServiceImpl implements CondicionPagoService {
	
	private final CondicionPagoRepository condicionPagoRepo;
	private final ClienteRepository clienteRepository;
	private final CondicionPagoMapper mapper;

	@Override
	public CondicionPagoResponseDto crear(CondicionPagoRequestDto request) {
		log.info("Creando condicion de pago: nombre='{}'", request.getNombre());
		
		//--- 1. NORMALIZACIONES
		String nombreLimpio = request.getNombre().trim();
		
		// --- 2. VALIDACIÓN DE NEGOCIO ---
		if(condicionPagoRepo.existsByNombreIgnoreCase(nombreLimpio)) {
			throw new BusinessException("El nombre de la condicion de pago ya existe: " + nombreLimpio);
		}
		
		// --- 3 --- MAPEO (Request DTO -> Entidad) ---
		CondicionPago nuevaCondicionPago = mapper.toEntity(request);
		
		// --- 4. APLICAR LÓGICA DE NEGOCIO ---
		nuevaCondicionPago.setNombre(nombreLimpio); // Guardo en mi entidad el nombre limpio
		
		// --- 5. PERSISTENCIA (GUARDAR EN BD) ---
		CondicionPago condicionPagoGuardada = condicionPagoRepo.save(nuevaCondicionPago);
		
		// --- 6. MAPEO DE RESPUESTA (Entidad -> Response DTO) ---
		log.debug("Condicion de pago creada id={}", condicionPagoGuardada.getCondicionPagoId());
		return mapper.toResponseDto(condicionPagoGuardada);
	}

	
	@Override
	public CondicionPagoResponseDto actualizar(Short id, CondicionPagoRequestDto req) {
		log.info("Actualizando condicion de pago id={}", id);
		
		//--- 1. OBTENER LA ENTIDAD ACTUAL DESDE LA BD ---
		CondicionPago condicionPagoActual = condicionPagoRepo.findById(id)
				.orElseThrow(() -> new BusinessException("Condicion de pago no encontrada con id: " + id));
		
		//--- 2. NORMALIZACIONES ---
		String nombreLimpio = req.getNombre().trim();
		
		//--- 3. VALIDACIÓN DE UNICIDAD ---
		Boolean nombreCambiado = !condicionPagoActual.getNombre().equalsIgnoreCase(nombreLimpio);
		if(nombreCambiado && condicionPagoRepo.existsByNombreIgnoreCase(nombreLimpio)) {
			throw new BusinessException("El nombre de la condicion de pago ya existe: " + nombreLimpio);
		}
		
		//--- 4. MAPEO (DTO -> Entidad Existente) ---
		mapper.updateEntityFromDto(req, condicionPagoActual);
		
		//--- 5. APLICAR LÓGICA DE NEGOCIO / SOBRESCRIBIR CON DATOS LIMPIOS ----
		condicionPagoActual.setNombre(nombreLimpio);
		
		//--- 6. PERSISTENCIA (GUARDAR EN BD) ---
		CondicionPago condicionPagoActualizado = condicionPagoRepo.save(condicionPagoActual);
		
		//--- 7. MAPEO DE RESPUESTA (Entidad -> Response DTO) ---
		log.debug("Condicion de pago actualizado id={}", condicionPagoActualizado.getCondicionPagoId());
		return mapper.toResponseDto(condicionPagoActualizado);
	}

	@Override
	public void eliminar(Short id) {
		log.info("Eliminando condicion de pago id={}", id);
		CondicionPago condicionPagoActual = condicionPagoRepo.findById(id)
				.orElseThrow(() -> new BusinessException("Condicion de pago no encontrado con id: " + id));
		
		long cantidadClientesAsociados = clienteRepository.countByCondicionPago_CondicionPagoId(id);
		if(cantidadClientesAsociados > 0) {
			throw new BusinessException("No se puede eliminar la condicion de pago id=" + id + " porque tiene " + cantidadClientesAsociados + " clientes asociados.");
		}
		
		// Si pasa la validación, procedo a eliminar
		condicionPagoRepo.delete(condicionPagoActual);
	}

	@Override
	@Transactional(readOnly = true)
	public CondicionPagoResponseDto obtener(Short id) {
		log.info("Obteniendo condicion de pago id={}", id);
		
		CondicionPago condicionPagoBuscado = condicionPagoRepo.findById(id)
				.orElseThrow(() -> new BusinessException("Condicion de pago no encontrado con id: " + id));
		return mapper.toResponseDto(condicionPagoBuscado);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CondicionPagoResponseDto> listar() {
		log.info("Listando todas las condiciones de pago");
		List<CondicionPago> listadoCondicionesPago = condicionPagoRepo.findAll();
		return listadoCondicionesPago.stream()
				.map(mapper::toResponseDto)
				.toList();
	}

}
