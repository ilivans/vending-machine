package pay_system;

public class Coins extends Money {

    public Coins(Integer number, Integer max_size, Integer denomination) {
        super(number, max_size, denomination);
    }

    public Coins() {
    }

    public Coins(Integer denomination) {
        super(denomination);
    }
}
