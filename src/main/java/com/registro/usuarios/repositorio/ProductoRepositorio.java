package com.registro.usuarios.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.registro.usuarios.modelo.Producto;




@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Long> {

}
