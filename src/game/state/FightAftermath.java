package game.state;

import game.Game;
import game.gameParts.cards.abilities.Ability;
import game.gameParts.player.PlayerStartingValues;
import game.gameParts.player.parts.Dice;
import game.state.initiationValues.GameSettings;
import game.state.output.ErrorMsg;
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
            List<Ability> abilities = this.game.getPlayer().getPlayerClass().getCards();
            abilities.forEach(ability -> {
                    Ability tmp = ability.copy();
                    tmp.upgrade();
                    this.game.getPlayer().addAbilityCard(tmp);
            });
            this.game.setState(new LevelSetUp(this.game));
        }
        else {
            if (!chooseDrop()) gameEnd();
            this.game.setState(new Fight(this.game));
        }
        if (this.game.getPlayer().getHp() != PlayerStartingValues.STARTING_HP.getValue()) heal();
    }

    private boolean chooseDrop() {
        int choice;
        if (this.game.getPlayer().getCurrentDice() == Dice.D12) choice = 0;
        else if (this.game.getAbilityCards().isEmpty()) choice = 1;
        else {
            System.out.println("Choose Runa's reward\n1) new ability cards\n2) next player dice");
            choice = getNumInput(2, NumInputRequest.ONE_INPUT_REQUEST.getOutput(2));
        }
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
        for (int i = 0; i < GameSettings.LOOT_CARDS.getValue(); i++) {
            selection.add(this.game.getAbilityCards().remove(0));
        }
        if (this.game.getRoom() != 1) {
            for (int i = 0; i < GameSettings.LOOT_CARDS.getValue(); i++) {
                if (!this.game.getAbilityCards().isEmpty())
                    selection.add(this.game.getAbilityCards().remove(0));
            }
        }
        System.out.println("Pick " + Math.round(selection.size() * 0.5) + " card(s) as loot");
        for (int index = 0; index < selection.size(); index++)
            System.out.println(index + 1 + ") " + selection.get(index));

        if (this.game.getRoom() == 1) {
            int chosen = getNumInput(selection.size(), NumInputRequest.ONE_INPUT_REQUEST.getOutput(selection.size()));
            if (chosen == -1) return false;
            this.game.getPlayer().addAbilityCard(selection.remove(chosen));
        }
        else {
            List<Integer> indices = getMultipleInputs(selection.size() - 1, 2,
                    NumInputRequest.MULTIPLE_INPUT_REQUEST.getOutput(selection.size()), ErrorMsg.NUMBER_OUT_OF_BOUNDS,
                    true, false);
            if (indices == null) return false;
            List<Ability> tmp = new LinkedList<>();
            for (int index : indices) tmp.add(selection.get(index));
            tmp.forEach(card -> this.game.getPlayer().addAbilityCard(card));
        }
        return true;
    }

    private void heal() {
        if (this.game.getPlayer().getAbilities().size() == 1) return;
        System.out.println("Runa (" + this.game.getPlayer().getHp() + "/" + PlayerStartingValues.STARTING_HP.getValue()
                + " HP) can discard ability cards for healing (or none)");
        for (int i = 0; i < this.game.getPlayer().getAbilities().size(); i++)
            System.out.printf("%d) %s%n",i + 1 ,this.game.getPlayer().getAbilities().get(i));

        List<Integer> indices = getHealInputs();
        if (indices == null) return;
        indices.sort((o1, o2) -> o2 - o1);
        for (int index : indices) {
            this.game.getPlayer().getAbilities().remove(index);
        }
        int healVal = indices.size() * 10;
        this.game.getPlayer().heal(healVal);
    }



    private List<Integer> getHealInputs() {
        return getMultipleInputs(this.game.getPlayer().getAbilities().size() - 1,
                this.game.getPlayer().getAbilities().size() - 1,
                NumInputRequest.MULTIPLE_INPUT_REQUEST.getOutput(this.game.getPlayer().getAbilities().size()),
                ErrorMsg.NUMBER_OUT_OF_BOUNDS, false, false);
    }

    private void newLevelCleanse() {
        this.game.getMonsterCards().clear();
        this.game.getAbilityCards().clear();
        this.game.nextLevel();
    }
}
