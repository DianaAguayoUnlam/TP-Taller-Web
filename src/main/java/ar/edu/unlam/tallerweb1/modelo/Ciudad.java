package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Ciudad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String ubicacionGeografica;
	
	
	@OneToOne
	private Ubicacion ubicacion;
	
	@ManyToOne
	private Pais pais;
	
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Pais getPais() {
		return pais;
	}


	public void setPais(Pais pais) {
		this.pais = pais;
	}



	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getUbicacionGeografica() {
		return ubicacionGeografica;
	}


	public void setUbicacionGeografica(String ubicacionGeografica) {
		this.ubicacionGeografica = ubicacionGeografica;
	}


	public Ubicacion getUbicacion() {
		return ubicacion;
	}


	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}


	
	
	
}
