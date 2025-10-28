package pe.edu.cibertec.erpCliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.cibertec.erpCliente.entity.CondicionPago;

public interface CondicionPagoRepository extends JpaRepository<CondicionPago, Short> {

	//METODOS ADICIONALES
	boolean existsByNombreIgnoreCase(String nombre);
	long countByDiasPlazoGreaterThan(Integer valor);
	
}
