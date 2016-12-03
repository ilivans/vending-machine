
import java.util.ArrayList;

public class CashBox extends Money {
    public Integer sum;
    private ArrayList<Coins> coins = new ArrayList<Coins>();
    private ArrayList<Banknotes> banknotes = new ArrayList<Banknotes>();

    public void insert(Boolean mode, ArrayList<Coins> coinss, ArrayList<Banknotes> banknotes) {
        this.coins.addAll(coinss);
        this.banknotes.addAll(banknotes);
        // TODO: to understand
    }

    private void addBanknotes(Banknotes banknotes) {
        // TODO: to understand
    }

    private void addCoins(Banknotes banknotes) {
        // TODO: to understand
    }

    public void take(Boolean mode, ArrayList<Coins> coinss, ArrayList<Banknotes> banknotes) {
        // TODO: to understand
    }

    private void subCoins(Coins unit) {
        // TODO: to understand
    }

    private void subBanknontes(Banknotes unit) {
        // TODO: to understand
    }

    public Pair<ArrayList<Coins>, ArrayList<Banknotes>> intToMoney() {
        // TODO: to understand
        return new Pair<ArrayList<Coins>, ArrayList<Banknotes>>(new ArrayList<Coins>(), new ArrayList<Banknotes>());
    }
}
