package game.state;

import game.RunasStrive;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.HashSet;
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
     * function to get the input of multiple numbers
     * @param max the highest acceptable value
     * @param minNumbers the least amount of numbers wanted
     * @param maxNumbers the maximum amount of numbers wanted
     * @param message the message that is supposed to printed leading the input-request
     * @param duplicatesAllowed true if duplicates in the given numbers are accepted, false if not
     * @return a list of integers following the demands all -1 to have the correct index of a list or array
     */
    protected List<Integer> getInput(int max, int minNumbers, int maxNumbers, String message,
                                     boolean duplicatesAllowed) {
        List<Integer> indices = new LinkedList<>();
        do {
            System.out.println(message);
            String input = new Scanner(System.in).nextLine();
            if (input.equals("quit")) {
                gameEnd();
                return null;
            }
            if (input.isEmpty() && minNumbers == 0) return new LinkedList<>();
            if (input.startsWith(",") || input.endsWith(","))
                continue;
            try {
                indices.addAll(Arrays.stream(input.split(",")).map(i -> Integer.parseInt(i) - 1)
                        .collect(Collectors.toList())); }
            catch (NumberFormatException e) {
                indices.clear();
                continue;
            }
            if (minNumbers > indices.size() || indices.size() > maxNumbers
                || indices.stream().anyMatch(index -> index >= max || index < 0)
                || !duplicatesAllowed && new HashSet<>(indices).size() != indices.size()) {
                indices.clear();
            }
        } while (indices.isEmpty());
        return indices;
    }
}
