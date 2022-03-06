package game.gameParts.cards.monsters.secondLevel;

import game.gameParts.cards.abilities.magical.Focus;
import game.gameParts.cards.abilities.magical.Lightning;
import game.gameParts.cards.abilities.magical.Water;
import game.gameParts.cards.monsters.Monster;

import java.util.LinkedList;

public class DarkElf extends Monster {
    public DarkElf() {
        this.name = "Dark Elf";
        this.hp = 34;
        this.type = null;
        this.preferredAbilities = new LinkedList<>() {{
                add(new Focus(2));
                add(new Water(1));
                add(new Lightning(1));
        }};
    }
}
