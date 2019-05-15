package ar.edu.unlam.tallerweb1.modelo;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Sucursal {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long Id;
	private String Calle;
	private Integer numero;
	
	
	
	@ManyToOne

	private Empresa empresa;
	
	
	
	
	public long getId() {
		return Id;
	}
	
	/*
	public Central getCentral() {
		return central;
	}
	public void setCentral(Central central) {
		this.central = central;
	}
	*/
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	public String getCalle() {
		return Calle;
	}
	public void setCalle(String calle) {
		Calle = calle;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
}
