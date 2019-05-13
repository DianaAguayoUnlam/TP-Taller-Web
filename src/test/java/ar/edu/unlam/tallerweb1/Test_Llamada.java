package ar.edu.unlam.tallerweb1;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Central;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import ar.edu.unlam.tallerweb1.modelo.Empresa;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import ar.edu.unlam.tallerweb1.modelo.Llamada;
import ar.edu.unlam.tallerweb1.modelo.Sucursal;

public class Test_Llamada extends SpringTest {
	
	@Test
	@Transactional
	@Rollback(true)
	public void TestQueIngresaLlamada()
	{
		//Llamada
		Llamada mil = new Llamada ();
		mil.setDuracion(0.25); //guardo una llamada
	
		
		
		//Central
		Central mic = new Central();
		mic.setNombre("Movistar");
	
		
		//Seteo en llamada una central
		mil.setCentral(mic);
		//N			//1
		
		
	//Conecto con la BD
	getSession().save(mil);
	getSession().save(mic);
	
	//--------------------------------------------------------------------------------
								//Recuperamos o Consultamos
	//Creamos otro objeto para Llamada y para Central
	Llamada mil2 = getSession().get(Llamada.class, mil.getId());
	
	
	//Objeto 2 = Clase Central , id
//En el objeto 2 estamos buscando en la clase Central el Id del objeto Central 1  
	Central mic2 = getSession().get(Central.class, mic.getId());
				//Objeto que guardaste y creaste en la BD
								//Obtener en la tabla Central el objeto central con su Id
	//El id Sirve para identificar el objeto que ya he creado anteriormente 
	

	//--------------------------------------------------------------------------------
	
//Verificar que se haya guardado correctamente la Llamada en la Bd
	assertThat(mil.getId()).isEqualTo(mil2.getId());
	
//Verificar que se haya guardado correctamente la Central en la Bd
	assertThat(mic.getId()).isEqualTo(mic2.getId());

	
//--------------------------------------------------------------------------------
								//Comparando objetos
	
//Almacenado "Movistar" linea 31
assertThat(mic).isEqualTo(mil.getCentral());		
							 //linea 35
	

								//Comparando por Nombre
				//Juntos Central->Nombre
			//objeto		//string									string
assertThat(mil.getCentral().getNombre()).isEqualTo					(mic.getNombre());
//Trae la central en donde esta la llamada (trae direccion de memoria - nombre -id)
				//.getNombre para pedir que solo me de el nombre
																	//linea 30


System.out.println(mil.getCentral().getNombre());
System.out.println(mic.getNombre());
	}
	
	
	@Test
	@Transactional
	@Rollback(true)
	public void Test_Que_Modifica_Con_Update()
	{
		
		Central mic = new Central ();//Creas Objeto
		mic.setNombre("Central Tanto");// Asignas nombre
		getSession().save(mic); //Lo guardas en la base de datos
		Central mic2 = getSession().get(Central.class, mic.getId()); //Creas otro objeto
		//y le asignas los datos del registro anterior
		
		mic2.setNombre("Central Metatanto"); //Le cambias el nombre al objeto
		getSession().update(mic); //Lo actualizas en la base de datos
		
		Central mic3= getSession().get(Central.class, mic2.getId()); //Creas otro objeto
		assertThat(mic.getNombre()).isEqualTo(mic3.getNombre()); //Verificas que el nombre
		//sea igual al primer objeto (osea que al que le cambiaste el nombre)
		
		System.out.println(mic.getNombre());
		System.out.println(mic3.getNombre());
	}
	

	
	@Test
	@Transactional
	@Rollback(true)
	public void Test_Que_elimina_Sucursal()
	{
		//crar Central
		Central mic = new Central ();	//Creas Objeto
		mic.setNombre("Central Tanto");// Asignas nombre
		getSession().save(mic); 		//Lo guardas en la base de datos
		
		//crear sucursal
		Sucursal mis = new Sucursal ();
		mis.setCalle("arieta");
		mis.setNumero(1234);
		
		//mis.setCentral(mic);
		mic.setSucursal(mis);
		getSession().save(mis);
		
		assertThat(mic.getId()).isNotNull();
		
		
		
//		//No recuperar para borrar ni modificar.. solo para guardar //genera otros objetos
//		Central mic2 = getSession().get(Central.class, mic.getId()); //Creas otro objeto
//		Sucursal mis2 = getSession().get(Sucursal.class, mis.getId());
//		
//		//verifico que se guardaron en la BD
//		assertThat(mic2.getId()).isEqualTo(mic);
//		assertThat(mis2.getId()).isEqualTo(mis);
		
		//elimino una sucursal
		getSession().delete(mis);
	
		Sucursal mis3=getSession().get(Sucursal.class, mis.getId());
		assertThat(mis3).isNull();
		
		
		
		
//		Central mic3= getSession().get(Central.class, mic2.getId()); //Creas otro objeto
//		assertThat(mic.getNombre()).isEqualTo(mic3.getNombre()); //Verificas que el nombre
		//sea igual al primer objeto (osea que al que le cambiaste el nombre)
		
		System.out.println(mic.getNombre());
		System.out.println(mis3);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void TestQueTraeTodasLasEmpresasConNombreTelefonica()
	{
		//Creo una central
		Central mic = new Central ();
		mic.setNombre("Central");
		//Creo una sucursal
		Sucursal mis = new Sucursal ();
		mis.setNumero(12);
		mis.setCalle("Sucursal");
		//Creo una empresa
		Empresa mie = new Empresa ();
		mie.setNombre("Telefonica");
		
		Empresa mie2 = new Empresa ();
		mie2.setNombre("Personal");
		
		Empresa mie3 = new Empresa ();
		mie3.setNombre("Telefonica");
		
		//Guardo en la Bd
		getSession().save(mie);
		getSession().save(mie2);
		getSession().save(mie3);
		getSession().save(mis);
		getSession().save(mic);
		
		//CRITERIA
		
		List<Empresa> emTel = getSession().createCriteria(Empresa.class)
				.add(Restrictions.like("Nombre","Telefonica")).list();
		

		//como verificar que devuelva bien?			
		
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void Test_Que_Devuelve_Todas_las_sucursales_de_una_empresa()
	{
		//Creo una empresa
				Empresa mie = new Empresa ();
				mie.setNombre("Telefonica");
				
				getSession().save(mie);
				
				Sucursal mis = new Sucursal ();
				mis.setCalle("arieta");
				mis.setNumero(1234);
				mis.setEmpresa(mie);
				
				getSession().save(mis);
				
				List<Sucursal> sucursal= getSession().createCriteria(Sucursal.class).
						add(Restrictions.eq("empresa",mie)).list();
		
	}

	
	@Test
	@Transactional
	@Rollback(true)
	public void Test_Que_Muestre_Todas_Las_Sucursales_conLaLetra_T()
	{
		//Creo una empresa
				Empresa mie = new Empresa ();
				mie.setNombre("Telefonica");
				mie.setNombre("Telecom");
				mie.setNombre("Movistar");
				getSession().save(mie);
				
				Sucursal mis = new Sucursal ();
				mis.setCalle("arieta");
				mis.setNumero(1234);
				mis.setEmpresa(mie);
				
				getSession().save(mis);
				
				List<Sucursal> sucursal= getSession().createCriteria(Sucursal.class)
						.createAlias("empresa", "EmpresaBuscada")
						.add(Restrictions.like("EmpresaBuscada.Nombre","T%")).list();
				
				//Probar si funciona assertThat(sucursal.size()).isEqualTo(2);
				
		
	}

	@Test
	@Transactional
	@Rollback(true)
	public void Test_Que_Muestre_Todas_Las_Llamadas_Menores_a_Dos__Central_FlorencioVarela_y_Empresa_Nombre_Telefonica()
	{
		
		Empresa mie = new Empresa ();//Creas Objeto
		mie.setNombre("Telefonica");
		
		Sucursal mis = new Sucursal ();//Creas Objeto
		mis.setCalle("arieta");
		mis.setEmpresa(mie);
	
		Central mic = new Central ();//Creas Objeto
		mic.setNombre("Central Tanto");// Asignas nombre
		mic.setDireccion("Florencio Varela");
		mic.setSucursal(mis);
		
		Llamada mil = new Llamada ();
		mil.setDuracion(3.0); //guardo una llamada
		
		mil.setCentral(mic);
		
		getSession().save(mie); //Lo guardas en la base de datos
		getSession().save(mis);
		getSession().save(mic);
		getSession().save(mil);
		
		
		Llamada mil2 = getSession().get(Llamada.class, mil.getId());
		
		
		System.out.println("DURACION");
		System.out.println(mil.getDuracion());
		System.out.println(mic.getNombre());
		System.out.println(mie.getNombre());
		System.out.println(mil.getCentral().getNombre());
	
		/*
		List<Llamada> llamadas= getSession().createCriteria(Llamada.class)
			.add(Restrictions.gt("Duracion",2.0))
			.createAlias("central", "CentralBuscada")
			
			.add(Restrictions.like("CentralBuscada.Direccion","Florencio Varela%"))
			.createAlias("CentralBuscada.sucursal", "SucursalBuscada")
			
			.createAlias("SucursalBuscada.empresa", "EmpresaBuscada")
			.add(Restrictions.like("EmpresaBuscada.Nombre","Telefonica%"))
			.list();
		*/
		
		
		
		List<Llamada> llamadas = getSession().createCriteria(Llamada.class)
				.add(Restrictions.gt("Duracion", 2.0))
				.createAlias("central", "CentralBuscada")
				.add(Restrictions.like("CentralBuscada.Direccion", "Florencio Varela"))
				.createAlias("CentralBuscada.sucursal.empresa", "EmpresaBuscada")
				.add(Restrictions.like("EmpresaBuscada.Nombre", "Telefonica")).list();
				
		System.out.println("Llamadas: ");
		System.out.println(llamadas.size());
		
//		System.out.println(mic.getNombre());
		for(Llamada l: llamadas){
			
			assertThat(l.getCentral().getSucursal().getEmpresa().getNombre()).isEqualTo(mie.getNombre());
		
			//Duracion de la llamada
			assertThat(l.getDuracion()).isGreaterThan(2.0);
			
			//Direccion de la calle
			assertThat(l.getCentral().getDireccion()).isEqualTo(mic.getDireccion());
			
			
		
		}
	
	//Modificar el setter de Llamada de setDuracion(double *Duracion*)
		
	}
	
	
	}
	


