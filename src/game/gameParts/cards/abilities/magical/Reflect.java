package game.gameParts.cards.abilities.magical;

import game.gameParts.player.Runa;

public class Reflect extends DefensiveMagicAbility {
    public Reflect(int abilityLevel) {
        super(abilityLevel);
        this.name = "Reflect";
    }

    @Override
    public void calculateMitigation(Object target) {
        if (target instanceof Runa runa) {
            runa.setMagicMitigation(10 * this.abilityLevel);
        }
    }
}
