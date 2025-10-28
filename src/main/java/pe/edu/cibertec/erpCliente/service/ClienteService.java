package pe.edu.cibertec.erpCliente.service;

import pe.edu.cibertec.erpCliente.api.request.ClienteRequestDto;
import pe.edu.cibertec.erpCliente.api.response.ClienteResponseDto;

import java.util.List;

public interface ClienteService {
	ClienteResponseDto crear(ClienteRequestDto request);
    ClienteResponseDto actualizar(Integer id, ClienteRequestDto request);
    void eliminar(Integer idCli);
    ClienteResponseDto obtener(Integer idCli);
    List<ClienteResponseDto> listar();

}
