package game.gameParts.cards.abilities.physical.monsterAbilities;

import game.gameParts.cards.abilities.physical.PhysicalDefensiveAbility;
import game.gameParts.cards.monsters.Monster;

/**
 * resembles the ability "Block"
 * @author upvlx
 * @version 0.1
 */
public class Block extends PhysicalDefensiveAbility {
    /**
     * constructor
     * @param abilityLevel the ability level the ability is supposed to have
     */
    public Block(int abilityLevel) {
        super(abilityLevel);
        this.name = "Block";
    }

    @Override
    public void calculateMitigation(Object target) {
        if (target instanceof Monster monster) {
            monster.setPhysicalMitigation(7 * this.abilityLevel);
        }
    }
}
