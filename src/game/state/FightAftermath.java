package game.state;

import game.Game;
import game.gameParts.cards.abilities.Ability;
import game.gameParts.player.PlayerStartingValues;
import game.gameParts.player.parts.Dice;
import game.state.initiationValues.GameSettings;
import game.state.initiationValues.MonstersLevels;
import game.state.output.ErrorMsg;
import game.state.output.NumInputRequest;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

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
                selection.add(this.game.getAbilityCards().remove(0));
            }
        }
        System.out.println("Pick " + selection.size() / 2 + " card(s) as loot");
        for (int index = 0; index < selection.size(); index++)
            System.out.println(index + 1 + ") " + selection.get(index));

        if (this.game.getRoom() == 1) {
            int chosen = getNumInput(selection.size(), NumInputRequest.ONE_INPUT_REQUEST.getOutput(selection.size()));
            if (chosen == -1) return false;
            this.game.getPlayer().addAbilityCard(selection.remove(chosen));
        }
        else {
            List<Integer> indices = getMultipleInputs(2,
                    NumInputRequest.MULTIPLE_INPUT_REQUEST.getOutput(selection.size()), ErrorMsg.NUMBER_OUT_OF_BOUNDS,
                    true);
            if (indices == null) return false;
            List<Ability> tmp = new LinkedList<>();
            for (Integer index : indices) tmp.add(selection.get(index));
            this.game.getPlayer().getAbilities().addAll(tmp);
            selection.removeIf(tmp::contains);
        }
        return true;
    }

    private void heal() {
        System.out.println("Runa (" + this.game.getPlayer().getHp() + "/" + PlayerStartingValues.STARTING_HP.getValue()
                + ") can discard ability cards for healing (or none)");
        this.game.getPlayer().getAbilities().forEach(ability ->
                System.out.println(this.game.getPlayer().getAbilities().indexOf(ability) + 1 + ") " + ability));

        List<Integer> indices = getHealInputs();
        List<Ability> abilitiesToBeRemoved = new LinkedList<>();
        if (indices == null) return;
        indices.forEach(index -> abilitiesToBeRemoved.add(this.game.getPlayer().getAbilities().get(index)));
        abilitiesToBeRemoved.forEach(ability -> this.game.getPlayer().getAbilities().remove(ability));
        int healVal = indices.size() * 10;
        this.game.getPlayer().heal(healVal);
    }

    private List<Integer> getMultipleInputs(int maxNumbers, String message, ErrorMsg errorMsg, boolean minIsMax) {
        Scanner scanner = new Scanner(System.in);
        int ittr = 0;
        List<Integer> indices = new LinkedList<>();
        while (indices.isEmpty()) {
            if (ittr > 0) System.out.println(errorMsg.getMsg() + " at max " + maxNumbers);
            System.out.println(message);
            ittr++;
            String input = scanner.nextLine();
            if (input.equals("quit")) return null;
            if (input.isEmpty()) return new LinkedList<>();
            String[] split = input.split(",");
            if (split.length > maxNumbers) continue;
            for (String num : split) {
                try {
                    int index = Integer.parseInt(num) - 1;
                    indices.add(index);
                } catch (NumberFormatException ignored) {
                    return getHealInputs();
                }
            }
            if (minIsMax && indices.size() != maxNumbers) indices = new LinkedList<>();
        }
        return indices;
    }

    private List<Integer> getHealInputs() {
        return getMultipleInputs(this.game.getPlayer().getAbilities().size() - 1,
                NumInputRequest.MULTIPLE_INPUT_REQUEST.getOutput(0), ErrorMsg.NUMBER_OUT_OF_BOUNDS,
                false);
    }

    private void newLevelCleanse() {
        this.game.getMonsterCards().clear();
        this.game.getAbilityCards().clear();
        this.game.nextLevel();
    }
}
