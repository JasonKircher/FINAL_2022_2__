package game.state.output;

public enum ErrorMsg {
    CLASS("Error, pls choose one of the given classes, enter the corresponding number in front of it");
    private final String msg;
    ErrorMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }
}
