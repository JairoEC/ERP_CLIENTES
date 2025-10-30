package pe.edu.cibertec.erpCliente.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cat_origen_contacto")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CatOrigenContacto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "origen_contacto_id")
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;
}
