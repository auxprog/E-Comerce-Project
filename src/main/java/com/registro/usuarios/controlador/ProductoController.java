package com.registro.usuarios.controlador;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.registro.usuarios.modelo.Cliente;
import com.registro.usuarios.modelo.Producto;
import com.registro.usuarios.servicio.ProductoService;
import com.registro.usuarios.servicio.UploadFileService;



@Controller
@RequestMapping("/Productos")
public class ProductoController {
	
	@Autowired
	private ProductoService productoservice;
	
	@Autowired
	private UploadFileService upload;
	
	@GetMapping(value="")
	public String show(Model model) {
		model.addAttribute("productoss", productoservice.findAll());
		return"Productos/show";
	}
	@GetMapping(value="/create")
	public String create() {
		
		return"Productos/create";
	}
	
	@PostMapping(value="/save")
	public String save(Producto producto,  @RequestParam("img") MultipartFile file)throws IOException  {
		Cliente u= new Cliente();
		producto.setUsuario(u);
		
		//imagen
				if (producto.getId()==null) { // cuando se crea un producto
					String nombreImagen= upload.saveImage(file);
					producto.setImagen(nombreImagen);
				}else {
					
				}
		
		productoservice.save(producto);
		return"redirect:/Productos";
	}
	
	@GetMapping(value="/edit/{id}")
	public String editar(@PathVariable Long id, Model model) {
		Producto producto= new Producto();
		Optional<Producto> optionalProducto=productoservice.get(id);
		producto= optionalProducto.get();
		
		
		model.addAttribute("producto", producto);
		
		return"Productos/edit";
	}
	
	@PostMapping("/update")
	public String update(Producto producto, @RequestParam("img") MultipartFile file ) throws IOException {
		
		if(file.isEmpty()) {
			Producto p = new Producto();
			p=productoservice.get(producto.getId()).get();
			producto.setImagen(p.getImagen());
		}else {
			Producto p= new Producto();
			p=productoservice.get(producto.getId()).get();
			
				if (!p.getImagen().equals("default.jpg")) {
					upload.deleteImage(p.getImagen());
				}
				
			
			String nombreImagen= upload.saveImage(file);
			producto.setImagen(nombreImagen);
		}
		productoservice.update(producto);
		return"redirect:/Productos";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		
		Producto p= new Producto();
		p=productoservice.get(id).get();
		
			if (!p.getImagen().equals("default.jpg")) {
				
				upload.deleteImage(p.getImagen());
			}
			
		
		
		productoservice.delete(id);
		
		return"redirect:/Productos";
	}
	
	
}
