package game.gameParts.cards.abilities.magical;

import game.gameParts.cards.monsters.Monster;
import game.gameParts.player.Runa;

public class Water extends OffensiveMagicAbility{
    public Water(int abilityLevel) {
        super(abilityLevel);
        this.name = "Water";
    }

    @Override
    public int calculateDamage(int value, Object target) {
        if (target instanceof Monster) {
            return (2 * this.abilityLevel + 4) * value;
        } else if (target instanceof Runa) {
            return 8 * this.abilityLevel + 2;
        }
        return 0;
    }

}
