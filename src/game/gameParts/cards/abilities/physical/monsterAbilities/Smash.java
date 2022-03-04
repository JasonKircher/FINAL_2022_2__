package game.gameParts.cards.abilities.physical.monsterAbilities;

import game.gameParts.cards.abilities.OffensiveAbility;
import game.gameParts.cards.monsters.Monster;

public class Smash extends OffensiveAbility {

    public Smash(int abilityLevel) {
        super(abilityLevel);
        this.name = "Smash";
    }

    @Override
    public int calculatePlayerDamage(int value, Monster target) {
        return 0;
    }

    @Override
    public int calculateMonsterDamage() {
        return 8 * this.abilityLevel;
    }
}
