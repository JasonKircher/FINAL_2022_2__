package game.state;

import game.RunasStrive;
import game.gameParts.cards.abilities.magical.*;
import game.gameParts.cards.abilities.physical.playerAbilities.*;
import game.state.initiationValues.MonstersLevels;
import game.state.output.CommonOutputs;
import game.state.output.ErrorMsg;
import game.state.output.NumInputRequest;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * class that sets up a level this class is responsible for shuffling cards etc (2.2 on the ex sheet)
 * @author upvlx
 * @version 0.1
 */
public class LevelSetUp extends GameState {
    /**
     * constructor for a game state
     * @param runasStrive game on which the game should be executed
     */
    public LevelSetUp(RunasStrive runasStrive) {
        super(runasStrive);
    }

    @Override
    public void executeState() {
        // add all abilities in correct order
        int lvl = this.runasStrive.getLevel();
        this.runasStrive.getAbilityCards().addAll(List.of(new Slash(lvl), new Swing(lvl), new Thrust(lvl),
                new Pierce(lvl), new Parry(lvl), new Focus(lvl), new Reflect(lvl), new Water(lvl), new Ice(lvl),
                new Fire(lvl), new Lightning(lvl)
        ));
        // remove initial abilities from collectable abilities
        this.runasStrive.getAbilityCards().removeIf(this.runasStrive.getPlayer().getPlayerClass().getCards()::contains);

        // add correct monsters
        if (this.runasStrive.getLevel() == 1) setUpFirstLevel();
        else setUpSecondLevel();

        // shuffle
        if (!shuffleCards()) return;

        // set next state
        this.runasStrive.setState(new Fight(this.runasStrive));
    }

    private void setUpFirstLevel() {
        this.runasStrive.getMonsterCards().addAll(MonstersLevels.FIRST.getMonsters());
    }

    private void setUpSecondLevel() {
        this.runasStrive.getMonsterCards().addAll(MonstersLevels.SECOND.getMonsters());
    }

    /**
     * function to shuffle cards
     * @return true if shuffling worked, false if shuffling was quit
     */
    private boolean shuffleCards() {
        System.out.println(CommonOutputs.SHUFFLE_CARDS);
        List<Integer> seeds = getMultipleInputs(Integer.MAX_VALUE, 2,  2,
                NumInputRequest.SEED_INPUT_REQUEST.toString(), ErrorMsg.SEED, true);
        if (seeds == null) {
            gameEnd();
            return false;
        }
        // correct input (+1 because of index correction)
        Random randomAbility = new Random(seeds.remove(0) + 1);
        Random randomMonster = new Random(seeds.remove(0) + 1);
        // shuffle cards
        Collections.shuffle(this.runasStrive.getAbilityCards(), randomAbility);
        Collections.shuffle(this.runasStrive.getMonsterCards(), randomMonster);
        return true;
    }
}
