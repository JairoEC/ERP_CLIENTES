package pe.edu.cibertec.erpCliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.cibertec.erpCliente.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	// --- MÉTODOS ADICIONALES ---
    
    // 1. Este es para ClienteServiceImpl 
    boolean existsByNumeroDocumento( String numeroDocumento);
    long countByCondicionPago_CondicionPagoId(Short id);
	
    // 2. Este es para SegmentoServiceImpl 
    long countBySegmentoSegmentoId(Short id);
	
    // 3. Este es para CondicionPagoServiceImpl
    long countByCondicionPagoCondicionPagoId(Short id);
    
    // 4. Este es para TipoDocumentoServiceImpl
    long countByTipoDocumentoTipoDocumentoId(Short id);
}
