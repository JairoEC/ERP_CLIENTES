package pe.edu.cibertec.erpCliente.service;

import pe.edu.cibertec.erpCliente.api.request.ClienteContactoRequestDto;
import pe.edu.cibertec.erpCliente.api.response.ClienteContactoResponseDto;

import java.util.List;

public interface ClienteContactoService {
    ClienteContactoResponseDto crear(ClienteContactoRequestDto request);
    ClienteContactoResponseDto actualizar(Long id, ClienteContactoRequestDto request);
    void eliminar(Long id);
    ClienteContactoResponseDto obtener(Long id);
    List<ClienteContactoResponseDto> listarPorCliente(Long clienteId);
    List<ClienteContactoResponseDto> listar();
    
    
}
