import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CodeInput extends JPanel {
    private JLabel code_display;
    private JButton[] digit_buttons;
    private JButton accept_button;
    private JButton cancel_button;
    private String error_text = "ERROR";

    public CodeInput() {
        super(new GridLayout(2,1, 0,0));
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
                    } else {
                        code_display.setText(code_display_text + digit);
                    }
                }
            });
            buttons.add(digit_buttons[i]);
        }

        accept_button = new JButton("Accept");
        cancel_button = new JButton("Cancel");

        accept_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String code_text = code_display.getText();
                if (code_text.length() != 2) {
                    code_display.setText(error_text);
                }
                Integer code = Integer.parseInt(code_text);
                // TODO: add logic with buying; maybe move this button out this class
            }
        });

        cancel_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                code_display.setText("");
            }
        });

        buttons.add(accept_button);
        buttons.add(cancel_button);
        add(buttons);
    }
}
