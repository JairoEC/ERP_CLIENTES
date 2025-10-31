package pe.edu.cibertec.erpCliente.api.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.cibertec.erpCliente.entity.enums.AccionEnum;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteCreditoEventoResponseDto {
	private Long eventoId;
	private Integer clienteId;
	private LocalDateTime fecha;
	private AccionEnum accion;
	private String detalle;
	private String usuario;
	private LocalDateTime ultimaActualizacion;

}
