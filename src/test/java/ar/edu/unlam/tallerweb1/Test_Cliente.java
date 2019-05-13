package ar.edu.unlam.tallerweb1;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Cliente;
import static org.assertj.core.api.Assertions.*;

public class Test_Cliente extends SpringTest {

	@Test
	@Transactional
	@Rollback(true)
	public void TestQueInsertaUnCliente()
	{
		Cliente mic = new Cliente();
		mic.setNombre("Omar"); //Creo cliente
		
	//Conecta con getSessionFactory - Guarda el objeto en la B. Datos
		getSession().save(mic); 
		
		
	
	//Otro Objeto para hacer la comparacion
		
		//Trae el objeto que guardamos en mic
	Cliente micliente = getSession().get(Cliente.class, mic.getId());
	
	
	assertThat(mic.getNombre()).isEqualTo(micliente.getNombre()); 
	

	}
	
	
	@Test
	@Transactional
	@Rollback(true)
	public void TestQueEliminaCliente(){
	
	Cliente mic = new Cliente();
	

	mic.setNombre("Alfaro");
	
	getSession().save(mic);
	getSession().delete(mic);
	
	getSession().delete(mic);
	Cliente central4 = getSession().get(Cliente.class, mic.getId());
	assertThat(mic).isNull();
	
	
	}
	

	@Test
	@Transactional
	@Rollback(true)
	public void TestQueModificaCliente()
	{
		Cliente mic = new Cliente();
		mic.setNombre("ClienteA");
		
		getSession().save(mic);
		getSession().update(mic);
		
		
	}
	
	

	
	
	
	
}
