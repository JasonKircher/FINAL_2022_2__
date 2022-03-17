package game.state.initiationValues;

/**
 * Setting for the Game
 * @author upvlx
 * @version 0.1
 */
public enum GameSettings {
    /**
     * number of rooms on one level
     */
    ROOMS(4),
    /**
     * number of levels
     */
    LEVELS(2),
    /**
     * cards to choose as loot at the end of a fight
     */
    LOOT_CARDS(2);
    private final int value;
    GameSettings(int value) {
        this.value = value;
    }

    /**
     * function to access the value of the entity
     * @return the corresponding value
     */
    public int getValue() {
        return this.value;
    }
}
