package game.gameParts.cards.physical.offensive;

import game.gameParts.player.Runa;

public class PierceAttack extends PhysicalAttack {
    public PierceAttack() {
        this.name = "Pierce";
    }
    @Override
    public int calculatePlayerDamage(Runa runa, int diceRoll) {
        int normalDamage = 7 * runa.getAbilityLevel() + diceRoll;
        return diceRoll >= 6 ? normalDamage + 5 * runa.getAbilityLevel() : normalDamage;
    }
}
