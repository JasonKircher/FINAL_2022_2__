package game.state.output;

public enum Exceptions {
    DMG_FROM_DEFENSIVE_ABILITY("can't take dmg from a defensive ability");
    private final String msg;
    Exceptions(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }
}
