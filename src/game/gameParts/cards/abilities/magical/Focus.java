package game.gameParts.cards.abilities.magical;

import game.gameParts.cards.abilities.Ability;
import game.gameParts.cards.monsters.Monster;
import game.gameParts.player.Runa;

/**
 * class for the ability focus
 * @author upvlx
 * @version 0.1
 */
public class Focus extends Ability {
    /**
     * constructor
     * @param abilityLevel the ability level the ability is supposed to have
     */
    public Focus(int abilityLevel) {
        super(abilityLevel);
        this.name = "Focus";
    }

    /**
     * function for a player to focus
     * @param runa the payer that should focus
     */
    public void focus(Runa runa) {
        runa.focus(this.abilityLevel);
    }
    /**
     * function for a monster to focus
     * @param monster the monster that should focus
     */
    public void focus(Monster monster) {
        monster.focus(this.abilityLevel);
    }
}
