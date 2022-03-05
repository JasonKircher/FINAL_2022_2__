package game.state.initiationValues;

import game.gameParts.cards.monsters.Monster;
import game.gameParts.cards.monsters.firstLevel.*;

import java.util.List;

public enum MonstersLevels {
    FIRST(List.of(new Frog(), new Ghost(), new Gorgon(), // skeleton & mushroomlin
            new Spider(), new Goblin(), new Rat()), new SpiderKing()),
    SECOND(List.of(), null);

    private final List<Monster> monsters;
    private final Monster boss;
    MonstersLevels(List<Monster> monsters, Monster boss) {
        this.monsters = monsters;
        this.boss = boss;
    }

    public List<Monster> getMonsters() {
         return this.monsters;
    }
    public Monster getBoss() {
        return this.boss;
    }
}
