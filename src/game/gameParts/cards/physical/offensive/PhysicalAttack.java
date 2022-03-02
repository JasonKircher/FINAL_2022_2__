package game.gameParts.cards.physical.offensive;

import game.gameParts.cards.Card;
import game.gameParts.player.Runa;

public abstract class PhysicalAttack implements Card {
    protected String name;
    public PhysicalAttack() {}

    public abstract int calculatePlayerDamage(Runa runa, int diceRoll);
}
