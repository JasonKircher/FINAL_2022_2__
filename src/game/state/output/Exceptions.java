package game.state.output;

/**
 * Messages that are output when an invalid move was made, for ex taking dmg from a defensive ability,
 * these are not part of the normal control flow but exist to make to code more safe
 * @author upvlx
 * @version 0.1
 */
public enum Exceptions {
    /**
     * exception message when an ability is called from the wrong entity, meaning they should not call it,
     * since theres no implementation
     */
    WRONG_ENTITY("the entity calling this ability is not supposed to call it"),
    /**
     * message when someone is trying to take dmg from a defensive ability
     */
    DMG_FROM_DEFENSIVE_ABILITY("can't take dmg from a defensive ability");
    private final String msg;

    /**
     * constructor for a Exception
     * @param msg the message for the Exception
     */
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
