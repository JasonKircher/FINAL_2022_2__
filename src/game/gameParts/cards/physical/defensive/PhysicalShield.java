package game.gameParts.cards.physical.defensive;

import game.gameParts.player.Runa;

public abstract class PhysicalShield {
    protected String name;
    public abstract int damageReduction(Runa runa);
}
