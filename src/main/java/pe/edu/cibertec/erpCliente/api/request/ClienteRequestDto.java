package pe.edu.cibertec.erpCliente.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.cibertec.erpCliente.entity.Segmento;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteRequestDto {
    private String tipoPersona;
    private Short tipoDocumentoId;
    private String numeroDocumento;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String razonSocial;
    private String nombreComercial;
    private String email;
    private String telefono;
    private Short segmento;

}
