package game.gameParts.cards.abilities.magical;

import game.gameParts.cards.monsters.Monster;

/**
 * class for the ability "Deflect"
 * @author upvlx
 * @version 0.1
 */
public class Deflect extends DefensiveMagicAbility {
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
            monster.setMagicMitigation(11 * this.abilityLevel + 2);
        }
    }
}
