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
	
//	4- Hacer con junit un test que busque todos los países cuya capital están al norte del trópico de cáncer.


	@SuppressWarnings("unchecked")
	@Test
	@Transactional
	@Rollback(true)
	public void Test_que_busque_todos_los_países_cuya_capital_están_al_norte_del_trópico(){
		
		Pais mip = new Pais();
		Ubicacion miu = new Ubicacion();
		Ciudad mic = new Ciudad();
		
		//Pais
		mip.setNombre("Pais1");;
		mip.setCapital(mic);
		
		//Ciudad
		mic.setNombre("Ciudad1");
		
		mic.setUbicacion(miu);
	
		
		//Ubicacion
		miu.setLatitud(3344);
		miu.setLongitud(4433);
		
		//save
		getSession().save(mip);
		getSession().save(mic);
		getSession().save(miu);
		
		
		List<Pais> mipa = getSession().createCriteria(Pais.class)
				.createAlias("capital", "CiudadBuscada")
				.createAlias("CiudadBuscada.ubicacion", "UbicacionBuscada")
				.add(Restrictions.ge("UbicacionBuscada.latitud", miu.getLatitud()))
				.add(Restrictions.ge("UbicacionBuscada.longitud", miu.getLongitud()))
				.list();

		for(Pais p: mipa)
		{
			assertThat(p.getCapital().getUbicacion().getLatitud()).isEqualTo(miu.getLatitud());
			System.out.println(p.getCapital().getUbicacion().getLatitud());
			
			assertThat(p.getCapital().getUbicacion().getLongitud()).isEqualTo(miu.getLongitud());
			System.out.println(p.getCapital().getUbicacion().getLongitud());
		}
	
	
	
	
	}
	
	//5- Hacer con junit un test que busque todas las ciudades del hemisferio sur
		@SuppressWarnings("unchecked")
		@Test
		@Transactional
		@Rollback(true)
		
		public void Test_Ciudades_Hemisferio_Sur(){
			
			Ciudad mic = new Ciudad();
			mic.setNombre("Cordoba");
			
			
			Ubicacion miu = new Ubicacion();
			miu.setLatitud(-4);
			miu.setLongitud(-5);
			
			mic.setUbicacion(miu);
			
			
			getSession().save(mic);
			getSession().save(miu);
				
				List<Ciudad> miciu = getSession().createCriteria(Ciudad.class)
				.createAlias("ubicacion", "CiudadBuscada")
				.add(Restrictions.lt("CiudadBuscada.latitud",0))
				.add(Restrictions.lt("CiudadBuscada.longitud", -1)).list();
				
				
				for(Ciudad m: miciu)
				{
					assertThat(m.getUbicacion().getLatitud()).isEqualTo(miu.getLatitud());
					System.out.println(m.getUbicacion().getLatitud());
					
					assertThat(m.getUbicacionGeografica()).isEqualTo(mic.getUbicacionGeografica());
					System.out.println(m.getUbicacionGeografica());
					
					assertThat(m.getUbicacion().getLongitud()).isEqualTo(miu.getLongitud());
					System.out.println(m.getUbicacion().getLongitud());
					
				
				}
			}
	

}
