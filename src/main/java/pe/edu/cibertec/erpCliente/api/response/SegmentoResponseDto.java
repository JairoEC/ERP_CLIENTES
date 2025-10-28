package pe.edu.cibertec.erpCliente.api.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SegmentoResponseDto {

	private Short segmentoId;
	
    private String nombre;

    private String descripcion;

    private Boolean activo;
    

}
