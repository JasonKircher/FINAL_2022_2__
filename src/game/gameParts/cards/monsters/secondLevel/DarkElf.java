package game.gameParts.cards.monsters.secondLevel;

import game.gameParts.cards.abilities.magical.Focus;
import game.gameParts.cards.abilities.magical.Lightning;
import game.gameParts.cards.abilities.magical.Water;
import game.gameParts.cards.monsters.Monster;

import java.util.LinkedList;

/**
 * class for the monster "Bear"
 * @author upvlx
 * @version 0.1
 */
public class DarkElf extends Monster {
    private static final int HP = 34;

    /**
     * Constructor for a Monster this sets the initial values for a specific Monster
     */
    public DarkElf() {
        this.name = "Dark Elf";
        this.hp = HP;
        this.type = null;
        this.preferredAbilities = new LinkedList<>() {{
                add(new Focus(2));
                add(new Water(1));
                add(new Lightning(1));
            }};
    }
}
