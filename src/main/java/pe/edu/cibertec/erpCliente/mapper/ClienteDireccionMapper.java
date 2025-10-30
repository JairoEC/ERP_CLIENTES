package pe.edu.cibertec.erpCliente.mapper;

import org.mapstruct.*;
import pe.edu.cibertec.erpCliente.api.request.ClienteDireccionRequestDto;
import pe.edu.cibertec.erpCliente.api.response.ClienteDireccionResponseDto;
import pe.edu.cibertec.erpCliente.entity.ClienteDireccion;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClienteDireccionMapper {
    ClienteDireccion toEntity(ClienteDireccionRequestDto dto);

    @Mapping(source = "cliente.clienteId", target = "clienteId")
    ClienteDireccionResponseDto toResponseDto(ClienteDireccion entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(ClienteDireccionRequestDto dto, @MappingTarget ClienteDireccion entity);
}
