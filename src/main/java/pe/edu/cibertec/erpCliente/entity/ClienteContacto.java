package pe.edu.cibertec.erpCliente.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "cliente_contacto")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteContacto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contacto_id")
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombres;

    @Column(length = 120)
    private String apellidos;

    @Column(length = 100)
    private String cargo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etapa_contacto_id",
            foreignKey = @ForeignKey(name = "fk_cliente_contacto_etapa"))
    private CatEtapaContacto etapaContacto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "origen_contacto_id",
            foreignKey = @ForeignKey(name = "fk_cliente_contacto_origen"))
    private CatOrigenContacto origenContacto;

    @Column(name = "linkedin_url", length = 200)
    private String linkedinUrl;

    @Column(name = "zona_horaria", length = 40)
    private String zonaHoraria;

    @Column(name = "consentimiento_email", nullable = false)
    private boolean consentimientoEmail = false;

    @Column(name = "consentimiento_sms", nullable = false)
    private boolean consentimientoSms = false;

    @Column(nullable = false)
    private boolean activo = true;

    @Column(name = "creado_en", insertable = false, updatable = false,
            columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime creadoEn;

    @Column(name = "ultima_actualizacion", nullable = false)
    @UpdateTimestamp
    private LocalDateTime ultimaActualizacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id",
            foreignKey = @ForeignKey(name = "fk_cliente_contacto_cliente"))
    private Cliente cliente;
}
