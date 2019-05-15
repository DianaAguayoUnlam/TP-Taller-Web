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

public class TestTP extends SpringTest{
	
//	PUNTO 2 Hacer con junit un test que busque todos los paises de habla inglesa.
	
	@Test
	@Transactional
	@Rollback(true)
	public void TestQueBuscaPaisesDeHablaInglesa(){
		Pais mip1= new Pais();
		Pais mip2 = new Pais ();
		Pais mip3 = new Pais ();
		
		mip1.setIdioma("Ingles");
		mip2.setIdioma("Arabe");
		mip3.setIdioma("Portugues");
		
		getSession().save(mip1);
		getSession().save(mip2);
		getSession().save(mip3);
		
	
		
		
		List<Pais>paises= getSession().createCriteria(Pais.class)
				.add(Restrictions.like("idioma", "Ingles")).list();
		
		for(Pais p :paises){
		assertThat(p.getIdioma()).isEqualTo("Ingles");
		}		
	}
	
//	PUNTO 3 Hacer con junit un test que busque todos los paises del continente europeo
	@Test
	@Transactional
	@Rollback(true)
	public void TestQueBuscaTodosLosPaisesEuropeos(){
		
		Pais mip1 = new Pais ();
		Pais mip2 = new Pais ();
		Pais mip3 = new Pais ();
		
		Continente mic1 = new Continente ();
		Continente mic2 = new Continente ();
		Continente mic3 = new Continente ();
		
		mip1.setContinente(mic1);
		mip2.setContinente(mic2);
		mip3.setContinente(mic3);
		
		mic1.setNombre("Europa");
		mic2.setNombre("America");
		mic3.setNombre("Africa");
		
		getSession().save(mip1);
		getSession().save(mip2);
		getSession().save(mip3);
		
		getSession().save(mic1);
		getSession().save(mic2);
		getSession().save(mic3);
		
		
		
		
		List <Pais> paises = getSession().createCriteria(Pais.class)
				.createAlias("continente", "ContinenteBuscado")
				.add(Restrictions.like("ContinenteBuscado.nombre", "Europa"))
				.list();
		
			for(Pais p: paises){
				assertThat(p.getContinente().getNombre()).isEqualTo("Europa");
		}
	
	}
	
//	PUNTO 4 Hacer con junit un test que busque todos los paises cuya capital estan al norte del tropico de cancer.


	@SuppressWarnings("unchecked")
	@Test
	@Transactional
	@Rollback(true)
	public void TestQueBusqueTodosLosPaisesCuyaCapitalEstanAlNorteDelTropico(){
		
		Pais mip1 = new Pais();
		Pais mip2 = new Pais();
		Pais mip3 = new Pais();

		Ciudad mic1 = new Ciudad();
		Ciudad mic2 = new Ciudad();
		Ciudad mic3 = new Ciudad();
	
		Ubicacion miu1 = new Ubicacion();
		Ubicacion miu2 = new Ubicacion();
		Ubicacion miu3 = new Ubicacion();
	
		
		mip1.setNombre("Canada");
		mip2.setNombre("Indonesia");
		mip3.setNombre("Noruega");
		
		
		mic1.setNombre("Montreal");
		mic2.setNombre("Ottawa");
		mic3.setNombre("Yakarta");
		
		mic1.setPais(mip1);
		mic2.setPais(mip1);
		mic3.setPais(mip2);
		
		mip1.setCapital(mic1);
		mip2.setCapital(mic2);
		mip3.setCapital(mic3);
		
		//Montreal
		miu1.setLatitud(45.508889);
		miu1.setLongitud(-73.561667);
		//Ottawa
		miu2.setLatitud(45.424722); 
		miu2.setLongitud(-75.695);
		//Yakarta
		miu3.setLatitud(-6.21462); 
		miu3.setLongitud(106.84513);
		
		mic1.setUbicacion(miu1);
		mic2.setUbicacion(miu2);
		mic3.setUbicacion(miu3);
		
//Valor del tropico de Cancer
		Double tropicoCancer = 23.43722222222222;
				
				getSession().save(mic1);
				getSession().save(mic2);
				getSession().save(mic3);
				
				getSession().save(miu1);
				getSession().save(miu2);
				getSession().save(miu3);
				
				getSession().save(mip1);
				getSession().save(mip2);
				getSession().save(mip3);

		
		List <Pais> mipa = getSession().createCriteria(Pais.class)
				.createAlias("capital", "CapitalBuscada")
				.createAlias("CapitalBuscada.ubicacion", "UbicacionBuscada")
				.add(Restrictions.gt("UbicacionBuscada.latitud", tropicoCancer))
				.list();
		
		
		for(Pais p: mipa)
		{
			assertThat(p.getCapital().getUbicacion().getLatitud()).isGreaterThan(tropicoCancer);
		}
	
	
	
	
	}
	
	// PUNTO 5 Hacer con junit un test que busque todas las ciudades del hemisferio sur
		@SuppressWarnings("unchecked")
		@Test
		@Transactional
		@Rollback(true)
		
		public void TestCiudadesHemisferioSur(){
			
			Ciudad mic1 = new Ciudad();
			Ciudad mic2 = new Ciudad();
			Ciudad mic3 = new Ciudad();
			
			Ubicacion miu1 = new Ubicacion();
			Ubicacion miu2 = new Ubicacion();
			Ubicacion miu3 = new Ubicacion();
			
			miu1.setLatitud(12.555897);
			miu2.setLatitud(-81.75125);
			miu3.setLatitud(32.4544855);
			
			mic1.setUbicacion(miu1);
			mic2.setUbicacion(miu2);
			mic3.setUbicacion(miu3);
			
			getSession().save(mic1);
			getSession().save(mic2);
			getSession().save(mic3);
			

			getSession().save(miu1);
			getSession().save(miu2);
			getSession().save(miu3);
		
			
			//El hemisferio sur se encuentra por debajo del Ecuador, el cual tiene una latitud de 0
			Double ecuador = 0.0;
				
				
				List <Ciudad> miciu = getSession().createCriteria(Ciudad.class)
						   .createAlias("ubicacion", "UbicacionBuscada")
						   .add(Restrictions.lt("UbicacionBuscada.latitud", ecuador))
						   .list();
				
				
				for(Ciudad m: miciu)
				{
					assertThat(m.getUbicacion().getLatitud()).isLessThan(ecuador);
					
				
				}
			}
	

}
