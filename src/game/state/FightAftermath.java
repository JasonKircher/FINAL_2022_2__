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

        // set next state
        if (this.game.getRoom() == 4) {
            this.game.setState(new LevelSetUp(this.game));
        }
        else {
            this.game.setState(new Fight(this.game));
        }
    }
}
