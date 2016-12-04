import java.util.Comparator;

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

    public static Comparator<Money> getCompByName() {
        Comparator comp = new Comparator<Money>(){
            public int compare(Money s1, Money s2) {
                return s1.denomination.compareTo(s2.denomination);
            }
        };
        return comp;
    }
}
