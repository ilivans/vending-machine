package pay_system;

public class Banknotes extends Money {

    public Banknotes(Integer number, Integer max_size, Integer denomination) {
        super(number, max_size, denomination);
    }

    public Banknotes() {
    }

    public Banknotes(Integer denomination) {
        super(denomination);
    }
}
