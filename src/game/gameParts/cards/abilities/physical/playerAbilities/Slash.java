package game.gameParts.cards.abilities.physical.playerAbilities;

import game.gameParts.cards.abilities.physical.PhysicalOffensiveAbility;
import game.gameParts.cards.monsters.Monster;

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
    public int calculateDamage(int value, Object target) {
        if (target instanceof Monster) {
            Monster monster = (Monster) target;
            monster.deBuff();
            return ABILITY_LEVEL_MODIFIER * this.abilityLevel + value;
        }
        return 0;
    }
}
