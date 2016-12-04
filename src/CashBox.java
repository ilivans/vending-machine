
import java.util.ArrayList;
import java.util.List;

public class CashBox {
    public Integer sum;
    private List<Coins> coins;
    private List<Banknotes> banknotes;

    public CashBox(List<Coins> coins, List<Banknotes> banknotes) {
        this.sum = 0;
        this.coins = coins;
        this.banknotes = banknotes;
    }

    public void insert(Boolean mode, List<Coins> coins, List<Banknotes> banknotes) {
        // TODO: implementation
    }

    private void addBanknotes(Banknotes banknotes) {
        // TODO: implementation
    }

    private void addCoins(Banknotes banknotes) {
        // TODO: implementation
    }

    public void take(Boolean mode, List<Coins> coinss, List<Banknotes> banknotes) {
        // TODO: implementation
    }

    private void subCoins(Coins unit) {
        // TODO: implementation
    }

    private void subBanknontes(Banknotes unit) {
        // TODO: implementation
    }

    public Pair<List<Coins>, List<Banknotes>> intToMoney() {
        // TODO: implementation
        return new Pair<>(new ArrayList<Coins>(), new ArrayList<Banknotes>());
    }
}
