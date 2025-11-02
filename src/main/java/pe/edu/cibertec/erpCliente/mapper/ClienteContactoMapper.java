package pe.edu.cibertec.erpCliente.mapper;

import org.mapstruct.*;
import pe.edu.cibertec.erpCliente.api.request.ClienteContactoRequestDto;
import pe.edu.cibertec.erpCliente.api.response.ClienteContactoResponseDto;
import pe.edu.cibertec.erpCliente.entity.ClienteContacto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClienteContactoMapper {

	// 1. De Request DTO a Entidad (Para CREAR)


    @Mapping(target = "cliente", ignore = true)      // El Servicio lo asignará manualmente
    @Mapping(target = "activo", ignore = true)       // El Servicio lo pondrá en 'true'
    @Mapping(target = "creadoEn", ignore = true)     // Se genera por @CreationTimestamp
    @Mapping(target = "ultimaActualizacion", ignore = true) // Se genera por @UpdateTimestamp
    ClienteContacto toEntity(ClienteContactoRequestDto dto);
    

    // 2. De Entidad a Response DTO (Para RESPONDER)
 
    @Mapping(source = "cliente.clienteId", target = "clienteId")
    ClienteContactoResponseDto toResponseDto(ClienteContacto entity);
    


    // 4. Para ACTUALIZAR una entidad existente (PUT/PATCH)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

    @Mapping(target = "cliente", ignore = true) // El cliente de un contacto no se debe cambiar
    @Mapping(target = "activo", ignore = true) // El 'activo' se maneja con un endpoint de borrado lógico
    @Mapping(target = "creadoEn", ignore = true) 
    @Mapping(target = "ultimaActualizacion", ignore = true) 
    void updateEntityFromDto(ClienteContactoRequestDto dto, @MappingTarget ClienteContacto entity);
}
