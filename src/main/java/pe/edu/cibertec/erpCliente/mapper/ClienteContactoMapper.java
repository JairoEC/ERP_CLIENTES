package pe.edu.cibertec.erpCliente.mapper;

import org.mapstruct.*;
import pe.edu.cibertec.erpCliente.api.request.ClienteContactoRequestDto;
import pe.edu.cibertec.erpCliente.api.response.ClienteContactoResponseDto;
import pe.edu.cibertec.erpCliente.entity.ClienteContacto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClienteContactoMapper {
    ClienteContacto toEntity(ClienteContactoRequestDto dto);
    @Mappings({
            @Mapping(source = "cliente.clienteId", target = "clienteId"),
            @Mapping(source = "etapaContacto.id", target = "etapaContactoId"),
            @Mapping(source = "etapaContacto.nombre", target = "etapaContactoNombre"),
            @Mapping(source = "origenContacto.id", target = "origenContactoId"),
            @Mapping(source = "origenContacto.nombre", target = "origenContactoNombre")
    })

    ClienteContactoResponseDto toResponseDto(ClienteContacto entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(ClienteContactoRequestDto dto, @MappingTarget ClienteContacto entity);
}
