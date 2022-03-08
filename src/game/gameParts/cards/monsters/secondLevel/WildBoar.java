package game.gameParts.cards.monsters.secondLevel;

import game.gameParts.cards.abilities.magical.Deflect;
import game.gameParts.cards.abilities.physical.monsterAbilities.Scratch;
import game.gameParts.cards.monsters.Monster;

import java.util.LinkedList;

public class WildBoar extends Monster {
    public WildBoar() {
        this.name = "Wild Boar";
        this.hp = 27;
        this.type = null;
        this.preferredAbilities = new LinkedList<>() {{
                add(new Scratch(2));
                add(new Deflect(2));
                add(new Scratch(2));
        }};
    }
}
