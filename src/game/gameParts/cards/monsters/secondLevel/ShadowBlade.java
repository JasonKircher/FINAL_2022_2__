package game.gameParts.cards.monsters.secondLevel;

import game.gameParts.cards.abilities.magical.Focus;
import game.gameParts.cards.abilities.magical.Lightning;
import game.gameParts.cards.abilities.physical.monsterAbilities.Scratch;
import game.gameParts.cards.monsters.Monster;
import game.gameParts.cards.monsters.MonsterType;

import java.util.LinkedList;

public class ShadowBlade extends Monster {
    public ShadowBlade() {
        this.name = "Shadow Blade";
        this.hp = 27;
        this.type = MonsterType.Lightning;
        this.preferredAbilities = new LinkedList<>() {{
                add(new Scratch(2));
                add(new Focus(2));
                add(new Lightning(2));
        }};
    }
}
