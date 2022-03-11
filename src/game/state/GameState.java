package game.state;

import game.Game;
import game.state.output.ErrorMsg;

import java.util.*;

public abstract class GameState {
    protected Game game;

    public GameState(Game game) {
        this.game = game;
    }

    public abstract void executeState();

    protected void gameEnd() {
        this.game.end();
    }

    protected int getNumInput(int max, String output) {
       return getNumInput(max, output, ErrorMsg.NUMBER_OUT_OF_BOUNDS);
    }

    protected int getNumInput(int max, String output, ErrorMsg customError) {
        int out = Integer.MAX_VALUE;
        int ittr = 0;
        Scanner scanner = new Scanner(System.in);
        while (out > max || out < 1) {
            // Error when first input was wrong
            if (ittr > 0) System.out.println(customError.getMsg());
            ittr++;
            System.out.println(output);
            String input = scanner.nextLine();
            if (input.equals("quit")) {
                gameEnd();
                return -1;
            }
            try {
                out = Integer.parseInt(input);
            } catch (NumberFormatException ignored) {
                // cannot be parsed
            }
        }
        // -1 to get correct index
        return out - 1;
    }

    protected List<Integer> getMultipleInputs(int max, int minNumbers, int maxNumbers, String message,
                                              ErrorMsg errorMsg,
                                              boolean duplicatesAllowed) {
        List<Integer> indices = new LinkedList<>();
        do {
            System.out.println(message);
            String input = new Scanner(System.in).nextLine();
            if (input.equals("quit")) return null;
            if (input.isEmpty() && minNumbers == 0) return new LinkedList<>();
            try { indices.addAll(Arrays.stream(input.split(",")).map(i -> Integer.parseInt(i) - 1).toList()); }
            catch (NumberFormatException e) { indices.clear(); }
            if (minNumbers > indices.size() || indices.size() > maxNumbers
                    || !duplicatesAllowed && new HashSet<>(indices).size() != indices.size()
                    || indices.stream().anyMatch(index -> index >= max || index < 0)) {
                System.out.println(errorMsg.getMsg() + " at max " + maxNumbers);
                indices.clear();
            }
        } while (indices.isEmpty());
        return indices;
    }
}
