package game.gameParts.cards.abilities;

import game.gameParts.cards.monsters.Monster;
import game.gameParts.player.Runa;

/**
 * class that resembles a defensive ability
 * @author upvlx
 * @version 0.1
 */
public abstract class DefensiveAbility extends Ability {
    /**
     * constructor for a defensive ability
     * @param abilityLevel the level of the ability
     */
    public DefensiveAbility(int abilityLevel) {
        super(abilityLevel);
        this.offensive = false;
    }

    /**
     * function to calculate and set the mitigation
     * @param target target that is casted the mitigation
     */
    public abstract void calculateMitigationPlayer(Runa target);

    /**
     * function to calculate and set the mitigation for a monster
     * @param target the monster the mitigation should be set to
     */
    public abstract void calculateMitigationMonster(Monster target);

}
