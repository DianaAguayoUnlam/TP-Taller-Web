package ar.edu.unlam.tallerweb1;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Central;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Empresa;

import static org.assertj.core.api.Assertions.*;

import org.hibernate.Session;

import ar.edu.unlam.tallerweb1.modelo.Llamada;

public class Test_Empresa extends SpringTest {
	
	@Test
	@Transactional
	@Rollback(true)
	public void TestQueIngresaLlamada()
	{
		Empresa mie = new Empresa ();
		mie.setNombre("Empresariales");
	}
	

}
