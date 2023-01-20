package com.registro.usuarios.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	private String nombre;
	
	
	private String email;
	
	private String password;

	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;

	@OneToMany
	private List<Producto> producto;
	
	@OneToMany(mappedBy = "usuario")
	private List<Orden> ordenes;
	
	@PrePersist
	private void prePersist(){
		
		createAt = new Date();
	}



	public Cliente() {
		super();
	}



	public Cliente(Long id, String nombre, String email, String password, Date createAt, List<Producto> producto) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.password = password;
		this.createAt = createAt;
		this.producto = producto;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public Date getCreateAt() {
		return createAt;
	}



	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}



	public List<Producto> getProducto() {
		return producto;
	}



	public void setProducto(List<Producto> producto) {
		this.producto = producto;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", email=" + email + ", password=" + password
				+ ", createAt=" + createAt + ", producto=" + producto + "]";
	}

	
	

}
