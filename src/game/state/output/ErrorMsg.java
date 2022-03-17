package game.state.output;

/**
 * Error messages that are to be printed when a wrong input is given or a input cannot be parsed
 * @author upvlx
 * @version 0.1
 */
public enum ErrorMsg {
    /**
     * error msg when too many or not enough numbers were given
     */
    AMOUNT("Error, too many or not enough numbers were given"),
    /**
     * error msg when duplicates are given but no duplicates allowed
     */
    DUPLICATES("Error, no duplicates are allowed for this input"),
    /**
     * error msg when no number was input
     */
    NUMBER("Error, the given input was not a number, pls enter positive numbers smaller or equal than requested "),
    /**
     * error msg for the class input
     */
    CLASS("Error, pls choose one of the given classes, enter the corresponding number in front of it"),
    /**
     * generic error msg when smth was input wrong
     */
    NUMBER_OUT_OF_BOUNDS("Error, the given number is out of bounds or not a number, pls respect the rules"),
    /**
     * error when the seed input was wrong
     */
    SEED("Error, Seeds must Integers separated by only a comma");
    private final String msg;
    ErrorMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }
}
