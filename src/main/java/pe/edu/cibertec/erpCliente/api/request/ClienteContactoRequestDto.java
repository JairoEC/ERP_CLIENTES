package pe.edu.cibertec.erpCliente.api.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ClienteContactoRequestDto {
	
	@NotNull(message = "El ID del cliente no puede ser nulo")
    private Integer clienteId;
    
	@NotBlank(message = "Los nombres no pueden estar vacíos")
    private String nombres;
	
	@Size(max=80, message = "Los apellidos no deben exceder los 80 caracteres")
    private String apellidos;
	
	@Size(max=80, message = "El cargo no debe exceder los 80 caracteres")
    private String cargo;

	@Size(max=120, message = "El email no debe exceder los 120 caracteres")
	@Email(message = "El email debe tener un formato válido")
    private String email;
	
	@Size(max = 30, message = "El teléfono no puede exceder los 30 caracteres")
    private String telefono;
	
	@Builder.Default
    private Boolean esPrincipal=false;
    
 

}
