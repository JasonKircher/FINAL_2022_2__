package game.gameParts.player;

public enum PlayerStartingValues {
    STARTING_HP(50),
    STARTING_FOCUS_POINTS(1);
    private final int value;
    PlayerStartingValues(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
