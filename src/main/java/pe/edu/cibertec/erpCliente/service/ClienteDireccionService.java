package pe.edu.cibertec.erpCliente.service;

import pe.edu.cibertec.erpCliente.api.request.ClienteDireccionRequestDto;
import pe.edu.cibertec.erpCliente.api.response.ClienteDireccionResponseDto;

import java.util.List;

public interface ClienteDireccionService {
    ClienteDireccionResponseDto crear(ClienteDireccionRequestDto request);
    ClienteDireccionResponseDto actualizar(Long id, ClienteDireccionRequestDto request);
    void eliminar(Long id);
    ClienteDireccionResponseDto obtener(Long id);
    List<ClienteDireccionResponseDto> listarPorCliente(Integer clienteId);
    List<ClienteDireccionResponseDto> listar();
}
