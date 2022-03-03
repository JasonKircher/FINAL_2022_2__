package game.gameParts.cards.abilities.physical.monsterAbilities;

import game.gameParts.cards.abilities.OffensiveAbility;

public class Bite extends OffensiveAbility {

    public Bite(int abilityLevel) {
        super(abilityLevel);
        this.name = "Bite";
    }

    @Override
    public int calculatePlayerDamage(int value) {
        return 0;
    }

    @Override
    public int calculateMonsterDamage() {
        return 10 * this.abilityLevel;
    }
}
