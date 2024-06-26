package ie.atu.sw;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;


public class BugList {
	
	//variables
	private LinkedList<Application> bug; //linked list
	ArrayList<String> result; // array list
	private String lines;

	//constructor
	public BugList() {
		bug = new LinkedList<Application>();
	}

	//add name,problem,status,platform,date and bug id from user input
	public synchronized void addApplication(String name, String problem,String status,String platform,String date,int id)
	{
		//add all user input
		Application temp = new Application(name, problem, status, platform, date,id);
		bug.add(temp);
		
		//write to text file
		try {
			
			FileWriter fw = new FileWriter("buglist.txt", true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			//add all name,problem,status,platform,date and bug id onto file 
			lines = name + " " + problem  + " " + status +  " " + platform + " " + date + id + "\n"; 
			bw.append(lines);
			
			
			
			//close files when done
			bw.close();
			fw.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//return all name,problem,status,platform,date and bug id information to view
	public synchronized String displayBugLists()
	{
		Iterator<Application> iter = bug.iterator(); 
		Application temp;
		String result="";
		
		while(iter.hasNext())
		{
			
			temp = iter.next();
			result = result + temp.getName()+"*"+temp.getProblem()+"?"+ temp.getStatus()+"*"+temp.getPlatform()+"?"
			+ temp.getDateTime()+"*"+temp.getAID()+"?";
		}
		
		return result;
	}
	
	//Add all application information to an array list
	public synchronized ArrayList<String> getApplication()
	{
		Iterator<Application> iter = bug.iterator();
		Application temp;
		result= new ArrayList<String>();
		while(iter.hasNext())
		{
			temp = iter.next();

			//add all application
			result.add(temp.getAll());
		}
		

		
		
		return result;
	}
	
	//allow user change status details by finding bug id
	public synchronized void editStatus(int id, String st)
	{
		Iterator<Application> iter = bug.iterator();
		Application temp;
		while(iter.hasNext())
		{
			temp = iter.next();
			
			//when id matches what user is looking for
			if(temp.getAID()==id) {
				temp.setStatus(st); //set users new input for status
			}
		}
	}
	
}
