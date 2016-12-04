import javax.swing.*;
import java.awt.*;

public class VendingMachine extends JFrame {
    private JPanel panel;

    public VendingMachine(String label) {
        super(label);
        setBackground(Color.white);
        panel = new Panel(true);
        add(panel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void run() {
        setVisible(true);
    }

    public static void main(String[] args) {
        VendingMachine vm = new VendingMachine("Vending Machine");
        vm.run();
    }
}
