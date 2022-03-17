package game.gameParts.cards.abilities.physical.playerAbilities;

import game.gameParts.cards.abilities.physical.PhysicalOffensiveAbility;
import game.gameParts.cards.monsters.Monster;

/**
 * resembles the ability "Pierce"
 * @author upvlx
 * @version 0.1
 */
public class Pierce extends PhysicalOffensiveAbility {
    /**
     * constructor
     * @param abilityLevel the ability level the ability is supposed to have
     */
    public Pierce(int abilityLevel) {
        super(abilityLevel);
        this.name = "Pierce";
    }

    @Override
    public int calculateDamage(int value, Object target) {
        if (target instanceof Monster) {
            int defaultDmg = 7 * this.abilityLevel + value;
            return value < 6 ? defaultDmg : defaultDmg + 5 * this.abilityLevel;
        }
        return 0;
    }
}
