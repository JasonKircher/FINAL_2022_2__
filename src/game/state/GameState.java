package game.state;

import game.Game;
import game.state.output.ErrorMsg;
import game.state.output.NumInputRequest;

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
            if (input.equals("quit")) return -1;
            try {
                out = Integer.parseInt(input);
            } catch (NumberFormatException ignored) {
                // cannot be parsed
            }
        }
        // -1 to get correct index
        return out - 1;
    }
}
