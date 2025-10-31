package pe.edu.cibertec.erpCliente.api.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.cibertec.erpCliente.entity.enums.AccionEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteCreditoEventoRequestDto {

	@NotNull(message = "El clienteId no puede estar vacío")
	private Integer clienteId;

	@NotNull(message = "La acción no puede estar vacía")
	private AccionEnum accion;
	
	@Size(max = 255, message = "El detalle no puede exceder los 255 caracteres")
	private String detalle;
	
	@Size(max = 60, message = "El usuario no puede exceder los 60 caracteres")
	private String usuario;
	

}
