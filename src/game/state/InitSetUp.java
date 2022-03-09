package game.state;

import game.Game;
import game.gameParts.player.parts.PlayerClass;
import game.state.output.ErrorMsg;
import game.state.output.NumInputRequest;

import java.util.*;

public class InitSetUp extends GameState{
    private final List<PlayerClass> classList;
    public InitSetUp(Game game) {
        super(game);
        // more classes could be added here
        this.classList = new LinkedList<>() {{
            add(PlayerClass.WARRIOR);
            add(PlayerClass.MAGE);
            add(PlayerClass.PALADIN);
        }};
    }

    @Override
    public void executeState() {
        // 2.1

        // choose class
        if (!chooseClass()) return;

        // set next state
        this.game.setState(new LevelSetUp(this.game));
    }

    private boolean chooseClass() {
        welcome();

        for (int index = 0; index < this.classList.size(); index++)
            System.out.println(index + 1 + ") " + this.classList.get(index).getDisplayName());
        int classPlayingIndex = this.getNumInput(this.classList.size(),
                NumInputRequest.ONE_INPUT_REQUEST.getOutput(this.classList.size()), ErrorMsg.CLASS);
        if (classPlayingIndex == -1) return false;
        // add initial abilities
        this.game.getPlayer().setPlayerClass(this.classList.get(classPlayingIndex));
        this.game.getPlayer().getAbilities().addAll(this.classList.get(classPlayingIndex).getCards());
        return true;
    }

    private void welcome() {
        System.out.println("Welcome to Runa’s Strive");
        System.out.println("Select Runa’s character class");
    }
}
