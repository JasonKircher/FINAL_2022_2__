package game.gameParts.cards.monsters.firstLevel;

import game.gameParts.cards.abilities.magical.Focus;
import game.gameParts.cards.monsters.Monster;
import game.gameParts.cards.monsters.MonsterType;

import java.util.LinkedList;

public class Skeleton extends Monster {
    public Skeleton() {
        this.name = "Skeleton";
        this.hp = 14;
        this.type = MonsterType.Lightning;
        this.preferredAbilities = new LinkedList<>() {{
                add(new Focus(1));
                // Lightning
        }};
    }
}
