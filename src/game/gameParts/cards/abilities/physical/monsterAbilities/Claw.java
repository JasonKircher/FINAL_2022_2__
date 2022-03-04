package game.gameParts.cards.abilities.physical.monsterAbilities;

import game.gameParts.cards.abilities.OffensiveAbility;
import game.gameParts.cards.monsters.Monster;

public class Claw extends OffensiveAbility {
    public Claw(int abilityLevel) {
        super(abilityLevel);
        this.name = "Claw";
    }

    @Override
    public int calculatePlayerDamage(int value, Monster target) {
        return 0;
    }

    @Override
    public int calculateMonsterDamage() {
        return this.abilityLevel * 6;
    }
}
