package game.gameParts.cards.physical.offensive;

import game.gameParts.player.Runa;

public class SwingAttack extends PhysicalAttack {
    public SwingAttack() {
        this.name = "Swing";
    }
    @Override
    public int calculatePlayerDamage(Runa runa, int diceRoll) {
        return 5 * runa.getAbilityLevel() + diceRoll;
    }
}
