package game.gameParts.player;

import game.gameParts.cards.Card;

import java.util.List;

public enum PlayerClass {
    MAGE("Mage", List.of()),
    WARRIOR("Warrior", List.of()),
    PALADIN("Paladin", List.of());

    private final String displayName;
    private final List<Card> cards;
    PlayerClass(String name, List<Card> cards) {
        this.displayName = name;
        this.cards = cards;
    }
    public String getDisplayName() {
        return this.displayName;
    }
    public List<Card> getCards() {
        return this.cards;
    }
}
