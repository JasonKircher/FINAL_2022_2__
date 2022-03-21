package game.gameParts.cards.abilities.physical.monsterAbilities;

import game.gameParts.cards.abilities.physical.PhysicalOffensiveAbility;
import game.gameParts.cards.monsters.Monster;
import game.gameParts.player.Runa;
import game.state.output.Exceptions;

/**
 * resembles the attack "Bite"
 * @author uplx
 * @version 0.1
 */
public class Bite extends PhysicalOffensiveAbility {
    private static final int ABILITY_LEVEL_MODIFIER = 10;

    /**
     * constructor
     * @param abilityLevel the ability level the ability is supposed to have
     */
    public Bite(int abilityLevel) {
        super(abilityLevel);
        this.name = "Bite";
    }

    @Override
    public int calculateDamagePlayer(int value, Monster target) {
        throw new RuntimeException(Exceptions.WRONG_ENTITY.getMsg());
    }

    @Override
    public int calculateDamageMonster(Runa target) {
        return ABILITY_LEVEL_MODIFIER * this.abilityLevel;
    }
}
