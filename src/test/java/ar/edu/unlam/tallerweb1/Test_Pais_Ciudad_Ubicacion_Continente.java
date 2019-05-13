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


public class Test_Pais_Ciudad_Ubicacion_Continente extends SpringTest {

	
	@Test
	@Transactional
	@Rollback(true)
	
	public void TestQueBusqueLosPaisesDeHablaInglesa(){
		
			//pais
			Pais mip = new Pais();
			mip.setNombre("Inglaterra");
			mip.setIdioma("Inglesa");
			mip.setHabitantes(180);
			
			//Guardo en BD
			getSession().save(mip);
		
		
			List<Pais> mipais = getSession().createCriteria(Pais.class)
			.add(Restrictions.like("idioma", "Inglesa"))
			.list();
			
			
			for(Pais mip2: mipais)
			{
			assertThat(mip2.getIdioma()).isEqualTo("Inglesa");
			}
			
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	@Transactional
	@Rollback(true)
	public void TestQueBusqueTodosLosPaisesDelContinenteEuropeo(){
		
		Pais mip = new Pais();
		mip.setNombre("Francia");
		
		
		Continente mic = new Continente();
		mic.setNombre("Europeo");
		
		mip.setContinente(mic);
		
		
		getSession().save(mip);
		getSession().save(mic);
		
		List<Pais> mipais = getSession().createCriteria(Pais.class)
				.createAlias("continente","ContinenteBuscado")
				.add(Restrictions.like("ContinenteBuscado.nombre", "Europeo"))
				.list();
		
		
		
		for(Pais m: mipais)
		{
			assertThat(m.getContinente().getNombre()).isEqualTo(mic.getNombre());
		
		}
		
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	@Transactional
	@Rollback(true)
	public void Test_que_busque_todos_los_países_cuya_capital_están_al_norte_del_trópico(){
		
		Pais mip = new Pais();
		mip.setNombre("Pais1");;
		
		
		
		Ubicacion miu = new Ubicacion();
		miu.setLatitud(3344);
		miu.setLongitud(4433);
		
		
		getSession().save(mip);
		getSession().save(miu);
		
		
		List<Pais> mipa = getSession().createCriteria(Pais.class)
				.createAlias("continente", "ContinenteBuscado")
				.createAlias("ContinenteBuscado.ubicacion", "UbicacionBuscada")
				.add(Restrictions.gt("UbicacionBuscada.latitud", miu.getLatitud()))
				.add(Restrictions.gt("UbicacionBuscada.longitud", miu.getLongitud()))
				.list();

		for(Pais p: mipa)
		{
			assertThat(p.getCapital().getUbicacion().getLatitud()).isEqualTo(miu.getLatitud());
			
			assertThat(p.getCapital().getUbicacion().getLongitud()).isEqualTo(miu.getLongitud());
		}
		
	}
	
	//Hacer con junit un test que busque todas las ciudades del hemisferio sur
	@SuppressWarnings("unchecked")
	@Test
	@Transactional
	@Rollback(true)
	
	public void Test_Ciudades_Hemisferio_Sur(){
		
		Ciudad mic = new Ciudad();
		mic.setNombre("Cordoba");
		
		Ubicacion miu = new Ubicacion();
		miu.setLatitud(1234);
		miu.setLongitud(467);
		
		mic.setUbicacion(miu);
		
		
		getSession().save(mic);
		getSession().save(miu);
			
			List<Ciudad> miciu = getSession().createCriteria(Ciudad.class)
			.createAlias("ubicacion", "CiudadBuscada")
			.add(Restrictions.eq("CiudadBuscada.latitud",1234))
			.add(Restrictions.eq("CiudadBuscada.longitud", 4567)).list();
			
			
			for(Ciudad m: miciu)
			{
				assertThat(m.getUbicacion().getLatitud()).isEqualTo(miu.getLatitud());
				
				assertThat(m.getUbicacion().getLongitud()).isEqualTo(miu.getLongitud());
				
				
			
			}
		}
	}