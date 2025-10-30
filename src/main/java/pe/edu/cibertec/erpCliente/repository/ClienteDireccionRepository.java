package pe.edu.cibertec.erpCliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.erpCliente.entity.ClienteDireccion;

import java.util.List;

public interface ClienteDireccionRepository extends JpaRepository<ClienteDireccion, Long> {
    List<ClienteDireccion> findByClienteClienteId(Integer clienteId);
}
