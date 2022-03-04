package game.gameParts.cards.monsters.firstLevel;

import game.gameParts.cards.abilities.magical.Focus;
import game.gameParts.cards.abilities.physical.monsterAbilities.Bite;
import game.gameParts.cards.abilities.physical.monsterAbilities.Block;
import game.gameParts.cards.monsters.Monster;

import java.util.LinkedList;

public class SpiderKing extends Monster {
    public SpiderKing() {
        this.hp = 50;
        this.boss = true;
        this.type = MonsterType.Lightning;
        this.preferredAbilities = new LinkedList<>() {{
            add(new Bite(1));
            add(new Block(1));
            add(new Focus(1));
            // TODO Lightning
        }};

    }
}
