package ie.atu.sw;

//imports
//import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.LinkedList;


public class List {
	
	//variables
	private LinkedList<Empoyees> users;
	private String lines;
	
	//constructor
	public List() {
	users = new LinkedList<Empoyees>();	// TODO Auto-generated constructor stub
	}
	

	//add email and id from user input
	public synchronized void addEmployee(String emails, String employeeIds)
	{
		//add user input
		Empoyees temp = new Empoyees(emails, employeeIds);
		users.add(temp);
		
		
		try {
			//write to text file
			FileWriter fwriter = new FileWriter("employee.txt", true);
			BufferedWriter bwrite = new BufferedWriter(fwriter);
			
			//add all email and id onto file 
			lines = emails + " " + employeeIds + "\n"; 
			bwrite.append(lines);
			
			//close files when done
			bwrite.close();
			fwriter.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
			
	}
	
	//return email and employee Id information
	public synchronized String getList()
	{
		Iterator<Empoyees> iter = users.iterator();
		Empoyees temp;
		String result="";
		while(iter.hasNext())
		{
			
			temp = iter.next();
			result = result + temp.getEmail()+"*"+temp.getID()+"?";
		}
		
		return result;
		
	}
	
	//verify employee
	public synchronized boolean userCheck(String email, String id)
	{
		
		Iterator<Empoyees> iter = users.iterator();
		boolean valid = false;
		Empoyees temp;
		
		while(iter.hasNext())
		{
			temp = iter.next();
			
			//when user entry matches the valid email and id information
			if(temp.getEmail().equalsIgnoreCase(email) && temp.getID().equalsIgnoreCase(id))
			{
				valid = true;
			}
			
			// when false
			else {
				valid = false;
			}
		}
		return valid;
	}

}
