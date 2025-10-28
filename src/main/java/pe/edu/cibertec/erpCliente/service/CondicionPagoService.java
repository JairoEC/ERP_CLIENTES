package pe.edu.cibertec.erpCliente.service;

import java.util.List;

import pe.edu.cibertec.erpCliente.api.request.CondicionPagoRequestDto;
import pe.edu.cibertec.erpCliente.api.response.CondicionPagoResponseDto;

public interface CondicionPagoService {
	CondicionPagoResponseDto crear(CondicionPagoRequestDto request);
	CondicionPagoResponseDto actualizar(Short id, CondicionPagoRequestDto request);
	void eliminar(Short id);
	CondicionPagoResponseDto obtener(Short id);
	List<CondicionPagoResponseDto> listar();

}
