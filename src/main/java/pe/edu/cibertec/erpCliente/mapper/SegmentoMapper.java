package pe.edu.cibertec.erpCliente.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import pe.edu.cibertec.erpCliente.api.request.SegmentoRequestDto;
import pe.edu.cibertec.erpCliente.api.response.SegmentoResponseDto;
import pe.edu.cibertec.erpCliente.entity.Segmento;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SegmentoMapper {
	
    // 1. De Request DTO a Entidad 

    Segmento toEntity(SegmentoRequestDto dto);

	
    // 2. De Entidad a Response DTO (para Listar/Obtener)
    SegmentoResponseDto toResponseDto(Segmento entity);


    // 3. Para Actualizar una entidad existente (para Actualizar/PUT)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(SegmentoRequestDto dto, @MappingTarget Segmento entity);
}
