import javax.swing.*;
import java.awt.*;

public class Compartment extends JLabel {
    public Integer id;
    public Integer cells_max;
    public Integer cells_free;
    public Product product;

    public Compartment(String s, Icon icon, int i, Integer id, Integer cells_max, Integer cells_free, Product product) {
        super(s, icon, i);
        this.id = id;
        this.cells_max = cells_max;
        this.cells_free = cells_free;
        this.product = product;
    }

    public void changeProduct(Product new_product) {
        product = new_product;
        // TODO: how to manage cells_free?
    }

    public void changeCellsFree(Integer new_cells_free) {
        cells_free = new_cells_free;
    }
}
