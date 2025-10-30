package pe.edu.cibertec.erpCliente.api.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ClienteDireccionRequestDto {
    private Integer clienteId;
    private String tipo;
    private String direccion;
    private String referencia;
    private String ubigeo;
    private String distrito;
    private String provincia;
    private String departamento;
    private String pais;
    private boolean esPrincipal;
    private boolean activo;
}
