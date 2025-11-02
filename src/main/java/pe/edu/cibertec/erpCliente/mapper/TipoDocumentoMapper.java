package pe.edu.cibertec.erpCliente.mapper;

import org.mapstruct.*;
import pe.edu.cibertec.erpCliente.api.request.TipoDocumentoRequestDto;
import pe.edu.cibertec.erpCliente.api.response.TipoDocumentoResponseDto;
import pe.edu.cibertec.erpCliente.entity.TipoDocumento;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TipoDocumentoMapper {
    TipoDocumento toEntity(TipoDocumentoRequestDto dto);

    @Mapping(source = "tipoDocumentoId", target = "id")
    TipoDocumentoResponseDto toResponseDto(TipoDocumento entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(TipoDocumentoRequestDto dto, @MappingTarget TipoDocumento entity);

}
