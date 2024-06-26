package ie.atu.sw;

public class Application {

	private String applicationName;
	private String problem;
	private String status;
	private String platform;
	private String dateTime;
	private int applicantID;
	
	//constructor
	public Application(String name, String pr, String st, String pl, String dt, int id) {
		applicationName = name;
		problem = pr;
		status = st;
		platform = pl;
		dateTime = dt;
		applicantID =id;
	}
	
	//set status
	//setter
	public void setStatus(String s) {
		status =s;
	}
	
	
	//getters
	public String getAll()
	{
		return applicationName+"*"+problem+"?"+status+"*"+platform+"?"+dateTime+"*"+applicantID;
	}
	
	String getName() {
		return applicationName;
	}
	 
	int getAID() {
		return applicantID;
	}
	
	String getProblem() {
		return problem;
	}
	
	String getStatus() {
		return status;
	}
	
	String getPlatform() {
		return platform;
	}
	
	
	String getDateTime() {
		return dateTime;
	}
	
	
}
