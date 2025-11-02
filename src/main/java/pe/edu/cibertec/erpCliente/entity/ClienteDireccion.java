package pe.edu.cibertec.erpCliente.entity;

import jakarta.persistence.*;
import lombok.*;
import pe.edu.cibertec.erpCliente.entity.enums.TipoDireccionEnum;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "cliente_direccion")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ClienteDireccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "direccion_id")
    private Long id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_cli_dir_cliente"))
    private Cliente cliente;

    @Enumerated(EnumType.STRING)
    @Column(name="tipo",nullable = false)
    @Builder.Default
    private TipoDireccionEnum tipo = TipoDireccionEnum.ENVIO;

    @Column(name="direccion", nullable = false, length = 200)
    private String direccion;

    @Column(name="referencia",length = 200)
    private String referencia;

    @Column(name="ubigeo",length = 6)
    private String ubigeo;

    @Column(name="distrito",length = 80)
    private String distrito;

    @Column(name="provincia",length = 80)
    private String provincia;

    @Column(name="departamento",length = 80)
    private String departamento;

    @Column(name="pais",length = 80)
    @Builder.Default
    private String pais = "PERU";

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
}
