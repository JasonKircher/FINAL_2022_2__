package game.gameParts.cards.monsters.firstLevel;

import game.gameParts.cards.abilities.magical.Fire;
import game.gameParts.cards.abilities.magical.Focus;
import game.gameParts.cards.monsters.Monster;
import game.gameParts.cards.monsters.MonsterType;

import java.util.LinkedList;

/**
 * class for the monster "Gorgon"
 * @author upvlx
 * @version 0.1
 */
public class Gorgon extends Monster {
    private static final int HP = 13;

    /**
     * Constructor for a Monster this sets the initial values for a specific Monster
     */
    public Gorgon() {
        this.name = "Gorgon";
        this.hp = HP;
        this.type = MonsterType.Fire;
        this.preferredAbilities = new LinkedList<>() {{
                add(new Focus(1));
                add(new Fire(1));
            }};
    }
}
