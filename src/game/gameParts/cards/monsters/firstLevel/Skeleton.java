package game.gameParts.cards.monsters.firstLevel;

import game.gameParts.cards.abilities.magical.Focus;
import game.gameParts.cards.abilities.magical.Lightning;
import game.gameParts.cards.monsters.Monster;
import game.gameParts.cards.monsters.MonsterType;

import java.util.LinkedList;

/**
 * class for the monster "Skeleton"
 * @author upvlx
 * @version 0.1
 */
public class Skeleton extends Monster {
    private static final int HP = 14;

    /**
     * Constructor for a Monster this sets the initial values for a specific Monster
     */
    public Skeleton() {
        this.name = "Skeleton";
        this.hp = HP;
        this.type = MonsterType.Lightning;
        this.preferredAbilities = new LinkedList<>() {{
                add(new Focus(1));
                add(new Lightning(1));
            }};
    }
}
