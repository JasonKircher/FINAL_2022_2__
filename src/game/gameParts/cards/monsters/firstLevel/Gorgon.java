package game.gameParts.cards.monsters.firstLevel;

import game.gameParts.cards.abilities.magical.Fire;
import game.gameParts.cards.abilities.magical.Focus;
import game.gameParts.cards.monsters.Monster;
import game.gameParts.cards.monsters.MonsterType;

import java.util.LinkedList;

public class Gorgon extends Monster {
    public Gorgon() {
        this.name = "Gorgon";
        this.hp = 13;
        this.type = MonsterType.Fire;
        this.preferredAbilities = new LinkedList<>() {{
           add(new Focus(1));
           add(new Fire(1));
        }};
    }
}
