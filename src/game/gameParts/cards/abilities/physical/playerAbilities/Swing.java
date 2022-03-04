package game.gameParts.cards.abilities.physical.playerAbilities;

import game.gameParts.cards.abilities.OffensiveAbility;
import game.gameParts.cards.abilities.physical.PhysicalOffensiveAbility;
import game.gameParts.cards.monsters.Monster;

public class Swing extends PhysicalOffensiveAbility {
    public Swing(int abilityLevel) {
        super(abilityLevel);
        this.name = "Swing";
    }

    @Override
    public int calculateDamage(int value, Object target) {
        return 0;
    }

}
