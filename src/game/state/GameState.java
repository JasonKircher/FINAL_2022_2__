package game.state;

import com.sun.source.tree.IfTree;
import game.Game;
import game.state.output.ErrorMsg;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

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

    protected List<Integer> getMultipleInputs(int max, int maxNumbers, String message, ErrorMsg errorMsg, boolean minIsMax,
                                              boolean duplicates) {
        Scanner scanner = new Scanner(System.in);
        int ittr = 0;
        List<Integer> indices = new LinkedList<>();
        while (indices.isEmpty()) {
            if (ittr > 0) System.out.println(errorMsg.getMsg() + " at max " + maxNumbers);
            System.out.println(message);
            ittr++;
            String input = scanner.nextLine();
            if (input.equals("quit")) return null;
            if (input.isEmpty()) return new LinkedList<>();
            String[] split = input.split(",");
            if (split.length > maxNumbers) continue;
            for (String num : split) {
                try {
                    int index = Integer.parseInt(num) - 1;
                    indices.add(index);
                } catch (NumberFormatException ignored) {
                    indices.clear();
                }
            }
            if (minIsMax && indices.size() != maxNumbers) indices.clear();
            if (!duplicates) {
                for (int i = 0; i < indices.size(); i++) {
                    for (int j = 0; j < indices.size(); j++) {
                        if (indices.get(i).equals(indices.get(j)) && j != i) {
                            indices.clear();
                            break;
                        }
                    }
                }
            }
            for (int index : indices)
                if (index > max || index < 1) {
                    indices = new LinkedList<>();
                    break;
                }
        }
        return indices;
    }
}
