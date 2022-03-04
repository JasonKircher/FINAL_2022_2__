package game.gameParts.cards.abilities.physical.monsterAbilities;

import game.gameParts.cards.abilities.OffensiveAbility;
import game.gameParts.cards.monsters.Monster;
import game.gameParts.player.Runa;

public class Smash extends OffensiveAbility {

    public Smash(int abilityLevel) {
        super(abilityLevel);
        this.name = "Smash";
    }

    @Override
    public int calculateDamage(int value, Object target) {
        if (target instanceof Runa) {
            return 8 * this.abilityLevel;
        }
        return 0;
    }

}
