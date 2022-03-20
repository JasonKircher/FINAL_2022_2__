package game.gameParts.cards.abilities.physical.playerAbilities;

import game.gameParts.cards.abilities.physical.PhysicalOffensiveAbility;
import game.gameParts.cards.monsters.Monster;

/**
 * resembles the ability "Thrust"
 * @author upvlx
 * @version 0.1
 */
public class Thrust extends PhysicalOffensiveAbility {
    private static final int ABILITY_LEVEL_MODIFIER = 6;
    private static final int SPECIAL_ABILITY_MULTIPLIER = 4;
    private static final int LEAST_DICE_VALUE = 6;

    /**
     * constructor
     * @param abilityLevel the ability level the ability is supposed to have
     */
    public Thrust(int abilityLevel) {
        super(abilityLevel);
        this.name = "Thrust";
    }

    @Override
    public int calculateDamage(int value, Object target) {
        if (target instanceof Monster) {
            int defaultDmg = ABILITY_LEVEL_MODIFIER * this.abilityLevel + value;
            return value < LEAST_DICE_VALUE ? defaultDmg : defaultDmg + SPECIAL_ABILITY_MULTIPLIER * this.abilityLevel;
        }
        return 0;
    }
}
