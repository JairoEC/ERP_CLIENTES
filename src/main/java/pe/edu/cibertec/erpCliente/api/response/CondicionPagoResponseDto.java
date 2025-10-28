package pe.edu.cibertec.erpCliente.api.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CondicionPagoResponseDto {

	private Short condicionPagoId;
	
	private String nombre;

	private Integer diasPlazo;


}
