package game.gameParts.cards.abilities.physical.monsterAbilities;

import game.gameParts.cards.abilities.OffensiveAbility;

public class Claw extends OffensiveAbility {
    public Claw(int abilityLevel) {
        super(abilityLevel);
        this.name = "Claw";
    }

    @Override
    public int calculatePlayerDamage(int value) {
        return 0;
    }

    @Override
    public int calculateMonsterDamage() {
        return this.abilityLevel * 6;
    }
}
