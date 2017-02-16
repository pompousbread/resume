

package com;

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
 * 
 * 
 * 
 * 
 *        c_code			varchar(8), 
	 c_title			varchar(50), 
	 course_level	varchar(20),
	 c_description	varchar(100),
	 status			varchar(20),
	 retail_price	numeric(10,2),
 */
public class CreateCourseFrame extends JFrame{
    private JTextField Field1;
    private JTextField Field2;
    private JTextField Field3;
    private JTextField Field4;
    private JTextField Field5;
    private JTextField Field6;
    private JButton buttonCreate;
    private JButton buttonRemove;
    private JButton buttonView;
    private JLabel Label1;//balance label
    private JLabel Label2;
    private JLabel Label3;
    private JLabel Label4;
    private JLabel Label5;
    private JLabel Label6;
    private JLabel cExplainLabel;//create
    private JLabel rExplainLabel;//remove
    private JLabel outputLabel;
    private JTextArea message;
    
    private String driverName = "oracle.jdbc.driver.OracleDriver";
    private String serverName = "dbsvcs.cs.uno.edu";
    private String serverPort = "1521";
    private String sid = "orcl";
    private String url = "jdbc:oracle:thin:@"+serverName+":"+serverPort+":"+sid;
    private String userName = "rwheinri";
    private String password = "g3tJsNmt";

    
    
    private JPanel panel;
    private JScrollPane scrollPane;
    
    final int FRAME_WIDTH = 700;
    final int FRAME_HEIGHT = 500;
    
    public CreateCourseFrame(){
        
        
        Label1 = new JLabel("C_CODE:");
        Label1.setLocation(15, 20);
        Label1.setSize(100, 30);
        Label2 = new JLabel("c_title:");
        Label2.setLocation(15, 70);
        Label2.setSize(100, 30);
        Label3 = new JLabel("course_level:");
        Label3.setLocation(15, 120);
        Label3.setSize(100, 30);
        Label4 = new JLabel("c_description:");
        Label4.setLocation(15, 170);
        Label4.setSize(100, 30);
        Label5 = new JLabel("status:");
        Label5.setLocation(15, 220);
        Label5.setSize(100, 30);
        Label6 = new JLabel("retail_price");
        Label6.setLocation(15, 270);
        Label6.setSize(100, 30);
        cExplainLabel = new JLabel("Remove only needs PRIMARY KEY.");
        cExplainLabel.setLocation(10, 355);
        cExplainLabel.setSize(400, 30);
        
        rExplainLabel = new JLabel("Create needs a unique PRIMARY KEY and  all text areas filled.");
        rExplainLabel.setLocation(10, 380);
        rExplainLabel.setSize(400, 30);
        
        outputLabel = new JLabel("");
        outputLabel.setLocation(10, 330);
        outputLabel.setSize(700, 30);
       
        
        message = new JTextArea("");
        message.setLineWrap(true);
        message.setWrapStyleWord(true);
        //message.setPreferredSize(new Dimension(400, 400));
        JScrollPane sp = new JScrollPane(message);
        //sp.setPreferredSize(new Dimension(400, 1000));
        //add(sampleField, BorderLayout.CENTER);
        sp.setLocation(390, 10);
        sp.setSize(300, 400);
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
        
        Field6 = new JTextField(FIELD_WIDTH);
        Field6.setLocation(100, 270);
        Field6.setSize(200, 30);
        Field6.setText("");
        
    }

