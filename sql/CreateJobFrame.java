

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
 */
public class CreateJobFrame extends JFrame{
    private JTextField Field1;
    private JTextField Field2;
    private JTextField Field3;
    private JTextField Field4;
    private JTextField Field5;
    private JTextField Field6;
    private JButton buttonCreate;
    private JButton buttonRemove;
    private final JLabel Label1;//balance label
    private final JLabel Label2;
    private final JLabel Label3;
    private final JLabel Label4;
    private final JLabel Label5;
    private final JLabel Label6;
    private final JLabel cExplainLabel;//create
    private final JLabel rExplainLabel;//remove
    private JLabel outputLabel;
    private JTextArea message;
    private JButton jpButtonView;
    private JButton jobButtonView;
    private JButton companyButtonView;
    
    
    private final String driverName = "oracle.jdbc.driver.OracleDriver";
    private final String serverName = "dbsvcs.cs.uno.edu";
    private final String serverPort = "1521";
    private final String sid = "orcl";
    private final String url = "jdbc:oracle:thin:@"+serverName+":"+serverPort+":"+sid;
    private final String userName = "rwheinri";
    private final String password = "g3tJsNmt";

    
    
    private JPanel panel;
    private JScrollPane scrollPane;
    
    final int FRAME_WIDTH = 700;
    final int FRAME_HEIGHT = 500;
    
