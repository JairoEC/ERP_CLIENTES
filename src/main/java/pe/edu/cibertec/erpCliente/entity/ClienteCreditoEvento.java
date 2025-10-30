package pe.edu.cibertec.erpCliente.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "cliente_credito_evento")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteCreditoEvento {
    @Id
    private Integer evento_id;
    private Date fecha;
    private String accion;
    private String detalle;
    private String usuario;
    private Date ultima_actualizacion;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
