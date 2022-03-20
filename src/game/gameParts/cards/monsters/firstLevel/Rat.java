package game.gameParts.cards.monsters.firstLevel;

import game.gameParts.cards.abilities.physical.monsterAbilities.Block;
import game.gameParts.cards.abilities.physical.monsterAbilities.Claw;
import game.gameParts.cards.monsters.Monster;

import java.util.LinkedList;

/**
 * class for the monster "Rat"
 * @author upvlx
 * @version 0.1
 */
public class Rat extends Monster {
    private static final int HP = 14;

    /**
     * Constructor for a Monster this sets the initial values for a specific Monster
     */
    public Rat() {
        this.name = "Rat";
        this.hp = HP;
        this.type = null;
        this.preferredAbilities = new LinkedList<>() {{
                add(new Block(1));
                add(new Claw(1));
            }};
    }
}
