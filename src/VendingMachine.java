import javax.swing.*;
import java.awt.*;

public class VendingMachine extends JFrame {
    private JPanel panel;


    public VendingMachine(String s, GraphicsConfiguration graphicsConfiguration) {
        super(s, graphicsConfiguration);
        panel = new Panel();
    }
}
