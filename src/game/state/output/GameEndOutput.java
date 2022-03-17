package game.state.output;

/**
 * All possibilities for the output at the end of the game
 * @author upvlx
 * @version 0.1
 */
public enum GameEndOutput {
    /**
     * winning game message
     */
    WON("Runa won!"),
    /**
     * losing game message
     */
    LOSE("Runa dies");
    private final String output;
    GameEndOutput(String msg) {
        this.output = msg;
    }

    /**
     * function to get the messages
     * @return the message of the chosen entity
     */
    public String toString() {
        return this.output;
    }
}
