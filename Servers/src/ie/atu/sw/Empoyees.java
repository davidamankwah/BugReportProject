package ie.atu.sw;

public class Empoyees {

	//variable
	private String email;
	private String employeeID;

	//constructor
	public Empoyees(String em, String id){
	   email = em;
	   employeeID = id;
	}
	
	
	//getters
	String getEmail() {
		return email;
	}
	
	
	String getID() {
		return employeeID;
	}
	
}
