package game.gameParts.cards.abilities.magical;

public class Lightning extends OffensiveMagicAbility {
    public Lightning(int abilityLevel) {
        super(abilityLevel);
        this.name = "Lightning";
    }

    @Override
    public int calculateDamage(int value, Object target) {
        return 0;
    }
}
