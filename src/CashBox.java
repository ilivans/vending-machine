
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
    private List<Coins> change;
    private JButton change_window;
    private JLabel sum_display;


    public CashBox(List<Coins> coins, List<Banknotes> banknotes) {
        super(new GridLayout(4, 1, 0, 0));

        this.sum = 0;
        this.coins = coins;
        this.banknotes = banknotes;

        setBorder(new TitledBorder(""));
        setBackground(Color.lightGray);

        sum_display = new JLabel(sum.toString());
        sum_display.setBorder(new TitledBorder("Sum entered"));
        add(sum_display);
        initCoinAcceptor();
        add(coin_acceptor);
        initBillAcceptor();
        add(bill_acceptor);
        initChangeWindow();
        add(change_window);
    }

    private void initCoinAcceptor() {
        coin_acceptor = new JButton("Coin Acceptor", new ImageIcon(Panel.class.getResource("images/coin-acceptor.png")));
        coin_acceptor.setHorizontalTextPosition(JLabel.CENTER);
        coin_acceptor.setVerticalTextPosition(JLabel.BOTTOM);
        coin_acceptor.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Object[] options = {"5 rub.", "10 rub."};
                int response = JOptionPane.showOptionDialog(new JFrame(),
                        "Choose denomination",
                        "",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[1]);
                int denomination = 0;
                switch (response) {
                    case JOptionPane.YES_OPTION: denomination = 5;
                        break;
                    default: denomination = 10;
                        break;
                }
                insert(true, Arrays.asList(new Coins(1, 1, denomination)), new ArrayList<>());
            }
        });
    }

    private void initBillAcceptor() {
        bill_acceptor = new JButton("Bill Acceptor", new ImageIcon(Panel.class.getResource("images/bill-acceptor.png")));
        bill_acceptor.setHorizontalTextPosition(JLabel.CENTER);
        bill_acceptor.setVerticalTextPosition(JLabel.BOTTOM);
        bill_acceptor.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Object[] options = {"10 rub.", "50 rub.", "100 rub."};
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
                insert(true, new ArrayList<>(), Arrays.asList(new Banknotes(1, 1, denomination)));
            }
        });
    }

    private void initChangeWindow() {
        change_window = new JButton("Your Change", new ImageIcon(Panel.class.getResource("images/change.png")));
        change_window.setHorizontalTextPosition(JLabel.CENTER);
        change_window.setVerticalTextPosition(JLabel.BOTTOM);
    }

    public void insert(Boolean mode, List<Coins> coins_new, List<Banknotes> banknotes_new) {
        // TODO: show new sum in sum_display
        // TODO: add logic with dropping extra money (that don't fit in anymore) into Change window
        int cur_sum = sum;
        for (Coins coins_add : coins_new) {
            addCoins(coins_add);
        }
        for (Banknotes banknotes_add : banknotes_new) {
            addBanknotes(banknotes_add);
        }
    }

    private void addBanknotes(Banknotes banknotes_add) {
        for (Banknotes banknotes : this.banknotes) {
            if (banknotes.denomination.equals(banknotes_add.denomination)) {
                banknotes.changeNumber(banknotes.number + banknotes_add.number);
            }
        }
    }

    private void addCoins(Coins coins_add) {
        for (Coins coins : this.coins) {
            if (coins.denomination.equals(coins_add.denomination)) {
                coins.changeNumber(coins.number + coins_add.number);
            }
        }
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
