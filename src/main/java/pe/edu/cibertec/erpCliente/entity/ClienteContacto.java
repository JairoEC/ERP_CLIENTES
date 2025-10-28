package pe.edu.cibertec.erpCliente.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cliente_contacto")
public class ClienteContacto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer contacto_id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Cliente cliente;
    private String nombres;
    private String apellidos;
    private String cargo;
    private String email;
    private String telefono;
    private Integer es_principal;
}
