

package ch12ex13graphiceditor;

import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Ryan Heinrich
 * 
 * 
 * Okay this problem is solved. you add images to the component by clicking. You can 
 * change the shape or the color by clicking a button. The save and load buttons work
 * and they will print any .png to the screen.
 * 
 * improvements: I could make objects for the shape and store the fill color inside the object
 * this way each shape would keep it's own unique color. I could change the location of the start 
 * directory for the JFileChooser
 */
public class GraphicEditor extends JFrame {
    private JPanel panel;
    private JScrollPane scrollPane;
    private JTextArea textArea;
    
    static final int FRAME_WIDTH = 600;
    static final int FRAME_HEIGHT = 600;
    
    public static int userEntry;
            
    
    
    public GraphicEditor() throws IOException{
 
        //createTextArea();
        createPanel();
        
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    private void createPanel() throws IOException{
        
        //any layout at all will mess with the Jcomponent and get rid of it.
        //setLayout(null);//this allows me to hardcode the location of everything on the frame. 
        JPanel panel = new SideTab();
        panel.setSize(100,250);
        panel.setLocation(0, 0);
        add(panel);

    }    
}
