package game.state.output;

/**
 * Messages that are output when an invalid move was made, for ex taking dmg from a defensive ability,
 * these are not part of the normal control flow but exist to make to code more safe
 * @author upvlx
 * @version 0.1
 */
public enum Exceptions {
    /**
     * message when someone is trying to take dmg from a defensive ability
     */
    DMG_FROM_DEFENSIVE_ABILITY("can't take dmg from a defensive ability");
    private final String msg;
    Exceptions(String msg) {
        this.msg = msg;
    }

    /**
     * function to get the message
     * @return the message of the entity
     */
    public String getMsg() {
        return this.msg;
    }
}
