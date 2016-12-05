import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CodeInput extends JPanel {
    public JLabel code_display;
    public String error_text = "ERROR";

    private JButton[] digit_buttons;
    private JButton cancel_button;

    public CodeInput() {
        super(new GridLayout(3,1, 0,0));
        code_display = new JLabel("");
        code_display.setBorder(new TitledBorder("Code entered"));
        add(code_display);

        // Adding the interactable buttons in a grid via a loop.  Except for the last two buttons.
        JPanel buttons = new JPanel(new GridLayout(4, 3, 3, 3));
        buttons.setBorder(new TitledBorder("Type product number"));
        digit_buttons = new JButton[10];

        for (int i = 1; i < 10; i++) {
            digit_buttons[i] = new JButton(String.valueOf(i));
            digit_buttons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String digit = ((JButton) e.getSource()).getText();
                    String code_display_text = code_display.getText();
                    if (code_display_text.equals(error_text)) {
                        code_display.setText(digit);
                    } else if (code_display_text.length() < 2) {
                        code_display.setText(code_display_text + digit);
                    }
                }
            });
            buttons.add(digit_buttons[i]);
        }

        cancel_button = new JButton("Cancel");
        cancel_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                code_display.setText("");
            }
        });
        buttons.add(cancel_button);
        add(buttons);
    }
}
