package game.gameParts.cards.monsters.secondLevel;

import game.gameParts.cards.abilities.magical.Fire;
import game.gameParts.cards.abilities.magical.Focus;
import game.gameParts.cards.abilities.physical.monsterAbilities.Scratch;
import game.gameParts.cards.monsters.Monster;
import game.gameParts.cards.monsters.MonsterType;

import java.util.LinkedList;

public class Hornet extends Monster {
    public Hornet() {
        this.name = "Hornet";
        this.hp = 32;
        this.type = MonsterType.Fire;
        this.preferredAbilities = new LinkedList<>() {{
                add(new Scratch(2));
                add(new Focus(2));
                add(new Fire(1));
                add(new Fire(2));
        }};
    }
}
