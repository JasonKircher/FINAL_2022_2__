package game.gameParts.cards.abilities;

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
     * function to calculate the damage
     * @param value the value with which the ability is customized
     * @param target the target on which the dmg is to be inflicted
     * @return the damage the target receives
     */
    public abstract int calculateDamage(int value, Object target);
}
