package goods;

public class Product {
    public String name;
    public Integer price;
    private Integer id;

    public Product(String name, Integer price, Integer id) {
        this.name = name;
        this.price = price;
        this.id = id;
    }

    public void changePrice(Integer new_price) {
        price = new_price;
    }
}
