package pe.edu.cibertec.erpCliente.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@Table(name = "cliente", uniqueConstraints = {
	    @UniqueConstraint(
	        name = "idx_cliente_doc", 
	        columnNames = {"tipo_documento_id", "numero_documento"} 
	    )
	})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id")
    private Integer clienteId;

    @Enumerated(EnumType.STRING) 
    @Column(name = "tipo_persona", nullable = false)
    private TipoPersona tipoPersona;

    // --- RELACIÓN 1: TIPO DOCUMENTO ---
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_documento_id", nullable = false)
    private TipoDocumento tipoDocumento;

    @Column(name = "numero_documento", nullable = false, length = 20)
    private String numeroDocumento;

    
    // --- Campos Persona Natural ---
    @Column(name = "nombres", length = 80)
    private String nombres;

    @Column(name = "apellido_paterno", length = 80)
    private String apellidoPaterno;

    @Column(name = "apellido_materno", length = 80)
    private String apellidoMaterno;

    
    // --- Campos Persona Jurídica ---
    @Column(name = "razon_social", length = 160)
    private String razonSocial;

    @Column(name = "nombre_comercial", length = 160)
    private String nombreComercial;

    
    // --- Campos Comunes ---
    @Column(name = "email", length = 120)
    @Email
    private String email;

    @Column(name = "telefono", length = 30)
    private String telefono;
    

    // --- RELACIÓN 2: SEGMENTO ---
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "segmento_id")
    private Segmento segmento;

    // --- RELACIÓN 3: CONDICIÓN PAGO ---

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "condicion_pago_id")
    private CondicionPago condicionPago;

    // --- Campos de Crédito ---
    @Enumerated(EnumType.STRING)
    @Column(name = "moneda_credito")
    private Moneda monedaCredito;

    @Column(name = "linea_credito")
    private BigDecimal lineaCredito;

    @Column(name = "bloqueado_credito", nullable = false)
    private boolean bloqueadoCredito = false; // Default


    @Column(name = "activo", nullable = false)
    private boolean activo = true; // Default

    @Column(name = "creado_en", updatable = false)
    private LocalDateTime creadoEn;

    @Column(name = "ultima_actualizacion", insertable = false, updatable = false)
    private LocalDateTime ultimaActualizacion;
    

    // --- RELACIONES "HIJO" (1-a-Muchos) ---

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClienteDireccion> direcciones;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClienteContacto> contactos;
    
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClienteCreditoEvento> eventosCredito;
    

}