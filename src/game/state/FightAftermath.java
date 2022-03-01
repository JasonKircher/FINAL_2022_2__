package game.state;

import game.Game;

public class FightAftermath extends GameState {
    public FightAftermath(Game game) {
        super(game);
    }

    @Override
    public void executeState() {
        // 2.4
        // heilen, karten etc
        this.game.nextLevel();
        // set next state
        this.game.setState(new LevelSetUp(this.game));
    }
}
