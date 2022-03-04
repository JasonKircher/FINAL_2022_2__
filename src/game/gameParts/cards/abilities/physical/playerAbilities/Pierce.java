package game.gameParts.cards.abilities.physical.playerAbilities;

import game.gameParts.cards.abilities.OffensiveAbility;
import game.gameParts.cards.abilities.physical.PhysicalOffensiveAbility;
import game.gameParts.cards.monsters.Monster;

public class Pierce extends PhysicalOffensiveAbility {
    public Pierce(int abilityLevel) {
        super(abilityLevel);
        this.name = "Pierce";
    }

    @Override
    public int calculatePlayerDamage(int value, Monster target) {
        return 0;
    }


    public int calculatePlayerDamage(int value) {
        return value >= 6 ? 12 * this.abilityLevel + value : 7 * this.abilityLevel + value;
    }

    @Override
    public int calculateMonsterDamage() {
        return 0;
    }
}
