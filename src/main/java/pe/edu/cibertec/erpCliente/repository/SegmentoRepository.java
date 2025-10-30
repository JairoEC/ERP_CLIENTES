package pe.edu.cibertec.erpCliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import pe.edu.cibertec.erpCliente.entity.Segmento;

@Repository
public interface SegmentoRepository extends JpaRepository <Segmento, Short> {
	
	//METODOS ADICIONALES
	boolean existsByNombreIgnoreCase(String nombre);
	Integer countByActivoTrue();


}
