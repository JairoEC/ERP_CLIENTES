package pe.edu.cibertec.erpCliente.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.cibertec.erpCliente.entity.enums.Accion;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "cliente_credito_evento")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteCreditoEvento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evento_id")
    private Integer evento_id;
    
    @Column(name = "fecha")
    private LocalDateTime fecha;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "accion", nullable = false)
    private Accion accion;
    
    @Column(name = "detalle", length = 255)
    private String detalle;
    
    @Column(name = "usuario", length = 60)
    private String usuario;
    
    @Column(name = "ultima_actualizacion", insertable = false, updatable = false)
    private Date ultima_actualizacion;
    
    
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
