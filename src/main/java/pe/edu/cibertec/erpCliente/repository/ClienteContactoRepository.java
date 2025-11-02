package pe.edu.cibertec.erpCliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.cibertec.erpCliente.entity.Cliente;
import pe.edu.cibertec.erpCliente.entity.ClienteContacto;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteContactoRepository extends JpaRepository<ClienteContacto, Long> {
    List<ClienteContacto> findByClienteClienteId(Long clienteId);


    
    boolean existsByClienteAndEmailAndContactoIdNot (Cliente cliente, String email, Long id);
    boolean existsByClienteAndEmail(Cliente cliente, String email);
}
