package pe.edu.cibertec.erpCliente.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "fin_condicion_pago")
public class CondicionPago {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "condicion_pago_id")
	private Short condicionPagoId;
	
	@Column(name = "nombre", nullable = false, length = 60, unique = true)
	private String nombre;

	@Column(name = "dias_plazo", nullable = false)
	private Integer diasPlazo=0;
	
	@Column(name = "ultima_actualizacion", insertable = false, updatable = false)
	private LocalDateTime ultimaActualizacion;
	
	@OneToMany(mappedBy = "condicionPago") // "Una" CondicionPago tiene "Muchas" Facturas
	private List<Cliente> clientes;

}
