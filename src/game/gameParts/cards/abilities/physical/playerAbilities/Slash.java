package game.gameParts.cards.abilities.physical.playerAbilities;

import game.gameParts.cards.abilities.physical.PhysicalOffensiveAbility;
import game.gameParts.cards.monsters.Monster;
import game.gameParts.player.Runa;
import game.state.output.Exceptions;

/**
 * resembles the ability "Smash"
 * @author upvlx
 * @version 0.1
 */
public class Slash extends PhysicalOffensiveAbility {
    private static final int ABILITY_LEVEL_MODIFIER = 4;

    /**
     * constructor
     * @param abilityLevel the ability level the ability is supposed to have
     */
    public Slash(int abilityLevel) {
        super(abilityLevel);
        this.name = "Slash";
    }

    @Override
    public int calculateDamagePlayer(int value, Monster target) {
        target.deBuff();
        return ABILITY_LEVEL_MODIFIER * this.abilityLevel + value;
    }

    @Override
    public int calculateDamageMonster(Runa target) {
        throw new RuntimeException(Exceptions.WRONG_ENTITY.getMsg());
    }
}
