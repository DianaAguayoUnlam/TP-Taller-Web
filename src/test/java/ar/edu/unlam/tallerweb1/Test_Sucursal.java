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
import ar.edu.unlam.tallerweb1.modelo.Sucursal;

public class Test_Sucursal extends SpringTest {
	
	@Test
	@Transactional
	@Rollback(true)
	public void TestQueIngresaUnaEmpresaEnUnaSucursal()
	{
		//Objeto sucursal
		Sucursal mis = new Sucursal ();
		mis.setCalle("Calle falsa");
		mis.setNumero(329);
		
		
		//Objeto empresa
		Empresa mie= new Empresa();
		mie.setNombre("Empresa SA");
		
		
		//Seteamos empresa en sucursal
		mis.setEmpresa(mie);
		
		//Conectamos con la base de datos
		getSession().save(mis);
		getSession().save(mie);
		
		
		//Recuperamos creando otro objeto para obtener los Id
		
	Sucursal mis2 = getSession().get(Sucursal.class, mis.getId());
	
	Empresa mie2 = getSession().get(Empresa.class, mie.getId());
	
	
	//Comparando que existen los Id
	assertThat(mis2.getId()).isEqualTo(mis.getId());
	assertThat(mie2.getId()).isEqualTo(mie.getId());
	
	
	//Comparamos que exista una sucursal dentro de una empresa
	
					//Comparamos por objetos
	assertThat(mie).isEqualTo(mis.getEmpresa());
	
					//Comparamos por Nombre
	assertThat(mis.getEmpresa().getNombre()).isEqualTo(mie.getNombre());
	
	System.out.println(mis.getEmpresa().getNombre());
	System.out.println(mie.getNombre());
		
	}
	
	
	
	
	//________________________________________________________________//
	@Test
	@Transactional
	@Rollback(true)
	public void Test_Que_Mapea_Una_Sucursal_En_Una_Central()
	{
		Sucursal mis = new Sucursal();
		mis.setCalle("Calle 123");
		
		Central mic = new Central();
		mic.setNombre("Plusmar");
		
		
		//Seteo una Central en una Sucursal
		mic.setSucursal(mis);
		
		//Guardo en la Bd
		getSession().save(mis);
		getSession().save(mic);
		
					//Recupero
		Sucursal mis2 = getSession().get(Sucursal.class, mis.getId());
		
		Central mic2 = getSession().get(Central.class, mic.getId());
		
		
		//comparo que exista una Sucursal en una central
		assertThat(mic.getSucursal()).isEqualTo(mis);//getCentral());
		
		//Compare por Nombres
		assertThat(mic.getSucursal().getCalle()).isEqualTo(mis.getCalle());
		
		
		
	}
	

}
