package game.gameParts.cards.monsters.firstLevel;

import game.gameParts.cards.abilities.physical.monsterAbilities.Bite;
import game.gameParts.cards.abilities.physical.monsterAbilities.Block;
import game.gameParts.cards.monsters.Monster;

import java.util.LinkedList;

/**
 * class for the monster "Spider"
 * @author upvlx
 * @version 0.1
 */
public class Spider extends Monster {
    /**
     * Constructor for a Monster this sets the initial values for a specific Monster
     */
    public Spider() {
        this.name = "Spider";
        this.hp = 15;
        this.type = null;
        this.preferredAbilities = new LinkedList<>() {{
                add(new Bite(1));
                add(new Block(1));
            }};
    }
}
