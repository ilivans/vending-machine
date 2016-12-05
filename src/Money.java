import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

import java.util.Comparator;

public class Money {
    public Integer number = 0;
    public Integer max_size = 0;
    public Integer denomination = 0;

    public Money(Integer number, Integer max_size, Integer denomination) {
        this.number = number;
        this.max_size = max_size;
        this.denomination = denomination;
    }

    public Money() {
    }

    public Money(Integer denomination) {
        number = 1;
        max_size = 1;
        this.denomination = denomination;
    }

    public void changeNumber(Integer new_number) {
        if (new_number > max_size || new_number < 0) {
            throw new ValueException("Invalid amount of money");
        }
        number = new_number;
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
