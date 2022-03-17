package game.state;

import game.RunasStrive;
import game.gameParts.player.parts.PlayerClass;
import game.state.output.CommonOutputs;
import game.state.output.ErrorMsg;
import game.state.output.NumInputRequest;

import java.util.*;

/**
 * class that is responsible for the initial set-up, with choosing a class and getting initial abilities (2.1 on sheet)
 * @author upvlx
 * @version 0.1
 */
public class InitSetUp extends GameState {
    private final List<PlayerClass> classList;

    /**
     * constructor for a game state
     * @param runasStrive game on which the game should be executed
     */
    public InitSetUp(RunasStrive runasStrive) {
        super(runasStrive);
        // more classes could be added here
        this.classList = new LinkedList<>() {{
                add(PlayerClass.WARRIOR);
                add(PlayerClass.MAGE);
                add(PlayerClass.PALADIN);
            }};
    }

    @Override
    public void executeState() {
        // choose class
        if (!chooseClass()) return;

        // set next state
        this.runasStrive.setState(new LevelSetUp(this.runasStrive));
    }

    private boolean chooseClass() {
        welcome();
        for (int index = 0; index < this.classList.size(); index++)
            System.out.printf("%d) %s%n", index + 1, this.classList.get(index).getDisplayName());
        int classPlayingIndex = this.getNumInput(this.classList.size(),
                NumInputRequest.ONE_INPUT_REQUEST.toString(this.classList.size()), ErrorMsg.CLASS, false);
        if (classPlayingIndex == -1) return false;
        // add initial abilities
        this.runasStrive.getPlayer().setPlayerClass(this.classList.get(classPlayingIndex));
        this.runasStrive.getPlayer().getAbilities().addAll(this.classList.get(classPlayingIndex).getCards());
        return true;
    }

    private void welcome() {
        System.out.println(CommonOutputs.WELCOME);
    }
}
