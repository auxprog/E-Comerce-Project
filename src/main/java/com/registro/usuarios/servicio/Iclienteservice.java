package com.registro.usuarios.servicio;

import java.util.List;
import java.util.Optional;

import com.registro.usuarios.modelo.Cliente;


public interface Iclienteservice {

    public List<Cliente>listar();
    
    public Optional<Cliente>listarid();
	
	public void save(Cliente cliente);
	
	public Cliente findOne( Long id);
	
	public void delete(Long id);
	
	
	 
	}
