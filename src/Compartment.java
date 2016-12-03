
public class Compartment {
    public Integer id;
    public Integer cells_max;
    public Integer cells_free;
    public Product product;

    public void changeProduct(Product new_product) {
        product = new_product;
        // TODO: to manage cells_free
    }

    public void changeCellsFree(Integer new_cells_free) {
        cells_free = new_cells_free;
    }
}
