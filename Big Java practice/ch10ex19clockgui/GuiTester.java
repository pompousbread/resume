

package ch10ex19clockgui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;

/**
 *
 * @author Ryan Heinrich
 */
public class GuiTester {
    public static void main(String[] args) {
        final ClockGui frame = new ClockGui();

            frame.setTitle("Draw Circles");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            final ClockComponent component = new ClockComponent();
            frame.add(component);
            
            class MousePressListener implements MouseListener{
            public void mousePressed(MouseEvent event){
                int x = event.getX();
                int y = event.getY();
                
                //repaint();
                component.moveTo(x,y);

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
