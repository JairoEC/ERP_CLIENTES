package pe.edu.cibertec.erpCliente.mapper;

import jdk.jfr.Name;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import pe.edu.cibertec.erpCliente.api.request.ClienteRequestDto;
import pe.edu.cibertec.erpCliente.api.response.ClienteResponseDto;
import pe.edu.cibertec.erpCliente.entity.Cliente;
import pe.edu.cibertec.erpCliente.entity.Segmento;
import pe.edu.cibertec.erpCliente.entity.TipoDocumento;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClienteMapper {
    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);
    @Mappings({
            @Mapping(target="tipoDocumento",source = "tipoDocumentoId", qualifiedByName = "tipoDocumentoRef"),
            @Mapping(target = "segmento", source = "segmento", qualifiedByName = "segmentoRef")
    })
    Cliente toEntity(ClienteRequestDto dto);
    ClienteResponseDto toResponseDto(Cliente entity);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "segmento", source = "segmento", qualifiedByName = "segmentoRef")
    void updateEntityFromDto(ClienteRequestDto dto, @MappingTarget Cliente entity);

    @Named("tipoDocumentoRef")
    default TipoDocumento mapTipoDocumentoRef(Short tipoDocId){
        if(tipoDocId == null) return null;
        return TipoDocumento.builder().tipoDocumentoId(tipoDocId).build();
    }
    @Named("segmentoRef")
    default Segmento mapSegmentoRef(Short segmentoId){
        if(segmentoId == null) return null;
        return Segmento.builder().segmentoId(segmentoId).build();
    }
}
