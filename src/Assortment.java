import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Assortment extends JPanel {
    public ArrayList<Compartment> compartments;

    public Assortment(LayoutManager layoutManager, boolean b, ArrayList<Compartment> compartments) {
        super(layoutManager, b);
        this.compartments = compartments;
    }

    public void changeCompartment(Compartment compartment, Product product, Integer number) {
        compartment.changeProduct(product);
        compartment.changeCellsFree(Integer.max(compartment.cells_max - number, 0));
    }
    public Integer getPrice(Integer compartment_id) {
        for (Compartment compartment : compartments) {
            if (compartment.id.equals(compartment_id)) {
                return compartment.product.price;
            }
        }
        throw new ValueException("No compartment with id " + compartment_id.toString());
    }
    public void buy(Integer compartment_id) {
        // TODO: implementation
    }
}
