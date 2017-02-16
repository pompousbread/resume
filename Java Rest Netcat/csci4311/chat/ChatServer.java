/*
    Ryan Heinich
 */

package csci4311.chat;



import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    

	
	// the HashMap of active groups and their corresponding Group object
	private HashMap<String,Group> groupMap;
	
	// the HashMap of active users and their corresponding DataOutputStream (for the sending of messages)
	private HashMap<String, DataOutputStream> userMap;
	
	// the HashMap of active users and their corresponding message History
	private HashMap<String, ArrayList<String>> userHistoryMap;

	// the RestServer object to handle Rest requests
	private RestfulServer restServer;
	
	

	

	public ChatServer(ServerSocket welcomeSocket, int restPort) throws Exception
	{
		Socket connectionSocket = null;
		groupMap = new HashMap<String,Group>();
		userMap = new HashMap<String, DataOutputStream>();
		userHistoryMap = new HashMap<String, ArrayList<String>>();

		// the RestServer object to handle Rest requests
		restServer = new RestfulServer( this, restPort );
		
		// While loop to handle arbitrary sequence of clients making requests
    	while(true) 
    	{
    		// accept a new client connection
			connectionSocket = welcomeSocket.accept();
			System.out.println( "Client Made Connection");

			// create a new TextMsgpServer thread to handle communication with the newly connected client
			TextMsgpServer thread = new TextMsgpServer( this, connectionSocket);
			thread.start();
    	} // end while; loop back to accept a new client connection
	}



	public static void main(String argv[]) throws Exception 
	{

		int port = argv.length > 0 ? Integer.parseInt(argv[0]) : 4311 ;
		

		int restPort = argv.length > 1 ? Integer.parseInt(argv[1]) : 8311 ;
		 
   		ServerSocket welcomeSocket = new ServerSocket( port, 0);
    	System.out.println("Server Ready for Connection");

		new ChatServer(welcomeSocket, restPort);
		
		
	} // end main

    

    //getter methods

	// getter for groupTable
	public HashMap<String,Group> getGroupMap(){
		return groupMap;
	}

	// getter for userTable
	public HashMap<String, DataOutputStream> getUserTable(){
		return userMap;
	}
	
	// getter for the list of logged-in users
	public Set getUsers(){
		return userMap.keySet();
	}
	
	// getter for the list of logged-in groups
	public Set getGroups(){
		return groupMap.keySet();
	}

	
	// gets the list of users in a group
	public ArrayList<String> getUserList( String groupName ){
		return groupMap.get( groupName ).getUsers();
	}

	// gets the list of messages sent to a group or a user
	public ArrayList<String> getGroupHistory( String groupName ){
		return groupMap.get(groupName).getHistory();
	}
	
	// gets the list of messages sent to a group or a user
	public ArrayList<String> getUserHistory( String userName ){
		return userHistoryMap.get(userName);
	}
	
	// gets the list of DataOutputStream for a given list of users
	public ArrayList<DataOutputStream> getOutStreamList( ArrayList<String> userList ){
		ArrayList<DataOutputStream> outStreamList = new ArrayList<DataOutputStream>();
		for ( String u: userList )
		{
			outStreamList.add( userMap.get( u ) );
		}
		return outStreamList;
	}
	

	// checks whether a group exists
	public boolean groupExists( String groupName ){
		return groupMap.containsKey( groupName );
	}

	// checks whether a user is already in System
	public boolean userExists( String userName ){
		return userMap.containsKey( userName );
	}

	// checks whether a user is a member of a group
	public boolean isInGroup( String userName ){
		for ( Group g : groupMap.values() )
		{
			if ( g.hasUser( userName ) )
				return true;
		}
		return false;
	}
	
	// checks whether a user is a member of a group
	public boolean isInGroup( String userName, String groupName ){
		return groupMap.get(groupName).hasUser(userName);
	}




	// adds a group
	public void addGroup( String groupName)
	{
		groupMap.put(groupName, new Group(groupName) );
		// if groupName is ReservedGroup, then this is the initialization procedure to add the user record to the system
		if ( groupName.equals("ReservedGroup")){
			System.out.println("init procedure to add the user record to the system");
		}
		else
		{
			System.out.println("A new group is created: "+groupName);
		}
	}
        // adds the user to a group
	public void addUserToGroup ( String userName, String groupName )
	{		
		Group temp = groupMap.get( groupName );
		temp.addUser( userName );
		System.out.println("user "+userName+" is added to #"+groupName);
	} 

    
	
	// remove the username and stream mapping.
	public void removeUserFromSystem( String userName ){
		userMap.remove(userName);
		userHistoryMap.remove(userName);
	}
	
	// add a message to history
	public void addUserHistory( String userName, String message )
	{
		userHistoryMap.get(userName).add( message );
	}
	
	

    
    
	public void removeUserFromGroup ( String userName, String groupName )
	{
		Group temp = groupMap.get( groupName );
		temp.removeUser( userName );		

		if ( groupName.equals("ReservedGroup")){
			groupMap.remove(groupName);
		}
		
	} 
    public void addUserToSystem( String userName, DataOutputStream outStream ){
		userMap.put( userName, outStream );
		userHistoryMap.put(userName, new ArrayList<String>());
	}

	public void addHistory ( String message, String groupName )
	{
		groupMap.get( groupName ).getHistory().add( message );
	}

} // end class ChatServer

