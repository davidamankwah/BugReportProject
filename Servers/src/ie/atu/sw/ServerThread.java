package ie.atu.sw;

//import
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;


public class ServerThread extends Thread {

	//variables
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private String loginEmail, loginID; //login information
	boolean login; 
	private String message,message2;
	ArrayList<String> searchId;
	String emails, eid, dept, ename; //employee information
	int aid =0,size;
	String aName, aProblem, aStatus, aPlatform, aDate; //applications information
	
	//Shared resource
	List emp;
	BugList bug;
	
	//constructor 
	public ServerThread(Socket s, List e, BugList b)
	{
		socket = s;
		emp = e;
		bug = b;
	}
	
	public void run()
	{
		//3. get Input and Output streams
		try 
		{
			out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(socket.getInputStream());
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Conversation from the server to the client
		try
		{
			do
			{
				//allow user sign up or login
				sendMessage("Press 1 to SIGN UP or Press 2 to LOGIN");
				message = (String)in.readObject();
				
				//Note sign up first to progress
				//option 1 - allow user to enter employee details to register
				if(message.equalsIgnoreCase("1"))
				{
					// sending message to client - enter name, department,email and id
					sendMessage("Please enter your Name");
					ename = (String)in.readObject();
					
					sendMessage("Please enter your Department");
					dept = (String)in.readObject();
					
					sendMessage("Please enter your Email");
					emails = (String)in.readObject();
					
					sendMessage("Please enter your ID");
					eid = (String)in.readObject();
					
					//Add employee email & id to the list....
					emp.addEmployee(emails, eid);
					
				}
				
				//option 2 - allow register user enter valid email and id
				else if(message.equalsIgnoreCase("2"))
				{
					// sending message to client - enter valid email and id
					sendMessage("Please enter your Email");
					loginEmail = (String)in.readObject();
					
					sendMessage("Please enter your ID");
					loginID = (String)in.readObject();
					
					
					// Getting valid login check
					login = emp.userCheck(loginEmail, loginID);
			
					//when email and id login are valid
					if (login == true)
					{
						// sending message to client - that details are correct
						sendMessage("Login Success. Welcome");
					}
					else {
						// sending message to client - that details are incorrect
						sendMessage("Incorrect Login");
					}
					
				}

			}while(message.equalsIgnoreCase("1") || login == false); //repeat when message is option 1 or login fail 
			
			
			//login required 
			//move to next stage
			do
			{
				//user has choices 3-6
				//allow user add application, assign bug record, view records and update status
				sendMessage("Press 3 to ADD an applicant \n"
						+ "or Press 4 to assign the bug record \n"
						+ "or Press 5 to view all bug records not assigned \n"
						+ "or Press 6 to Update the status \n");
				message = (String)in.readObject();
				
				//when user enter choice 3
				if(message.equalsIgnoreCase("3"))
				{
					// sending message to client - to add name, problem,status,platform and date
					sendMessage("Please enter your Name");
					aName = (String)in.readObject();
					
					sendMessage("Please enter the problem");
					aProblem = (String)in.readObject();
					
					sendMessage("Please enter the status - Open, Closed or Assigned");
					aStatus = (String)in.readObject();
					
					sendMessage("Please enter the platform");
					aPlatform = (String)in.readObject();
					
					sendMessage("Please enter the date");
					aDate = (String)in.readObject();
					
					//assign bug id
					aid++;
					
					//Add application name, problem,status,platform, date and bug id to the list....
					bug.addApplication(aName, aProblem, aStatus, aPlatform, aDate,aid);
				}
				
				//when user enter choice 4
				else if(message.equalsIgnoreCase("4"))
				{
					// sending message to client - display message
					sendMessage("Assigned");
				}
				
				//when user enter choice 5
				//display all the application
				else if(message.equalsIgnoreCase("5"))
				{
                     message2 = bug.displayBugLists(); 
					
					
					String[] app = message2.split("\\?");
					
					sendMessage(""+app.length);
					
					
					for(int i=0;i<app.length;i++)
					{
						String[] details = app[i].split("\\*");
						
						sendMessage(details[0]);
						sendMessage(details[1]);
					}
				}
				
				//when user enter choice 6
				else if(message.equalsIgnoreCase("6"))
				{
					
					// sending message to client - enter bug id to update status
					sendMessage("You have chosen to update status details. Please enter the bug id");
					searchId = bug.getApplication();
					
					//get size
					size = searchId.size();
					sendMessage(""+size); 
					
					//display element in list
					for(int i = 0; i < size; i++) {
						sendMessage(searchId.get(i));
					}
					
					String locateId = (String)in.readObject(); //user enters bug id
					
					// sending message to client - enter a new status
					sendMessage("Please enter new status to update status");
					message2 = (String)in.readObject();
					
					//edit status by bug id
					bug.editStatus(Integer.parseInt(locateId), message2);
				}
				
				//ask user to repeat or exit
				sendMessage("Please enter 1 to repeat or 2 to exit");
				message = (String)in.readObject();
				
			}while(message.equalsIgnoreCase("1")); //loops the option 3-6 when user press 1 to continue 
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			
		}
		
		
	}
	
	
	public void sendMessage(String msg)
	{
		try{
			out.writeObject(msg);
			out.flush();
			System.out.println("server>" + msg);
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	
}
