package game.gameParts.cards.monsters.secondLevel;

import game.gameParts.cards.abilities.magical.Focus;
import game.gameParts.cards.abilities.magical.Ice;
import game.gameParts.cards.abilities.physical.monsterAbilities.Bite;
import game.gameParts.cards.monsters.Monster;
import game.gameParts.cards.monsters.MonsterType;

import java.util.LinkedList;

public class Snake extends Monster {
    public Snake() {
        this.name = "Snake";
        this.hp = 31;
        this.type = MonsterType.Ice;
        this.preferredAbilities = new LinkedList<>() {{
                add(new Bite(2));
                add(new Focus(2));
                add(new Ice(2));
        }};
    }
}
