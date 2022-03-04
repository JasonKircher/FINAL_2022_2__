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

    protected int validateNum(int maxValue, String outputMsg, ErrorMsg msg) {
        int out = Integer.MAX_VALUE;
        int ittr = 0;
        Scanner scanner = new Scanner(System.in);
        do {
            if (ittr > 0) System.out.println(msg.getMsg());
            ittr++;
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
                // no number given
            }
        } while(!(out >= 1 && out <= maxValue));
        return out;
    }
}
