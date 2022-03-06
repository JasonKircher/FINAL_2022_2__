package game.state;

import game.Game;
import game.state.initiationValues.GameSettings;
import game.state.initiationValues.MonstersLevels;

public class FightAftermath extends GameState {
    public FightAftermath(Game game) {
        super(game);
    }

    @Override
    public void executeState() {
        // 2.4

        if (this.game.getRoom() == GameSettings.ROOMS.getValue()) {
            newLevelCleanse();
            if (this.game.getLevel() == GameSettings.LEVELS.getValue() + 1) {
                this.gameEnd();
                return;
            }
            this.game.setState(new LevelSetUp(this.game));
        }
        else {
            this.game.setState(new Fight(this.game));
        }
        // normales drop heilen
    }
    private void newLevelCleanse() {
        this.game.getMonsterCards().removeIf(MonstersLevels.FIRST.getMonsters()::contains);
        this.game.getMonsterCards().removeIf(MonstersLevels.SECOND.getMonsters()::contains);
        this.game.nextLevel();
    }
}
