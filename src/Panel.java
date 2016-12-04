import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class Panel extends JPanel {
    private CashBox cashBox;
    private Assortment assortment;
    private Boolean mode;

    private ThreadLocalRandom TLR;

    public Panel(Boolean mode) {
        super();
        setBackground(Color.white);
        this.mode = mode;
        TLR = ThreadLocalRandom.current();

        initCashBox();
        initAssortment();
        add(assortment);
        add(cashBox);
    }

    private void initCashBox() {
        Integer coins_max_size = 40;
        List<Integer> coins_denominations = Arrays.asList(5, 10);
        List<Coins> coins = new ArrayList<>();
        for (Integer denomination : coins_denominations) {
            coins.add(new Coins(TLR.nextInt(coins_max_size / 2, coins_max_size), coins_max_size, denomination));
        }

        Integer banknotes_max_size = 100;
        List<Integer> banknotes_denominations = Arrays.asList(10, 50, 100);
        List<Banknotes> banknotes = new ArrayList<>();
        for (Integer denomination : banknotes_denominations) {
            banknotes.add(new Banknotes(TLR.nextInt(banknotes_max_size / 2, banknotes_max_size), banknotes_max_size, denomination));
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
            Integer price = TLR.nextInt(40, 80) / 5 * 5;
            Product product = new Product(product_name, price, i);
            Integer compartment_id = i + 10;
            String label = String.format("%d | %dр", compartment_id, product.price);
            ImageIcon icon = new ImageIcon(Panel.class.getResource(String.format("images/%s.jpg", product_name)));
            Integer cells_free = TLR.nextInt(0, 10);
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
