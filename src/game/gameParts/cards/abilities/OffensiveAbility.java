package game.gameParts.cards.abilities;

import game.gameParts.cards.monsters.Monster;
import game.gameParts.player.Runa;

/**
 * class that resembles an offensive ability
 * @author upvlx
 * @version 0.1
 */
public abstract class OffensiveAbility extends Ability {
    /**
     * constructor for an offensive ability
     * @param abilityLevel level of the ability
     */
    public OffensiveAbility(int abilityLevel) {
        super(abilityLevel);
        this.offensive = true;
    }

    /**
     * function to calculate the damage when a player is using the ability
     * @param value the value with which the ability is customized
     * @param target the target monster on which the dmg is to be inflicted
     * @return the damage the target receives
     */
    public abstract int calculateDamagePlayer(int value, Monster target);

    /**
     * function to calculate the damage when a player is using the ability
     * @param target the Player on which the dmg is to be inflicted
     * @return the damage the target receives
     */
    public abstract int calculateDamageMonster(Runa target);
}
