package game.gameParts.cards.abilities;

public abstract class OffensiveAbility extends Ability {
    public OffensiveAbility(int abilityLevel) {
        super(abilityLevel);
        this.offensive = true;
    }
    public abstract int calculateDamage(int value, Object target);
}
