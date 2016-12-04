import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class Panel extends JPanel {
    private CashBox cashBox;
    private Assortment assortment;
    private Boolean mode;

    private ThreadLocalRandom random = ThreadLocalRandom.current();
    private CodeInput code_input = new CodeInput();
    private JButton lock;
    private ImageIcon lock_icon = new ImageIcon(Panel.class.getResource("images/lock.png"));
    private ImageIcon unlock_icon = new ImageIcon(Panel.class.getResource("images/unlock.png"));

    public Panel(Boolean mode) {
        super();
//        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.mode = mode;
        setBackground(Color.white);

        initCashBox();
        initAssortment();
        initLock();
        
        add(assortment);
        add(code_input);
        add(cashBox);
        add(lock);

    }

    private void initLock() {
        lock = new JButton("Staff only", lock_icon);
        lock.setHorizontalTextPosition(JLabel.CENTER);
        lock.setVerticalTextPosition(JLabel.BOTTOM);
        lock.setBackground(Color.white);
        lock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mode = !mode;
                if (mode) {
                    lock.setIcon(lock_icon);
                    for (JSpinner spinner : assortment.compartment_managers) {
//                        spinner.setVisible(false);
                        spinner.setEnabled(false);
                    }
                } else {
                    lock.setIcon(unlock_icon);
                    for (JSpinner spinner : assortment.compartment_managers) {
                        spinner.setEnabled(true);
                    }
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

        cashBox = new CashBox(coins, banknotes);
    }

    private void initAssortment() {
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
            ImageIcon icon = new ImageIcon(Panel.class.getResource(String.format("images/%s.jpg", product_name)));
            Integer cells_free = random.nextInt(0, 10);
            compartments.add(new Compartment(label, icon, JLabel.CENTER, compartment_id, cells_max, cells_free, product));
        }
        assortment = new Assortment(compartments);
    }

    public void work() {

    }

    private void getChange() {
        // TODO: implementation
    }

    private void putMoney() {
        // TODO: implementation
    }

    private void getMoney() {
        // TODO: implementation
    }

    private void tryBuy(Integer compartment_id) {
        // TODO: implementation
    }

}
