package pe.edu.cibertec.erpCliente.api.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import pe.edu.cibertec.erpCliente.entity.enums.TipoDireccionEnum;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ClienteDireccionRequestDto {
	@NotNull(message = "El ID del cliente no puede ser nulo")
	private Integer clienteId; 

	@NotNull(message = "El tipo de dirección no puede ser nulo")
	private TipoDireccionEnum tipo; 

	@NotBlank(message = "La dirección no puede estar vacía")
	@Size(max = 200, message = "La dirección no puede exceder los 200 caracteres")
	private String direccion; 

	@Size(max = 200, message = "La referencia no puede exceder los 200 caracteres")
	private String referencia;

	@Size(max = 6, message = "El ubigeo no puede exceder los 6 caracteres")
	private String ubigeo;

	@Size(max = 80, message = "El distrito no puede exceder los 80 caracteres")
	private String distrito;

	@Size(max = 80, message = "La provincia no puede exceder los 80 caracteres")
	private String provincia;

	@Size(max = 80, message = "El departamento no puede exceder los 80 caracteres")
	private String departamento;

	@Size(max = 80, message = "El país no puede exceder los 80 caracteres")
	@Builder.Default
	private String pais = "PERU"; 

	private boolean esPrincipal;


}
