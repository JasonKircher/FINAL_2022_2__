package game.state;

import game.Game;
import game.state.initiationValues.MonstersLevels;
import game.state.output.NumInputRequest;

import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class LevelSetUp extends GameState {
    public LevelSetUp(Game game) {
        super(game);
    }

    @Override
    public void executeState() {
        // 2.2
        // mix cards
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
        this.game.getMonsterCards().removeAll(MonstersLevels.FIRST.getMonsters());
        this.game.getMonsterCards().addAll(MonstersLevels.SECOND.getMonsters());
    }

    private boolean shuffleCards() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("To shuffle ability cards and monsters, enter two seeds");
        String[] split = NumInputRequest.SEED_INPUT_REQUEST.getOutput(0).split("--");
        split = split[1].split(" ");
        int max = Integer.parseInt(split[0].replace("]", ""));
        int one = Integer.MAX_VALUE;
        int two = Integer.MAX_VALUE;
        while(!(one > 1 && one < max) || !(two > 1 && two < max)) {
            System.out.println(NumInputRequest.SEED_INPUT_REQUEST.getOutput(0));
            String input = scanner.nextLine();
            if (input.equals("quit")) {
                this.gameEnd();
                return false;
            }
            split = input.split(",");
            if (split.length != 2) continue;
            try {
                one = Integer.parseInt(split[0]);
                two = Integer.parseInt(split[1]);
            } catch (NumberFormatException ignored) {
                // cannot be parsed
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
