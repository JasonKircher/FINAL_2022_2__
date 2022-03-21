package game.gameParts.cards.abilities.physical.monsterAbilities;

import game.gameParts.cards.abilities.physical.PhysicalDefensiveAbility;
import game.gameParts.cards.monsters.Monster;
import game.gameParts.player.Runa;
import game.state.output.Exceptions;

/**
 * resembles the ability "Block"
 * @author upvlx
 * @version 0.1
 */
public class Block extends PhysicalDefensiveAbility {
    private static final int ABILITY_LEVEL_MODIFIER = 7;

    /**
     * constructor
     * @param abilityLevel the ability level the ability is supposed to have
     */
    public Block(int abilityLevel) {
        super(abilityLevel);
        this.name = "Block";
    }

    @Override
    public void calculateMitigationPlayer(Runa target) {
        throw new RuntimeException(Exceptions.WRONG_ENTITY.getMsg());
    }

    @Override
    public void calculateMitigationMonster(Monster target) {
        target.setPhysicalMitigation(ABILITY_LEVEL_MODIFIER * this.abilityLevel);
    }
}
