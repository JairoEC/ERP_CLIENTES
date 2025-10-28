package pe.edu.cibertec.erpCliente.api.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CondicionPagoRequestDto {
	

	@NotBlank(message = "El nombre no puede estar vacío")
	@Size(max = 60, message = "El nombre no puede tener más de 60 caracteres")
	private String nombre;

	
	@NotNull(message = "Los días de plazo no pueden ser nulos")
	@Min(value = 0, message = "Los días de plazo no pueden ser negativos")
	private Integer diasPlazo;
	
}
