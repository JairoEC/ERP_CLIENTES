package pe.edu.cibertec.erpCliente.api.response;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ClienteContactoResponseDto {
    private Long ContactoId;
    private Integer clienteId;
    
    private String nombres;
    private String apellidos;
    private String cargo;

    private String email;
    private String telefono;
    
    private String esPrincipal;    
    private boolean activo;
    
    private LocalDateTime creadoEn;
    private LocalDateTime ultimaActualizacion;
}
