package pe.edu.cibertec.erpCliente.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "cliente_direccion")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDireccion {
    @Id
    private Integer direccion_id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Cliente cliente;
    private String tipo;
    private String direccion;
    private String referencia;
    private String ubigeo;
    private String distrito;
    private String provincia;
    private String departamento;
    private String pais;
    private Integer es_principal;
    private Integer activo;
    private Date creado_en;
    private Date ultima_actualizacion;
}
