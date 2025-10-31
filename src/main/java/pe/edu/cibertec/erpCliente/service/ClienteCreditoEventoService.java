package pe.edu.cibertec.erpCliente.service;

import java.util.List;

import pe.edu.cibertec.erpCliente.api.request.ClienteCreditoEventoRequestDto;
import pe.edu.cibertec.erpCliente.api.response.ClienteCreditoEventoResponseDto;

public interface ClienteCreditoEventoService {
	ClienteCreditoEventoResponseDto crear(ClienteCreditoEventoRequestDto request);
	ClienteCreditoEventoResponseDto actualizar(Long id, ClienteCreditoEventoRequestDto request);
	void eliminar(Long id);
	ClienteCreditoEventoResponseDto obtener(Long id);
	List<ClienteCreditoEventoResponseDto> listar();
}
