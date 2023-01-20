package com.registro.usuarios.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.registro.usuarios.modelo.Producto;
import com.registro.usuarios.repositorio.ProductoRepositorio;



@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductoRepositorio productoRepository;
	
	@Override
	public List<Producto> findAll() {
		// TODO Auto-generated method stub
		return productoRepository.findAll();
	}

	@Override
	public Producto save(Producto producto) {
		// TODO Auto-generated method stub
		return productoRepository.save(producto);
	}

	@Override
	public Optional<Producto> get(Long id) {
		// TODO Auto-generated method stub
		return productoRepository.findById(id);
	}

	@Override
	public void update(Producto producto) {
		// TODO Auto-generated method stub
		productoRepository.save(producto);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		productoRepository.deleteById(id);
	}

}
