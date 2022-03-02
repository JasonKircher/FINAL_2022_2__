package game.state;

import game.Game;
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

    protected int validateNum(int maxValue, String outputMsg) {
        int out = Integer.MAX_VALUE;
        Scanner scanner = new Scanner(System.in);
        do {
            out = Integer.MAX_VALUE;
            System.out.println(outputMsg);
            String input = scanner.nextLine();
            if (input.equals("quit")) {
                this.gameEnd();
                return out;
            }
            try {
                out = Integer.parseInt(input);
            } catch (NumberFormatException ignored) {
                // can't parse the int, so a word was given instead of a number
            }
        } while(!(out >= 1 && out <= maxValue));
        return out;
    }
}
