package game.state;

import game.RunasStrive;
import game.gameParts.cards.abilities.Ability;
import game.gameParts.player.PlayerStartingValues;
import game.gameParts.player.parts.Dice;
import game.state.initiationValues.GameSettings;
import game.state.output.CommonOutputs;
import game.state.output.NumInputRequest;

import java.util.LinkedList;
import java.util.List;

/**
 * GameState that resembles the interactions after a fight (heal and loot) (2.4 on ex sheet)
 * @author upvlx
 * @version 0.1
 */
public class FightAftermath extends GameState {
    /**
     * constructor
     * @param runasStrive  Game on which the operation should be done
     */
    public FightAftermath(RunasStrive runasStrive) {
        super(runasStrive);
    }

    @Override
    public void executeState() {
        if (this.runasStrive.getRoom() == GameSettings.ROOMS.getValue()) {
            newLevelCleanse();
            if (this.runasStrive.getLevel() == GameSettings.LEVELS.getValue() + 1) {
                this.gameEnd();
                return;
            }
            // upgrade default spells
            List<Ability> abilities = this.runasStrive.getPlayer().getPlayerClass().getCards();
            abilities.forEach(ability -> {
                Ability tmp = ability.copy();
                tmp.upgrade();
                this.runasStrive.getPlayer().addAbilityCard(tmp);
            });
            this.runasStrive.setState(new LevelSetUp(this.runasStrive));
        }
        else {
            if (!chooseDrop())
                return;
            this.runasStrive.setState(new Fight(this.runasStrive));
        }
        if (this.runasStrive.getPlayer().getHp() != PlayerStartingValues.STARTING_HP.getValue())
            heal();
    }

    /**
     * function to enable the selection of the loot
     * @return true if the selection was complete, false if the selection was quit
     */
    private boolean chooseDrop() {
        List<Integer> choice = new LinkedList<>();
        if (this.runasStrive.getPlayer().getCurrentDice() == Dice.D12) choice.add(0);
        else if (this.runasStrive.getAbilityCards().isEmpty()) choice.add(1);
        else {
            System.out.println(CommonOutputs.CHOOSE_LOOT);
            choice = getInput(2, 1,  1,
                    NumInputRequest.ONE_INPUT_REQUEST.toString(2), false);
            if (choice == null) return false;
        }
        switch (choice.remove(0)) {
            case 0:
                return chooseAbility();
            case 1:
                this.runasStrive.getPlayer().upgradeDice();
                System.out.println("Runa upgrades her die to a d"
                        + this.runasStrive.getPlayer().getCurrentDice().getMaxValue());
                return true;
            default:
                return false;
        }
    }

    /**
     * function to choose loot cards
     * @return true if loot cards were chosen false if the selection was quit
     */
    private boolean chooseAbility() {
        List<Ability> selection = new LinkedList<>();
        int cardsToPick = this.runasStrive.getRoom() == 1 ? 1 : GameSettings.LOOT_CARDS.getValue();

        for (int i = 0; i < cardsToPick * 2; i++)
            if (!this.runasStrive.getAbilityCards().isEmpty())
                selection.add(this.runasStrive.getAbilityCards().remove(0));

        String inputMessage = cardsToPick == 1
                ? NumInputRequest.ONE_INPUT_REQUEST.toString(selection.size())
                : NumInputRequest.MULTIPLE_INPUT_REQUEST.toString(selection.size());

        System.out.println("Pick " + cardsToPick + " card(s) as" + " loot");
        for (int index = 0; index < selection.size(); index++)
            System.out.println(index + 1 + ") " + selection.get(index));

        List<Integer> indices = getInput(selection.size(), cardsToPick, cardsToPick,
                inputMessage, false);

        if (indices == null) return false;
        List<Ability> tmp = new LinkedList<>();
        for (int index : indices) tmp.add(selection.get(index));
        tmp.forEach(card -> this.runasStrive.getPlayer().addAbilityCard(card));
        return true;
    }

    /**
     * function to enable the healing at the end of a round
     * @return true if healing is complete, false if it was quit
     */
    private boolean heal() {
        if (this.runasStrive.getPlayer().getAbilities().size() == 1) return true;
        System.out.printf("%s (%d/%d %s%n", CommonOutputs.PLAYER, this.runasStrive.getPlayer().getHp(),
                PlayerStartingValues.STARTING_HP.getValue(), CommonOutputs.HEAL);
        for (int i = 0; i < this.runasStrive.getPlayer().getAbilities().size(); i++)
            System.out.printf("%d) %s%n", i + 1 , this.runasStrive.getPlayer().getAbilities().get(i));

        List<Integer> indices = getHealInputs();
        if (indices == null) return false;
        indices.sort((o1, o2) -> o2 - o1);
        for (int index : indices) {
            this.runasStrive.getPlayer().getAbilities().remove(index);
        }
        int healVal = indices.size() * GameSettings.HEALING_MULTIPLIER.getValue();
        this.runasStrive.getPlayer().heal(healVal);
        return true;
    }

    /**
     * input request for healing
     * @return a list of indices given by the user
     */
    private List<Integer> getHealInputs() {
        List<Integer> indices = null;
        int max = this.runasStrive.getPlayer().getAbilities().size();
        String output = max > 2 ? NumInputRequest.MULTIPLE_INPUT_REQUEST.toString(max)
                : NumInputRequest.ONE_INPUT_REQUEST.toString(max);

        indices = getInput(max, 0, max - 1, output, false);

        return indices;
    }

    private void newLevelCleanse() {
        this.runasStrive.getMonsterCards().clear();
        this.runasStrive.getAbilityCards().clear();
        this.runasStrive.nextLevel();
    }
}
