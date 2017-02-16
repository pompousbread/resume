

package com;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Ryan Heinrich
 * 
 * ch12Ex5Email
 */
public class CreatePersonFrame extends JFrame{
    private JTextField Field1;
    private JTextField Field2;
    private JTextField Field3;
    private JTextField Field4;
    private JTextField Field5;
    private JButton buttonCreate;
    private JButton buttonRemove;
    private JButton buttonView;
    private JLabel Label1;//balance label
    private JLabel Label2;
    private JLabel Label3;
    private JLabel Label4;
    private JLabel Label5;
    private JLabel cExplainLabel;//create
    private JLabel rExplainLabel;//remove
    private JLabel outputLabel;
    private JTextArea message;

    
    
    private JPanel panel;
    private JScrollPane scrollPane;
    
    final int FRAME_WIDTH = 550;
    final int FRAME_HEIGHT = 500;
    
    public CreatePersonFrame(){
        
        
        Label1 = new JLabel("PER_ID:");
        Label1.setLocation(30, 20);
        Label1.setSize(80, 30);
        Label2 = new JLabel("p_name:");
        Label2.setLocation(30, 70);
        Label2.setSize(80, 30);
        Label3 = new JLabel("address:");
        Label3.setLocation(30, 120);
        Label3.setSize(80, 30);
        Label4 = new JLabel("email:");
        Label4.setLocation(30, 170);
        Label4.setSize(80, 30);
        Label5 = new JLabel("gender:");
        Label5.setLocation(30, 220);
        Label5.setSize(80, 30);
        cExplainLabel = new JLabel("Remove only needs PRIMARY KEY.");
        cExplainLabel.setLocation(10, 290);
        cExplainLabel.setSize(400, 30);
        
        rExplainLabel = new JLabel("Create needs a unique PRIMARY KEY and  all text areas filled.");
        rExplainLabel.setLocation(10, 310);
        rExplainLabel.setSize(400, 30);
        
        outputLabel = new JLabel("");
        outputLabel.setLocation(10, 250);
        outputLabel.setSize(700, 30);
       
        
        message = new JTextArea("");
        message.setLineWrap(true);
        message.setWrapStyleWord(true);
        //message.setPreferredSize(new Dimension(400, 400));
        JScrollPane sp = new JScrollPane(message);
        //sp.setPreferredSize(new Dimension(400, 1000));
        //add(sampleField, BorderLayout.CENTER);
        sp.setLocation(320, 10);
        sp.setSize(200, 300);
        //sp.setPreferredSize(new Dimension(800, 300));
        add(sp);
        
        
        createTextField();
        createLogInButton();
        createPanel();
        
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }
    
    
    private void createTextField(){
        final int FIELD_WIDTH = 10;
        
        Field1 = new JTextField(FIELD_WIDTH);
        Field1.setLocation(100, 20);
        Field1.setSize(200, 30);
        Field1.setText("");
        
        Field2 = new JTextField(FIELD_WIDTH);
        Field2.setLocation(100, 70);
        Field2.setSize(200, 30);
        Field2.setText("");
        
        Field3 = new JTextField(FIELD_WIDTH);
        Field3.setLocation(100, 120);
        Field3.setSize(200, 30);
        Field3.setText("");
        
        Field4 = new JTextField(FIELD_WIDTH);
        Field4.setLocation(100, 170);
        Field4.setSize(200, 30);
        Field4.setText("");
        
        Field5 = new JTextField(FIELD_WIDTH);
        Field5.setLocation(100, 220);
        Field5.setSize(200, 30);
        Field5.setText("");
        
    }

