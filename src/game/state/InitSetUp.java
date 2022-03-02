package game.state;

import game.Game;
import game.gameParts.player.PlayerClass;
import game.state.output.NumInputRequest;

import java.util.*;

public class InitSetUp extends GameState{
    private final Map<Integer, PlayerClass> classMap;
    public InitSetUp(Game game) {
        super(game);
        // more classes could be added here
        this.classMap = new HashMap<>() {{
            put(1, PlayerClass.WARRIOR);
            put(2, PlayerClass.MAGE);
            put(3, PlayerClass.PALADIN);
        }};
    }

    @Override
    public void executeState() {
        // 2.1
        // TODO add cards
        this.game.getAbilityCards().addAll(List.of());

        // choose class
        chooseClass();
        // shuffle
        shuffleCards();

        // set next state
        System.out.println("leaving state");
        this.game.setState(new LevelSetUp(this.game));
    }

    private void chooseClass() {
        welcome();
        for (Map.Entry<Integer, PlayerClass> entry : this.classMap.entrySet())
            System.out.println(entry.getKey() + ") " + entry.getValue().getDisplayName());
        int classPlaying = this.validateNum(this.classMap.size(),
                NumInputRequest.DICE_INPUT_REQUEST.getOutput(this.classMap.size()));
        // add abilities
        this.classMap.get(classPlaying).getCards().forEach(card -> {
            this.game.getPlayer().addAbilityCard(card);
        });
        // remove cards
    }

    private void shuffleCards() {
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
                return;
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
    }

    private void welcome() {
        System.out.println("Welcome to Runa’s Strive");
        System.out.println("Select Runa’s character class");
    }
}
