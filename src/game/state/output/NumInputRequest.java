package game.state.output;

public enum NumInputRequest {
    ONE_INPUT_REQUEST("Enter number [1--<n>]:"),
    MULTIPLE_INPUT_REQUEST("Enter numbers [1--<n>] separated by comma:"),
    DICE_INPUT_REQUEST("Enter dice roll [1--<n>]:"),
    SEED_INPUT_REQUEST("Enter seeds [1--2147483647] separated by comma:");
    private final String output;
    NumInputRequest(String output) {
        this.output = output;
    }
    public String toString(int maxVal) {
        return this.output.replace("<n>", "" + maxVal);
    }
    public String toString() {
        return this.output;
    }
}
