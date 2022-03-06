package game.state.initiationValues;

public enum GameSettings {
    ROOMS(4),
    LEVELS(2);
    private final int value;
    GameSettings(int value) {
        this.value = value;
    }
    public int getValue() {
        return this.value;
    }
}
