package game.state;

import game.Game;
import game.gameParts.cards.abilities.Ability;
import game.state.initiationValues.GameSettings;
import game.state.initiationValues.MonstersLevels;
import game.state.output.NumInputRequest;

import java.util.LinkedList;
import java.util.List;

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
            // upgrade default spells
            this.game.getPlayer().getAbilities().forEach(ability -> {
                if (this.game.getPlayer().getPlayerClass().getCards().contains(ability)) {
                    ability.upgrade();
                }
            });
            this.game.setState(new LevelSetUp(this.game));
        }
        else {
            if (!chooseDrop()) gameEnd();
            this.game.setState(new Fight(this.game));
        }
        // TODO heal
    }

    private boolean chooseDrop() {
        System.out.println("Choose Runa's reward\n1) new ability cards\n2) next player dice");
        int choice = getNumInput(2, NumInputRequest.ONE_INPUT_REQUEST.getOutput(2));
        switch (choice) {
            case 0:
                return chooseAbility();
            case 1:
                this.game.getPlayer().upgradeDice();
                System.out.println("Runa upgrades her die to a d"
                        + this.game.getPlayer().getCurrentDice().getMaxValue());
                return true;
            default:
                return false;
        }
    }

    private boolean chooseAbility() {
        List<Ability> selection = new LinkedList<>();
        for(int i = 0; i < GameSettings.LOOT_CARDS.getValue(); i++) {
            selection.add(this.game.getAbilityCards().remove(0));
        }
        if (this.game.getRoom() != 1) {
            for(int i = 0; i < GameSettings.LOOT_CARDS.getValue(); i++) {
                selection.add(this.game.getAbilityCards().remove(0));
            }
        }
        selection.forEach(ability -> System.out.println(selection.indexOf(ability) + 1 + ") " + ability));
        int chosen = getNumInput(selection.size(), NumInputRequest.ONE_INPUT_REQUEST.getOutput(selection.size()));
        if (chosen == -1) return false;
        this.game.getPlayer().addAbilityCard(selection.remove(chosen));
        this.game.getAbilityCards().addAll(selection);
        return true;
    }

    private void newLevelCleanse() {
        this.game.getMonsterCards().removeIf(MonstersLevels.FIRST.getMonsters()::contains);
        this.game.getMonsterCards().removeIf(MonstersLevels.SECOND.getMonsters()::contains);
        this.game.nextLevel();
    }
}
