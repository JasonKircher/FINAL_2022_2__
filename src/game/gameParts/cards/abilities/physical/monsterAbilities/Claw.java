package game.gameParts.cards.abilities.physical.monsterAbilities;

import game.gameParts.cards.abilities.physical.PhysicalOffensiveAbility;
import game.gameParts.player.Runa;

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
    public int calculateDamage(int value, Object target) {
        if (target instanceof Runa) {
            Runa runa = (Runa) target;
            runa.deBuff();
            return ABILITY_LEVEL_MODIFIER * this.abilityLevel;
        }
        return 0;
    }
}
