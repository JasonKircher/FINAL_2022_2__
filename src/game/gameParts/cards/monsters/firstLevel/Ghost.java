package game.gameParts.cards.monsters.firstLevel;

import game.gameParts.cards.abilities.magical.Focus;
import game.gameParts.cards.abilities.magical.Ice;
import game.gameParts.cards.monsters.Monster;

import java.util.LinkedList;

public class Ghost extends Monster {
    public Ghost() {
        this.hp = 15;
        this.boss = false;
        this.type = MonsterType.Ice;
        this.preferredAbilities = new LinkedList<>() {{
           add(new Focus(1));
           add(new Ice(1));
        }};
    }
}
