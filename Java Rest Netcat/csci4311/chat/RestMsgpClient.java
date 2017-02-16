/*
    Ryan Heinich
 */

package csci4311.chat;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;


public class RestMsgpClient{
	

 	private String request;
	private int numRcvdMsg;
 	private String userName;
 	private HttpClient  client;
 	private HttpGet httpRequest;
 	private HttpResponse response;
 	private BufferedReader reader;
 	private JsonReader jsReader;
 	private JsonObject jsObject;
	private JsonArray jsArray;
	private PoolingHttpClientConnectionManager connManager ;
 	public RestMsgpClient(String userName) throws Exception
 	{
 		this.userName = userName;
 		this.request = "";
 		numRcvdMsg = 0;
 		connManager = new PoolingHttpClientConnectionManager();
 		
 		httpRequest = null;
 		response = null;
 		reader = null;
    	jsReader = Json.createReader(reader);
 	}

    

    


    public ArrayList<String> groups() throws Exception
    {
    	ArrayList<String> groups = new ArrayList<String>();
    	
    	this.connManager = new PoolingHttpClientConnectionManager();
    	client = HttpClientBuilder.create().setConnectionManager(connManager).build();
    	httpRequest = new HttpGet("http://localhost:8311/groups");
    	response = client.execute(httpRequest);
    	reader = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
    	jsReader = Json.createReader(reader);
    	
    	jsObject = jsReader.readObject();
    	if (jsObject.toString().equals("{}"))
    		return null;
    	
    	jsArray = jsObject.getJsonArray("groups");
    	for ( int i=0; i<jsArray.size();i++ )
    	{
    		groups.add( jsArray.getString(i));
    	}
      	
    	this.connManager.shutdown();
    	//groups = new ArrayList<String> ((String[]) jsReader.readArray().getValuesAs(String));
      	return groups;

    }

    public int send(MsgpMessage msg) throws Exception{
    	request = "msgp send\nfrom: "+msg.getFrom()+"\n";
    	for ( String t: msg.getTo())
    	{
    		request += ("to: "+ t +"\n");
    	}
    	request += "\n"+msg.getMessage()+"\n\n";
    	
    	
    	this.connManager = new PoolingHttpClientConnectionManager();
    	client = HttpClientBuilder.create().setConnectionManager(connManager).build();
    	HttpPost post = new HttpPost("http://localhost:8311/message");
    	StringEntity input = new StringEntity(request);
    	post.setEntity(input);
    	
    	response = client.execute(post);

      	
    	this.connManager.shutdown();
      	return response.getStatusLine().getStatusCode();
    }

    
    
    public ArrayList<String> users(String group) throws Exception
    {
    	ArrayList<String> users = new ArrayList<String>();
    	
    	this.connManager = new PoolingHttpClientConnectionManager();
    	client = HttpClientBuilder.create().setConnectionManager(connManager).build();
    	
    	// get users of the entire system
    	if ( group == null)
    		httpRequest = new HttpGet("http://localhost:8311/users");
    	// get users of a group
    	else
    		httpRequest = new HttpGet("http://localhost:8311/group/"+group);
    	
    	response = client.execute(httpRequest);
    	reader = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
    	jsReader = Json.createReader(reader);
    	
    	jsObject = jsReader.readObject();
    	if (jsObject.toString().equals("{}"))
    		return null;
    	jsArray = jsObject.getJsonArray("users");
    	for ( int i=0; i<jsArray.size();i++ )
    	{
    		users.add( jsArray.getString(i));
    	}
      	
    	this.connManager.shutdown();
    	//groups = new ArrayList<String> ((String[]) jsReader.readArray().getValuesAs(String));
      	return users;
    }
    
    
    public int leave(String user, String group) throws Exception{
    	
    	this.connManager = new PoolingHttpClientConnectionManager();
    	client = HttpClientBuilder.create().setConnectionManager(connManager).build();
    	HttpDelete delete = new HttpDelete("http://localhost:8311/group/"+group+"/"+user);

    	response = client.execute(delete);
      	
    	this.connManager.shutdown();
   
      	return response.getStatusLine().getStatusCode();
	}

    public List<MsgpMessage> history(String target) throws Exception{
    	List<MsgpMessage> history = new ArrayList<MsgpMessage>();
    	
    	this.connManager = new PoolingHttpClientConnectionManager();
    	client = HttpClientBuilder.create().setConnectionManager(connManager).build();
    	httpRequest = new HttpGet("http://localhost:8311/messages/"+target);
    	response = client.execute(httpRequest);
    	reader = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
    	jsReader = Json.createReader(reader);
    	
    	jsObject = jsReader.readObject();
    	if (jsObject.toString().equals("{}"))
    		return null;
    	jsArray = jsObject.getJsonArray("messages");
    	for ( int i=0; i<jsArray.size();i++ )
    	{
    		history.add( this.decodeMessage(jsArray.getString(i)));
    	}
      	
    	this.connManager.shutdown();
      	return history;
    }



    

    private MsgpMessage decodeMessage(String msg)
    {

        msg = msg.substring( msg.indexOf("from") );

		String sender = msg.split("\n",2)[0];
		sender = sender.split(" ")[1];
		String message = msg.split("\n\n",2)[1];
		if ( message.contains("\n") )
			message = message.substring(0,message.indexOf("\n"));

		// return the message in a MsgpMessage object, recipients are not needed here
		return new MsgpMessage(sender,null,message);
    }
    public ArrayList<String> join(String user, String group) throws Exception
    {
    	ArrayList<String> users = new ArrayList<String>();
    	
    	this.connManager = new PoolingHttpClientConnectionManager();
    	client = HttpClientBuilder.create().setConnectionManager(connManager).build();
    	HttpPost post = new HttpPost("http://localhost:8311/group/"+group);
    	StringEntity input = new StringEntity("user="+user);
    	post.setEntity(input);

        //build in http method.
    	response = client.execute(post);
    	reader = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
    	jsReader = Json.createReader(reader);
    	
        //reads incoming Json object.
    	jsObject = jsReader.readObject();
    	if (jsObject.toString().equals("{}"))
    		return null;
    	jsArray = jsObject.getJsonArray("users");
    	for ( int i=0; i<jsArray.size();i++ )
    	{
    		users.add( jsArray.getString(i));
    	}
      	
    	this.connManager.shutdown();
      	return users;
    }


    public List<MsgpMessage> getNewMessages()
    {
    	List<MsgpMessage> messages = new ArrayList<MsgpMessage>();
    	
    	try
		{
    		PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
	    	HttpClient client = HttpClientBuilder.create().setConnectionManager(connManager).build();
	    	HttpGet httpRequest = new HttpGet("http://localhost:8311/messages/@"+userName);
	    	HttpResponse response = client.execute(httpRequest);
	    	BufferedReader reader = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
	    	jsReader = Json.createReader(reader);
	    	
	    	this.connManager.shutdown();
	    	
	    	jsObject = jsReader.readObject();
	    	
	    	if (jsObject.toString().equals("{}")) {} // no action
	    	else
	    	{
	    		jsArray = jsObject.getJsonArray("messages");
		    	for ( int i=this.numRcvdMsg; i<jsArray.size();i++ )
		    	{
		    		messages.add( this.decodeMessage(jsArray.getString(i)));
		    		numRcvdMsg++;
		    	}
	    	}	
	    	reader.close();
	    	jsReader.close();
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}	
    	return messages;
    	
    }
	
}

