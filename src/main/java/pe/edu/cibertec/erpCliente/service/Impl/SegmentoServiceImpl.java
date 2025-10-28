package pe.edu.cibertec.erpCliente.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pe.edu.cibertec.erpCliente.api.request.SegmentoRequestDto;
import pe.edu.cibertec.erpCliente.api.response.SegmentoResponseDto;
import pe.edu.cibertec.erpCliente.entity.Segmento;
import pe.edu.cibertec.erpCliente.mapper.SegmentoMapper;
import pe.edu.cibertec.erpCliente.repository.SegmentoRepository;
import pe.edu.cibertec.erpCliente.repository.ClienteRepository;
import pe.edu.cibertec.erpCliente.service.SegmentoService;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional

public class SegmentoServiceImpl implements SegmentoService {
	
	private final SegmentoRepository segmentoRepo;
	private final ClienteRepository clienteRepository;
	private final SegmentoMapper mapper;

	@Override
	public SegmentoResponseDto crear(SegmentoRequestDto req) {
		log.info("Creando segmento: nombre='{}'", req.getNombre());
		
		
		//--- 1. NORMALIZACIONES
		String nombreLimpio = req.getNombre().trim();
		String descripcionLimpia = (req.getDescripcion() != null) 
                ? req.getDescripcion().trim() 
                : null;
		
		// --- 2. VALIDACIÓN DE NEGOCIO ---
		if(segmentoRepo.existsByNombreIgnoreCase(nombreLimpio)) {
			throw new RuntimeException("El nombre del segmento ya existe: " + nombreLimpio);
		}
		
		// --- 3 --- MAPEO (Request DTO -> Entidad) ---
        Segmento nuevoSegmento = mapper.toEntity(req);
        
        // --- 4. APLICAR LÓGICA DE NEGOCIO ---
        nuevoSegmento.setNombre(nombreLimpio); // Guardo en mi entidad el nombre limpio
        nuevoSegmento.setDescripcion(descripcionLimpia); // Guardo en mi entidad la descripción limpia
        nuevoSegmento.setActivo(true); // Regla de negocio: Todos los segmentos nacen ACTIVOS
        
        // --- 5. PERSISTENCIA (GUARDAR EN BD) ---
        Segmento segmentoGuardado = segmentoRepo.save(nuevoSegmento);
        
        // --- 6. MAPEO DE RESPUESTA (Entidad -> Response DTO) ---
        log.debug("Segmento creado id={}", segmentoGuardado.getSegmentoId());
        return mapper.toResponseDto(segmentoGuardado);
	}
	

	@Override
	public SegmentoResponseDto actualizar(Short id, SegmentoRequestDto req) {
		log.info("Actualizando segmento con Id={}", id);
		
		//--- 1. OBTENER LA ENTIDAD ACTUAL DESDE LA BD ---
		Segmento segmentoActual = segmentoRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Segmento no encontrado: " + id));
		
		//--- 2. NORMALIZAR DATOS DE ENTRADA---
		String nombreLimpio = req.getNombre().trim();
        String descripcionLimpia = (req.getDescripcion() != null)
                                    ? req.getDescripcion().trim()
                                    : null;
        
        //--- 3. VALIDACIÓN DE UNICIDAD
        Boolean nombreCambiado = !segmentoActual.getNombre().equalsIgnoreCase(nombreLimpio);

		if(nombreCambiado && segmentoRepo.existsByNombreIgnoreCase(nombreLimpio)) {
			throw new RuntimeException("El nombre '" + nombreLimpio + "' ya está siendo usado por otro segmento.");
        }
		
		
		//--- 4. MAPEO (DTO -> Entidad Existente) ---
		mapper.updateEntityFromDto(req, segmentoActual);
		
		//--- 5. APLICAR LÓGICA DE NEGOCIO / SOBRESCRIBIR CON DATOS LIMPIOS ----
		segmentoActual.setNombre(nombreLimpio);
				segmentoActual.setDescripcion(descripcionLimpia);
		
		//--- 6. PERSISTENCIA (GUARDAR EN BD) ---
		Segmento segmentoGuardado = segmentoRepo.save(segmentoActual);
		
		//--- 7. MAPEO DE RESPUESTA (Entidad -> Response DTO) ---
		log.debug("Segmento actualizado Id={}", segmentoGuardado.getSegmentoId());
		return mapper.toResponseDto(segmentoGuardado);
	}

	@Override
	public void eliminar(Short id) {
		log.info("Eliminando segmento con Id={}", id);
		Segmento segmentoActual = segmentoRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Segmento no encontrado: " + id));
	
		
		long clientesVinculados = clienteRepository.countBySegmentoSegmentoId(id);
        if (clientesVinculados > 0) {
            throw new RuntimeException("No se puede eliminar el segmento: tiene " 
                                     + clientesVinculados + " cliente(s) asociado(s).");
        }
        
        // Si no hay vinculaciones, procedo a eliminar
        
		segmentoRepo.delete(segmentoActual);
		log.debug("Segmento eliminado Id={}", id);
	}

	@Override
	@Transactional(readOnly = true)
	public SegmentoResponseDto obtener(Short id) {
		log.info("Obteniendo segmento con Id={}", id);
		
		Segmento segmentoBuscado = segmentoRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Segmento no encontrado: " + id));
		        
		return mapper.toResponseDto(segmentoBuscado);
	}

	@Override
	@Transactional(readOnly = true)
	public List<SegmentoResponseDto> listar() {
		log.info("Listando todos los segmentos");
		List<Segmento> listaDeEntidades = segmentoRepo.findAll();
		return listaDeEntidades.stream()
				.map(mapper::toResponseDto)
				.toList();

	}

}
