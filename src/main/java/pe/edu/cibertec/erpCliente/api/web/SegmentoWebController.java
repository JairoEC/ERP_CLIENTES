package pe.edu.cibertec.erpCliente.api.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import pe.edu.cibertec.erpCliente.service.SegmentoService;

@Controller
@RequestMapping("/segmentos")
@RequiredArgsConstructor

public class SegmentoWebController {
	private final SegmentoService service;
	
	@GetMapping
	public String listarSegmentos(Model model) {
		var listaSegmentosDto = service.listar();
		model.addAttribute("listadoSegmentos", listaSegmentosDto);
		return "segmentos/lista"; // Nombre de la vista Thymeleaf
	}

}
