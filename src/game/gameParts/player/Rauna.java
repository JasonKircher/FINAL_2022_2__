package game.gameParts.player;

import game.gameParts.cards.abilities.Ability;

import java.util.LinkedList;
import java.util.List;

public class Rauna {
    private int                 hp;
    private int                 abilityLevel;
    private List<Ability>       abilities;

    public Rauna() {
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
    public void addAbilityCard(Ability card) {
        abilities.add(card);
    }
}
