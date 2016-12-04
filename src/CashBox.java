
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.*;

public class CashBox extends JPanel {
    public Integer sum;
    private List<Coins> coins;
    private List<Banknotes> banknotes;
    private JButton bill_acceptor;
    private JButton coin_acceptor;

    public CashBox(List<Coins> coins, List<Banknotes> banknotes) {
        super(new GridLayout(3, 1, 50, 50));

        this.sum = 0;
        this.coins = coins;
        this.banknotes = banknotes;

        setBorder(new TitledBorder("CashBox"));
        setBackground(Color.white);

        bill_acceptor = new JButton("Bill Acceptor", new ImageIcon(Panel.class.getResource("images/bill-acceptor.jpg")));
        bill_acceptor.setHorizontalTextPosition(JLabel.CENTER);
        bill_acceptor.setVerticalTextPosition(JLabel.BOTTOM);
        bill_acceptor.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Object[] options = {"10", "50", "100"};
                int response = JOptionPane.showOptionDialog(new JFrame(),
                        "Choose denomination",
                        "",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[2]);
                int denomination = 0;
                switch (response) {
                    case JOptionPane.YES_OPTION: denomination = 10;
                        break;
                    case JOptionPane.NO_OPTION: denomination = 50;
                        break;
                    default: denomination = 100;
                        break;
                }
                insert(true, new ArrayList<>(), Arrays.asList(new Banknotes(1, 0, denomination)));
            }
        });
        add(bill_acceptor);
    }

    public void insert(Boolean mode, List<Coins> coins, List<Banknotes> banknotes) {
        // TODO: implementation
    }

    private void addBanknotes(Banknotes banknotes) {
        // TODO: implementation
    }

    private void addCoins(Banknotes banknotes) {
        // TODO: implementation
    }

    public void take(Boolean mode, List<Coins> coinss, List<Banknotes> banknotes) {
        // TODO: implementation
    }

    private void subCoins(Coins unit) {
        // TODO: implementation
    }

    private void subBanknontes(Banknotes unit) {
        // TODO: implementation
    }

    public Pair<List<Coins>, List<Banknotes>> intToMoney() {
        // TODO: implementation
        return new Pair<>(new ArrayList<Coins>(), new ArrayList<Banknotes>());
    }
}
