package game.gameParts.cards.monsters.secondLevel;

import game.gameParts.cards.abilities.physical.monsterAbilities.Bite;
import game.gameParts.cards.abilities.physical.monsterAbilities.Block;
import game.gameParts.cards.abilities.physical.monsterAbilities.Scratch;
import game.gameParts.cards.monsters.Monster;

import java.util.LinkedList;

/**
 * class for the monster "Bear"
 * @author upvlx
 * @version 0.1
 */
public class Tarantula extends Monster {
    private static final int HP = 33;

    /**
     * Constructor for a Monster this sets the initial values for a specific Monster
     */
    public Tarantula() {
        this.name = "Tarantula";
        this.hp = HP;
        this.type = null;
        this.preferredAbilities = new LinkedList<>() {{
                add(new Bite(2));
                add(new Block(2));
                add(new Scratch(2));
            }};
    }
}
