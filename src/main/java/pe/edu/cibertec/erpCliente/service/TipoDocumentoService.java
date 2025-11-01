package pe.edu.cibertec.erpCliente.service;

import pe.edu.cibertec.erpCliente.api.request.TipoDocumentoRequestDto;
import pe.edu.cibertec.erpCliente.api.response.TipoDocumentoResponseDto;

public interface TipoDocumentoService {
    TipoDocumentoResponseDto crear(TipoDocumentoRequestDto request);
    TipoDocumentoResponseDto actualizar(Short id, TipoDocumentoRequestDto request);
    void eliminar(Integer id);
    TipoDocumentoResponseDto obtener(Short id);
    TipoDocumentoResponseDto listar();
}
