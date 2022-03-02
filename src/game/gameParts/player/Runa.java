package game.gameParts.player;

import game.gameParts.cards.Card;

import java.util.LinkedList;
import java.util.List;

public class Runa {
    private int                             hp;
    private int                             abilityLevel;
    private int                             focusPoints;
    private final List<Card>    abilities;

    public Runa() {
        this.abilities = new LinkedList<>();
        this.hp = PlayerStartingValues.STARTING_HP.getValue();
        this.abilityLevel = PlayerStartingValues.STARTING_LEVEL.getValue();
    }

    public boolean takeDamage(int damage) {
        this.hp -= damage;
        return hp >= 0;
    }

    public void increaseLevel() {
        abilityLevel++;
    }

    public int getAbilityLevel() {
        return this.abilityLevel;
    }
    public void addAbilityCard(Card card) {
        abilities.add(card);
    }

    public void setFocusPoints(int focusPoints) {
        this.focusPoints = focusPoints;
    }

    public int getFocusPoints() {
        return this.focusPoints;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
