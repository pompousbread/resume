/*
    Ryan Heinich
 */

package csci4311.chat;



import java.util.*;
import java.io.*;
import java.net.*;

public class TextMsgpServer extends Thread{

	private ChatServer server;
	private Socket connectionSocket;

	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	private String request;
	private String response;
	ArrayList<String> receivingUsers ;
	ArrayList<String> receivingGroups ;
	ArrayList<DataOutputStream> outStreamList;	
	String user ;

 
	public TextMsgpServer( ChatServer server, Socket connectionSocket) {
		this.user = null;
		this.server = server;
		this.connectionSocket = connectionSocket;
		request = "";
		response = "";
		receivingUsers = new ArrayList<String>();
		receivingGroups = new ArrayList<String>();
		outStreamList = new ArrayList<DataOutputStream>();

		try
		{
			inputStream = new DataInputStream( connectionSocket.getInputStream());
			outputStream = new DataOutputStream( connectionSocket.getOutputStream());
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
	}
	
	public TextMsgpServer( ChatServer server) {
		this.server = server;
		receivingUsers = new ArrayList<String>();
		receivingGroups = new ArrayList<String>();
		outStreamList = new ArrayList<DataOutputStream>();
	}


	public void run() 
	{
		try {
			while( true ) {
				request = inputStream.readUTF();
				System.out.println("\nRequest: " + request);


				if ( !request.startsWith("msgp send") ){
					String[] requestParts = request.split(" ");
				
					if ( requestParts[1].equals("join") )
						response = this.join( requestParts[2], requestParts[3] );

					else if ( requestParts[1].equals("leave") )
						response = this.leave( requestParts[2], requestParts[3] );

					else if ( requestParts[1].equals("groups") )
						response = this.groups();

					else if ( requestParts[1].equals("users") )
						response = this.users( requestParts[2] );

					else if ( requestParts[1].equals("history") )
						response = this.history( requestParts[2] );
				}
				
				else{
					response = this.send(request);
				}

				System.out.println("Response: "+response);
				outputStream.writeUTF(response);
				response = "";
			} 
		}
		catch( IOException ex){
			
			System.out.println(connectionSocket+" disconnected");
			server.removeUserFromSystem(user);
			
		}
		
	}

	public String join(String user, String group){
			if ( !server.userExists(user) ){
				server.addUserToSystem(user,outputStream);
				this.user = user;
			}

		if ( !server.isInGroup(user) ){
			if ( !server.groupExists(group) )
				server.addGroup( group );
			
			server.addUserToGroup(user,group);

			return "msgp 200 OK";
		}
		else{
			return "msgp 201 No result";
		}
	}

	public String leave(String user, String group){
		if ( !server.groupExists(group) )
			return "msgp 400 Error";
			
		else if ( !server.getGroupMap().get(group).hasUser(user) )
			return "msgp 201 No result";
			
		else{
			server.removeUserFromGroup(user, group);
			return "msgp 200 OK";
		}
	}


	public String groups(){
		if ( server.getGroupMap().isEmpty() )
			return "msgp 201 No result";

		else{	
			String response = "msgp 200\n";
			for ( Group g : server.getGroupMap().values()){
				response += g.getName()+"\n"; 
			}

			return response;
		}	
	}

        
	public String users(String group)
	{
		if ( !server.getGroupMap().containsKey(group) )
			return "msgp 400 Error";
		if ( server.getGroupMap().get(group).getUsers().isEmpty() )
			return "msgp 201 No result";
		else{	
			String response = "msgp 200 OK\n";
			for ( String u : server.getGroupMap().get(group).getUsers() ){
				response += u+"\n"; 
			}
			return response;
		}
	
	}

	public String history(String group){
		if ( !server.groupExists( group ) ){
	 		return "msgp 400 Error";
	 	}
	 	else if ( server.getGroupMap().get(group).getHistory().isEmpty() ){
	 		return "msgp 201 No result";
	 	}
	 	else{
	 		String response = "msgp 200 OK\n";
	 		for ( String h: server.getGroupMap().get(group).getHistory() ){
	 			response += h;
	 		}
	 		return response;
	 	}
	}


	public String send(String request){
		String temp = request.split("\n\n",2)[0];

		String subString = temp.substring(temp.indexOf("to:"));

		String[] otherStrings = subString.split("\n");

		String[] recipients = new String[otherStrings.length];
		for( int i = 0; i < otherStrings.length ; i++){
			recipients[i] = otherStrings[i].substring(4);
		}

		int replyNum = this.sendMessage( recipients , request );

		if ( replyNum == 400)
			return "msgp 400";
		else
			return "msgp 200";
	}

	public int sendMessage(String[] receivers, String message){
            //first clear streams
		receivingUsers.clear();
		receivingGroups.clear();
		outStreamList.clear();

		for( String r: receivers ){
			if ( r.startsWith("@"))
				receivingUsers.add( r.substring(1));
			else
				receivingGroups.add( r.substring(1));
		}

		for ( String user: receivingUsers ){
			if ( !server.userExists(user))
				return 400;
			else
			{
				server.addUserHistory(user, message);
			}
		}
	
		for ( String tempGroup: receivingGroups ){
			if ( !server.groupExists( tempGroup ) )
				return 400;
				
			else{	
				server.addHistory( message, tempGroup );

				for ( String us : server.getUserList( tempGroup ) ){
					// avoid some dups
					if ( !receivingUsers.contains( us ) ){
						receivingUsers.add( us );
					}
				}
			}
		}

		outStreamList = server.getOutStreamList( receivingUsers );

		for ( DataOutputStream d: outStreamList ){
			try { d.writeUTF( message ); }
			catch ( IOException e) { e.printStackTrace(); } 
		}
		return 200;
	}
}
