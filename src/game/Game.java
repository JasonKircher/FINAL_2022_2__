package game;

import game.gameParts.cards.monsters.Monster;
import game.gameParts.cards.abilities.Ability;
import game.gameParts.player.Runa;
import game.state.GameState;
import game.state.InitSetUp;
import game.state.output.GameEndOutput;

import java.util.LinkedList;
import java.util.List;

public class Game {
    private         GameState           state;
    private         int                 level;
    private         int                 room;
    private         boolean             run;
    private final   Runa                runa;
    private final   List<Ability>       abilities;
    private final   List<Monster>       monsterCards;


    public Game() {
        this.run = true;
        this.level = 0;
        this.room = 0;
        this.runa = new Runa();
        this.state = new InitSetUp(this);
        this.abilities = new LinkedList<>();
        this.monsterCards = new LinkedList<>();
    }

    public void start() {
        while(run) {
            this.state.executeState();
        }
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public List<Ability> getAbilityCards() {
        return this.abilities;
    }

    public List<Monster> getMonsterCards() {
        return monsterCards;
    }


    public void nextLevel() {
        this.level++;
    }

    public void nextRoom() {
        this.room++;
    }

    public int getRoom() {
        return this.room;
    }

    public int getLevel() {
        return this.level;
    }

    public void end() {
        this.run = false;
        if (runa.getHp() <= 0 ) {
            System.out.println(GameEndOutput.LOSE.getOutput());
        } else if (monsterCards.isEmpty()) {
            System.out.println(GameEndOutput.WON.getOutput());
        }
    }

    public Runa getPlayer() {
        return runa;
    }
}
