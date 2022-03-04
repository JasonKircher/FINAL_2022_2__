package game.state;

import game.Game;
import game.gameParts.player.PlayerClass;
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
        // TODO add cards
        this.game.getAbilityCards().addAll(List.of());

        // choose class
        if (!chooseClass()) return;
        // shuffle
        if (!shuffleCards()) return;

        // set next state
        System.out.println("leaving state");
        this.game.setState(new LevelSetUp(this.game));
    }

    private boolean chooseClass() {
        welcome();
        for (int index = 0; index < this.classplayerClassesap.size(); index++)
            System.out.println(index + ") " + this.classplayerClassesap.get(index));
        int classPlaying = this.validateNum(this.classplayerClassesap.size(),
                NumInputRequest.DICE_INPUT_REQUEST.getOutput(this.classplayerClassesap.size()));
        if (this.classplayerClassesap.get(classPlaying) == null) return false;
        // add abilities
        this.classplayerClassesap.get(classPlaying).getCards().forEach(card -> {
            this.game.getPlayer().addAbilityCard(card);
        });
        // TODO remove cards
        return true;
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
        scanner.close();
        // TODO shuffle cards
        Random random = new Random(one);
        Collections.shuffle(this.game.getAbilityCards(), random);
        random = new Random(two);
        Collections.shuffle(this.game.getMonsterCards(), random);
        return true;
    }

    private void welcome() {
        System.out.println("Welcome to Runa’s Strive");
        System.out.println("Select Runa’s character class");
    }
}
