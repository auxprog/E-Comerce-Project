package com.registro.usuarios.controlador;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.registro.usuarios.modelo.Producto;
import com.registro.usuarios.servicio.ProductoService;



@Controller
public class Controlador {
	

	@Autowired
	private ProductoService productoService;
	
	

 
    @GetMapping(value="")
    public String index(Model model) {
    	List<Producto> productos = productoService.findAll();
		model.addAttribute("productos", productos);
    	
    	return"index";
    }
    
 
 
}