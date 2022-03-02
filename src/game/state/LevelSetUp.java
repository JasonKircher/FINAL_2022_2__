package game.state;

import game.Game;

public class LevelSetUp extends GameState {
    public LevelSetUp(Game game) {
        super(game);
    }

    @Override
    public void executeState() {
        // 2.2
        // mix cards

        // set next state
        this.game.setState(new Fight(this.game));
    }

    private void setUpFirstLevel() {

    }
    private void setUpSecondLevel() {

    }
}
