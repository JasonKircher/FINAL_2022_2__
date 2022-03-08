package game.gameParts.cards.monsters.secondLevel;

import game.gameParts.cards.abilities.magical.Deflect;
import game.gameParts.cards.abilities.physical.monsterAbilities.Block;
import game.gameParts.cards.abilities.physical.monsterAbilities.Claw;
import game.gameParts.cards.monsters.Monster;

import java.util.LinkedList;

public class Bear extends Monster {
    public Bear() {
        this.name = "Bear";
        this.hp = 40;
        this.type = null;
        this.preferredAbilities = new LinkedList<>() {{
                add(new Claw(2));
                add(new Deflect(2));
                add(new Block(2));
        }};
    }
}
