/*
    Ryan Heinich
 */

package csci4311.chat;


import java.util.List;

public class MsgpMessage {
    private String from, body;
    private List<String> to;

    
    public MsgpMessage(String from, List<String> to, String message) {
        this.from = from;
        this.to = to;
        this.body = message;
    }
    public List<String> getTo()      {return to;}
    public String       getMessage() {return body;}
        public String       getFrom()    {return from;}
}

