package game.gameParts.cards.abilities.physical.monsterAbilities;

import game.gameParts.cards.abilities.OffensiveAbility;
import game.gameParts.cards.monsters.Monster;
import game.gameParts.player.Runa;

public class Bite extends OffensiveAbility {

    public Bite(int abilityLevel) {
        super(abilityLevel);
        this.name = "Bite";
    }

    @Override
    public int calculateDamage(int value, Object target) {
        if (target instanceof Runa) {
            return 10 * this.abilityLevel;
        }
        return 0;
    }
}
