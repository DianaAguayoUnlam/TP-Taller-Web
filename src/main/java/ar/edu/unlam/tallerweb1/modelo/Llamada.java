package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType; 
@Entity
public class Llamada {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long Id;
	private double Duracion;

	
	@ManyToOne
	@Cascade(value = CascadeType.ALL)
	private Central central;
	
	
	
	
public void setDuracion(double Duracion)
{
	this.Duracion= Duracion;
}

public double getDuracion()
{
	return Duracion;
}

public long getId() {
	return Id;
}

public Central getCentral() {
	return central;
}

public void setCentral(Central central) {
	this.central = central;
}






 


}
