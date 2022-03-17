package game.state.initiationValues;

import game.gameParts.cards.monsters.Monster;
import game.gameParts.cards.monsters.firstLevel.*;
import game.gameParts.cards.monsters.secondLevel.*;

import java.util.List;

/**
 * class that holds the correct monster constellation for each level
 * @author upvlx
 * @version 0.1
 */
public enum MonstersLevels {
    /**
     * monsters for the first level
     */
    FIRST(List.of(new Frog(), new Ghost(), new Gorgon(), new Skeleton(), new Spider(), new Goblin(),
            new Rat(), new Mushroomlin()), new SpiderKing()),
    /**
     * monsters for the second level
     */
    SECOND(List.of(new Snake(), new DarkElf(), new ShadowBlade(), new Hornet(), new Tarantula(), new Bear(),
            new Mushroomlon(), new WildBoar()), new MegaSaurus());

    private final List<Monster> monsters;
    private final Monster boss;
    MonstersLevels(List<Monster> monsters, Monster boss) {
        this.monsters = monsters;
        this.boss = boss;
    }

    /**
     * function to access all monsters
     * @return a list of all monsters that are meant to be on the first level
     */
    public List<Monster> getMonsters() {
        return this.monsters;
    }

    /**
     * function to access the boss of the level
     * @return the monster that ist meant to be the boss for the corresponding level
     */
    public Monster getBoss() {
        return this.boss;
    }
}
