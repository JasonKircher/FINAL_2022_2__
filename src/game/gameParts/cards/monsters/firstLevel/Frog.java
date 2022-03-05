package game.gameParts.cards.monsters.firstLevel;

import game.gameParts.cards.abilities.magical.Focus;
import game.gameParts.cards.abilities.magical.Water;
import game.gameParts.cards.monsters.Monster;

import java.util.LinkedList;

public class Frog extends Monster {
    public Frog() {
        this.name = "Frog";
        this.hp = 16;
        this.type = MonsterType.Water;
        this.preferredAbilities = new LinkedList<>() {{
           add(new Focus(1));
           add(new Water(1));
        }};
    }
}
