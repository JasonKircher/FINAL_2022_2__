package game.gameParts.cards.monsters.firstLevel;

import game.gameParts.cards.abilities.magical.Focus;
import game.gameParts.cards.abilities.magical.Ice;
import game.gameParts.cards.monsters.Monster;
import game.gameParts.cards.monsters.MonsterType;

import java.util.LinkedList;

/**
 * class for the monster "Ghost"
 * @author upvlx
 * @version 0.1
 */
public class Ghost extends Monster {
    /**
     * Constructor for a Monster this sets the initial values for a specific Monster
     */
    public Ghost() {
        this.name = "Ghost";
        this.hp = 15;
        this.type = MonsterType.Ice;
        this.preferredAbilities = new LinkedList<>() {{
                add(new Focus(1));
                add(new Ice(1));
            }};
    }
}
