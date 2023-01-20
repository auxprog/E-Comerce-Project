package com.registro.usuarios.servicio;

import java.util.List;
import java.util.Optional;

import com.registro.usuarios.modelo.Producto;


public interface ProductoService {
	public Producto save( Producto producto);
	public Optional<Producto> get(Long id);
	public void update(Producto producto);
	public void delete(Long id);
	public List<Producto> findAll();
	
}
