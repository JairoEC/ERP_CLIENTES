package pe.edu.cibertec.erpCliente.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "cliente_contacto",
uniqueConstraints = { 
    @UniqueConstraint(name = "idx_contacto_email_unico", columnNames = {"cliente_id", "email"}),
    @UniqueConstraint(name = "idx_contacto_tel_unico", columnNames = {"cliente_id", "telefono"})
}
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteContacto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contacto_id")
    private Long contactoId;


    @Column(name ="nombres",nullable = false, length = 80)
    private String nombres;

    @Column(name ="apellidos",length = 80)
    private String apellidos;

    @Column(name ="cargo",length = 80)
    private String cargo;
    
    @Email
    @Column(name ="email", length = 120)
    private String email;
    
    @Column(name ="telefono",length = 30)
    private String telefono;
    
    @Column(name = "es_principal", nullable = false)
    @Builder.Default
    private boolean esPrincipal = false;

    
    @Column(nullable = false)
    @Builder.Default
    private boolean activo = true;

    @CreationTimestamp 
    @Column(name = "creado_en", updatable = false, nullable = false)
    private LocalDateTime creadoEn;

    @UpdateTimestamp 	@Column(name = "ultima_actualizacion", insertable = false, updatable = false)
    private LocalDateTime ultimaActualizacion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;
}

