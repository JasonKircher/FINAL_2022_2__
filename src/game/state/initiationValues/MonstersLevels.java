package game.state.initiationValues;

import game.gameParts.cards.monsters.Monster;

import java.util.List;

public enum MonstersLevels {
    FIRST(List.of()),
    SECOND(List.of());

    private final List<Monster> monsters;
    MonstersLevels(List<Monster> monsters) {
        this.monsters = monsters;
    }

    public List<Monster> getMonsters() {
         return this.monsters;
    }
}
