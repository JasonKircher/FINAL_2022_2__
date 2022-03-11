package game.state.initiationValues;

import game.gameParts.cards.monsters.Monster;
import game.gameParts.cards.monsters.firstLevel.*;
import game.gameParts.cards.monsters.secondLevel.*;

import java.util.List;

public enum MonstersLevels {
    FIRST(List.of(new Frog(), new Ghost(), new Gorgon(), new Skeleton(), new Spider(), new Goblin(),
            new Rat(), new Mushroomlin()), new SpiderKing()),
    SECOND(List.of(new Snake(), new DarkElf(), new ShadowBlade(), new Hornet(), new Tarantula(), new Bear(),
            new Mushroomlon(), new WildBoar()), new MegaSaurus());

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
