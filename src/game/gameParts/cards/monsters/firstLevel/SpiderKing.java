package game.gameParts.cards.monsters.firstLevel;

import game.gameParts.cards.abilities.magical.Focus;
import game.gameParts.cards.abilities.magical.Lightning;
import game.gameParts.cards.abilities.physical.monsterAbilities.Bite;
import game.gameParts.cards.abilities.physical.monsterAbilities.Block;
import game.gameParts.cards.monsters.Monster;
import game.gameParts.cards.monsters.MonsterType;

import java.util.LinkedList;

public class SpiderKing extends Monster {
    public SpiderKing() {
        this.name = "Spider King";
        this.hp = 50;
        this.type = MonsterType.Lightning;
        this.preferredAbilities = new LinkedList<>() {{
            add(new Bite(1));
            add(new Block(1));
            add(new Focus(1));
            add(new Lightning(1));
        }};

    }
}
