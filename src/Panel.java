import goods.Assortment;
import goods.Compartment;
import goods.Product;
import pay_system.Banknotes;
import pay_system.CashBox;
import pay_system.Coins;
import utils.Pair;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Panel extends JPanel {
    private CashBox cashbox;
    private Assortment assortment;
    private Boolean mode;   // true - customer, false - staff

    private ThreadLocalRandom random = ThreadLocalRandom.current();

    private CodeInput code_input = new CodeInput();
    private JButton buy_button;

    private JPanel money_manipulators = new JPanel(new GridLayout(4, 1, 0, 0));
    private JLabel sum_display;
    private JButton coin_acceptor;
    private JButton bill_acceptor;
    private JButton change_window;
    private Pair<List<Coins>, List<Banknotes>> change;

    private JButton lock;
    private ImageIcon lock_icon = new ImageIcon(Panel.class.getResource("images/lock.png"));
    private ImageIcon unlock_icon = new ImageIcon(Panel.class.getResource("images/unlock.png"));

    public Panel(Boolean mode) {
        super();
        this.mode = mode;
        setBackground(Color.white);

        setBorder(new TitledBorder(""));
        setBackground(Color.white);

        initAssortment();
        initBuyButton();
        initCashBox();
        initMoneyManipulators();
        initLock();

        add(assortment);
        add(code_input);
        add(money_manipulators);
        add(lock);
        add(cashbox);

        work();
    }

    private void initMoneyManipulators() {
        sum_display = new JLabel(cashbox.sum.toString());
        sum_display.setBorder(new TitledBorder("Sum entered"));
        money_manipulators.add(sum_display);
        initCoinAcceptor();
        money_manipulators.add(coin_acceptor);
        initBillAcceptor();
        money_manipulators.add(bill_acceptor);
        initChangeWindow();
        money_manipulators.add(change_window);
    }

    private void initCoinAcceptor() {
        coin_acceptor = new JButton("Coin Acceptor", new ImageIcon(Panel.class.getResource("images/coin-acceptor.png")));
        coin_acceptor.setHorizontalTextPosition(JLabel.CENTER);
        coin_acceptor.setVerticalTextPosition(JLabel.BOTTOM);
        coin_acceptor.addActionListener(new ActionListener(){
            // TODO: add images for each denomination (it's probably needed to replace OptionDialog with something else)
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
                putMoney(Collections.singletonList(new Coins(denomination)), Collections.singletonList(new Banknotes()));
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
                putMoney(Collections.singletonList(new Coins()), Collections.singletonList(new Banknotes(denomination)));
            }
        });
    }

    private void initChangeWindow() {
        change_window = new JButton("Get Change", new ImageIcon(Panel.class.getResource("images/change.png")));
        change_window.setBackground(Color.white);
        change_window.setHorizontalTextPosition(JLabel.CENTER);
        change_window.setVerticalTextPosition(JLabel.BOTTOM);
        change_window.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getChange();
            }
        });
    }

    private void initBuyButton() {
        buy_button = new JButton("BUY");
        buy_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String code_text = code_input.code_display.getText();
                if (code_text.length() != 2) {
                    code_input.code_display.setText(code_input.error_text + ": invalid code");
                    return;
                }
                tryBuy(Integer.parseInt(code_text));
            }
        });
        // Just add to JPanel
        code_input.add(buy_button);
    }

    private void initLock() {
        lock = new JButton("Staff only", lock_icon);
        lock.setHorizontalTextPosition(JLabel.CENTER);
        lock.setVerticalTextPosition(JLabel.BOTTOM);
        lock.setBackground(Color.white);
        lock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mode && cashbox.sum > 0) {
                    JOptionPane.showMessageDialog(new JFrame(), "Money is entered.");
                    return;
                }
                mode = !mode;
                if (mode) {
                    lock.setIcon(lock_icon);
                    for (JSpinner spinner : assortment.compartment_managers) {
                        spinner.setEnabled(false);
                    }
                    for (JSpinner spinner : cashbox.coins_managers) {
                        spinner.setEnabled(false);
                    }
                    for (JSpinner spinner : cashbox.banknotes_managers) {
                        spinner.setEnabled(false);
                    }
                    for (JLabel icon : cashbox.coins_icons) {
                        icon.setEnabled(false);
                    }
                    for (JLabel icon : cashbox.banknotes_icons) {
                        icon.setEnabled(false);
                    }
                    buy_button.setEnabled(true);
                    coin_acceptor.setEnabled(true);
                    bill_acceptor.setEnabled(true);
                    change_window.setEnabled(true);
                } else {
                    lock.setIcon(unlock_icon);
                    for (JSpinner spinner : assortment.compartment_managers) {
                        spinner.setEnabled(true);
                    }
                    for (JSpinner spinner : cashbox.coins_managers) {
                        spinner.setEnabled(true);
                    }
                    for (JSpinner spinner : cashbox.banknotes_managers) {
                        spinner.setEnabled(true);
                    }
                    for (JLabel icon : cashbox.coins_icons) {
                        icon.setEnabled(true);
                    }
                    for (JLabel icon : cashbox.banknotes_icons) {
                        icon.setEnabled(true);
                    }
                    buy_button.setEnabled(false);
                    coin_acceptor.setEnabled(false);
                    bill_acceptor.setEnabled(false);
                    change_window.setEnabled(false);
                }
            }
        });
    }

    private void initCashBox() {
        Integer coins_max_size = 40;
        List<Integer> coins_denominations = Arrays.asList(5, 10);
        List<Coins> coins = new ArrayList<>();
        for (Integer denomination : coins_denominations) {
            coins.add(new Coins(random.nextInt(coins_max_size / 2, coins_max_size), coins_max_size, denomination));
        }

        Integer banknotes_max_size = 100;
        List<Integer> banknotes_denominations = Arrays.asList(10, 50, 100);
        List<Banknotes> banknotes = new ArrayList<>();
        for (Integer denomination : banknotes_denominations) {
            banknotes.add(new Banknotes(random.nextInt(banknotes_max_size / 2, banknotes_max_size), banknotes_max_size, denomination));
        }
        cashbox = new CashBox(coins, banknotes);
    }

    private void initAssortment() {
        // TODO: move all parameters of machine (e.g. amount of money or product descriptions) to configuration file
        // there must must be pictures with the same names and .jpg format in images folder
        List<String> product_names = Arrays.asList("bonaqua", "bounty", "coca-cola", "fanta", "lay's", "m&m's");
        List<Compartment> compartments = new ArrayList<>();
        int cells_max = 10;
        // iterate throw products and add them to compartments list
        for (int i = 0; i < product_names.size(); i++) {
            String product_name = product_names.get(i);
            Integer price = random.nextInt(40, 80) / 5 * 5;
            Product product = new Product(product_name, price, i);
            Integer compartment_id = i + 11;
            String label = String.format("#%d | %d rub.", compartment_id, product.price);
            ImageIcon icon = new ImageIcon(Product.class.getResource(String.format("images/%s.jpg", product_name)));
            Integer cells_free = random.nextInt(0, 10);
            compartments.add(new Compartment(label, icon, JLabel.CENTER, compartment_id, cells_max, cells_free, product));
        }
        assortment = new Assortment(compartments);
    }

    public void work() {
        setVisible(true);
    }

    private void getChange() {
        change = cashbox.intToMoney();
        getMoney(change.getFirst(), change.getSecond());
        cashbox.sum = 0;
        sum_display.setText("0");
        // TODO: add a visualization of the money received by customer
    }

    private void putMoney(List<Coins> coins, List<Banknotes> banknotes) {
        cashbox.insert(mode, coins, banknotes);
        sum_display.setText(Integer.toString(cashbox.sum));
    }

    private void getMoney(List<Coins> coins, List<Banknotes> banknotes) {
        cashbox.take(mode, coins, banknotes);
    }

    private void tryBuy(Integer compartment_id) {
        Compartment compartment = null;
        for (Compartment comp : assortment.compartments) {
            if (compartment_id.equals(comp.id)) {
                compartment = comp;
                break;
            }
        }
        if (compartment == null) {
            code_input.code_display.setText(code_input.error_text + ": invalid code");
            return;
        }
        if (Objects.equals(compartment.cells_free, compartment.cells_max)) {
            code_input.code_display.setText(code_input.error_text + ": out of goods");
            return;
        }
        if (cashbox.sum < compartment.product.price) {
            code_input.code_display.setText(code_input.error_text + ": not enough money");
            return;
        }
        code_input.code_display.setText("");
        cashbox.sum -= compartment.product.price;
        sum_display.setText(Integer.toString(cashbox.sum));
        assortment.buy(compartment_id);
        // TODO: add a visualization of the bought product
    }

}
