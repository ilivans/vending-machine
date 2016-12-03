import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    private CashBox cashBox;
    private Assortment assortment;
    private Boolean mode;

    public Panel(LayoutManager layoutManager, boolean b, CashBox cashBox, Assortment assortment) {
        super(layoutManager, b);
        this.cashBox = cashBox;
        this.assortment = assortment;
        this.mode = true;
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
