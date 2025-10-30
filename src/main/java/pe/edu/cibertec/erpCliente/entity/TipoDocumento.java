package pe.edu.cibertec.erpCliente.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.cibertec.erpCliente.entity.enums.TipoPersona;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "cat_tipo_documento")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoDocumento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tipo_documento_id")
    private Short tipoDocumentoId;
    
    @Column(name = "codigo", length = 10, nullable = false, unique = true)
    private String codigo;
    
    @Column(name = "nombre", length = 60, nullable = false)
    private String nombre;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_persona", nullable = false)
    @Builder.Default
    private TipoPersona tipoPersona = TipoPersona.AMBOS;
    
    @Column(name = "ultima_actualizacion", insertable = false , updatable = false )
    private LocalDateTime ultimaActualizacion;
    
    @OneToMany(mappedBy = "tipoDocumento")
    private List<Cliente> clientes;
    
 
}
