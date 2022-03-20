package game.gameParts.cards.abilities.magical;

import game.gameParts.cards.monsters.Monster;

/**
 * class for the ability "Deflect"
 * @author upvlx
 * @version 0.1
 */
public class Deflect extends DefensiveMagicAbility {
    private static final int ABILITY_LEVEL_MODIFIER = 11;
    private static final int DAMAGE_TO_ADD = 2;

    /**
     * constructor
     * @param abilityLevel the level the ability is supposed to have
     */
    public Deflect(int abilityLevel) {
        super(abilityLevel);
        this.name = "Deflect";
    }

    @Override
    public void calculateMitigation(Object target) {
        if (target instanceof Monster) {
            Monster monster = (Monster) target;
            monster.setMagicMitigation(ABILITY_LEVEL_MODIFIER * this.abilityLevel + DAMAGE_TO_ADD);
        }
    }
}
