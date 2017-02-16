/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Ryan Heinrich
 */
public class MessageGUI extends JFrame {
    private static final int FRAME_WIDTH = 900;
    private static final int FRAME_HEIGHT = 550;
    
    public static Font textProperties;
    
    private JTextArea message;
    private String storageString;
    
    private int querySelected;
    private int parameterSize;
    
    private JRadioButton query1Button;
    private JRadioButton query2Button;
    private JRadioButton query3Button;
    private JRadioButton query4Button;
    private JRadioButton query5Button;
    private JRadioButton query6Button;
    private JRadioButton query7Button;
    private JRadioButton query8Button;
    private JRadioButton query9Button;
    private JRadioButton query10Button;
    private JRadioButton query11Button;
    private JRadioButton query12Button;
    private JRadioButton query13Button;
    private JRadioButton query14Button;
    private JRadioButton query15Button;
    private JRadioButton query16Button;
    private JRadioButton query17Button;
    private JRadioButton query18Button;
    private JRadioButton query19Button;
    private JRadioButton query20Button;
    private JRadioButton query21Button;
    private JRadioButton query22Button;
    private JRadioButton query23Button;
    private JRadioButton query24Button;
    private JRadioButton query25Button;
    private JRadioButton query26Button;
    private JRadioButton query27Button;
    private JRadioButton query28Button;
    
    
    private JComboBox facenameCombo;
    private ActionListener listener;
    
