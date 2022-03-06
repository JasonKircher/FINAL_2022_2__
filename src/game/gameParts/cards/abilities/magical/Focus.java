package game.gameParts.cards.abilities.magical;

import game.gameParts.cards.abilities.Ability;
import game.gameParts.cards.monsters.Monster;
import game.gameParts.player.Runa;

public class Focus extends Ability {
    public Focus(int abilityLevel) {
        super(abilityLevel);
        this.name = "Focus";
    }
    public void focus(Runa runa) {
        if (!runa.isDeBuffed()) {
            runa.increaseFocusPoints();
        }
    }
    public void focus(Monster monster) {
        if (!monster.isDeBuffed()) {
            monster.increaseFocusPoints();
        }
    }
}
