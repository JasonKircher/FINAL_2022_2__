package game.gameParts.cards.monsters.firstLevel;

import game.gameParts.cards.abilities.physical.monsterAbilities.Bite;
import game.gameParts.cards.abilities.physical.monsterAbilities.Block;
import game.gameParts.cards.monsters.Monster;

import java.util.LinkedList;

public class Spider extends Monster {
    public Spider() {
        this.name = "Spider";
        this.hp = 15;
        this.type = null;
        this.preferredAbilities = new LinkedList<>() {{
            add(new Bite(1));
            add(new Block(1));
        }};
    }
}
