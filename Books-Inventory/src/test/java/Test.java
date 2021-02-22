import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.sagar.EntityDaoDemo.EntityDAO;
import com.sagar.entity.Address;
import com.sagar.entity.City;
import com.sagar.entity.Country;
import com.sagar.entity.State;
import com.sagar.entity.UserEntity;

class Test {
	
	@org.junit.jupiter.api.Test
	void test() {
		//fail("Not yet implemented");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Book_Details");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		UserEntity user = new UserEntity();
		user.setFname("Sagar");
		user.setEmail("sagar.solanki@gmail.com");
		user.setLname("Solanki");
		user.setPwd("admin@1234"); 
		
		
		City city = EntityDAO.fetchCity("Adalaj");
		State state = city.getState();
		Country country = state.getCountry();		
		
		Country country1=EntityDAO.getCountry("India");
		List<State> states= country1.getState();
		State state1=states.get(0);
		List<City> cities= state1.getCities();
		City city1=cities.get(3);
		
		//assertEquals(new String("Navsari") ,city.getCityName());
		
		
		
		System.out.println(city1.getCityName());
		System.out.println(state1.getstateName());
		System.out.println(country1.getName());
		
		System.out.println("===============================================================");
		 
		  System.out.println(city.getCityName());
		  System.out.println(state.getstateName());
		  System.out.println(country.getName());
		  
		 
		
		Address addr1 = new Address();		
		addr1.setAddrLine1("By city Testing");
		addr1.setAddrLine2("Behind school");
		addr1.setAddrLine3("Jalalpore");
		addr1.setAddrType("home");
		addr1.setPincode("396421");
		addr1.setCity(city);
		addr1.setCountry(country);
		addr1.setState(state);
		
		Address addr2 = new Address();
		addr2.setAddrLine1("by country testing");
		addr2.setAddrLine2("Behind school");
		addr2.setAddrLine3("Jalalpore");
		addr2.setAddrType("home");
		addr2.setPincode("396421");
		addr2.setCity(city1);
		addr2.setCountry(country1);
		addr2.setState(state1);
		
		user.getAddrList().add(addr1);
		user.getAddrList().add(addr2);

		em.persist(user);
		//em.detach(user);
		/*
		 * addr2.setAddrLine1("South Africa Zoo"); user.setLname("BhakhriWala");
		 */
		//addr1.setCity(EntityDAO.fetchCity("Navsari"));
		//em.merge(user);
		em.getTransaction().commit();
		em.close();
		emf.close();
	}

}
