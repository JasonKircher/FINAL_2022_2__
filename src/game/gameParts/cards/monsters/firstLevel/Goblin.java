package game.gameParts.cards.monsters.firstLevel;

import game.gameParts.cards.abilities.physical.monsterAbilities.Smash;
import game.gameParts.cards.monsters.Monster;

import java.util.LinkedList;

public class Goblin extends Monster {
    public Goblin() {
        this.name = "Goblin";
        this.hp = 12;
        this.type = null;
        this.preferredAbilities = new LinkedList<>() {{
           add(new Smash(1));
           // TODO Deflect
        }};
    }
}
