import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VendingMachine extends JFrame {
    private Panel panel;

    public VendingMachine(String label) {
        super(label);
        setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new Panel(true);
        add(panel);
        pack();
    }

    public void run() {
        setVisible(true);
    }

    public static void main(String[] args) {
        VendingMachine vm = new VendingMachine("Vending Machine");
        vm.run();
    }
}
