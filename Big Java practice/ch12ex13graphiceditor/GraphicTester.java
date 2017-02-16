
package ch12ex13graphiceditor;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author Ryan Heinrich
 */
public class GraphicTester {
    public static GraphicComponent component;
    
    public static void main(String[] args) throws IOException {
            JFrame frame = new GraphicEditor();

            frame.setTitle("Make some Shapes");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            component = new GraphicComponent();
            frame.add(component);
            
            class MousePressListener implements MouseListener{
            public void mousePressed(MouseEvent event){
                int x = event.getX();
                int y = event.getY();
                component.moveTo(x, y);
            }
            public void mouseClicked(MouseEvent e){}
            public void mouseReleased(MouseEvent e){}
            public void mouseEntered(MouseEvent e){}
            public void mouseExited(MouseEvent e){}
        }       
        MouseListener listener = new MousePressListener();
        component.addMouseListener(listener);

        frame.setVisible(true);
    }
}
