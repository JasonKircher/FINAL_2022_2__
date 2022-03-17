package game.gameParts.cards.abilities.physical.playerAbilities;

import game.gameParts.cards.abilities.physical.PhysicalOffensiveAbility;
import game.gameParts.cards.monsters.Monster;

/**
 * resembles ability "Swing"
 * @author upvlx
 * @version 0.1
 */
public class Swing extends PhysicalOffensiveAbility {
    /**
     * constructor
     * @param abilityLevel the ability level the ability is supposed to have
     */
    public Swing(int abilityLevel) {
        super(abilityLevel);
        this.name = "Swing";
    }

    @Override
    public int calculateDamage(int value, Object target) {
        if (target instanceof Monster) {
            Monster monster = (Monster) target;
            monster.deBuff();
            return 5 * this.abilityLevel + value;
        }
        return 0;
    }

}
