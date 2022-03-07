package game.state;

import game.Game;
import game.state.initiationValues.GameSettings;
import game.state.initiationValues.MonstersLevels;
import game.state.output.NumInputRequest;

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
        if (!chooseDrop()) gameEnd();
    }

    private boolean chooseDrop() {
        System.out.println("Choose Runa's reward\n1) new ability cards\n2) next player dice");
        int choice = getNumInput(2, NumInputRequest.ONE_INPUT_REQUEST.getOutput(2));
        if (choice == -1) return false;
        if (choice == 0) {
            // choose ability
            return true;
        }
        this.game.getPlayer().upgradeDice();
        return true;
    }

    private void newLevelCleanse() {
        this.game.getMonsterCards().removeIf(MonstersLevels.FIRST.getMonsters()::contains);
        this.game.getMonsterCards().removeIf(MonstersLevels.SECOND.getMonsters()::contains);
        this.game.nextLevel();
    }
}