    private void createLogInButton(){
        buttonCreate = new JButton("Create");
        buttonCreate.setSize(120, 20);
        buttonCreate.setLocation(FRAME_WIDTH/2-150,FRAME_HEIGHT-80);
        
        buttonRemove = new JButton("Remove");
        buttonRemove.setSize(120, 20);
        buttonRemove.setLocation(FRAME_WIDTH/2+30,FRAME_HEIGHT-80);
        
        buttonView = new JButton("View");
        buttonView.setSize(120, 20);
        buttonView.setLocation(FRAME_WIDTH/2-(buttonView.getWidth()/2),FRAME_HEIGHT-50);
        
        class AddInterestListener implements ActionListener{
            public void actionPerformed(ActionEvent event){
                String c_code = Field1.getText();
                String c_title = Field2.getText();
                String course_level = Field3.getText();
                String c_description = Field4.getText();
                String status= Field5.getText();
                String retail_price = Field6.getText();
                
                
                
                if(event.getActionCommand().equals("View")){
                        Connection conn = null;
                        try {
                            Class.forName(driverName);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(CreatePersonFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            conn = DriverManager.getConnection(url, userName, password);          
                            System.out.println("Sucessfully connected to the database.");
                            Statement st = null;
                            String returnMessage = String.format("%-30s%-20s\n", "c_title", "c_code");
                                st = conn.createStatement();
                                ResultSet rs = st.executeQuery("select c_title, c_code from course ORDER BY c_title ASC");
                                while(rs.next()){
                                    String name1 = rs.getString(1).toString();
                                    String name2 = rs.getString(2).toString();
                                    returnMessage += String.format("%-30s%-20s\n", name1, name2);
                                }
                                message.setText(returnMessage);
                                
                        } catch (SQLException ex) {
                            Logger.getLogger(CreatePersonFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                }
                if(event.getActionCommand().equals("Create")){
                    boolean keyExists = false;
                    if(c_code.length() == 8 && c_title.length() > 0 && course_level.length() > 0 && c_description.length() > 0 && status.length() > 0 && retail_price.length() > 0){
                        Connection conn = null;
                        try {
                            Class.forName(driverName);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(CreatePersonFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            conn = DriverManager.getConnection(url, userName, password);          
                            System.out.println("Sucessfully connected to the database.");
                            Statement st = null;
                                st = conn.createStatement();
                                ResultSet rs = st.executeQuery("select count(1) from course where c_code = "+c_code);
                                while(rs.next()){
                                    if(rs.getInt(1) == 1){
                                        outputLabel.setText("course "+c_code+" exist.");
                                        keyExists = true;
                                    }else{
                                        outputLabel.setText("course "+c_code+" does not exist.");
                                    }
                                }
                        } catch (SQLException ex) {
                            Logger.getLogger(CreatePersonFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }//end if
                    else{
                        outputLabel.setText("all fields must be filled out. id format is XXX.XXXX");
                        keyExists = true;
                    }
                    if(keyExists == false){
                        Connection conn = null;
                        try {
                            Class.forName(driverName);
                            conn = DriverManager.getConnection(url, userName, password); 

                            Statement st = null;
                            st = conn.createStatement();
                            ResultSet rs = st.executeQuery("insert into course values('"+c_code+"', '"+c_title+"', '"+course_level+"', '"+c_description+"', '"+status+"', '"+retail_price+"')");
                            String temp = null;
                            outputLabel.setText("insertion of p_name: "+c_title+" is complete.");
                            
                        }catch (SQLException ex) {
                            Logger.getLogger(CreatePersonFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }catch (ClassNotFoundException ex) {
                            Logger.getLogger(CreatePersonFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }//end ifExists  
                }//end createbutton
                if(event.getActionCommand().equals("Remove")){
                    boolean keyExists = false;
                    if(c_code.length() == 8){
                        Connection conn = null;
                        try {
                            Class.forName(driverName);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(CreatePersonFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            conn = DriverManager.getConnection(url, userName, password);          
                            System.out.println("Sucessfully connected to the database.");
                            Statement st = null;
                                st = conn.createStatement();
                                ResultSet rs = st.executeQuery("select count(1) from course where c_code = "+c_code);
                                while(rs.next()){
                                    if(rs.getInt(1) == 1){
                                        outputLabel.setText("couse "+c_code+" exist.");
                                        keyExists = true;
                                    }else{
                                        outputLabel.setText("course "+c_code+" does not exist.");
                                    }
                                }
                        } catch (SQLException ex) {
                            Logger.getLogger(CreatePersonFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }//end if
                    else{
                        outputLabel.setText("improper id format is XXX.XXXX");
                    }
                    if(keyExists == true){
                        Connection conn = null;
                        try {
                            Class.forName(driverName);
                            conn = DriverManager.getConnection(url, userName, password); 

                            Statement st = null;
                            st = conn.createStatement();
                            ResultSet rs = st.executeQuery("DELETE FROM course WHERE c_code = '"+c_code+"'");
                            outputLabel.setText("deletion of c_code: "+c_code+" is complete.");
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
        //any layout at all will mess with the Jcomponent and get rid of it.
        setLayout(null);//this allows me to hardcode the location of everything on the frame.        

        add(Label1);
        add(Label2);
        add(Label3);
        add(Label4);
        add(Label5); 
        add(Label6); 
        add(cExplainLabel);   
        add(rExplainLabel);
        add(outputLabel);
        
        add(Field1);
        add(Field2);
        add(Field3);
        add(Field4);
        add(Field5);
        add(Field6);
        
        add(buttonCreate);
        add(buttonRemove);
        add(buttonView);
    }
    
}
