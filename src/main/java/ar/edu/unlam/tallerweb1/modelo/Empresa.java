package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class Empresa {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long Id;
	private String Nombre;
	
	
	
	public void setNombre(String Nombre)
	{
		this.Nombre=Nombre;
	}
	
	public String getNombre()
	{
		return Nombre;
	}
	
	public long getId() {
		return Id;
	}



	

}
