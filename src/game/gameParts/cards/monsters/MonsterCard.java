package game.gameParts.cards.monsters;

import game.gameParts.cards.Card;
import game.gameParts.cards.abilities.Ability;

import java.util.LinkedList;
import java.util.List;

public abstract class MonsterCard implements Card {
    private final List<Ability> abilities;
    public MonsterCard() {
        this.abilities = new LinkedList<>();
    }
}
