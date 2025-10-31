package pe.edu.cibertec.erpCliente.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import pe.edu.cibertec.erpCliente.api.request.ClienteCreditoEventoRequestDto;
import pe.edu.cibertec.erpCliente.api.response.ClienteCreditoEventoResponseDto;
import pe.edu.cibertec.erpCliente.entity.ClienteCreditoEvento;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClienteCreditoEventoMapper {
	//1. De Request DTO a Entidad 
	@Mapping(target = "cliente", ignore = true)
	@Mapping(target = "fecha", ignore = true)
	@Mapping(target = "eventoId", ignore = true)
	ClienteCreditoEvento toEntity(ClienteCreditoEventoRequestDto dto);
	

	// 2. De Entidad a Response DTO (para Listar/Obtener)
	@Mapping(source = "cliente.clienteId", target = "clienteId")

	ClienteCreditoEventoResponseDto toResponseDto(ClienteCreditoEvento entity);
	
	// 3. Para Actualizar una entidad existente (para Actualizar/PUT)
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	@Mapping(target = "cliente", ignore = true)
	@Mapping(target = "fecha", ignore = true)
	@Mapping(target = "eventoId", ignore = true)
	void updateEntityFromDto(ClienteCreditoEventoRequestDto dto, @MappingTarget ClienteCreditoEvento entity);

}
