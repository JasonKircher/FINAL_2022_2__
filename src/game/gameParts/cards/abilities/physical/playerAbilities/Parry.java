package game.gameParts.cards.abilities.physical.playerAbilities;

import game.gameParts.cards.abilities.physical.PhysicalDefensiveAbility;
import game.gameParts.player.Runa;

/**
 * resembles the ability "Parry"
 * @author upvlx
 * @version 0.1
 */
public class Parry extends PhysicalDefensiveAbility {
    /**
     * constructor
     * @param abilityLevel the ability level the ability is supposed to have
     */
    public Parry(int abilityLevel) {
        super(abilityLevel);
        this.name = "Parry";
    }

    @Override
    public void calculateMitigation(Object target) {
        if (target instanceof Runa) {
            Runa runa = (Runa) target;
            runa.setPhysicalMitigation(this.abilityLevel * 7);
        }
    }
}
