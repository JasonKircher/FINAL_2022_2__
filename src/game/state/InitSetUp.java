package game.state;

import game.Game;

public class InitSetUp extends GameState{
    public InitSetUp(Game game) {
        super(game);
    }

    @Override
    public void executeState() {
        // 2.1
        // choose class etc

        // set next state
        this.game.setState(new LevelSetUp(this.game));
    }
}
