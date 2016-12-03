
import java.util.ArrayList;

public class CashBox {
    public Integer sum;
    private ArrayList<Coins> coins;
    private ArrayList<Banknotes> banknotes;

    public CashBox(ArrayList<Coins> coins, ArrayList<Banknotes> banknotes) {
        this.sum = 0;
        this.coins = coins;
        this.banknotes = banknotes;
    }

    public void insert(Boolean mode, ArrayList<Coins> coins, ArrayList<Banknotes> banknotes) {
        // TODO: implementation
    }

    private void addBanknotes(Banknotes banknotes) {
        // TODO: implementation
    }

    private void addCoins(Banknotes banknotes) {
        // TODO: implementation
    }

    public void take(Boolean mode, ArrayList<Coins> coinss, ArrayList<Banknotes> banknotes) {
        // TODO: implementation
    }

    private void subCoins(Coins unit) {
        // TODO: implementation
    }

    private void subBanknontes(Banknotes unit) {
        // TODO: implementation
    }

    public Pair<ArrayList<Coins>, ArrayList<Banknotes>> intToMoney() {
        // TODO: implementation
        return new Pair<ArrayList<Coins>, ArrayList<Banknotes>>(new ArrayList<Coins>(), new ArrayList<Banknotes>());
    }
}
