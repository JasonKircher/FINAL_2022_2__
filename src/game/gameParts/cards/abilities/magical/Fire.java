package game.gameParts.cards.abilities.magical;

import game.gameParts.cards.monsters.Monster;
import game.gameParts.player.Runa;

public class Fire extends OffensiveMagicAbility {
    public Fire(int abilityLevel) {
        super(abilityLevel);
        this.name = "Fire";
    }

    @Override
    public int calculateDamage(int value, Object target) {
        if (target instanceof Runa runa) {

        } else if (target instanceof Monster monster) {

        }
        return 0;
    }

}
