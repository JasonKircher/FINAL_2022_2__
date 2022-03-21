package game.gameParts.cards.abilities.physical.playerAbilities;

import game.gameParts.cards.abilities.physical.PhysicalDefensiveAbility;
import game.gameParts.cards.monsters.Monster;
import game.gameParts.player.Runa;
import game.state.output.Exceptions;

/**
 * resembles the ability "Parry"
 * @author upvlx
 * @version 0.1
 */
public class Parry extends PhysicalDefensiveAbility {
    private static final int ABILITY_LEVEL_MODIFIER = 7;

    /**
     * constructor
     * @param abilityLevel the ability level the ability is supposed to have
     */
    public Parry(int abilityLevel) {
        super(abilityLevel);
        this.name = "Parry";
    }

    @Override
    public void calculateMitigationPlayer(Runa target) {
        target.setPhysicalMitigation(ABILITY_LEVEL_MODIFIER * this.abilityLevel);
    }

    @Override
    public void calculateMitigationMonster(Monster target) {
        throw new RuntimeException(Exceptions.WRONG_ENTITY.getMsg());
    }
}
