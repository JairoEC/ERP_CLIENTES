package pe.edu.cibertec.erpCliente.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class CatOrigenContacto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "origen_contacto_id")
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;
}
