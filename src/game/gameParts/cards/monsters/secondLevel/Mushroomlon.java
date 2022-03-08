package game.gameParts.cards.monsters.secondLevel;

import game.gameParts.cards.abilities.magical.Deflect;
import game.gameParts.cards.abilities.physical.monsterAbilities.Block;
import game.gameParts.cards.abilities.physical.monsterAbilities.Scratch;
import game.gameParts.cards.monsters.Monster;

import java.util.LinkedList;

public class Mushroomlon extends Monster {
    public Mushroomlon() {
        this.name = "Mushroomlon";
        this.hp = 50;
        this.type = null;
        this.preferredAbilities = new LinkedList<>() {{
                add(new Deflect(2));
                add(new Scratch(2));
                add(new Block(2));
        }};
    }
}
