package game.gameParts.cards.monsters.firstLevel;

import game.gameParts.cards.abilities.physical.monsterAbilities.Block;
import game.gameParts.cards.abilities.physical.monsterAbilities.Claw;
import game.gameParts.cards.monsters.Monster;

import java.util.LinkedList;

public class Rat extends Monster {
    public Rat() {
        this.name = "Rat";
        this.hp = 14;
        this.preferredAbilities = new LinkedList<>() {{
           add(new Block(1));
           add(new Claw(1));
        }};
    }
}
