package game.gameParts.cards.abilities.physical.monsterAbilities;

import game.gameParts.cards.abilities.OffensiveAbility;

public class Smash extends OffensiveAbility {

    public Smash(int abilityLevel) {
        super(abilityLevel);
        this.name = "Smash";
    }

    @Override
    public int calculatePlayerDamage(int value) {
        return 0;
    }

    @Override
    public int calculateMonsterDamage() {
        return 8 * this.abilityLevel;
    }
}
