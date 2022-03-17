package game.state.output;

/**
 * Class which holds all Number-input-request outputs, so all outputs that request a input
 * @author upvlx
 * @version 0.1
 */
public enum NumInputRequest {
    /**
     * Request for a single number
     */
    ONE_INPUT_REQUEST("Enter number [1--<n>]:"),
    /**
     * Request fpr multiple inputs, separated by a comma
     */
    MULTIPLE_INPUT_REQUEST("Enter numbers [1--<n>] separated by comma:"),
    /**
     * Request for a single Input of a dice roll
     */
    DICE_INPUT_REQUEST("Enter dice roll [1--<n>]:"),
    /**
     * Request for the input of a seed, max being the highest int
     */
    SEED_INPUT_REQUEST("Enter seeds [1--2147483647] separated by comma:");
    private final String output;
    NumInputRequest(String output) {
        this.output = output;
    }

    /**
     * Function to get the Text of the inputs with a replacement for the highest possible value
     * @param maxVal the maximum value that is supposed to be put in the input
     * @return the input-request-String with the highest number already put in
     */
    public String toString(int maxVal) {
        return this.output.replace("<n>", "" + maxVal);
    }

    /**
     * function to get the Text of the inputs
     * @return the unchanged messages with a placeholder for highest value (besides seeds-request)
     */
    public String toString() {
        return this.output;
    }
}
