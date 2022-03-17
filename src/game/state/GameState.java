package game.state;

import game.RunasStrive;
import game.state.output.ErrorMsg;

import java.util.*;
import java.util.stream.Collectors;

/**
 * game states of a specific game all resembling a different phase in the game procedure
 * @author upvlx
 * @version 0.1
 */
public abstract class GameState {
    /**
     * the game on which the states are executed
     */
    protected final RunasStrive runasStrive;

    /**
     * constructor for a game state
     * @param runasStrive game on which the game should be executed
     */
    public GameState(RunasStrive runasStrive) {
        this.runasStrive = runasStrive;
    }

    /**
     * function to execute a state
     */
    public abstract void executeState();

    /**
     * function to end the game
     */
    protected void gameEnd() {
        this.runasStrive.end();
    }

    /**
     * overload of the function for a single input with a generic error msg
     * @param max the highest acceptable value
     * @param output msg to be displayed when asking for input
     * @return the input - 1 to get the correct index
     */
    protected int getNumInput(int max, String output) {
        return getNumInput(max, output, ErrorMsg.NUMBER_OUT_OF_BOUNDS);
    }

    /**
     * function to get an input of a single number
     * @param max the highest acceptable value for the input
     * @param output msg to be displayed when asking for input
     * @param customError error msg to be displayed for a wrong input
     * @return the input - 1 to have the correct index
     */
    protected int getNumInput(int max, String output, ErrorMsg customError) {
        int out = Integer.MAX_VALUE;
        int ittr = 0;
        Scanner scanner = new Scanner(System.in);
        while (out > max || out < 1) {
            // Error when first input was wrong
            if (ittr > 0) System.out.println(customError.getMsg());
            System.out.println(output);
            String input = scanner.nextLine();
            if (input.equals("quit")) {
                gameEnd();
                return -1;
            }
            try {
                out = Integer.parseInt(input);
            } catch (NumberFormatException ignored) {
                // cannot parse
            }
            if (!(out > 1 && out < max)) ittr++;
        }
        // -1 to get correct index
        return out - 1;
    }

    /**
     * function to get the input of multiple numbers
     * @param max the highest acceptable value
     * @param minNumbers the least amount of numbers wanted
     * @param maxNumbers the maximum amount of numbers wanted
     * @param message the message that is supposed to printed leading the input-request
     * @param errorMsg the message that is supposed to be displayed when the input was incorrect or could not be parsed
     * @param duplicatesAllowed true if duplicates in the given numbers are accepted, false if not
     * @return a list of integers following the demands all -1 to have the correct index of a list or array
     */
    protected List<Integer> getMultipleInputs(int max, int minNumbers, int maxNumbers, String message,
                                              ErrorMsg errorMsg, boolean duplicatesAllowed) {
        List<Integer> indices = new LinkedList<>();
        do {
            System.out.println(message);
            String input = new Scanner(System.in).nextLine();
            if (input.equals("quit")) return null;
            if (input.isEmpty() && minNumbers == 0) return new LinkedList<>();
            if (input.startsWith(",") || input.endsWith(",")) {
                System.out.println(errorMsg.getMsg() + " at max " + maxNumbers + " numbers.");
                continue;
            }
            try {
                indices.addAll(Arrays.stream(input.split(",")).map(i -> Integer.parseInt(i) - 1)
                        .collect(Collectors.toList())); }
            catch (NumberFormatException e) {
                System.out.println(ErrorMsg.NUMBER.getMsg());
                indices.clear();
                continue;
            }
            if (minNumbers > indices.size() || indices.size() > maxNumbers) {
                System.out.println(ErrorMsg.AMOUNT.getMsg());
                indices.clear();
            }
            else if (indices.stream().anyMatch(index -> index >= max || index < 0)) {
                System.out.println(errorMsg.getMsg());
                indices.clear();
            }
            else if (!duplicatesAllowed && new HashSet<>(indices).size() != indices.size()) {
                System.out.println(ErrorMsg.DUPLICATES.getMsg());
                indices.clear();
            }
        } while (indices.isEmpty());
        return indices;
    }
}
