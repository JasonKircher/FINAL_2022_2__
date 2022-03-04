package game.state;

import game.Game;
import game.gameParts.player.PlayerClass;
import game.state.output.ErrorMsg;
import game.state.output.NumInputRequest;

import java.util.*;

public class InitSetUp extends GameState{
    private final List<PlayerClass> classplayerClassesap;
    public InitSetUp(Game game) {
        super(game);
        // more classes could be added here
        this.classplayerClassesap = new LinkedList<>() {{
            add(PlayerClass.WARRIOR);
            add(PlayerClass.MAGE);
            add(PlayerClass.PALADIN);
        }};
    }

    @Override
    public void executeState() {
        // 2.1
        // TODO add all abilities in correct order
        this.game.getAbilityCards().addAll(List.of());

        // choose class
        if (!chooseClass()) return;

        // set next state
        this.game.setState(new LevelSetUp(this.game));
    }

    private boolean chooseClass() {
        welcome();

        for (int index = 0; index < this.classplayerClassesap.size(); index++)
            System.out.println(index + 1 + ") " + this.classplayerClassesap.get(index));
        int classPlaying = this.validateNum(this.classplayerClassesap.size(),
                NumInputRequest.ONE_INPUT_REQUEST.getOutput(this.classplayerClassesap.size()), ErrorMsg.CLASS) - 1;
        System.out.println("chose " + this.classplayerClassesap.get(classPlaying));
        // add abilities
        this.classplayerClassesap.get(classPlaying).getCards().forEach(card -> {
            this.game.getPlayer().addAbilityCard(card);
        });
        // remove cards
        this.game.getAbilityCards().removeIf(this.classplayerClassesap.get(classPlaying).getCards()::contains);
        return true;
    }



    private void welcome() {
        System.out.println("Welcome to Runa’s Strive");
        System.out.println("Select Runa’s character class");
    }
}
