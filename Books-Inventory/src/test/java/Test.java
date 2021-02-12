import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.sagar.entity.Address;
import com.sagar.entity.City;
import com.sagar.entity.Country;
import com.sagar.entity.State;
import com.sagar.entity.UserEntity;
import com.sagar.main.EntityDAO;

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
		

		City city=EntityDAO.fetchCity("Navsari");
		State state= city.getState();
		Country country=state.getCountry();
		
		
		assertEquals(new String("Navsari") ,city.getCityName());
		
		System.out.println(city.getCityName());
		System.out.println(state.getstateName());
		System.out.println(country.getName());
		
		
		Address addr1 = new Address();
		addr1.setAddrLine1("12 house number,smtg society");
		addr1.setAddrLine2("Behind school");
		addr1.setAddrLine3("Jalalpore");
		addr1.setAddrType("home");
		addr1.setPincode("396421");
		addr1.setUser(user);
		addr1.setCity(city);
		addr1.setCountry(country);
		addr1.setState(state);

		user.getAddrList().add(addr1);

		em.persist(user);
		//em.merge(user);
		em.getTransaction().commit();
		em.close();
		emf.close();
	}

}
