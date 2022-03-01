package game.state;

import game.Game;

public class Fight extends GameState {
    public Fight(Game game) {
        super(game);
    }

    @Override
    public void executeState() {
        // 2.3
        // raum betreten und kämpfen

        // set next state
        this.game.setState(new FightAftermath(this.game));
    }
}
