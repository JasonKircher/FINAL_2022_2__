package game.gameParts.cards.monsters.firstLevel;

import game.gameParts.cards.abilities.magical.Deflect;
import game.gameParts.cards.abilities.physical.monsterAbilities.Scratch;
import game.gameParts.cards.monsters.Monster;

import java.util.LinkedList;

public class Mushroomlin extends Monster {
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
