package game.gameParts.cards.physical.defensive;

import game.gameParts.player.Runa;

public class Parry extends PhysicalShield {
    public Parry() {
        this.name = "Parry";
    }
    @Override
    public int damageReduction(Runa runa) {
        return 7 * runa.getAbilityLevel();
    }
}
