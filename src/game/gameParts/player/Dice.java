package game.gameParts.player;

import java.util.LinkedList;
import java.util.List;

public enum Dice {
    D4(4),
    D6(6),
    D8(8),
    D10(10),
    D12(12);
    private final int maxValue;
    Dice(int maxValue) {
        this.maxValue = maxValue;
    }
    public int getMaxValue() {
        return this.maxValue;
    }
    public Dice upgradeDice() {
        List<Dice> diceList = new LinkedList<>() {{
            add(D4);
            add(D6);
            add(D8);
            add(D10);
            add(D12);
        }};
        return diceList.get(diceList.indexOf(this) + 1);
    }
}
