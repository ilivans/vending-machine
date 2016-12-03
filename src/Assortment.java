import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class Assortment {
    public ArrayList<Compartment> compartments;
    public ArrayList<Integer> productsIDs;

    public void occupyID() {
        // TODO: to understand
    }
    public void vacateID() {
        // TODO: to understand
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
}
