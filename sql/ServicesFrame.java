/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class ServicesFrame extends JFrame{
    private JTextField Field1;
    private JTextField Field2;
    
    private final JLabel Label1;//balance label
    private final JLabel Label2;
    
    private final JLabel cExplainLabel;//create
    private final JLabel rExplainLabel;//remove
    private JLabel outputLabel;
    private JTextArea message;
    private JButton jobHuntButton;
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
    
    final int FRAME_WIDTH = 1000;
    final int FRAME_HEIGHT = 500;
    
    public ServicesFrame(){
        
        
        Label1 = new JLabel("PER_ID:");
        Label1.setLocation(15, 30);
        Label1.setSize(100, 30);
        Label2 = new JLabel("Primary Sector:");
        Label2.setLocation(8, 65);
        Label2.setSize(100, 30);
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
        sp.setSize(600, 400);
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
        Field1.setLocation(100, 30);
        Field1.setSize(200, 30);
        Field1.setText("");
        
        Field2 = new JTextField(FIELD_WIDTH);
        Field2.setLocation(100, 65);
        Field2.setSize(200, 30);
        Field2.setText("");
        
        
    }

    private void createLogInButton(){
        
        jobHuntButton = new JButton("Job Hunt");
        jobHuntButton.setSize(120, 20);
        jobHuntButton.setLocation(FRAME_WIDTH/2-(jobHuntButton.getWidth()/2) -250, 5);
        
        companyButtonView = new JButton("View Company");
        companyButtonView.setSize(120, 20);
        companyButtonView.setLocation(FRAME_WIDTH/2-(companyButtonView.getWidth()/2)+ 150,FRAME_HEIGHT-50);
        
        
        class AddInterestListener implements ActionListener{
            public void actionPerformed(ActionEvent event){
                String company_id = Field1.getText();
                String job_code = Field2.getText();
                
                
                
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
                if(event.getActionCommand().equals("Job Hunt")){
                        Connection conn = null;
                        String per_id = Field1.getText();
                        if(Field1.getText().equals("") && Field2.getText().equals("")){
                            
                            
                            
                            try {
                                Class.forName(driverName);
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(CreatePersonFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            try {
                                conn = DriverManager.getConnection(url, userName, password);          
                                System.out.println("Sucessfully connected to the database.");
                                Statement st = null;
                                String returnMessage = String.format("%-20s%-25s%-10s%-20s\n", "title", "company_name", "avg_pay", "primary_sector");
                                    st = conn.createStatement();
                                    ResultSet rs = st.executeQuery("select title, company_name, avg_pay, primary_sector from job_profile natural join job natural join company where status =  'vacant'");
                                    while(rs.next()){
                                        String name1 = rs.getString(1).toString();
                                        String name2 = rs.getString(2).toString();
                                        String name3 = rs.getString(3).toString();
                                        String name4 = rs.getString(4).toString();
                                        returnMessage += String.format("%-20s%-25s%-10s%-20s\n", name1, name2, name3, name4);
                                    }
                                    message.setText(returnMessage);

                            } catch (SQLException ex) {
                                Logger.getLogger(CreatePersonFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        
                        
                        
                        
                        //is it the right length
                        //do two queries back to back.
                        if(Field1.getText().length() == 5){
                             boolean perExists = false;
                            //does person exist.
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
                                ResultSet rs = st.executeQuery("select count(1) from person where per_id = '"+per_id+"'");
                                while(rs.next()){
                                    if(rs.getInt(1) == 1){

                                        perExists = true;
                                    }
                                }
                                
                        } catch (SQLException ex) {
                            Logger.getLogger(CreatePersonFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        
                        
                        
                        
                        if(perExists == true){
                            String name = Field1.getText();
                            String result= "";
                            try {
                                Class.forName(driverName);
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(CreatePersonFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            try {
                                String query1 = "with all_person_skills as (select ks_code \n" +
"                       from  person natural join has_skill\n" +
"                       where per_id = '"+per_id+"'),                      \n" +
"skills_missing (jp_code, amnt_missing) as (select jp.jp_code, (0 + count(req.ks_code))\n" +
"                                         from job_profile jp,  job_prereq req \n" +
"                                         where jp.jp_code = req.jp_code and req.ks_code not in (select ks_code\n" +
"                                                                                              from all_person_skills)\n" +
"                                                                                              group by jp.jp_code) select title, company_name, avg_pay, primary_sector, amnt_missing \n" +
"from job_profile natural join job natural join company natural join skills_missing\n" +
"where status =  'vacant'";
                                conn = DriverManager.getConnection(url, userName, password);          
                                System.out.println("Sucessfully connected to the database.");
                                Statement st = null;
                                String returnMessage = String.format("%-20s%-25s%-10s%-20s%-15s\n", "title", "company_name", "avg_pay", "primary_sector", "missing_ks");
                                    st = conn.createStatement();
                                    ResultSet rs = st.executeQuery(query1);
                                    while(rs.next()){
                                        String name1 = rs.getString(1).toString();
                                        String name2 = rs.getString(2).toString();
                                        String name3 = rs.getString(3).toString();
                                        String name4 = rs.getString(4).toString();
                                        int no = rs.getInt(5);
                                        returnMessage += String.format("%-20s%-25s%-10s%-20s%-10d\n", name1, name2, name3, name4, no);
                                    }
                                    result = returnMessage;
                                    //message.setText(returnMessage);

                            } catch (SQLException ex) {
                                Logger.getLogger(CreatePersonFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            
                            try {
                                Class.forName(driverName);
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(CreatePersonFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            try {
                                String query2 = "with all_person_skills as (select ks_code \n" +
"                       from  person natural join has_skill\n" +
"                       where per_id = '"+per_id+"'),                      \n" +
"skills_missing (jp_code, amnt_missing) as (select jp.jp_code, (0 + count(req.ks_code))\n" +
"                                         from job_profile jp,  job_prereq req \n" +
"                                         where jp.jp_code = req.jp_code and req.ks_code not in (select ks_code\n" +
"                                                                                              from all_person_skills)\n" +
"                                                                                              group by jp.jp_code), qualified(jp_code, missing) as ((select jp_code, 0\n" +
"                                          from job_profile)\n" +
"                                          minus\n" +
"                                          (select jp_code, 0\n" +
"                                          from skills_missing)\n" +
"                                          ) select title, company_name, avg_pay, primary_sector, missing \n" +
"from qualified natural join job_profile natural join job natural join company\n" +
"where status =  'vacant'";
                                conn = DriverManager.getConnection(url, userName, password);          
                                System.out.println("Sucessfully connected to the database.");
                                Statement st = null;
                                String returnMessage = String.format("%-20s%-25s%-10s%-20s%-15s\n", "title", "company_name", "avg_pay", "primary_sector", "missing_ks");
                                    st = conn.createStatement();
                                    ResultSet rs = st.executeQuery(query2);
                                    while(rs.next()){
                                        String name1 = rs.getString(1).toString();
                                        String name2 = rs.getString(2).toString();
                                        String name3 = rs.getString(3).toString();
                                        String name4 = rs.getString(4).toString();
                                        int no = rs.getInt(5);
                                        returnMessage += String.format("%-20s%-25s%-10s%-20s%-10d\n", name1, name2, name3, name4, no);
                                    }
                                    result += "\n"+returnMessage;
                                    message.setText(result);

                            } catch (SQLException ex) {
                                Logger.getLogger(CreatePersonFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            }
                        }
                }
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
        companyButtonView.addActionListener(listener);
        jobHuntButton.addActionListener(listener);
    }
    
    
    private void createPanel(){   
        //any layout at all will fuck with the Jcomponent and get rid of it.
        setLayout(null);//Boom! this allows me to hardcode the location of everything on the frame.        

        add(Label1);
        add(Label2);
        
        add(cExplainLabel);   
        add(rExplainLabel);
        add(outputLabel);
        
        add(Field1);
        add(Field2);
        
        
        add(jobHuntButton);
        add(companyButtonView);

    }
    
}