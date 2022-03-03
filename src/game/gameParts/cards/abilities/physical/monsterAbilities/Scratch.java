package game.gameParts.cards.abilities.physical.monsterAbilities;

import game.gameParts.cards.abilities.OffensiveAbility;

public class Scratch extends OffensiveAbility {
    public Scratch(int abilityLevel) {
        super(abilityLevel);
        this.name = "Scratch";
    }

    @Override
    public int calculatePlayerDamage(int value) {
        return 0;
    }

    @Override
    public int calculateMonsterDamage() {
        return 5 * this.abilityLevel;
    }
}
