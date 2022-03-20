package game.gameParts.cards.monsters.secondLevel;

import game.gameParts.cards.abilities.magical.Deflect;
import game.gameParts.cards.abilities.physical.monsterAbilities.Scratch;
import game.gameParts.cards.monsters.Monster;

import java.util.LinkedList;

/**
 * class for the monster "Bear"
 * @author upvlx
 * @version 0.1
 */
public class WildBoar extends Monster {
    private static final int HP = 27;

    /**
     * Constructor for a Monster this sets the initial values for a specific Monster
     */
    public WildBoar() {
        this.name = "Wild Boar";
        this.hp = HP;
        this.type = null;
        this.preferredAbilities = new LinkedList<>() {{
                add(new Scratch(2));
                add(new Deflect(2));
                add(new Scratch(2));
            }};
    }
}
