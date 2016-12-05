
import utils.Pair;
import java.util.*;
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

    public void insert(Boolean mode, List<Coins> coins_new, List<Banknotes> banknotes_new) {
        for (Coins coins_add : coins_new) {
            addCoins(coins_add);
            if (mode) {
                sum += coins_add.denomination * coins_add.number;
            }
        }
        for (Banknotes banknotes_add : banknotes_new) {
            addBanknotes(banknotes_add);
            if (mode) {
                sum += banknotes_add.denomination * banknotes_add.number;
            }
        }
    }

    private void addBanknotes(Banknotes banknotes_add) {
        for (Banknotes banknotes : this.banknotes) {
            if (banknotes.denomination.equals(banknotes_add.denomination)) {
                if (banknotes.max_size < banknotes.number + banknotes_add.number) {
                    banknotes.changeNumber(banknotes.max_size);
                    // TODO: add logic with dropping extra money (that don't fit in anymore) into Change window
                }
                else {
                    banknotes.changeNumber(banknotes.number + banknotes_add.number);
                }
                break;
            }
        }
    }

    private void addCoins(Coins coins_add) {
        for (Coins coins : this.coins) {
            if (coins.denomination.equals(coins_add.denomination)) {
                if (coins.max_size < coins.number + coins_add.number) {
                    coins.changeNumber(coins.max_size);
                    // TODO: add logic with dropping extra money (that don't fit in anymore) into Change window
                }
                else {
                    coins.changeNumber(coins.number + coins_add.number);
                }
                break;
            }
        }
    }

    public void take(Boolean mode, List<Coins> coins, List<Banknotes> banknotes) {
        for (Coins coin : coins) {
            subCoins(mode, coin);
        }
        for (Banknotes banknote : banknotes) {
            subBanknontes(mode, banknote);
        }
    }

    private void subCoins(Boolean mode, Coins coins_sub) {
        for (Coins coins : this.coins) {
            if (coins.denomination.equals(coins_sub.denomination)) {
                coins.changeNumber(Math.max(0, coins.number - coins_sub.number));
                if (mode) {
                    sum -= coins.denomination * Math.max(0, coins.number - coins_sub.number);
                }
                break;
            }
        }
    }

    private void subBanknontes(Boolean mode, Banknotes banknotes_sub) {
        for (Banknotes banknotes : this.banknotes) {
            if (banknotes.denomination.equals(banknotes_sub.denomination)) {
                banknotes.changeNumber(Math.max(0, banknotes.number - banknotes_sub.number));
                if (mode) {
                    sum -= banknotes.denomination * Math.max(0, banknotes.number - banknotes_sub.number);
                }
                break;
            }
        }
    }

    public Pair<List<Coins>, List<Banknotes>> intToMoney() {
        ArrayList<Coins> res_coins = new ArrayList<Coins>();
        ArrayList<Banknotes> res_banknotes = new ArrayList<Banknotes>();
        Collections.sort(this.coins, Money.getCompByName());
        Collections.sort(this.banknotes, Money.getCompByName());
        for (Banknotes banknote : this.banknotes) {
            Integer count = Math.min(banknote.number, sum/banknote.denomination);
            res_banknotes.add(new Banknotes(count, banknote.max_size, banknote.denomination));
        }
        for (Coins coin : this.coins) {
            Integer count = Math.min(coin.number, sum/coin.denomination);
            res_coins.add(new Coins(count, coin.max_size, coin.denomination));
        }
        return new Pair<>(coins, banknotes);
    }
}
