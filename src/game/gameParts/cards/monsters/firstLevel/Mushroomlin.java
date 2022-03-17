package game.gameParts.cards.monsters.firstLevel;

import game.gameParts.cards.abilities.magical.Deflect;
import game.gameParts.cards.abilities.physical.monsterAbilities.Scratch;
import game.gameParts.cards.monsters.Monster;

import java.util.LinkedList;

/**
 * class for the monster "Mushroomlin"
 * @author upvlx
 * @version 0.1
 */
public class Mushroomlin extends Monster {
    /**
     * Constructor for a Monster this sets the initial values for a specific Monster
     */
    public Mushroomlin() {
        this.name = "Mushroomlin";
        this.hp = 20;
        this.type = null;
        this.preferredAbilities = new LinkedList<>() {{
                add(new Deflect(1));
                add(new Scratch(1));
            }};
    }
}
