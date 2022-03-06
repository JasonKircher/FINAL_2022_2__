package game.gameParts.cards.monsters.firstLevel;

import game.gameParts.cards.abilities.magical.Focus;
import game.gameParts.cards.abilities.magical.Ice;
import game.gameParts.cards.monsters.Monster;
import game.gameParts.cards.monsters.MonsterType;

import java.util.LinkedList;

public class Ghost extends Monster {
    public Ghost() {
        this.name = "Ghost";
        this.hp = 15;
        this.type = MonsterType.Ice;
        this.preferredAbilities = new LinkedList<>() {{
           add(new Focus(1));
           add(new Ice(1));
        }};
    }
}
