import javax.swing.*;
import java.awt.*;

public class Compartment extends JLabel {
    public Integer id;
    public Integer cells_max;
    public Integer cells_free;
    public Product product;

    public Compartment(String s, Icon icon, int i, Integer id, Integer cells_max, Integer cells_free, Product product) {
        super(s, icon, i);
        setHorizontalTextPosition(JLabel.CENTER);
        setVerticalTextPosition(JLabel.BOTTOM);
        this.id = id;
        this.cells_max = cells_max;
        this.cells_free = cells_free;
        this.product = product;
    }

    public void changeProduct(Product new_product) {
        product = new_product;
    }

    public void changeCellsFree(Integer new_cells_free) {
        cells_free = new_cells_free;
    }
}
