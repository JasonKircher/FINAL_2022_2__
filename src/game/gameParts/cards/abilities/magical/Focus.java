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
        runa.focus(this.abilityLevel);
    }
    public void focus(Monster monster) {
        monster.focus(this.abilityLevel);
    }
}
