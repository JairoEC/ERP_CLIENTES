package pe.edu.cibertec.erpCliente.service;

import java.util.List;

import pe.edu.cibertec.erpCliente.api.request.SegmentoRequestDto;
import pe.edu.cibertec.erpCliente.api.response.SegmentoResponseDto;

public interface SegmentoService {
	SegmentoResponseDto crear(SegmentoRequestDto request);
	SegmentoResponseDto actualizar(Short id, SegmentoRequestDto request);
	void eliminar(Short id);
	SegmentoResponseDto obtener(Short id);
	List<SegmentoResponseDto> listar();

}
