package pe.edu.cibertec.erpCliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.cibertec.erpCliente.entity.Segmento;

public interface SegmentoRepository extends JpaRepository <Segmento, Short> {
	
	//METODOS ADICIONALES
	boolean existsByNombreIgnoreCase(String nombre);
	Integer countByActivoTrue();


}
