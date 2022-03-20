package game.gameParts.cards.monsters.secondLevel;

import game.gameParts.cards.abilities.magical.Deflect;
import game.gameParts.cards.abilities.physical.monsterAbilities.Block;
import game.gameParts.cards.abilities.physical.monsterAbilities.Claw;
import game.gameParts.cards.monsters.Monster;

import java.util.LinkedList;

/**
 * class for the monster "Bear"
 * @author upvlx
 * @version 0.1
 */
public class Bear extends Monster {
    private static final int HP = 40;

    /**
     * Constructor for a Monster this sets the initial values for a specific Monster
     */
    public Bear() {
        this.name = "Bear";
        this.hp = HP;
        this.type = null;
        this.preferredAbilities = new LinkedList<>() {{
                add(new Claw(2));
                add(new Deflect(2));
                add(new Block(2));
            }};
    }
}
