package pe.edu.cibertec.erpCliente.api.response;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ClienteDireccionResponseDto {
    private Long id;
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

    private LocalDateTime creadoEn;
    private LocalDateTime ultimaActualizacion;
}
