package pe.edu.cibertec.erpCliente.api.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ClienteContactoRequestDto {
    private Integer clienteId;
    private String nombres;
    private String apellidos;
    private String cargo;
    private Long etapaContactoId;
    private Long origenContactoId;
    private String linkedinUrl;
    private String zonaHoraria;
    private boolean consentimientoEmail;
    private boolean consentimientoSms;
    private boolean activo;
}
