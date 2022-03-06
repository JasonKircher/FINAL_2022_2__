package game.state;

import game.Game;
import game.gameParts.cards.abilities.magical.*;
import game.gameParts.cards.abilities.physical.playerAbilities.*;
import game.gameParts.player.parts.PlayerClass;
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

        // add all abilities in correct order
        int lvl = this.game.getLevel() + 1;
        this.game.getAbilityCards().addAll(List.of(new Slash(lvl), new Swing(lvl), new Thrust(lvl), new Pierce(lvl),
                new Parry(lvl), new Focus(lvl), new Reflect(lvl), new Water(lvl), new Ice(lvl), new Fire(lvl)
                // TODO add lightning
        ));
        // choose class
        if (!chooseClass()) return;

        // set next state
        this.game.setState(new LevelSetUp(this.game));
    }

    private boolean chooseClass() {
        welcome();

        for (int index = 0; index < this.classplayerClassesap.size(); index++)
            System.out.println(index + 1 + ") " + this.classplayerClassesap.get(index).getDisplayName());
        int classPlayingIndex = this.getNumInput(this.classplayerClassesap.size(),
                NumInputRequest.ONE_INPUT_REQUEST.getOutput(this.classplayerClassesap.size()), ErrorMsg.CLASS);
        if (classPlayingIndex == -1) return false;
        // add initial abilities
        this.classplayerClassesap.get(classPlayingIndex).getCards().forEach(card -> {
            this.game.getPlayer().addAbilityCard(card);
        });
        // remove initial abilities from collectable abilities
        this.game.getAbilityCards().removeIf(this.classplayerClassesap.get(classPlayingIndex).getCards()::contains);
        return true;
    }

    private void welcome() {
        System.out.println("Welcome to Runa’s Strive");
        System.out.println("Select Runa’s character class");
    }
}
