/*
    Ryan Heinich
 */

package csci4311.chat;



import javax.json.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.util.*;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.*;

public class RestfulServer {
	private ChatServer myServer;
	

	public RestfulServer( ChatServer chatServer, int restPort) throws IOException 
	{
		this.myServer = chatServer;

		InetSocketAddress addr = new InetSocketAddress(restPort);
		HttpServer httpServer = HttpServer.create(addr, 0);
	
		httpServer.createContext( "/users", new UsersHandler(myServer));	
		httpServer.createContext( "/groups", new GroupsGetHandler(myServer));
		httpServer.createContext( "/group/", new GroupHandler(myServer));	
		httpServer.createContext( "/messages/", new MessagesHandler(myServer));
		httpServer.createContext( "/message", new MessageHandler(myServer));	
		httpServer.setExecutor( Executors.newCachedThreadPool());
		httpServer.start();
		System.out.println("RestServer is listening on port "+ restPort);
	}
}




class UsersHandler implements HttpHandler {
	private ChatServer server; 

	public UsersHandler( ChatServer server )
	{
		this.server = server;
	}
	public void handle( HttpExchange exchange) throws IOException {
        System.out.println("user");
		
		Headers responseHeaders = exchange.getResponseHeaders();
		responseHeaders.set( "Content-Type", "text/plain");
		exchange.sendResponseHeaders( 200, 0);

		PrintStream response = new PrintStream( exchange.getResponseBody());
		
		JsonObjectBuilder jsObjectBuilder = Json.createObjectBuilder();
		
		Set<String> users = server.getUsers();
		if (!users.isEmpty()){
			JsonArrayBuilder jsArrayBuilder = Json.createArrayBuilder();
			for ( String u : users )
			{
				jsArrayBuilder.add(u);
			}
			jsObjectBuilder.add("users",jsArrayBuilder.build());
		}
		
		response.println( jsObjectBuilder.build());
		response.close();
	}	
}


class GroupsGetHandler implements HttpHandler {
	private ChatServer server; 
	public GroupsGetHandler ( ChatServer server )
	{
		this.server = server;
	}
	public void handle( HttpExchange exchange) throws IOException {
		
		Headers responseHeaders = exchange.getResponseHeaders();
		responseHeaders.set( "Content-Type", "text/plain");
		exchange.sendResponseHeaders( 200, 0);

		PrintStream response = new PrintStream( exchange.getResponseBody());
		
		JsonObjectBuilder jsObjectBuilder = Json.createObjectBuilder();
        
		
		Set<String> groups = server.getGroups();
		if (!groups.isEmpty())
		{
			JsonArrayBuilder jsArrayBuilder = Json.createArrayBuilder();
			for ( String g : groups )
			{
				jsArrayBuilder.add(g);
			}
			jsObjectBuilder.add("groups",jsArrayBuilder.build());
		}
		
		response.println( jsObjectBuilder.build() );
		response.close();
	}		
}


