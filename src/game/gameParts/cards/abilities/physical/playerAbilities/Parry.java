package game.gameParts.cards.abilities.physical.playerAbilities;

import game.gameParts.cards.abilities.DefensiveAbility;
import game.gameParts.cards.abilities.physical.PhysicalDefensiveAbility;

public class Parry extends PhysicalDefensiveAbility {
    public Parry(int abilityLevel) {
        super(abilityLevel);
        this.name = "Parry";
    }

    @Override
    public int calculatePlayerMitigation() {
        return 7 * this.abilityLevel;
    }

    @Override
    public int calculateMonsterMitigation() {
        return 0;
    }
}
