package game.gameParts.player;


import game.gameParts.cards.abilities.Ability;
import game.gameParts.cards.abilities.magical.Reflect;
import game.gameParts.cards.abilities.physical.playerAbilities.Parry;
import game.gameParts.cards.abilities.physical.playerAbilities.Slash;
import game.gameParts.cards.abilities.physical.playerAbilities.Thrust;

import java.util.List;

public enum PlayerClass {
    MAGE("Mage", List.of()),
    WARRIOR("Warrior", List.of(new Thrust(1), new Parry(1))),
    PALADIN("Paladin", List.of(new Slash(1), new Reflect(1)));

    private final String displayName;
    private final List<Ability> cards;
    PlayerClass(String name, List<Ability> cards) {
        this.displayName = name;
        this.cards = cards;
    }
    public String getDisplayName() {
        return this.displayName;
    }
    public List<Ability> getCards() {
        return this.cards;
    }
}
