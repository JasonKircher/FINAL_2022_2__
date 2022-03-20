package game.gameParts.cards.abilities.physical.monsterAbilities;

import game.gameParts.cards.abilities.physical.PhysicalOffensiveAbility;
import game.gameParts.player.Runa;

/**
 * resembles the ability "Scratch"
 * @author upvlx
 * @version 0.1
 */
public class Scratch extends PhysicalOffensiveAbility {
    private static final int ABILITY_LEVEL_MODIFIER = 5;

    /**
     * constructor
     * @param abilityLevel the ability level the ability is supposed to have
     */
    public Scratch(int abilityLevel) {
        super(abilityLevel);
        this.name = "Scratch";
    }

    @Override
    public int calculateDamage(int value, Object target) {
        if (target instanceof Runa) {
            Runa runa = (Runa) target;
            runa.deBuff();
            return ABILITY_LEVEL_MODIFIER * this.abilityLevel;
        }
        return 0;
    }
}
