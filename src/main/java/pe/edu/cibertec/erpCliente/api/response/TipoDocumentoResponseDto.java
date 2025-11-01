package pe.edu.cibertec.erpCliente.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoDocumentoResponseDto {

    private Integer id;
    private String codigo;
    private String nombre;

}
