package com.registro.usuarios.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.registro.usuarios.modelo.Cliente;
import com.registro.usuarios.modelo.DetalleOrden;
import com.registro.usuarios.modelo.Orden;
import com.registro.usuarios.modelo.Producto;
import com.registro.usuarios.servicio.Iclienteservice;
import com.registro.usuarios.servicio.ProductoService;

  

@Controller
public class ListarController {
	
	private final Logger log = LoggerFactory.getLogger(ListarController.class);
  
	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private Iclienteservice clienteservice;
	
	List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();
	
	// datos de la orden
		Orden orden = new Orden();
	
	@GetMapping(value="/form")
	public String create( Map<String, Object> model) {
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		return"create";
	}
	
	@RequestMapping(value="/form", method = RequestMethod.POST)
	public String save(Cliente cliente) {
		clienteservice.save(cliente);
		return"create";
		
	}
	
	@GetMapping(value="/ver/{id}")
	public String ver(@PathVariable Long id,  Model model) {
		log.info("Id producto enviado como parámetro {}", id);
		
		Producto producto = new Producto();
		Optional<Producto> productoOptional = productoService.get(id);
		producto = productoOptional.get();

		model.addAttribute("producto", producto);
		return"Productos/productohome";
	}
	
	@PostMapping(value="/cart")
	public String addcart(@RequestParam Long id,@RequestParam Long cantidad, Model model) {
		
		DetalleOrden detalleOrden = new DetalleOrden();
		Producto producto = new Producto();
		double sumaTotal = 0;

		Optional<Producto> optionalProducto = productoService.get(id);
		log.info("Producto añadido: {}", optionalProducto.get());
		log.info("Cantidad: {}", cantidad);
		producto = optionalProducto.get();

		detalleOrden.setCantidad(cantidad);
		detalleOrden.setPrecio(producto.getPrecio());
		detalleOrden.setNombre(producto.getNombre());
		detalleOrden.setTotal(producto.getPrecio() * cantidad);
		detalleOrden.setProducto(producto);
		
		Long idProducto=producto.getId();
		boolean ingresado=detalles.stream().anyMatch(p -> p.getProducto().getId()==idProducto);
		
		if (!ingresado) {
			detalles.add(detalleOrden);
		}
		
		
		sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();
		
		orden.setTotal(sumaTotal);
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);

		
		return"Productos/carrito";
	}

	@GetMapping("/delete/cart/{id}")
	public String deleteProductoCart(@PathVariable Long id, Model model) {

		// lista nueva de prodcutos
		List<DetalleOrden> ordenesNueva = new ArrayList<DetalleOrden>();

		for (DetalleOrden detalleOrden : detalles) {
			if (detalleOrden.getProducto().getId() != id) {
				ordenesNueva.add(detalleOrden);
			}
		}

		// poner la nueva lista con los productos restantes
		detalles = ordenesNueva;

		double sumaTotal = 0;
		sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

		orden.setTotal(sumaTotal);
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);

		return "Productos/carrito";
	}
	
	@GetMapping("/getCart")
	public String getCart(Model model) {
		
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);
		
	
		return "Productos/carrito";
	}
	
	@GetMapping(value="/order")
	public String Rorden(Model model) {
		
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);
		
		return"Productos/resumenorden";
	}
	
	
}
