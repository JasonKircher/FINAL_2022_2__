package game.gameParts.cards.abilities.physical.monsterAbilities;

import game.gameParts.cards.abilities.OffensiveAbility;
import game.gameParts.cards.monsters.Monster;

public class Bite extends OffensiveAbility {

    public Bite(int abilityLevel) {
        super(abilityLevel);
        this.name = "Bite";
    }

    @Override
    public int calculatePlayerDamage(int value, Monster target) {
        return 0;
    }

    @Override
    public int calculateMonsterDamage() {
        return 10 * this.abilityLevel;
    }
}
