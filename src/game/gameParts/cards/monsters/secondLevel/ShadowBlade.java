package game.gameParts.cards.monsters.secondLevel;

import game.gameParts.cards.abilities.magical.Focus;
import game.gameParts.cards.abilities.magical.Lightning;
import game.gameParts.cards.abilities.physical.monsterAbilities.Scratch;
import game.gameParts.cards.monsters.Monster;
import game.gameParts.cards.monsters.MonsterType;

import java.util.LinkedList;

/**
 * class for the monster "Bear"
 * @author upvlx
 * @version 0.1
 */
public class ShadowBlade extends Monster {
    /**
     * Constructor for a Monster this sets the initial values for a specific Monster
     */
    public ShadowBlade() {
        this.name = "Shadow Blade";
        this.hp = 27;
        this.type = MonsterType.Lightning;
        this.preferredAbilities = new LinkedList<>() {{
                add(new Scratch(2));
                add(new Focus(2));
                add(new Lightning(2));
            }};
    }
}
