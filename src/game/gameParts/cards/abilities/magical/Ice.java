package game.gameParts.cards.abilities.magical;

import game.gameParts.cards.monsters.Monster;

public class Ice extends OffensiveMagicAbility {
    public Ice(int abilityLevel) {
        super(abilityLevel);
        this.name = "Ice";
    }

    @Override
    public int calculateDamage(int value, Object target) {
        return 0;
    }
}
