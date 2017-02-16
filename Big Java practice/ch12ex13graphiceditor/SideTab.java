

package ch12ex13graphiceditor;

import static ch12ex13graphiceditor.GraphicComponent.componentToImage;
import static ch12ex13graphiceditor.GraphicTester.component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

/**
 *
 * @author Ryan Heinrich
 * this is going to be like keypad.
 * 
 * colors: blue, Green, Red
 * shapes: lines, rectangle, ellipse
 */
public class SideTab extends JPanel {
    private JPanel buttonPanel;
    public static boolean blue = false;
    public static boolean green = false;
    public static boolean red = false;
    
    public static boolean circleOn = true;
    public static boolean rectOn = false;
    public static boolean lineOn = false;
    
    public static BufferedImage image;
    
    public SideTab(){
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(8, 1));
        
        addButton("Blue");
        addButton("Green");
        addButton("Red");
        addButton("Line");
        addButton("Rectangle");
        addButton("Ellipse");
        addButton("Load");
        addButton("Save");
        
        add(buttonPanel, "Center");
    }
    
    private void addButton(final String label){
        class DigitButtonListener implements ActionListener{
            @Override
            //there is going to be a default and these will change the default state.
            public void actionPerformed(ActionEvent e) {
                if(label.equals("Blue")){
                    blue = true;
                    green = false;
                    red = false;
                }else if(label.equals("Green")){
                    blue = false;
                    green = true;
                    red = false;
                }else if(label.equals("Red")){
                    blue = false;
                    green = false;
                    red = true;
                }else if(label.equals("Line")){
                    lineOn = true;
                    rectOn = false;
                    circleOn = false;
                }else if(label.equals("Rectangle")){ 
                    lineOn = false;
                    rectOn = true;
                    circleOn = false;
                }else if(label.equals("Ellipse")){
                    lineOn = false;
                    rectOn = false;
                    circleOn = true;
                }else if(label.equals("Save")){
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                    int result = fileChooser.showSaveDialog(null);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        try {
                            String path = fileChooser.getSelectedFile().getAbsolutePath();
                            System.out.println(path);
                            BufferedImage image = componentToImage(component,null);
                            ImageIO.write(image, "PNG", new File(path+".png"));
                            //ImageSaverPanel test = new ImageSaverPanel();
                        } catch (IOException ex) {
                            Logger.getLogger(GraphicComponent.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }       
                }else if(label.equals("Load")){ 
                    
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                    int result = fileChooser.showOpenDialog(null);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        String path = fileChooser.getSelectedFile().getAbsolutePath();
                        System.out.println("Selected file: " + path);          
                            try {
                                 image = ImageIO.read(new File(path));
                           }catch (IOException ex) {
                                Logger.getLogger(GraphicComponent.class.getName()).log(Level.SEVERE, null, ex);
                           }
                    }
                }
            }
        }
        JButton button = new JButton(label);
        buttonPanel.add(button);
        ActionListener listener = new DigitButtonListener();
        button.addActionListener(listener);
        
      }
        
}
    
