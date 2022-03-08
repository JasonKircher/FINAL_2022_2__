package game.gameParts.cards.abilities.magical;

import game.gameParts.cards.monsters.Monster;

public class Deflect extends DefensiveMagicAbility {
    public Deflect(int abilityLevel) {
        super(abilityLevel);
        this.name = "Deflect";
    }

    @Override
    public void calculateMitigation(Object target) {
        if (target instanceof Monster monster) {
            monster.setMagicMitigation(11 * this.abilityLevel + 2);
        }
    }
}
