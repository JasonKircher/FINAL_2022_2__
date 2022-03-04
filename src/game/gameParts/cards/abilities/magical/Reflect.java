package game.gameParts.cards.abilities.magical;

import game.gameParts.cards.monsters.Monster;
import game.gameParts.player.Runa;

public class Reflect extends DefensiveMagicAbility {
    public Reflect(int abilityLevel) {
        super(abilityLevel);
        this.name = "Reflect";
    }

    @Override
    public void calculatePlayerMitigation(Runa runa) {
        runa.setMagicMitigation(10 * this.abilityLevel);
    }

    @Override
    public void calculateMonsterMitigation(Monster monster) {
        return;
    }
}
