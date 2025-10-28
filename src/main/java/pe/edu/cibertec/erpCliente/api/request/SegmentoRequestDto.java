package pe.edu.cibertec.erpCliente.api.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class SegmentoRequestDto {

	@NotBlank(message = "El nombre no puede estar vacío")
	@Size(max = 60, message = "El nombre no puede exceder los 60 caracteres")
    private String nombre;

	@Size(max = 255, message = "La descripción no puede exceder los 255 caracteres")
    private String descripcion;

	// private Short segmentoId; 
    //No se incluye el ID en el request DTO
    
    
    //private Boolean activo; 
    //No se incluye este campo porque por defecto es true y eso se define en el servicio
}
