import org.testng.annotations.DataProvider;

public class DataProviderUtils {

	@DataProvider(name="invalidLoginData")
	public Object[][] getInvalidLoginData() {
		
		return Object[][] {
			 {"invalidUser", "tegEzyt", "User or Password is not valid"},
	         {"mngr650523", "invalidPass", "User or Password is not valid"},
	         {"mngr56", "hsjsd87", "User or Password is not valid"}
	        };
		}

}

}
