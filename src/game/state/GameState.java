package game.state;

import game.Game;

public abstract class GameState {
    protected Game game;

    public GameState(Game game) {
        this.game = game;
    }
    public abstract void executeState();
    protected void gameEnd() {
        this.game.end();
    }
}
