package game.state;

import game.Game;
import game.gameParts.cards.abilities.magical.*;
import game.gameParts.cards.abilities.physical.playerAbilities.*;
import game.state.initiationValues.MonstersLevels;
import game.state.output.CommonOutputs;
import game.state.output.ErrorMsg;
import game.state.output.NumInputRequest;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LevelSetUp extends GameState {
    public LevelSetUp(Game game) {
        super(game);
    }

    @Override
    public void executeState() {
        // 2.2

        // add all abilities in correct order
        int lvl = this.game.getLevel();
        this.game.getAbilityCards().addAll(List.of(new Slash(lvl), new Swing(lvl), new Thrust(lvl), new Pierce(lvl),
                new Parry(lvl), new Focus(lvl), new Reflect(lvl), new Water(lvl), new Ice(lvl), new Fire(lvl),
                new Lightning(lvl)
        ));
        // remove initial abilities from collectable abilities
        this.game.getAbilityCards().removeIf(this.game.getPlayer().getPlayerClass().getCards()::contains);

        if (this.game.getLevel() == 1) setUpFirstLevel();
        else setUpSecondLevel();

        // shuffle
        if (!shuffleCards()) return;

        // set next state
        this.game.setState(new Fight(this.game));
    }

    private void setUpFirstLevel() {
        this.game.getMonsterCards().addAll(MonstersLevels.FIRST.getMonsters());
    }

    private void setUpSecondLevel() {
        this.game.getMonsterCards().addAll(MonstersLevels.SECOND.getMonsters());
    }

    private boolean shuffleCards() {
        System.out.println(CommonOutputs.SHUFFLE_CARDS.getOut());
        List<Integer> seeds = getMultipleInputs(Integer.MAX_VALUE, 2,
                NumInputRequest.SEED_INPUT_REQUEST.getOutput(), ErrorMsg.SEED, true, true);
        // correct input (+1 because of index correction)
        Random randomAbility = new Random(seeds.remove(0) + 1);
        Random randomMonster = new Random(seeds.remove(0) + 1);
        // shuffle cards
        Collections.shuffle(this.game.getAbilityCards(), randomAbility);
        Collections.shuffle(this.game.getMonsterCards(), randomMonster);
        return true;
    }
}
