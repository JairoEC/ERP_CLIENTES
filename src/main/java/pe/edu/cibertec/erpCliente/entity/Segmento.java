package pe.edu.cibertec.erpCliente.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "cat_segmento")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Segmento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "segmento_id")
	private Short segmentoId;
	
    @Column(name = "nombre", nullable = false, length = 60, unique = true)
    private String nombre;

    @Column(name = "descripcion", length = 255, nullable = true)
    private String descripcion;

    @Column(name = "activo", nullable = false)
    @Builder.Default
    private Boolean activo = true; // 1=Activo, 0=Inactivo
    
    @Column(name = "ultima_actualizacion", insertable = false, updatable = false)
    private LocalDateTime ultimaActualizacion;
    
    @OneToMany(mappedBy = "segmento") // "Un" Segmento tiene "Muchos" Clientes
    private List<Cliente> clientes;
}
