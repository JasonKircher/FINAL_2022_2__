package game.gameParts.player;

/**
 * stating values for a Player
 * @author upvlx
 * @version 0.1
 */
public enum PlayerStartingValues {
    /**
     * stating hp for a player
     */
    STARTING_HP(50),
    /**
     * stating focus points
     */
    STARTING_FOCUS_POINTS(1);
    private final int value;

    /**
     * constructor for the values
     * @param value the value of the parameter
     */
    PlayerStartingValues(int value) {
        this.value = value;
    }

    /**
     * function to get the value of the entity
     * @return the value that is saved
     */
    public int getValue() {
        return value;
    }
}