    private void createLogInButton(){
        buttonCreate = new JButton("Create");
        buttonCreate.setSize(120, 20);
        buttonCreate.setLocation(FRAME_WIDTH/2-150,FRAME_HEIGHT-100);
        
        buttonRemove = new JButton("Remove");
        buttonRemove.setSize(120, 20);
        buttonRemove.setLocation(FRAME_WIDTH/2+30,FRAME_HEIGHT-100);
        
        buttonView = new JButton("View");
        buttonView.setSize(120, 20);
        buttonView.setLocation(FRAME_WIDTH/2-(buttonView.getWidth()/2),FRAME_HEIGHT-50);
        
        class AddInterestListener implements ActionListener{
            public void actionPerformed(ActionEvent event){
                String id = Field1.getText();
                String p_name = Field2.getText();
                String address = Field3.getText();
                String email = Field4.getText();
                String gender = Field5.getText();
                
                
                
                if(event.getActionCommand().equals("View")){
                        String driverName = "oracle.jdbc.driver.OracleDriver";
                        Connection conn = null;
                        try {
                            Class.forName(driverName);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(CreatePersonFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        String serverName = "dbsvcs.cs.uno.edu";
                        String serverPort = "1521";
                        String sid = "orcl";
                        String url = "jdbc:oracle:thin:@"+serverName+":"+serverPort+":"+sid;
                        String userName = "rwheinri";
                        String password = "g3tJsNmt";
                        try {
                            conn = DriverManager.getConnection(url, userName, password);          
                            System.out.println("Sucessfully connected to the database.");
                            Statement st = null;
                            String returnMessage = String.format("%-15s%-15s\n", "p_name", "per_id");
                                st = conn.createStatement();
                                ResultSet rs = st.executeQuery("select p_name, per_id from person ORDER BY p_name ASC");
                                while(rs.next()){
                                    String name1 = rs.getString(1).toString();
                                    String name2 = rs.getString(2).toString();
                                    returnMessage += String.format("%-15s%-15s\n", name1, name2);
                                }
                                message.setText(returnMessage);
                                
                        } catch (SQLException ex) {
                            Logger.getLogger(CreatePersonFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                }
                if(event.getActionCommand().equals("Create")){
                    boolean keyExists = false;
                    if(id.length() == 5 && p_name.length() > 0 && address.length() > 0 && email.length() > 0 && gender.length() > 0){
                        String driverName = "oracle.jdbc.driver.OracleDriver";
                        Connection conn = null;
                        try {
                            Class.forName(driverName);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(CreatePersonFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        String serverName = "dbsvcs.cs.uno.edu";
                        String serverPort = "1521";
                        String sid = "orcl";
                        String url = "jdbc:oracle:thin:@"+serverName+":"+serverPort+":"+sid;
                        String userName = "rwheinri";
                        String password = "g3tJsNmt";
                        try {
                            conn = DriverManager.getConnection(url, userName, password);          
                            System.out.println("Sucessfully connected to the database.");
                            Statement st = null;
                                st = conn.createStatement();
                                ResultSet rs = st.executeQuery("select count(1) from person where per_id = "+id);
                                while(rs.next()){
                                    if(rs.getInt(1) == 1){
                                        outputLabel.setText("person "+id+" exist.");
                                        keyExists = true;
                                    }else{
                                        outputLabel.setText("person "+id+" does not exist.");
                                    }
                                }
                            //nothing for now. I'll make a responce string or label.

                            //maxLabel.setText(String.valueOf("The max is "+heights.getMaximum()+"."));
                            //minLabel.setText(String.valueOf("The min is "+heights.getMinimum()+"."));
                            //avgLabel.setText(String.valueOf("The average is "+heights.getAverage()+"."));
                        } catch (SQLException ex) {
                            Logger.getLogger(CreatePersonFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }//end if
                    else{
                        outputLabel.setText("all fields must be filled out. id format is XXXXX");
                        keyExists = true;
                    }
                    if(keyExists == false){
                        String driverName = "oracle.jdbc.driver.OracleDriver";
                        Connection conn = null;
                        try {
                            Class.forName(driverName);
                            String serverName = "dbsvcs.cs.uno.edu";
                            String serverPort = "1521";
                            String sid = "orcl";
                            String url = "jdbc:oracle:thin:@"+serverName+":"+serverPort+":"+sid;
                            String userName = "rwheinri";
                            String password = "g3tJsNmt";
                            conn = DriverManager.getConnection(url, userName, password); 

                            Statement st = null;
                            st = conn.createStatement();
                            ResultSet rs = st.executeQuery("insert into person values('"+id+"', '"+p_name+"', '"+address+"', '"+email+"', '"+gender+"')");
                            String temp = null;
                            outputLabel.setText("insertion of p_name: "+p_name+" is complete.");
                            
                        }catch (SQLException ex) {
                            Logger.getLogger(CreatePersonFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }catch (ClassNotFoundException ex) {
                            Logger.getLogger(CreatePersonFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }//end ifExists  
                }//end createbutton
                if(event.getActionCommand().equals("Remove")){
                    boolean keyExists = false;
                    if(id.length() == 5){
                        String driverName = "oracle.jdbc.driver.OracleDriver";
                        Connection conn = null;
                        try {
                            Class.forName(driverName);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(CreatePersonFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        String serverName = "dbsvcs.cs.uno.edu";
                        String serverPort = "1521";
                        String sid = "orcl";
                        String url = "jdbc:oracle:thin:@"+serverName+":"+serverPort+":"+sid;
                        String userName = "rwheinri";
                        String password = "g3tJsNmt";
                        try {
                            conn = DriverManager.getConnection(url, userName, password);          
                            System.out.println("Sucessfully connected to the database.");
                            Statement st = null;
                                st = conn.createStatement();
                                ResultSet rs = st.executeQuery("select count(1) from person where per_id = "+id);
                                while(rs.next()){
                                    if(rs.getInt(1) == 1){
                                        outputLabel.setText("person "+id+" exist.");
                                        keyExists = true;
                                    }else{
                                        outputLabel.setText("person "+id+" does not exist.");
                                    }
                                }
                        } catch (SQLException ex) {
                            Logger.getLogger(CreatePersonFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }//end if
                    else{
                        outputLabel.setText("improper id format is XXXXX");
                    }
                    if(keyExists == true){
                        String driverName = "oracle.jdbc.driver.OracleDriver";
                        Connection conn = null;
                        try {
                            Class.forName(driverName);
                            String serverName = "dbsvcs.cs.uno.edu";
                            String serverPort = "1521";
                            String sid = "orcl";
                            String url = "jdbc:oracle:thin:@"+serverName+":"+serverPort+":"+sid;
                            String userName = "rwheinri";
                            String password = "g3tJsNmt";
                            conn = DriverManager.getConnection(url, userName, password); 

                            Statement st = null;
                            st = conn.createStatement();
                            //ResultSet rs = st.executeQuery("select per_id from person");
                            ResultSet rs = st.executeQuery("DELETE FROM person WHERE per_id = '"+id+"'");
                            outputLabel.setText("deletion of per_id: "+id+" is complete.");
                        }catch (SQLException ex) {
                            Logger.getLogger(CreatePersonFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }catch (ClassNotFoundException ex) {
                            Logger.getLogger(CreatePersonFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }//end ifExists
                }//if remove   
            }//end action performed.
        }
        ActionListener listener = new AddInterestListener();
        buttonCreate.addActionListener(listener);
        buttonRemove.addActionListener(listener);
        buttonView.addActionListener(listener);
    }
    
    
    private void createPanel(){   
        setLayout(null);

        add(Label1);
        add(Label2);
        add(Label3);
        add(Label4);
        add(Label5); 
        add(cExplainLabel);   
        add(rExplainLabel);
        add(outputLabel);
        
        add(Field1);
        add(Field2);
        add(Field3);
        add(Field4);
        add(Field5);
        
        add(buttonCreate);
        add(buttonRemove);
        add(buttonView);
    }
    
}
