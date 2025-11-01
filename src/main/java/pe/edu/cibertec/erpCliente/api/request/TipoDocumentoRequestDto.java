package pe.edu.cibertec.erpCliente.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoDocumentoRequestDto {
    private String codigo;
    private String nombre;
}
