package game.state;

import game.Game;
import game.gameParts.cards.abilities.magical.*;
import game.gameParts.cards.abilities.physical.playerAbilities.*;
import game.state.initiationValues.MonstersLevels;
import game.state.output.ErrorMsg;
import game.state.output.NumInputRequest;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

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
        Scanner scanner = new Scanner(System.in);
        System.out.println("To shuffle ability cards and monsters, enter two seeds");
        int one = -1;
        int two = -1;
        int ittr = 0;
        while(!(one >= 1) || !(two >= 1)) {
            if (ittr > 0) System.out.println(ErrorMsg.SEED.getMsg());
            ittr++;
            System.out.println(NumInputRequest.SEED_INPUT_REQUEST.getOutput(0));
            String input = scanner.nextLine();
            if (input.equals("quit")) {
                this.gameEnd();
                return false;
            }
            String[] split = input.split(",");
            if (split.length != 2) {
                one = -1;
                two = -1;
                continue;
            }
            try {
                one = Integer.parseInt(split[0]);
                two = Integer.parseInt(split[1]);
            } catch (NumberFormatException ignored) {
                one = -1;
                two = -1;
            }
        }
        // shuffle cards
        Random random = new Random(one);
        Collections.shuffle(this.game.getAbilityCards(), random);
        random = new Random(two);
        Collections.shuffle(this.game.getMonsterCards(), random);
        return true;
    }
}
