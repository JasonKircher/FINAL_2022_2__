package game.state.output;

public enum GameEndOutput {
    WON("Runa won!"),
    LOSE("Runa dies");
    private String output;
    GameEndOutput(String msg) {
        this.output = msg;
    }
    public String getOutput() {
        return this.output;
    }
}