    public CreateJobFrame(){
        
        
        Label1 = new JLabel("COMPANY_ID:");
        Label1.setLocation(15, 30);
        Label1.setSize(100, 30);
        Label2 = new JLabel("JOB_CODE:");
        Label2.setLocation(15, 70);
        Label2.setSize(100, 30);
        Label3 = new JLabel("jp_code:");
        Label3.setLocation(15, 120);
        Label3.setSize(100, 30);
        Label4 = new JLabel("pay_type:");
        Label4.setLocation(15, 170);
        Label4.setSize(100, 30);
        Label5 = new JLabel("pay_rate:");
        Label5.setLocation(15, 220);
        Label5.setSize(100, 30);
        Label6 = new JLabel("status:");
        Label6.setLocation(15, 270);
        Label6.setSize(100, 30);
        cExplainLabel = new JLabel("Remove only needs PRIMARY KEY.");
        cExplainLabel.setLocation(10, 355);
        cExplainLabel.setSize(400, 30);
        
        rExplainLabel = new JLabel("Create needs a unique PRIMARY KEY and all text areas filled.");
        rExplainLabel.setLocation(10, 380);
        rExplainLabel.setSize(400, 30);
        
        outputLabel = new JLabel("");
        outputLabel.setLocation(5, 330);
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
        
        jobButtonView = new JButton("View Job");
        jobButtonView.setSize(120, 20);
        jobButtonView.setLocation(FRAME_WIDTH/2-(jobButtonView.getWidth()/2),FRAME_HEIGHT-50);
        
        companyButtonView = new JButton("View Company");
        companyButtonView.setSize(120, 20);
        companyButtonView.setLocation(FRAME_WIDTH/2-(companyButtonView.getWidth()/2)+ 150,FRAME_HEIGHT-50);
        
        jpButtonView = new JButton("View Job Profile");
        jpButtonView.setSize(120, 20);
        jpButtonView.setLocation(FRAME_WIDTH/2-(jpButtonView.getWidth()/2) - 150,FRAME_HEIGHT-50);
        
        class AddInterestListener implements ActionListener{
            public void actionPerformed(ActionEvent event){
                String company_id = Field1.getText();
                String job_code = Field2.getText();
                String jp_code = Field3.getText();
                String pay_type = Field4.getText();
                String pay_rate = Field5.getText();
                String status = Field6.getText();
                
                
                
                if(event.getActionCommand().equals("View Job")){
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
                            String returnMessage = String.format("%-15s%-15s%-15s\n", "company_id", "job_code", "jp_code");
                                st = conn.createStatement();
                                ResultSet rs = st.executeQuery("select company_id, job_code, jp_code from job ORDER BY company_id ASC");
                                while(rs.next()){
                                    String name1 = rs.getString(1).toString();
                                    String name2 = rs.getString(2).toString();
                                    String name3 = rs.getString(3).toString();
                                    returnMessage += String.format("%-15s%-15s%-15s\n", name1, name2, name3);
                                }
                                message.setText(returnMessage);
                                
                        } catch (SQLException ex) {
                            Logger.getLogger(CreatePersonFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                }
                if(event.getActionCommand().equals("View Company")){
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
                            String returnMessage = String.format("%-30s%-15s\n", "company_name", "company_id");
                                st = conn.createStatement();
                                ResultSet rs = st.executeQuery("select company_name, company_id from company ORDER BY company_name ASC");
                                while(rs.next()){
                                    String name1 = rs.getString(1).toString();
                                    String name2 = rs.getString(2).toString();
                                    returnMessage += String.format("%-30s%-15s\n", name1, name2);
                                }
                                message.setText(returnMessage);
                                
                        } catch (SQLException ex) {
                            Logger.getLogger(CreatePersonFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                }
                if(event.getActionCommand().equals("View Job Profile")){
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
                            String returnMessage = String.format("%-30s%-10s\n", "title", "jp_code");
                                st = conn.createStatement();
                                ResultSet rs = st.executeQuery("select title, jp_code from job_profile ORDER BY title ASC");
                                while(rs.next()){
                                    String name1 = rs.getString(1).toString();
                                    String name2 = rs.getString(2).toString();
                                    returnMessage += String.format("%-30s%-10s\n", name1, name2);
                                }
                                message.setText(returnMessage);
                                
                        } catch (SQLException ex) {
                            Logger.getLogger(CreatePersonFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                }
                if(event.getActionCommand().equals("Create")){
                    boolean compExists = false;
                    boolean jobExists = false;
                    if(company_id.length() == 9 && job_code.length() == 7 && jp_code.length() == 6 && pay_type.length() > 0 && pay_rate.length() > 0 && status.length() > 0){
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
                            String temp = null;
                                st = conn.createStatement();
                                ResultSet rs = st.executeQuery("select count(1) from company where company_id = "+company_id);
                                while(rs.next()){
                                    if(rs.getInt(1) == 1){
                                        temp = "company "+company_id+" exist.";
                                        //outputLabel.setText("company "+company_id+" exist.");
                                        compExists = true;
                                    }else{
                                        temp = "company doesn't exist.";
                                        //outputLabel.setText("company "+company_id+" does not exist.");
                                        compExists= false;
                                    }
                                }
                                
                                conn = DriverManager.getConnection(url, userName, password);          
                                System.out.println("Sucessfully connected to the database.");
                                Statement st2 = null;
                                st2 = conn.createStatement();
                                ResultSet rs2 = st2.executeQuery("select count(1) from job where job_code = "+job_code);
                                while(rs2.next()){
                                    if(rs2.getInt(1) == 1){
                                        temp += " job "+job_code+" exist.";
                                        //outputLabel.setText("job "+job_code+" exist.");
                                        jobExists = true;
                                    }else{
                                        temp += " job doesn't exist.";
                                        //outputLabel.setText("job "+job_code+" does not exist.");
                                    }
                                }
                                outputLabel.setText(temp);
                        } catch (SQLException ex) {
                            Logger.getLogger(CreatePersonFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }//end if
                    else{
                        outputLabel.setText("all fields must be filled out. job_code format is XXXXXXX");
                        compExists = false;
                        jobExists = false;
                    }
                    if(compExists == true && jobExists == false){
                        Connection conn = null;
                        try {
                            Class.forName(driverName);
                            conn = DriverManager.getConnection(url, userName, password); 

                            Statement st = null;
                            st = conn.createStatement();
                            ResultSet rs = st.executeQuery("insert into job values('"+company_id+"', '"+job_code+"', '"+jp_code+"', '"+pay_type+"', '"+pay_rate+"', '"+status+"')");
                            String temp = null;
                            outputLabel.setText("insertion of p_name: "+job_code+" is complete.");
                            
                        }catch (SQLException ex) {
                            Logger.getLogger(CreatePersonFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }catch (ClassNotFoundException ex) {
                            Logger.getLogger(CreatePersonFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }//end ifExists  
                }//end createbutton
                if(event.getActionCommand().equals("Remove")){
                    boolean keyExists = false;
                    if(job_code.length() == 7 && company_id.length() == 9){
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
                                ResultSet rs = st.executeQuery("select count(1) from job where job_code = "+job_code);
                                while(rs.next()){
                                    if(rs.getInt(1) == 1){
                                        outputLabel.setText("job "+job_code+" exist.");
                                        keyExists = true;
                                    }else{
                                        outputLabel.setText("job "+job_code+" does not exist.");
                                    }
                                }
                        } catch (SQLException ex) {
                            Logger.getLogger(CreatePersonFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }//end if
                    else{
                        outputLabel.setText("improper job format XXXXXXX. company format XXXXXXXXX.");
                    }
                    if(keyExists == true){
                        Connection conn = null;
                        try {
                            Class.forName(driverName);
                            conn = DriverManager.getConnection(url, userName, password); 

                            Statement st = null;
                            st = conn.createStatement();
                            ResultSet rs = st.executeQuery("DELETE FROM job WHERE job_code = '"+job_code+"'");
                            outputLabel.setText("deletion of c_code: "+job_code+" is complete.");
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
        jpButtonView.addActionListener(listener);
        companyButtonView.addActionListener(listener);
        jobButtonView.addActionListener(listener);
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
        add(jobButtonView);
        add(jpButtonView);
        add(companyButtonView);

    }
    
}
