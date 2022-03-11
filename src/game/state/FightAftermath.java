package game.state;

import game.Game;
import game.gameParts.cards.abilities.Ability;
import game.gameParts.player.PlayerStartingValues;
import game.gameParts.player.parts.Dice;
import game.state.initiationValues.GameSettings;
import game.state.output.CommonOutputs;
import game.state.output.ErrorMsg;
import game.state.output.NumInputRequest;

import java.util.LinkedList;
import java.util.List;

/**
 * GameState that resembles the interactions after a fight (heal and loot)
 * @author upvlx
 * @version 0.1
 */
public class FightAftermath extends GameState {

    /**
     * constructor
     * @param game  Game on which the operation should be done
     */
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
            if (!chooseDrop()) {
                gameEnd();
                return;
            }
            this.game.setState(new Fight(this.game));
        }
        if (this.game.getPlayer().getHp() != PlayerStartingValues.STARTING_HP.getValue())
            if (!heal()) gameEnd();

    }

    private boolean chooseDrop() {
        int choice;
        if (this.game.getPlayer().getCurrentDice() == Dice.D12) choice = 0;
        else if (this.game.getAbilityCards().isEmpty()) choice = 1;
        else {
            System.out.println(CommonOutputs.CHOOSE_LOOT.getOut());
            choice = getNumInput(2, NumInputRequest.ONE_INPUT_REQUEST.toString(2));
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
        int cardsToPick = this.game.getRoom() == 1 ? 1 : GameSettings.LOOT_CARDS.getValue();

        for (int i = 0; i < cardsToPick * 2; i++)
            if (!this.game.getAbilityCards().isEmpty())
                selection.add(this.game.getAbilityCards().remove(0));

        String inputMessage = cardsToPick == 1
                ? NumInputRequest.ONE_INPUT_REQUEST.toString(selection.size())
                : NumInputRequest.MULTIPLE_INPUT_REQUEST.toString(selection.size());

        System.out.println("Pick " + cardsToPick + " card(s) as" + " loot");
        for (int index = 0; index < selection.size(); index++)
            System.out.println(index + 1 + ") " + selection.get(index));

        List<Integer> indices = getMultipleInputs(selection.size(), cardsToPick, cardsToPick,
                inputMessage, ErrorMsg.NUMBER_OUT_OF_BOUNDS, false);

        if (indices == null) return false;
        List<Ability> tmp = new LinkedList<>();
        for (int index : indices) tmp.add(selection.get(index));
        tmp.forEach(card -> this.game.getPlayer().addAbilityCard(card));
        return true;
    }

    private boolean heal() {
        if (this.game.getPlayer().getAbilities().size() == 1) return true;
        System.out.println("Runa (" + this.game.getPlayer().getHp() + "/" + PlayerStartingValues.STARTING_HP.getValue()
                + " HP) can discard ability cards for healing (or none)");
        for (int i = 0; i < this.game.getPlayer().getAbilities().size(); i++)
            System.out.printf("%d) %s%n", i + 1 , this.game.getPlayer().getAbilities().get(i));

        List<Integer> indices = getHealInputs();
        if (indices == null) return false;
        indices.sort((o1, o2) -> o2 - o1);
        for (int index : indices) {
            this.game.getPlayer().getAbilities().remove(index);
        }
        int healVal = indices.size() * 10;
        this.game.getPlayer().heal(healVal);
        return true;
    }

    private List<Integer> getHealInputs() {
        return getMultipleInputs(this.game.getPlayer().getAbilities().size() - 1, 0,
                this.game.getPlayer().getAbilities().size() - 1,
                NumInputRequest.MULTIPLE_INPUT_REQUEST.toString(this.game.getPlayer().getAbilities().size()),
                ErrorMsg.NUMBER_OUT_OF_BOUNDS, false);
    }

    private void newLevelCleanse() {
        this.game.getMonsterCards().clear();
        this.game.getAbilityCards().clear();
        this.game.nextLevel();
    }
}
