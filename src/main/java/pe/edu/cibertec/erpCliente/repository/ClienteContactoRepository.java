package pe.edu.cibertec.erpCliente.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.erpCliente.entity.ClienteContacto;

import java.util.List;
import java.util.Optional;

public interface ClienteContactoRepository extends JpaRepository<ClienteContacto, Long> {
    List<ClienteContacto> findByClienteClienteId(Integer clienteId);

    @EntityGraph(attributePaths = {"etapaContacto", "origenContacto"})
    Optional<ClienteContacto> findById(Long id);
}
