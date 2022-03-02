package game.gameParts.cards.physical.offensive;

import game.gameParts.player.Runa;

public class SlashAttack extends PhysicalAttack {
    public SlashAttack() {
        this.name = "Slash";
    }

    @Override
    public int calculatePlayerDamage(Runa runa, int diceRoll) {
        return 4 * runa.getAbilityLevel() + diceRoll;
    }
}
