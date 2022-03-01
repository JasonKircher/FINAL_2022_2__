package game.gameParts.player;

import game.gameParts.cards.abilities.AbilityCard;

import java.util.LinkedList;
import java.util.List;

public class Rauna {
    private int                 hp;
    private int                 abilityLevel;
    private List<AbilityCard>   abilityCards;

    public Rauna() {
        this.abilityCards = new LinkedList<>();
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
    public void addCard(AbilityCard card) {
        abilityCards.add(card);
    }
}
