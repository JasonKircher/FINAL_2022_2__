package game.gameParts.player;

public class Rauna {
    private int hp;
    private int abilityLevel;
    public Rauna() {
        this.hp = PlayerStartingValues.STARTING_HP.getValue();
        this.abilityLevel = PlayerStartingValues.STARTING_LEVEL.getValue();
    }
}
