package game.gameParts.cards.abilities.magical;

import game.gameParts.player.Runa;

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
    public void calculateMitigation(Object target) {
        if (target instanceof Runa) {
            Runa runa = (Runa) target;
            runa.setMagicMitigation(ABILITY_LEVEL_MODIFIER * this.abilityLevel);
            runa.setReflecting();
        }
    }
}
