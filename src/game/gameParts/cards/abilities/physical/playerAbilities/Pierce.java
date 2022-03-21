package game.gameParts.cards.abilities.physical.playerAbilities;

import game.gameParts.cards.abilities.physical.PhysicalOffensiveAbility;
import game.gameParts.cards.monsters.Monster;
import game.gameParts.player.Runa;
import game.state.output.Exceptions;

/**
 * resembles the ability "Pierce"
 * @author upvlx
 * @version 0.1
 */
public class Pierce extends PhysicalOffensiveAbility {
    private static final int ABILITY_LEVEL_MODIFIER = 7;
    private static final int SPECIAL_ABILITY_MULTIPLIER = 5;
    private static final int LEAST_DICE_VALUE = 6;

    /**
     * constructor
     * @param abilityLevel the ability level the ability is supposed to have
     */
    public Pierce(int abilityLevel) {
        super(abilityLevel);
        this.name = "Pierce";
    }

    @Override
    public int calculateDamagePlayer(int value, Monster target) {
        int defaultDmg = ABILITY_LEVEL_MODIFIER * this.abilityLevel + value;
        return value < LEAST_DICE_VALUE ? defaultDmg : defaultDmg + SPECIAL_ABILITY_MULTIPLIER * this.abilityLevel;
    }

    @Override
    public int calculateDamageMonster(Runa target) {
        throw new RuntimeException(Exceptions.WRONG_ENTITY.getMsg());
    }
}
