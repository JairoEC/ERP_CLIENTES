package pe.edu.cibertec.erpCliente.service;

import pe.edu.cibertec.erpCliente.api.request.TipoDocumentoRequestDto;
import pe.edu.cibertec.erpCliente.api.response.TipoDocumentoResponseDto;

import java.util.List;

public interface TipoDocumentoService {
    TipoDocumentoResponseDto crear(TipoDocumentoRequestDto request);
    TipoDocumentoResponseDto actualizar(Short id, TipoDocumentoRequestDto request);
    void eliminar(Short id);
    TipoDocumentoResponseDto obtener(Short id);
    List<TipoDocumentoResponseDto> listar();
}
