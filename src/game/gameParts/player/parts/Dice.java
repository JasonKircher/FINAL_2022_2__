package game.gameParts.player.parts;

import java.util.LinkedList;
import java.util.List;

/**
 * enum which holds the dices
 * @author upvlx
 * @version 0.1
 */
public enum Dice {
    /**
     * d4 dice
     */
    D4(4),
    /**
     * d6 dice
     */
    D6(6),
    /**
     * d8 dice
     */
    D8(8),
    /**
     * d10 dice
     */
    D10(10),
    /**
     * d12 dice
     */
    D12(12);
    private final int maxValue;

    /**
     * constructor for a dice
     * @param maxValue maximum value of the dice
     */
    Dice(int maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * getter for the max value pf a dice
     * @return the maximum value of a specific dice
     */
    public int getMaxValue() {
        return this.maxValue;
    }

    /**
     * function to upgrade a dice
     * @return the corresponding next dice
     */
    public Dice upgradeDice() {
        // list which holds the dice in the correct order
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
