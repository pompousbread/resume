/*
    Ryan Heinich
 */
package csci4311.chat;


import java.util.*;

public class Group
{
	
	// the name of the group
	private String name;
	// the ArrayList that saves the group's members' names
	private ArrayList<String> users;
	// the LinkedList that saves the group's chat history
	private ArrayList<String> history;

	
	// constructor
	public Group(String name){	
		this.name = name; 
		users = new ArrayList<String>();
		history = new ArrayList<String>();
	}


	//gets name
	public String getName(){
		return this.name;
	}
        //gets users
	public ArrayList<String> getUsers(){
		return this.users;
	}

        //get users size
	public int getSize(){
		return this.users.size();
	}
	
	// get the group's history
	public ArrayList<String> getHistory(){
		return this.history;
	}
	
	public boolean hasUser( String user ){
		return users.contains( user );
	}

        
	public void addUser (String user){
		users.add( user );
	} 

	public void removeUser (String user){
		users.remove( user );
	}

	public void addHistory( String message ){
		history.add( message );
	}

	public void clearHistory( String message ){
		history.clear();
	}
}
