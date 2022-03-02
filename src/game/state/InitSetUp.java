package game.state;

import game.Game;
import game.gameParts.player.ClassMap;
import game.state.output.NumInputRequest;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InitSetUp extends GameState{
    private final Map<Integer, String> classMap;
    public InitSetUp(Game game) {
        super(game);
        this.classMap = new HashMap<>() {{
            put(1, "Warrior");
            put(2, "Mage");
            put(3, "Paladin");
        }};
    }

    @Override
    public void executeState() {
        // 2.1
        // etc

        // add cards
        this.game.getAbilityCards().addAll(List.of());

        // choose class
        chooseClassOutput();


        // set next state
        this.game.setState(new LevelSetUp(this.game));
    }

    private void chooseClassOutput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Runa’s Strive");
        System.out.println("Select Runa’s character class");
        for (Map.Entry<Integer, String> entry : this.classMap.entrySet())
            System.out.println(entry.getKey() + ") " + entry.getValue());
        int classPlaying = Integer.MAX_VALUE;
        while(this.classMap.get(classPlaying) == null) {
            System.out.println(NumInputRequest.ONE_INPUT_REQUEST.getOutput(this.classMap.size()));
            String input = scanner.nextLine();
            if (input.equals("quit")) {
                this.gameEnd();
                return;
            }
            try {
                classPlaying = Integer.parseInt(input);
            } catch (NumberFormatException ignored) {
                // can't parse the int
            }
        }
        System.out.println("chosen " + classMap.get(classPlaying));
    }
}
