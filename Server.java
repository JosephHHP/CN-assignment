import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Server
{
	private Socket		 socket = null;
	private ServerSocket server = null;
	private DataInputStream in	 = null;
	private File myObj = null;
	private FileWriter myWriter = null;
	private Scanner myReader = null;


	public Server(int port)
	{
		
		try
		{
			server = new ServerSocket(port);
			System.out.println("Server started");

			System.out.println("Waiting for a client ...");

			socket = server.accept();
			System.out.println("Client accepted");

			
			in = new DataInputStream(
				new BufferedInputStream(socket.getInputStream()));

			String line = "";
			String line2;

			
			while(!line.equals("over"))
			{
				line = in.readUTF();
			if (line.equals("create file f1.txt"))
			{
				try
				{
					myWriter = new FileWriter("f1.txt");
				    myWriter.write("Hello World");
				    myWriter.close();

				}
				catch(IOException i)
				{
					System.out.println(i);
				}
			}
			
			else if (line.equals("cat f1.txt"))
			{
				try
				{
					myObj = new File("f1.txt");
				    Scanner myReader = new Scanner(myObj);
				    while (myReader.hasNextLine()) {
				    String data = myReader.nextLine();
				    System.out.println(data);
				    }
				    myReader.close();
				    
				}
				catch(FileNotFoundException e)
				{
					System.out.println(e);
				}
			}
			
			else if (line.equals("edit f1.txt"))
			{
				try
				{
					line2 = in.readUTF();
					myWriter = new FileWriter("f1.txt");
				    myWriter.write(line2);
				    myWriter.close();

				    }
				    
				
			
				catch(FileNotFoundException e)
				{
					System.out.println(e);
				}
			}
			
			else if (line.equals("delete f1.txt"))
			{
				File myObj = new File("f1.txt"); 
			    if (myObj.delete()) { 
			      System.out.println("Deleted the file: " + myObj.getName());
			    } else {
			      System.out.println("Failed to delete the file.");
			    }
			}
			}	
		
			System.out.println("Closing connection");
			

			
			socket.close();
			in.close();
		}
		catch(IOException i)
		{
			System.out.println(i);
		}
}

	public static void main(String args[])
	{
		Server server = new Server(8080);
	}
}
