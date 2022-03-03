package game.gameParts.cards.abilities;

public abstract class DefensiveAbility extends Ability {
    public DefensiveAbility(int abilityLevel) {
        super(abilityLevel);
        this.offensive = false;
    }

    public abstract int calculatePlayerMitigation();
    public abstract int calculateMonsterMitigation();
}
