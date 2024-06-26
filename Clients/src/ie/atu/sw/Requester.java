package ie.atu.sw;

import java.io.*;
import java.net.*;
import java.util.Scanner;
public class Requester{
	
	//variable
	Socket requestSocket;
	ObjectOutputStream out;
 	ObjectInputStream in;
 	String message, message2, message3; 
 	int size;
 	Scanner input;
 	
 	//constructor 
	Requester()
	{
		
		input = new Scanner(System.in);
	}
	void run()
	{
		try{
			//1. creating a socket to connect to the server
			
			requestSocket = new Socket("127.0.0.1", 2004);
			System.out.println("Connected to localhost in port 2004");
			//2. get Input and Output streams
			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(requestSocket.getInputStream());
			
			//3: Communicating with the server from the client
			do
			{
				//client enter choices 1 or 2
				message2 = (String)in.readObject();
				System.out.println(message2);
				message = input.nextLine();
				sendMessage(message);
				
				message3 = message; //set message3
				
				//first choice
				if(message.equalsIgnoreCase("1"))
				{
					//add employee details
					message2 = (String)in.readObject();
					System.out.println(message2);
					message = input.nextLine();
					sendMessage(message);
					
					message2 = (String)in.readObject();
					System.out.println(message2);
					message = input.nextLine();
					sendMessage(message);
					
					message2 = (String)in.readObject();
					System.out.println(message2);
					message = input.nextLine();
					sendMessage(message);
					
					message2 = (String)in.readObject();
					System.out.println(message2);
					message = input.nextLine();
					sendMessage(message);
				}
				
				//second choice
				else if(message.equalsIgnoreCase("2"))
				{
					//enter email and id
					message2 = (String)in.readObject();
					System.out.println(message2);
					message = input.nextLine();
					sendMessage(message);
					
					message2 = (String)in.readObject();
					System.out.println(message2);
					message = input.nextLine();
					sendMessage(message);
					
					//show login status
					message2 = (String)in.readObject();
					System.out.println(message2);
				}
					
				//message3 is 1 or login is fails
			}while(message3.equalsIgnoreCase("1") || message2.equalsIgnoreCase("Incorrect Login"));
			
			do
			{
				//client enter choices 3-6
				message2 = (String)in.readObject();
				System.out.println(message2);
				message = input.nextLine();
				sendMessage(message);
				
				//third choice - enter application details
				if(message.equalsIgnoreCase("3"))
				{
					message2 = (String)in.readObject();
					System.out.println(message2);
					message = input.nextLine();
					sendMessage(message);
					
					message2 = (String)in.readObject();
					System.out.println(message2);
					message = input.nextLine();
					sendMessage(message);
					
					message2 = (String)in.readObject();
					System.out.println(message2);
					message = input.nextLine();
					sendMessage(message);
					
					message2 = (String)in.readObject();
					System.out.println(message2);
					message = input.nextLine();
					sendMessage(message);
					
					message2 = (String)in.readObject();
					System.out.println(message2);
					message = input.nextLine();
					sendMessage(message);
				}
				
				//fourth choice - assign
				else if(message.equalsIgnoreCase("4"))
				{
					message2 = (String)in.readObject();
					System.out.println(message2);
				}
				
				//fifth choice - view application details
				else if(message.equalsIgnoreCase("5"))
				{
					message3 = (String)in.readObject();
					int numDetail = Integer.parseInt(message3);
					
					for(int i=0; i<numDetail;i++)
					{
						message3 = (String)in.readObject();
						System.out.println(message3);
						
						message3 = (String)in.readObject();
						System.out.println(message3);
						
						message3 = (String)in.readObject();
						System.out.println(message3);
						
						message3 = (String)in.readObject();
						System.out.println(message3);
						
						message3 = (String)in.readObject();
						System.out.println(message3);
						
						message3 = (String)in.readObject();
						System.out.println(message3);
					}
				}
				
				//sixth choice - update status by ID
				else if(message.equalsIgnoreCase("6"))
				{
				
					message2 = (String)in.readObject();
					System.out.println(message2);
					
					message2 = (String)in.readObject();
					size = Integer.parseInt(message2);
				
					for(int i = 0; i <size;i++) {
						message2 = (String)in.readObject();
						System.out.println(message2);
					}
					
					message = input.nextLine();
					sendMessage(message);
					
					message2 = (String)in.readObject();
					System.out.println(message2);
					message = input.nextLine();
					sendMessage(message);
					
				}
					
				//a choice to repeat or exit
				message2 = (String)in.readObject();
				System.out.println(message2);
				message = input.nextLine();
				sendMessage(message);
				
			}while(message.equalsIgnoreCase("1")); //repeat again
		}
		catch(ClassNotFoundException e)
		{
			
		}
		catch(UnknownHostException unknownHost){
			System.err.println("You are trying to connect to an unknown host!");
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
		finally{
			//4: Closing connection
			try{
				in.close();
				out.close();
				requestSocket.close();
			}
			catch(IOException ioException){
				ioException.printStackTrace();
			}
		}
	}
	void sendMessage(String msg)
	{
		try{
			out.writeObject(msg);
			out.flush();
			System.out.println("client>" + msg);
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	public static void main(String args[])
	{
		Requester client = new Requester();
		client.run();
	}
}