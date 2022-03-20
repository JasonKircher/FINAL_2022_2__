package game.gameParts.cards.monsters.secondLevel;

import game.gameParts.cards.abilities.magical.Fire;
import game.gameParts.cards.abilities.magical.Focus;
import game.gameParts.cards.abilities.magical.Lightning;
import game.gameParts.cards.abilities.physical.monsterAbilities.Bite;
import game.gameParts.cards.abilities.physical.monsterAbilities.Block;
import game.gameParts.cards.monsters.Monster;

import java.util.LinkedList;

/**
 * class for the boss "Mega Saurus"
 * @author upvlx
 * @version 0.1
 */
public class MegaSaurus extends Monster {
    private static final int HP = 100;

    /**
     * Constructor for a Monster this sets the initial values for a specific Monster
     */
    public MegaSaurus() {
        this.name = "Mega Saurus";
        this.hp = HP;
        this.type = null;
        this.preferredAbilities = new LinkedList<>() {{
                add(new Bite(2));
                add(new Block(2));
                add(new Focus(2));
                add(new Fire(1));
                add(new Lightning(1));
            }};
    }
}
