package game.gameParts.cards.abilities;

import game.gameParts.cards.monsters.MonsterCard;
import game.gameParts.player.Runa;

public class Focus extends AbilityOffensive {
    public Focus() {
        this.name = "Focus";
    }
    @Override
    public int calculatePlayerDamage(Runa runa) {
        runa.setFocusPoints(runa.getFocusPoints() + runa.getAbilityLevel());
        return 0;
    }

    @Override
    public int calculateMonsterDamage(MonsterCard monster) {
        return 0;
    }
}
