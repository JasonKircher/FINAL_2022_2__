package game.gameParts.cards.abilities.magical;

import game.gameParts.cards.monsters.Monster;
import game.gameParts.player.Runa;
import game.state.output.Exceptions;

/**
 * class that resembles the Ability "Reflect"
 * @author upvlx
 * @version 0.1
 */
public class Reflect extends DefensiveMagicAbility {
    private static final int ABILITY_LEVEL_MODIFIER = 10;

    /**
     * constructor
     * @param abilityLevel the ability level the ability is supposed to have
     */
    public Reflect(int abilityLevel) {
        super(abilityLevel);
        this.name = "Reflect";
    }

    @Override
    public void calculateMitigationPlayer(Runa target) {
        target.setMagicMitigation(ABILITY_LEVEL_MODIFIER * this.abilityLevel);
        target.setReflecting();
    }

    @Override
    public void calculateMitigationMonster(Monster target) {
        throw new RuntimeException(Exceptions.WRONG_ENTITY.getMsg());
    }
}
