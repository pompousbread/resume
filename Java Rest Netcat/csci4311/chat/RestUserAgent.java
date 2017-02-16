/*
    Ryan Heinich
 */

package csci4311.chat;


import java.util.ArrayList;
import java.util.Scanner;

public class RestUserAgent extends Thread {
	private ArrayList<String> recipients;
	// outgoing message
	private String messageOutBound;
	private String user;//user name
	private RestMsgpClient msgp;// message protocol

	
	@SuppressWarnings("all")
	public RestUserAgent (String user, String server, int port) throws Exception
	{
		this.user = user;
		messageOutBound= "";
		recipients = new ArrayList<String>();
		
		// start polling thread
		this.start();
		
		// creates a socket
		System.out.println("Registered "+user+" with REST server "+server+"on port 8311.");
		// init RestMsgpClient
		msgp = new RestMsgpClient( user );  
		
		msgp.join(user,"ReservedGroup");
		msgp.leave(user,"ReservedGroup");

		Scanner input = new Scanner( System.in );
		
		// the users command
		String command = "";
        //the pieces of the command string.
		String[] commandParts = null;

		int replyCode = 0;

		
		while(true)
		{
			// prints the specified prompt
			System.out.print("\n"+user+" : ");
			
			// read user's input
			command = input.nextLine();

			if ( !command.startsWith("send") ){
				commandParts = command.split(" ");
			
				if ( commandParts[0].equals("join") ){
					ArrayList<String> users = msgp.join( user, commandParts[1] );		 
					int userCount = users == null ? 0 : users.size(); 
					System.out.println("joined #"+commandParts[1]+" with "+userCount+" current member");
						

				}

				else if ( commandParts[0].equals("leave") ){
					
					replyCode = msgp.leave( user, commandParts[1] );
					
                                        
					switch (replyCode)
					{
						case 200:
						System.out.println(user+" successfully left group "+commandParts[1]);
						break;
						case 401:
						System.out.println(user+" is not a member of the group");
						break;
						case 400:
						System.out.println("group "+commandParts[1]+" does not even exist!!!");
						break;
						default:
						System.out.println("I don't know what is going on ?!?");
					}
				}

				else if ( commandParts[0].equals("groups") ){

					ArrayList<String> groups = (ArrayList<String>)msgp.groups();

					ArrayList<String> users;
					
					if (groups != null)
					{
						for ( String g: groups)
						{
							
							users = (ArrayList<String>)msgp.users( g );							
							int userCount = users == null ? 0 : users.size(); 
							System.out.println("#"+ g +" has "+userCount+" members");
						}
					}
					
				}

				else if ( commandParts[0].equals("users") ){
					
					ArrayList<String> users;
					if ( commandParts.length > 1)
						users = (ArrayList<String>)msgp.users(commandParts[1]);
					else
						users = (ArrayList<String>)msgp.users(null);

					if (users != null){
						for ( String u: users){
							System.out.println("@"+u);
						}
					}
				
				}
				
				
				else if ( commandParts[0].equals("users") )
				{
					
					ArrayList<String> users = (ArrayList<String>)msgp.users(commandParts[1]);
					if (users != null){
						for ( String u: users)
						{
							System.out.println("@"+u);
						}
					}
				
				}

				else if ( commandParts[0].equals("history") )
				{
					
					ArrayList<MsgpMessage> history = (ArrayList<MsgpMessage>)msgp.history( commandParts[1] );

					if ( history != null )
						this.printMessages( history );
				}
					
			}
                        //else send!
			else
			{
				extractSendCommand( command );

				
				replyCode = msgp.send(new MsgpMessage(user,recipients,messageOutBound));
				if (replyCode == 402)
				{
					System.out.println("one or more recipients do not exist!!!");
				}
			}
			
		}// end of while loop
	}


	// the main method
	public static void main( String args[]) throws Exception{
                //string parsing
		if (args.length < 1 || args.length > 4){
			System.out.println("Wrong number of arguments!!!");
			System.exit(0);
		}
		
		String user = args[0];
		String server = args.length > 1 ? args[1] : "localhost";
		int port = args.length == 3 ? Integer.parseInt(args[2]) : 4311 ;
		// REST default 8311
		
		new RestUserAgent(user, server, port);
	}// end of main


	public void run()
	{
		while( true )
		{
			try
			{
				Thread.sleep(2000);
			}
			catch( InterruptedException ie)
			{
				ie.printStackTrace();
			}
			ArrayList<MsgpMessage> messages = (ArrayList<MsgpMessage>) msgp.getNewMessages();
			
			this.printMessages(messages);
		}	
	} // end of run method
	


	// helper method to split the send command into the recipient list and the message
	private void extractSendCommand(String command){
		recipients.clear();
		String subStrings[] = command.split(" ",2);
		
		do{
			subStrings = subStrings[1].split(" ",2);

			recipients.add( subStrings[0] );
		}
		while( subStrings[1].startsWith("@") || subStrings[1].startsWith("#") );

		messageOutBound = subStrings[1];
		
	}// end of method extractSendCommand


	public void deliver(MsgpMessage message){
		System.out.println("["+message.getFrom()+"] "+message.getMessage() );

		if ( !message.getFrom().equals( user ) )
			System.out.print("\n"+user+" : ");
	}

        
	public void printMessages(ArrayList<MsgpMessage> history){
		for (MsgpMessage message : history )
		{
			System.out.println("("+message.getFrom()+") "+message.getMessage() );
		} 	
	}
}
