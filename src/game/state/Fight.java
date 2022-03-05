package game.state;

import game.Game;
import game.gameParts.cards.monsters.Monster;
import game.gameParts.player.PlayerStartingValues;
import game.state.initiationValues.MonstersLevels;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Fight extends GameState {
    List<Monster> active;
    public Fight(Game game) {
        super(game);
        active = new LinkedList<>();
    }

    @Override
    public void executeState() {
        // 2.3
        this.game.nextRoom();

        selectMonsters();
        welcomeText();

        if (!fight()) return;

        // set next state
        this.game.setState(new FightAftermath(this.game));
    }

    private void selectMonsters() {
        Monster boss = this.game.getLevel() == 1 ? MonstersLevels.FIRST.getBoss() : MonstersLevels.SECOND.getBoss();
        if (this.game.getRoom() == 1) this.active.add(this.game.getMonsterCards().remove(0));
        else if (this.game.getRoom() == 4) this.active.add(boss);
        else {
            this.active.add(this.game.getMonsterCards().remove(0));
            this.active.add(this.game.getMonsterCards().remove(0));
        }
    }

    private boolean fight() {
        printInfo();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.equals("quit")) {
            this.gameEnd();
            return false;
        }
        return true;
    }

    private void printInfo() {
        System.out.println("----------------------------------------");
        System.out.println("Runa (" + this.game.getPlayer().getHp() + "/" + PlayerStartingValues.STARTING_HP.getValue()
                + ", " + this.game.getPlayer().getFocusPoints() + "/"
                + PlayerStartingValues.MAX_FOCUS_POINTS.getValue() + " FP)");
        System.out.println("vs.");

        // todo extended print function
        this.active.forEach(monster -> {
            System.out.println(monster.extendedToString());
        });
        System.out.println("----------------------------------------");
    }

    private void welcomeText() {
        System.out.println("Runa enters Stage " + this.game.getRoom() + " of Level " + this.game.getLevel());
    }
}
