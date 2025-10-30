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
    private Long id;
    private Integer clienteId;
    private String nombres;
    private String apellidos;
    private String cargo;

    private Long etapaContactoId;
    private String etapaContactoNombre;

    private Long origenContactoId;
    private String origenContactoNombre;

    private String linkedinUrl;
    private String zonaHoraria;

    private boolean consentimientoEmail;
    private boolean consentimientoSms;
    private boolean activo;

    private LocalDateTime creadoEn;
    private LocalDateTime ultimaActualizacion;
}
