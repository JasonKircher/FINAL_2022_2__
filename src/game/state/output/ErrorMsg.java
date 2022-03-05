package game.state.output;

public enum ErrorMsg {
    CLASS("Error, pls choose one of the given classes, enter the corresponding number in front of it"),
    NUMBER_OUT_OF_BOUNDS("Error, the given number is out of bounds or not a number, pls respect the rules"),
    NOT_ENOUGH_FOCUS("Error, you do not have enough focus");
    private final String msg;
    ErrorMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }
}
