package game.state.output;

public enum CommonOutputs {
    SEPARATOR("----------------------------------------"),
    VS("vs."),
    SELECT_TARGET("Select Runa's target"),
    SELECT_CARD("Select card to play"),
    CHOOSE_LOOT("Choose Runa's reward\n1) new ability cards\n2) next player dice"),
    STAGE("Runa enters Stage"),
    LEVEL("of Level"),
    PLAYER("Runa"),
    FOCUS("focus"),
    PHYSICAL("phy."),
    MAGICAL("mag."),
    USE("uses"),
    DIE("dies"),
    GET("gets"),
    GAIN("gains");
    private final String out;

    CommonOutputs(String out) {
        this.out = out;
    }
    public String getOut() {
        return this.out;
    }

}
