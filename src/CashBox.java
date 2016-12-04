import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.awt.*;
import java.util.List;

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
        coin_acceptor = new JButton("Coin Acceptor", new ImageIcon(Panel.class.getResource("images/coca-cola.jpg")));
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
            if (!mode) {
                sum += coins_add.denomination * coins_add.number;
                //display new sum
            }
        }
        for (Banknotes banknotes_add : banknotes_new) {
            addBanknotes(banknotes_add);
            if (!mode) {
                sum += banknotes_add.denomination * banknotes_add.number;
                //display new sum
            }
        }
    }

    private void addBanknotes(Banknotes banknotes_add) {
        for (Banknotes banknotes : this.banknotes) {
            if (banknotes.denomination.equals(banknotes_add.denomination)) {
                if (banknotes.max_size < banknotes.number + banknotes_add.number) {
                    banknotes.changeNumber(banknotes.max_size);
                    //show that you get (banknotes.number -banknotes_add.number) back
                }
                else {
                    banknotes.changeNumber(banknotes.number + banknotes_add.number);
                }
                break;
            }
        }
    }

    private void addCoins(Coins coins_add) {
        for (Coins coins : this.coins) {
            if (coins.denomination.equals(coins_add.denomination)) {
                if (coins.max_size < coins.number + coins_add.number) {
                    coins.changeNumber(coins.max_size);
                    //show that you get (coins.number -coins_add.number) back
                }
                else {
                    coins.changeNumber(coins.number + coins_add.number);
                }
                break;
            }
        }
    }

    public void take(Boolean mode, List<Coins> coins, List<Banknotes> banknotes) {
        for (Coins coin : coins) {
            subCoins(mode, coin);
        }

        for (Banknotes banknote : banknotes) {
            subBanknontes(mode, banknote);
        }
    }

    private void subCoins(Boolean mode, Coins coins_sub) {
        for (Coins coins : this.coins) {
            if (coins.denomination.equals(coins_sub.denomination)) {
                coins.changeNumber(Math.max(0, coins.number - coins_sub.number));
                if (!mode) {
                    sum -= coins.denomination * Math.max(0, coins.number - coins_sub.number);
                }
                break;
            }
        }
    }

    private void subBanknontes(Boolean mode, Banknotes banknotes_sub) {
        for (Banknotes banknotes : this.banknotes) {
            if (banknotes.denomination.equals(banknotes_sub.denomination)) {
                banknotes.changeNumber(Math.max(0, banknotes.number - banknotes_sub.number));
                if (!mode) {
                    sum -= banknotes.denomination * Math.max(0, banknotes.number - banknotes_sub.number);
                }
                break;
            }
        }
    }

    public Pair<List<Coins>, List<Banknotes>> intToMoney() {
        ArrayList<Coins> res_coins = new ArrayList<Coins>();
        ArrayList<Banknotes> res_banknotes = new ArrayList<Banknotes>();
        Collections.sort(this.coins, Money.getCompByName());
        Collections.sort(this.banknotes, Money.getCompByName());
        for (Banknotes banknote : this.banknotes) {
            Integer count = Math.min(banknote.number, sum/banknote.denomination);
            res_banknotes.add(new Banknotes(count, banknote.max_size, banknote.denomination));
        }
        for (Coins coin : this.coins) {
            Integer count = Math.min(coin.number, sum/coin.denomination);
            res_coins.add(new Coins(count, coin.max_size, coin.denomination));
        }
        return new Pair<>(coins, banknotes);
    }
}
