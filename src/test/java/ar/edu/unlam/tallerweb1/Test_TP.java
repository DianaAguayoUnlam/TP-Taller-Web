package ar.edu.unlam.tallerweb1;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Ciudad;
import ar.edu.unlam.tallerweb1.modelo.Continente;
import ar.edu.unlam.tallerweb1.modelo.Pais;
import ar.edu.unlam.tallerweb1.modelo.Ubicacion;

public class Test_TP extends SpringTest{
	
//	2- Hacer con junit un test que busque todos los países de habla inglesa.
	
	@Test
	@Transactional
	@Rollback(true)
	public void Test_Que_Busca_paises_de_habla_inglesa(){
		
		Pais mip= new Pais();
		mip.setIdioma("ingles");
		getSession().save(mip);
		
		List<Pais>paises= getSession().createCriteria(Pais.class)
				.add(Restrictions.like("idioma", "ingles")).list();
		
		for(Pais p :paises){
		assertThat(p.getIdioma()).isEqualTo("ingles");
		}		
	}
	
//	3- Hacer con junit un test que busque todos los países del continente europeo
	@Test
	@Transactional
	@Rollback(true)
	public void Test_Que_busca_todos_los_paises_europeos(){
		Continente mic = new Continente();
		mic.setNombre("Europa");
		getSession().save(mic);
		
		Pais mip2 = new Pais();
		mip2.setNombre("España");
		mip2.setContinente(mic);
		getSession().save(mip2);
		
		
		List<Pais> paises = getSession().createCriteria(Pais.class)
				.createAlias("continente", "continenteBuscado")
				.add(Restrictions.like("continenteBuscado.nombre","Europa"))
				.list();
			for(Pais p: paises){
			assertThat(p.getContinente().getNombre()).isEqualTo(mic.getNombre());
		}
	
	}
	
//	4- Hacer con junit un test que busque todos los países
//	   cuya capital están al norte del trópico de cáncer.
	@Test
	@Transactional
	@Rollback(true)
	public void Test_que_busca_paises_con_capital_en_Norte_del_tropico_de_cancer(){
		
		Ubicacion miu = new Ubicacion();
		Ciudad mic= new Ciudad();
		Pais mip3 = new Pais();
		
		//Sets Ubicacion
		miu.setLatitud(1236);
		miu.setLongitud(5678);
		getSession().save(miu);
		
		
		//Sets Ciudad
		mic.setNombre("Whashington DC");
		mic.setPais(mip3);
		mic.setUbicacion(miu);
		getSession().save(mic);
		
		
		//Sets Pais
		mip3.setNombre("EEUU");
		mip3.setCapital(mic);
		//mip3.setCiudad(mic);
		getSession().save(mip3);
		
		// Otro Pais...
		Ubicacion miu4 = new Ubicacion();
		Ciudad mic4= new Ciudad();
		Pais mip4 = new Pais();
		
		//Sets Ubicacion
		miu4.setLatitud(1200);
		miu4.setLongitud(999);
		getSession().save(miu4);
				
				
		//Sets Ciudad
		mic4.setNombre("Brasil");
		mic4.setPais(mip4);
		mic4.setUbicacion(miu4);
		getSession().save(mic4);
				
				
		//Sets Pais
		mip4.setNombre("Brasilia");
		mip4.setCapital(mic4);
		//mip3.setCiudad(mic);
		getSession().save(mip4);
		
		List<Pais> paises = getSession().createCriteria(Pais.class)
				.createAlias("capital", "CiudadBuscada" )
				.createAlias("CiudadBuscada.ubicacion", "UbicacionBuscada" )
				.add(Restrictions.gt("UbicacionBuscada.latitud",1234)) // gt(mayor que el tropico de cancer
				.list();
				
				for(Pais p: paises){
					
				assertThat(p.getCapital().getUbicacion().getLatitud()).isGreaterThan(1234);
				System.out.println(p.getCapital().getNombre());
								   }

	}
	
// 5- Hacer con junit un test que busque todas las ciudades del hemisferio sur	
	@Test
	@Transactional
	@Rollback(true)
	public void Test_Que_Busca_ciudades_del_hemisferio_sur(){
		
		Ciudad mic= new Ciudad();
		Ubicacion miu = new Ubicacion();
		
		mic.setNombre("Buenos Aires");
		mic.setUbicacion(miu);
		getSession().save(mic);
		
		//Sets Ubicacion
		miu.setLatitud(-1234);
		miu.setLongitud(999);
		getSession().save(miu);
		
		List<Ciudad>ciudades= getSession().createCriteria(Ciudad.class)
				.createAlias("ubicacion", "ubicacionBuscada" )
				.add(Restrictions.lt("ubicacionBuscada.latitud", 0)).list();
		
		for(Ciudad c :ciudades){
		assertThat(c.getUbicacion().getLatitud()).isLessThan(0);
		}		
	}
	
	
	
	
	
	
	
	
}
