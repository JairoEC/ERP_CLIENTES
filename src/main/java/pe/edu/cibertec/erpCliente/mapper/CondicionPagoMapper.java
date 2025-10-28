package pe.edu.cibertec.erpCliente.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import pe.edu.cibertec.erpCliente.api.request.CondicionPagoRequestDto;
import pe.edu.cibertec.erpCliente.api.response.CondicionPagoResponseDto;
import pe.edu.cibertec.erpCliente.entity.CondicionPago;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CondicionPagoMapper {
    // 1. De Request DTO a Entidad 

    CondicionPago toEntity(CondicionPagoRequestDto dto);

	
    // 2. De Entidad a Response DTO (para Listar/Obtener)
    CondicionPagoResponseDto toResponseDto(CondicionPago entity);


    // 3. Para Actualizar una entidad existente (para Actualizar/PUT)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(CondicionPagoRequestDto dto, @MappingTarget CondicionPago entity);

}
