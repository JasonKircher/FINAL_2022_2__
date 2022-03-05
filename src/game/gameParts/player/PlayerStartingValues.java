package game.gameParts.player;

public enum PlayerStartingValues {
    STARTING_HP(50),
    STARTING_LEVEL(1),
    MAX_FOCUS_POINTS(4);
    private final int value;
    PlayerStartingValues(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