    public MessageGUI(){
        message = new JTextArea("");
        message.setLineWrap(true);
        message.setWrapStyleWord(true);
        //message.setPreferredSize(new Dimension(400, 400));
        JScrollPane sp = new JScrollPane(message);
        //sp.setPreferredSize(new Dimension(400, 1000));
        //add(sampleField, BorderLayout.CENTER);
        sp.setPreferredSize(new Dimension(800, 300));
        //add(sp);
        //all this does is say what buttons are currently selected
        class ChoiceListener implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                setSampleFont();
            }
        }
        listener = new ChoiceListener();
        
       createControlPanel(sp);
       setSampleFont();
       setSize(FRAME_WIDTH, FRAME_HEIGHT);    
    }
    
    public void createControlPanel(JScrollPane pane){
        JPanel styleGroupPanel = createRadioButtons();
        JPanel displayPanel = createDisplayPanel();

        
        JPanel controlPanel = new JPanel();
        controlPanel.add(pane);

        controlPanel.add(styleGroupPanel);
        controlPanel.add(displayPanel);
        //controlPanel.setPreferredSize(new Dimension(700, 350));
        //add(controlPanel);
        controlPanel.setPreferredSize(new Dimension(300, 800));//pointless
        add(controlPanel);
    }
    
    public JPanel createDisplayPanel(){
        JPanel panel = new JPanel();
        //panel.setSize(200, 200);
        JButton tempButton = new JButton("View Query");
            class FrameListener implements ActionListener{
                public void actionPerformed(ActionEvent e) {
                    Query aQuery = new Query();
                    QueryMessages aMessage = new QueryMessages();
                    String returnMessage = "";
                    if(querySelected == 1){
                        returnMessage = aQuery.getQuery(aMessage.getQuery1(), 1);
                    }else if(querySelected == 2){
                        returnMessage = aQuery.getQuery(aMessage.getQuery2(), 2);
                    }else if(querySelected == 3){
                        returnMessage = aQuery.getQuery(aMessage.getQuery3(), 3);
                    }else if(querySelected == 4){
                        returnMessage = aQuery.getQuery(aMessage.getQuery4(), 4);
                    }else if(querySelected == 5){
                        returnMessage = aQuery.getQuery(aMessage.getQuery5(), 5);
                    }else if(querySelected == 6){
                        returnMessage = aQuery.getQuery(aMessage.getQuery6(), 6);
                    }else if(querySelected == 7){
                        returnMessage = aQuery.getQuery(aMessage.getQuery7(), 7);
                    }else if(querySelected == 8){
                        returnMessage = aQuery.getQuery(aMessage.getQuery8(), 8);
                    }else if(querySelected == 9){
                        returnMessage = aQuery.getQuery(aMessage.getQuery9(), 9);
                    }else if(querySelected == 10){
                        returnMessage = aQuery.getQuery(aMessage.getQuery10(), 10);
                    }else if(querySelected == 11){
                        returnMessage = aQuery.getQuery(aMessage.getQuery11(), 11);
                    }else if(querySelected == 12){
                        returnMessage = aQuery.getQuery(aMessage.getQuery12(), 12);
                    }else if(querySelected == 13){
                        returnMessage = aQuery.getQuery(aMessage.getQuery13(), 13);
                    }else if(querySelected == 14){
                        returnMessage = aQuery.getQuery(aMessage.getQuery14(), 14);
                    }else if(querySelected == 15){
                        returnMessage = aQuery.getQuery(aMessage.getQuery15(), 15);
                    }else if(querySelected == 16){
                        returnMessage = aQuery.getQuery(aMessage.getQuery16(), 16);
                    }else if(querySelected == 17){
                        returnMessage = aQuery.getQuery(aMessage.getQuery17(), 17);
                    }else if(querySelected == 18){
                        returnMessage = aQuery.getQuery(aMessage.getQuery18(), 18);
                    }else if(querySelected == 19){
                        returnMessage = aQuery.getQuery(aMessage.getQuery19(), 19);
                    }else if(querySelected == 20){
                        returnMessage = aQuery.getQuery(aMessage.getQuery20(), 20);
                    }else if(querySelected == 21){
                        returnMessage = aQuery.getQuery(aMessage.getQuery21(), 21);
                    }else if(querySelected == 22){
                        returnMessage = aQuery.getQuery(aMessage.getQuery22(), 22);
                    }else if(querySelected == 23){
                        returnMessage = aQuery.getQuery(aMessage.getQuery23(), 23);
                    }else if(querySelected == 24){
                        returnMessage = aQuery.getQuery(aMessage.getQuery24(), 24)+"\n";
                        returnMessage += aQuery.getQuery(aMessage.getQuery24b(), 242);
                    }else if(querySelected == 25){
                        returnMessage = aQuery.getQuery(aMessage.getQuery25(), 25)+"\n";
                        returnMessage += aQuery.getQuery(aMessage.getQuery25b(), 252);
                    }else if(querySelected == 26){
                        returnMessage = aQuery.getQuery(aMessage.getQuery26(), 26);
                    }else if(querySelected == 27){
                        returnMessage = aQuery.getQuery(aMessage.getQuery27(), 27);
                    }else if(querySelected == 28){
                        returnMessage = aQuery.getQuery(aMessage.getQuery28(), 28);
                    }
                    message.setText(returnMessage);
                    /*setSampleFont();
                    DisplayFrame frame = new DisplayFrame(message.getText());
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setTitle("DisplayGUI");
                    frame.setVisible(true); 
                    */
                    /*try {
                        frame.startMovement();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MessageGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }*/
                }
            }
        tempButton.addActionListener(new FrameListener());
        
        
        class CreationListener implements ActionListener{
            public void actionPerformed(ActionEvent e) { 
                
                if(e.getActionCommand().equals("Create person")){
                    JFrame insertFrame = new CreatePersonFrame();
                    insertFrame.setTitle("Add or Remove a person");
                    //insertFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    insertFrame.setVisible(true);
                }
                if(e.getActionCommand().equals("Create job profile")){
                    JFrame insertFrame = new CreateJpFrame();
                    insertFrame.setTitle("Add or Remove a job profile");
                    insertFrame.setVisible(true);
                }
                if(e.getActionCommand().equals("Create course")){
                    JFrame insertFrame = new CreateCourseFrame();
                    insertFrame.setTitle("create or remove a course");
                    insertFrame.setVisible(true);
                }
                if(e.getActionCommand().equals("Create job")){
                    JFrame insertFrame = new CreateJobFrame();
                    insertFrame.setTitle("create or remove a job");
                    insertFrame.setVisible(true);
                }
                if(e.getActionCommand().equals("Business tools")){
                    JFrame insertFrame = new ServicesFrame();
                    insertFrame.setTitle("create or remove a job");
                    insertFrame.setVisible(true);
                }
            }
        }
        JButton createPersonButton = new JButton("Create person");
        createPersonButton.addActionListener(new CreationListener());

        JButton createJpButton = new JButton("Create job profile");
        createJpButton.addActionListener(new CreationListener());

        JButton createCourseButton = new JButton("Create course");
        createCourseButton.addActionListener(new CreationListener());
        
        JButton createJobButton = new JButton("Create job");
        createJobButton.addActionListener(new CreationListener());
        
        JButton createServicesButton = new JButton("Business tools");
        createServicesButton.addActionListener(new CreationListener());
        

        //tempButton.setPreferredSize(new Dimension(100, 40));
        panel.add(createJobButton);
        panel.add(createCourseButton);
        panel.add(createJpButton);
        panel.add(createPersonButton);
        panel.add(createServicesButton);
        panel.add(tempButton);
        return panel;
    }

    /*
    public JPanel createCheckBoxes(){
        italicCheckBox = new JCheckBox("Italic");
        italicCheckBox.addActionListener(listener);
        
        boldCheckBox = new JCheckBox("Bold");
        boldCheckBox.addActionListener(listener);
        
        JPanel panel = new JPanel();
        panel.add(italicCheckBox);
        panel.add(boldCheckBox);
        panel.setBorder(new TitledBorder( new EtchedBorder(), "Style"));
        
        return panel;
    }*/
    
    public JPanel createRadioButtons(){
        query1Button = new JRadioButton("Query 1");
        query1Button.addActionListener(listener);
        query1Button.setSelected(true);
        
        query2Button = new JRadioButton("Query 2");
        query2Button.addActionListener(listener);
        
        query3Button = new JRadioButton("Query 3");
        query3Button.addActionListener(listener);
        
        query4Button = new JRadioButton("Query 4");
        query4Button.addActionListener(listener);
        
        query5Button = new JRadioButton("Query 5");
        query5Button.addActionListener(listener);
        
        query6Button = new JRadioButton("Query 6");
        query6Button.addActionListener(listener);
        
        query7Button = new JRadioButton("Query 7");
        query7Button.addActionListener(listener);
        
        query8Button = new JRadioButton("Query 8");
        query8Button.addActionListener(listener);
        
        query9Button = new JRadioButton("Query 9");
        query9Button.addActionListener(listener);
        
        query10Button = new JRadioButton("Query 10");
        query10Button.addActionListener(listener);
        
        query11Button = new JRadioButton("Query 11");
        query11Button.addActionListener(listener);
        
        query12Button = new JRadioButton("Query 12");
        query12Button.addActionListener(listener);
        
        query13Button = new JRadioButton("Query 13");
        query13Button.addActionListener(listener);
        
        query14Button = new JRadioButton("Query 14");
        query14Button.addActionListener(listener);
        
        query15Button = new JRadioButton("Query 15");
        query15Button.addActionListener(listener);
        
        query16Button = new JRadioButton("Query 16");
        query16Button.addActionListener(listener);
        
        query17Button = new JRadioButton("Query 17");
        query17Button.addActionListener(listener);
        
        query18Button = new JRadioButton("Query 18");
        query18Button.addActionListener(listener);
        
        query19Button = new JRadioButton("Query 19");
        query19Button.addActionListener(listener);
        
        query20Button = new JRadioButton("Query 20");
        query20Button.addActionListener(listener);
        
        query21Button = new JRadioButton("Query 21");
        query21Button.addActionListener(listener);
        
        query22Button = new JRadioButton("Query 22");
        query22Button.addActionListener(listener);
        
        query23Button = new JRadioButton("Query 23");
        query23Button.addActionListener(listener);
        
        query24Button = new JRadioButton("Query 24");
        query24Button.addActionListener(listener);
        
        query25Button = new JRadioButton("Query 25");
        query25Button.addActionListener(listener);
        
        query26Button = new JRadioButton("Query 26");
        query26Button.addActionListener(listener);
        
        query27Button = new JRadioButton("Query 27");
        query27Button.addActionListener(listener);
        
        query28Button = new JRadioButton("Query 28");
        query28Button.addActionListener(listener);
        
        ButtonGroup group = new ButtonGroup();
        group.add(query1Button);
        group.add(query2Button);
        group.add(query3Button);
        group.add(query4Button);
        group.add(query5Button);
        group.add(query6Button);
        group.add(query7Button);
        group.add(query8Button);
        group.add(query9Button);
        group.add(query10Button);
        group.add(query11Button);
        group.add(query12Button);
        group.add(query13Button);
        group.add(query14Button);
        group.add(query15Button);
        group.add(query16Button);
        group.add(query17Button);
        group.add(query18Button);
        group.add(query19Button);
        group.add(query20Button);
        group.add(query21Button);
        group.add(query22Button);
        group.add(query23Button);
        group.add(query24Button);
        group.add(query25Button);
        group.add(query26Button);
        group.add(query27Button);
        group.add(query28Button);
        
        JPanel panel = new JPanel();
        panel.add(query1Button);
        panel.add(query2Button);
        panel.add(query3Button);
        panel.add(query4Button);
        panel.add(query5Button);
        panel.add(query6Button);
        panel.add(query7Button);
        panel.add(query8Button);
        panel.add(query9Button);
        panel.add(query10Button);
        panel.add(query11Button);
        panel.add(query12Button);
        panel.add(query13Button);
        panel.add(query14Button);
        panel.add(query15Button);
        panel.add(query16Button);
        panel.add(query17Button);
        panel.add(query18Button);
        panel.add(query19Button);
        panel.add(query20Button);
        panel.add(query21Button);
        panel.add(query22Button);
        panel.add(query23Button);
        panel.add(query24Button);
        panel.add(query25Button);
        panel.add(query26Button);
        panel.add(query27Button);
        panel.add(query28Button);
        panel.setBorder(new TitledBorder(new EtchedBorder(), "Size"));
        panel.setPreferredSize(new Dimension(600, 175));
        return panel;
    }
    
    public void setSampleFont(){
        /* parameter size does nothing right now. */ 
        
        
        if(query1Button.isSelected()){
            querySelected = 1;
            parameterSize = 1;
            
        }else if(query2Button.isSelected()){
            querySelected = 2;
            parameterSize = 1;
        }
        else if(query3Button.isSelected()){
            querySelected = 3;
            parameterSize = 1;
        }
        else if(query4Button.isSelected()){
            querySelected = 4;
            parameterSize = 1;
        }
        else if(query5Button.isSelected()){
            querySelected = 5;
            parameterSize = 1;
        }
        else if(query6Button.isSelected()){
            querySelected = 6;
            parameterSize = 1;
        }
        else if(query7Button.isSelected()){
            querySelected = 7;
            parameterSize = 1;
        }
        else if(query8Button.isSelected()){
            querySelected = 8;
            parameterSize = 1;
        }
        else if(query9Button.isSelected()){
            querySelected = 9;
            parameterSize = 1;
        }
        else if(query10Button.isSelected()){
            querySelected = 10;
            parameterSize = 1;
        }
        else if(query11Button.isSelected()){
            querySelected = 11;
            parameterSize = 1;
        }
        else if(query12Button.isSelected()){
            querySelected = 12;
            parameterSize = 1;
        }else if(query13Button.isSelected()){
            querySelected = 13;
            parameterSize = 1;
        }
        else if(query14Button.isSelected()){
            querySelected = 14;
            parameterSize = 1;
        }
        else if(query15Button.isSelected()){
            querySelected = 15;
            parameterSize = 1;
        }
        else if(query16Button.isSelected()){
            querySelected = 16;
            parameterSize = 1;
        }
        else if(query17Button.isSelected()){
            querySelected = 17;
            parameterSize = 1;
        }
        else if(query18Button.isSelected()){
            querySelected = 18;
            parameterSize = 1;
        }
        else if(query19Button.isSelected()){
            querySelected = 19;
            parameterSize = 1;
        }
        else if(query20Button.isSelected()){
            querySelected = 20;
            parameterSize = 1;
        }
        else if(query21Button.isSelected()){
            querySelected = 21;
            parameterSize = 1;
        }
        else if(query22Button.isSelected()){
            querySelected = 22;
            parameterSize = 1;
        }
        else if(query23Button.isSelected()){
            querySelected = 23;
            parameterSize = 1;
        }
        else if(query24Button.isSelected()){
            querySelected = 24;
            parameterSize = 1;
        }
        else if(query25Button.isSelected()){
            querySelected = 25;
            parameterSize = 1;
        }
        else if(query26Button.isSelected()){
            querySelected = 26;
            parameterSize = 1;
        }
        else if(query27Button.isSelected()){
            querySelected = 27;
            parameterSize = 1;
        }
        else if(query28Button.isSelected()){
            querySelected = 28;
            parameterSize = 1;
        }
     
        //storageString = message.getText();
        //storageString.setFont(new Font(facename, style, size));
        //textProperties = new Font(facename, style, size);
        //sampleField.repaint();
                
    }
}
