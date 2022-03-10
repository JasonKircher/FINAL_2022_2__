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
        while(out > max || out < 1) {
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

    protected List<Integer> getMultipleInputs(int max, int maxNumbers, String message, ErrorMsg errorMsg,
                                              boolean minIsMax, boolean duplicatesAllowed) {
        Scanner scanner = new Scanner(System.in);
        int ittr = 0;
        List<Integer> indices = new LinkedList<>();
        while (indices.isEmpty()) {
            if (ittr++ > 0) System.out.println(errorMsg.getMsg() + " at max " + maxNumbers);
            System.out.println(message);
            String input = scanner.nextLine();
            if (input.equals("quit")) return null;
            if (input.isEmpty()) return new LinkedList<>();
            if (input.split(",").length > maxNumbers) continue;
            try { Arrays.stream(input.split(",")).forEach(index -> indices.add(Integer.parseInt(index) - 1)); }
            catch (NumberFormatException e) { indices.clear(); }
            if (minIsMax && indices.size() != maxNumbers) indices.clear();
            if (!duplicatesAllowed && new HashSet<>(indices).size() != indices.size()) indices.clear();
            if (indices.stream().allMatch(index -> index > max || index < 0)) indices.clear();
        }
        return indices;
    }
}
