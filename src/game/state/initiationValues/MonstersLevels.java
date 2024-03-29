package game.state.initiationValues;

import game.gameParts.cards.monsters.Monster;
import game.gameParts.cards.monsters.firstLevel.Frog;
import game.gameParts.cards.monsters.firstLevel.Ghost;
import game.gameParts.cards.monsters.firstLevel.Gorgon;
import game.gameParts.cards.monsters.firstLevel.Skeleton;
import game.gameParts.cards.monsters.firstLevel.Spider;
import game.gameParts.cards.monsters.firstLevel.Goblin;
import game.gameParts.cards.monsters.firstLevel.Rat;
import game.gameParts.cards.monsters.firstLevel.Mushroomlin;
import game.gameParts.cards.monsters.firstLevel.SpiderKing;
import game.gameParts.cards.monsters.secondLevel.DarkElf;
import game.gameParts.cards.monsters.secondLevel.ShadowBlade;
import game.gameParts.cards.monsters.secondLevel.Snake;
import game.gameParts.cards.monsters.secondLevel.Hornet;
import game.gameParts.cards.monsters.secondLevel.Tarantula;
import game.gameParts.cards.monsters.secondLevel.Bear;
import game.gameParts.cards.monsters.secondLevel.Mushroomlon;
import game.gameParts.cards.monsters.secondLevel.WildBoar;
import game.gameParts.cards.monsters.secondLevel.MegaSaurus;

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

    /**
     * constructor for monsters per level
     * @param monsters a list of the Monsters that should be available in the level
     * @param boss the boss for the corresponding level
     */
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
