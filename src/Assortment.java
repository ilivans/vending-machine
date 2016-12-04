import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

import javax.swing.*;

import java.util.List;

public class Assortment extends JPanel {
    public List<Compartment> compartments;
    private Integer num_compartments;

    public Assortment(List<Compartment> compartments) {
        num_compartments = compartments.size();
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
