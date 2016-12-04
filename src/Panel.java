import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Panel extends JPanel {
    private CashBox cashBox;
    private Assortment assortment;
    private Boolean mode;

    public Panel() {
        super();
        this.mode = true;
        cashBox = new CashBox(new ArrayList<Coins>(), new ArrayList<Banknotes>());

        int num_compartments = 10;
        List<String> product_names = Arrays.asList("bonaqua", "bounty", "coca-cola");
        List<Compartment> compartments = new ArrayList<>();
        for (int i =1; i < num_compartments; i++) {
            compartments.add(new Compartment())
        }

        assortment = new Assortment(new ArrayList<Compartment>());

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
