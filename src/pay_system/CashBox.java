package pay_system;

import utils.Pair;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.*;
import java.util.List;

public class CashBox extends JPanel {
    public Integer sum = 0;
    private List<Coins> coins;
    private List<Banknotes> banknotes;

    public List<JLabel> coins_icons = new ArrayList<>();
    public List<JLabel> banknotes_icons = new ArrayList<>();
    public List<JSpinner> coins_managers = new ArrayList<>();
    public List<JSpinner> banknotes_managers = new ArrayList<>();

    private Boolean mode;

    public CashBox(List<Coins> coins, List<Banknotes> banknotes) {
        super(new GridLayout(3, 2, 50, 50));
        setBackground(Color.white);
        setBorder(new TitledBorder("Cashbox"));
        setEnabled(false);
        this.coins = coins;
        this.banknotes = banknotes;

        initPanels();
    }

    private void initPanels() {
        for (Coins c : coins) {
            JPanel coin_panel = new JPanel(new BorderLayout());

            JSpinner spinner = new JSpinner(new SpinnerNumberModel((int) c.number, 0,(int) c.max_size, 1));
            spinner.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    for (int i = 0; i < coins.size(); i++) {
                        if (spinner.equals(coins_managers.get(i))) {
                            coins.get(i).number = (Integer) spinner.getValue();
                        }
                    }
                }
            });
            spinner.setEnabled(false);
            coins_managers.add(spinner);

            JLabel icon = new JLabel(new ImageIcon(CashBox.class.getResource(String.format("images/coin%d.jpg", c.denomination))));
            icon.setEnabled(false);
            coins_icons.add(icon);
            coin_panel.add(icon, BorderLayout.CENTER);
            coin_panel.add(spinner, BorderLayout.PAGE_END);
            add(coin_panel);
        }
        for (Banknotes b : banknotes) {
            JPanel banknote_panel = new JPanel(new BorderLayout());

            JSpinner spinner = new JSpinner(new SpinnerNumberModel((int) b.number, 0,(int) b.max_size, 1));
            spinner.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    for (int i = 0; i < banknotes.size(); i++) {
                        if (spinner.equals(banknotes_managers.get(i))) {
                            banknotes.get(i).number = (Integer) spinner.getValue();
                        }
                    }
                }
            });
            spinner.setEnabled(false);
            banknotes_managers.add(spinner);

            JLabel icon = new JLabel(new ImageIcon(CashBox.class.getResource(String.format("images/bill%d.jpg", b.denomination))));
            icon.setEnabled(false);
            banknotes_icons.add(icon);
            banknote_panel.add(icon, BorderLayout.CENTER);
            banknote_panel.add(spinner, BorderLayout.PAGE_END);
            add(banknote_panel);
        }
    }

    public void insert(Boolean mode, List<Coins> coins_new, List<Banknotes> banknotes_new) {
        this.mode = mode;
        for (Coins coins_add : coins_new) {
            addCoins(coins_add);
        }
        for (Banknotes banknotes_add : banknotes_new) {
            addBanknotes(banknotes_add);
        }
        updateManagers();
    }

    private void updateManagers() {
        for (int i = 0; i < coins.size(); i++) {
            coins_managers.get(i).setValue(coins.get(i).number);
        }
        for (int i = 0; i < banknotes.size(); i++) {
            banknotes_managers.get(i).setValue(banknotes.get(i).number);
        }
    }

    private void addBanknotes(Banknotes banknotes_add) {
        for (Banknotes banknotes : this.banknotes) {
            if (banknotes.denomination.equals(banknotes_add.denomination)) {
                if (mode) {
                    sum += banknotes_add.denomination * Integer.min(banknotes_add.number, banknotes.max_size - banknotes_add.number);
                }
                if (banknotes.max_size < banknotes.number + banknotes_add.number) {
                    banknotes.changeNumber(banknotes.max_size);
                    // TODO: drop extra money (that don't fit in anymore) into Change window
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
                if (mode) {
                    sum += coins_add.denomination * Integer.min(coins_add.number, coins.max_size - coins.number);
                }
                if (coins.max_size < coins.number + coins_add.number) {
                    coins.changeNumber(coins.max_size);
                    // TODO: drop extra money (that don't fit in anymore) into Change window
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
        updateManagers();
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
        int cur_sum = sum;
        ArrayList<Coins> res_coins = new ArrayList<Coins>();
        ArrayList<Banknotes> res_banknotes = new ArrayList<Banknotes>();
        Collections.sort(this.coins, Money.getCompByName());
        Collections.sort(this.banknotes, Money.getCompByName());
        for (Banknotes banknote : this.banknotes) {
            Integer count = Math.min(banknote.number, cur_sum/banknote.denomination);
            cur_sum -= count * banknote.denomination;
            res_banknotes.add(new Banknotes(count, banknote.max_size, banknote.denomination));
        }
        for (Coins coin : this.coins) {
            Integer count = Math.min(coin.number, cur_sum/coin.denomination);
            cur_sum -= count * coin.denomination;
            res_coins.add(new Coins(count, coin.max_size, coin.denomination));
        }
        return new Pair<>(res_coins, res_banknotes);
    }
}
