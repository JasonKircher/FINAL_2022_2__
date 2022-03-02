package game.gameParts.cards.physical.offensive;

import game.gameParts.player.Runa;

public class ThrustAttack extends PhysicalAttack {
    public ThrustAttack() {
        this.name = "Thrust";
    }
    @Override
    public int calculatePlayerDamage(Runa runa, int diceRoll) {
        int normalDamage = 6 * runa.getAbilityLevel() + diceRoll;
        return diceRoll >= 6 ? normalDamage + 4 * runa.getAbilityLevel() : normalDamage;
    }
}
