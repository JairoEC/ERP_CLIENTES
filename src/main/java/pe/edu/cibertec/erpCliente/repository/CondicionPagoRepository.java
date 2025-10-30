package pe.edu.cibertec.erpCliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import pe.edu.cibertec.erpCliente.entity.CondicionPago;

@Repository
public interface CondicionPagoRepository extends JpaRepository<CondicionPago, Short> {

	//METODOS ADICIONALES
	boolean existsByNombreIgnoreCase(String nombre);
	long countByDiasPlazoGreaterThan(Integer valor);
	
}
