
public class Money {
    public Integer number;
    public Integer max_size;
    public Integer denomination;

    public Money(Integer number, Integer max_size, Integer denomination) {
        this.number = number;
        this.max_size = max_size;
        this.denomination = denomination;
    }

    public void changeNumber(Integer new_number) {
        number = Integer.max(new_number, number);
    }
}
