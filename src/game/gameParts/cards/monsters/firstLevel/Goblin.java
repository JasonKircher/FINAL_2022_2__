package game.gameParts.cards.monsters.firstLevel;

import game.gameParts.cards.abilities.magical.Deflect;
import game.gameParts.cards.abilities.physical.monsterAbilities.Smash;
import game.gameParts.cards.monsters.Monster;

import java.util.LinkedList;

/**
 * class for the monster "Goblin"
 * @author upvlx
 * @version 0.1
 */
public class Goblin extends Monster {
    private static final int HP = 12;

    /**
     * Constructor for a Monster this sets the initial values for a specific Monster
     */
    public Goblin() {
        this.name = "Goblin";
        this.hp = HP;
        this.type = null;
        this.preferredAbilities = new LinkedList<>() {{
                add(new Smash(1));
                add(new Deflect(1));
            }};
    }
}
