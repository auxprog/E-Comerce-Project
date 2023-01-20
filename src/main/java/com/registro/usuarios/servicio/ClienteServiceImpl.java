package com.registro.usuarios.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.registro.usuarios.modelo.Cliente;
import com.registro.usuarios.repositorio.IClienteDao;




@Service
public class ClienteServiceImpl  implements Iclienteservice {
  
	
	@Autowired
	private IClienteDao clientedao;

	@Override
	@Transactional
	public void save(Cliente cliente) {
		clientedao.save(cliente);
		
	}

	@Override
	@Transactional
	public Cliente findOne(Long id) {
		// TODO Auto-generated method stub
		return clientedao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		clientedao.deleteById(id);
	}

	@Override
	public List<Cliente> listar() {
		// TODO Auto-generated method stub
		return (List<Cliente> )clientedao.findAll();
	}

	@Override
	public Optional<Cliente> listarid() {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	 
	




}
