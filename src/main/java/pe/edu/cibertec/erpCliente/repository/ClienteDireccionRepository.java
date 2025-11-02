package pe.edu.cibertec.erpCliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.erpCliente.entity.ClienteDireccion;

import java.util.List;

@Repository
public interface ClienteDireccionRepository extends JpaRepository<ClienteDireccion, Long> {
    List<ClienteDireccion> findByCliente_ClienteId(Long clienteId);
}