class GroupHandler implements HttpHandler {
	private ChatServer server; 
	public GroupHandler ( ChatServer server )
	{
		this.server = server;
	}
	public void handle( HttpExchange exchange) throws IOException {
		String requestMethod = exchange.getRequestMethod();
		
		String groupName = "";
		
		String uri = exchange.getRequestURI().toString();
				
		PrintStream response = new PrintStream( exchange.getResponseBody());
				
		Headers responseHeaders = exchange.getResponseHeaders();
		responseHeaders.set( "Content-Type", "text/plain");
		
		if( requestMethod.equalsIgnoreCase( "POST")) {
			
			groupName = uri.substring(7);
			
			BufferedReader body = new BufferedReader( new InputStreamReader( exchange.getRequestBody()));
			String bodyLine = body.readLine();
			String userName = bodyLine.substring(5);
            
            System.out.println("This Group handler is being called and that makes me happy.");
			
			if ( !server.userExists(userName) ) 
				server.addUserToSystem(userName,null);
			 
			if ( !server.groupExists(groupName) )
				server.addGroup( groupName );
			
			server.addUserToGroup(userName,groupName);
		}
		else if (requestMethod.equalsIgnoreCase( "DELETE"))
		{
			uri = uri.substring(7); //separate the string.
			groupName = uri.split("/")[0];
			String userName = uri.split("/")[1];
			
			if ( !server.groupExists(groupName)){	
				exchange.sendResponseHeaders( 400, 0);
				response.close();
				return;
			}
			else if (!server.isInGroup(userName, groupName)){
				exchange.sendResponseHeaders( 401, 0);
				response.close();
				return;
			}
			else{
				server.removeUserFromGroup(userName, groupName);
			}
		}
		else{
			groupName = uri.substring(7);
						
			if ( !server.groupExists(groupName))
			{	
				exchange.sendResponseHeaders( 400, 0);
				response.close();
				return;
			}
		}
		
		exchange.sendResponseHeaders( 200, 0);
		
		JsonObjectBuilder jsObjectBuilder = Json.createObjectBuilder();
		
                //forms a JsonArray with the users.
		ArrayList<String> users = server.getUserList(groupName);
		if (!users.isEmpty()){
			JsonArrayBuilder jsArrayBuilder = Json.createArrayBuilder();
			for ( String u : users ){
				jsArrayBuilder.add(u);
			}
			jsObjectBuilder.add("users",jsArrayBuilder.build());
		}
		
		// send the response
		response.println( jsObjectBuilder.build() );
		response.close();
	}		
}



//this will get messages and group
class MessagesHandler implements HttpHandler {
	private ChatServer server; 
	public MessagesHandler ( ChatServer server )
	{
		this.server = server;
	}
	public void handle( HttpExchange exchange) throws IOException {

		Headers responseHeaders = exchange.getResponseHeaders();
		responseHeaders.set( "Content-Type", "text/plain");
		exchange.sendResponseHeaders( 200, 0);
		PrintStream response = new PrintStream( exchange.getResponseBody());
		JsonObjectBuilder jsObjectBuilder = Json.createObjectBuilder();
		String target = exchange.getRequestURI().toString().substring(10); // takes the messages off
		List<String> messages;
		

		if (target.startsWith("@"))
		{
			messages = server.getUserHistory( target.substring(1)); // takes of the first subString
		}
		
		else
		{
			messages = server.getGroupHistory( target);
		}
		
		if (!messages.isEmpty())
		{
			JsonArrayBuilder jsArrayBuilder = Json.createArrayBuilder();
			for ( String m : messages )
			{
				jsArrayBuilder.add(m);
			}
			jsObjectBuilder.add("messages",jsArrayBuilder.build());
		}
		
		response.println( jsObjectBuilder.build() );
		response.close();
	}		
}


//this will handle the posts.
class MessageHandler implements HttpHandler {
	private ChatServer server; 
	public MessageHandler ( ChatServer server )
	{
		this.server = server;
	}
	public void handle( HttpExchange exchange) throws IOException {
				
		Headers responseHeaders = exchange.getResponseHeaders();
		responseHeaders.set( "Content-Type", "text/plain");
		
		// this will send a response.
		PrintStream response = new PrintStream( exchange.getResponseBody());
		
		// The main message body
		String message = "";
		
		BufferedReader body = new BufferedReader( new InputStreamReader( exchange.getRequestBody()));
		String bodyLine;
		while ( (bodyLine = body.readLine()) != null)
		{
			if (( bodyLine.startsWith("to: @") && !server.userExists(bodyLine.substring(5))) 
			 || ( bodyLine.startsWith("to: #") && !server.groupExists(bodyLine.substring(5))))
			{
				exchange.sendResponseHeaders( 402, 0);
				response.close();
				return;
			}
			
                        //put message back together.
			message += bodyLine+"\n";
		}
		
		if( message.endsWith("\n\n\n")){
			message = message.substring(0, message.length()-1);
		}
		
		exchange.sendResponseHeaders( 200, 0);
		
		TextMsgpServer tms = new TextMsgpServer(server);
		tms.send(message);

		
		response.println( Json.createObjectBuilder().build() );
		
		response.close();
	}		
}

