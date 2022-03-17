package game.gameParts.cards.monsters.firstLevel;

import game.gameParts.cards.abilities.magical.Focus;
import game.gameParts.cards.abilities.magical.Water;
import game.gameParts.cards.monsters.Monster;
import game.gameParts.cards.monsters.MonsterType;

import java.util.LinkedList;

/**
 * class for the monster "Frog"
 * @author upvlx
 * @version 0.1
 */
public class Frog extends Monster {
    /**
     * Constructor for a Monster this sets the initial values for a specific Monster
     */
    public Frog() {
        this.name = "Frog";
        this.hp = 16;
        this.type = MonsterType.Water;
        this.preferredAbilities = new LinkedList<>() {{
                add(new Focus(1));
                add(new Water(1));
            }};
    }
}
