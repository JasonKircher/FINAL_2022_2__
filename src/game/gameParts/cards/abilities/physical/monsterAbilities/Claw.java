package game.gameParts.cards.abilities.physical.monsterAbilities;

import game.gameParts.cards.abilities.physical.PhysicalOffensiveAbility;
import game.gameParts.cards.monsters.Monster;
import game.gameParts.player.Runa;
import game.state.output.Exceptions;

/**
 * resembles the ability "Claw"
 * @author upvlx
 * @version 0.1
 */
public class Claw extends PhysicalOffensiveAbility {
    private static final int ABILITY_LEVEL_MODIFIER = 6;

    /**
     * constructor
     * @param abilityLevel the ability level the ability is supposed to have
     */
    public Claw(int abilityLevel) {
        super(abilityLevel);
        this.name = "Claw";
    }

    @Override
    public int calculateDamagePlayer(int value, Monster target) {
        throw new RuntimeException(Exceptions.WRONG_ENTITY.getMsg());
    }

    @Override
    public int calculateDamageMonster(Runa target) {
        target.deBuff();
        return ABILITY_LEVEL_MODIFIER * this.abilityLevel;
    }
}
