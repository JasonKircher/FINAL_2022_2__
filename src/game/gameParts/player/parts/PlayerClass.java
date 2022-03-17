package game.gameParts.player.parts;

import game.gameParts.cards.abilities.Ability;
import game.gameParts.cards.abilities.magical.Focus;
import game.gameParts.cards.abilities.magical.Reflect;
import game.gameParts.cards.abilities.magical.Water;
import game.gameParts.cards.abilities.physical.playerAbilities.Parry;
import game.gameParts.cards.abilities.physical.playerAbilities.Slash;
import game.gameParts.cards.abilities.physical.playerAbilities.Thrust;

import java.util.List;

/**
 * enum which holds the Player classes and the corresponding starting abilities
 * @author upvlx
 * @version 0.1
 */
public enum PlayerClass {
    /**
     * the mage class
     */
    MAGE("Mage", List.of(new Focus(1), new Water(1))),
    /**
     * the warrior class
     */
    WARRIOR("Warrior", List.of(new Thrust(1), new Parry(1))),
    /**
     * the paladin class
     */
    PALADIN("Paladin", List.of(new Slash(1), new Reflect(1)));

    private final String displayName;
    private final List<Ability> cards;
    PlayerClass(String name, List<Ability> cards) {
        this.displayName = name;
        this.cards = cards;
    }

    /**
     * function that gets the display name to be output
     * @return the name that is to be displayed
     */
    public String getDisplayName() {
        return this.displayName;
    }

    /**
     * gets the starting abilities
     * @return a list of the starting abilities
     */
    public List<Ability> getCards() {
        return this.cards;
    }
}
