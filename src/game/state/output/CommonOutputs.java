package game.state.output;

/**
 * enum containing common outputs
 */
public enum CommonOutputs {
    /**
     * Player name
     */
    PLAYER("Runa"),
    /**
     * welcome text
     */
    WELCOME(String.format("Welcome to %s's Strive\nSelect %s's character class", PLAYER, PLAYER)),
    /**
     * separator between the information of fights
     */
    FIGHT_ROUND_SEPARATOR("----------------------------------------"),
    /**
     * output for healing
     */
    HEAL("HP) can discard ability cards for healing (or none)"),
    /**
     * String that separates enemies from players stats
     */
    VS("vs."),
    /**
     * Output to Select a target if multiple monsters are active
     */
    SELECT_TARGET(String.format("Select %s's target.", PLAYER)),
    /**
     * String to select a card
     */
    SELECT_CARD("Select card to play"),
    /**
     * input request for loot
     */
    CHOOSE_LOOT(String.format("Choose %s's reward\n1) new ability cards\n2) next player dice", PLAYER)),
    /**
     * String when the Player enters a new stage
     */
    STAGE(String.format("%s enters Stage", PLAYER)),
    /**
     * output to shuffle cards
     */
    SHUFFLE_CARDS("To shuffle ability cards and monsters, enter two seeds"),
    /**
     * String for level
     */
    LEVEL("of Level"),
    /**
     * string for dmg
     */
    DAMAGE("damage"),
    /**
     * focus
     */
    FOCUS("focus"),
    /**
     * physical
     */
    PHYSICAL("phy."),
    /**
     * magical
     */
    MAGICAL("mag."),
    /**
     * use
     */
    USE("uses"),
    /**
     * output when someone dies
     */
    DIE("dies"),
    /**
     * String for take
     */
    TAKE("takes"),
    /**
     * output when the player receives smth
     */
    GET("gets"),
    /**
     * output when player gains smth (focus)
     */
    GAIN("gains");
    private final String out;

    CommonOutputs(String out) {
        this.out = out;
    }

    /**
     * custom toString to get the wanted output
     * @return the String specified in each element
     */
    @Override
    public String toString() {
        return this.out;
    }

}
