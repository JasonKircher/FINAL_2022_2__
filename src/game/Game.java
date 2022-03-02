package game;

import game.gameParts.cards.abilities.AbilityOffensive;
import game.gameParts.cards.monsters.MonsterCard;
import game.gameParts.player.Runa;
import game.state.GameState;
import game.state.InitSetUp;
import game.state.output.GameEndOutput;

import java.util.LinkedList;
import java.util.List;

public class Game {
    private         GameState               state;
    private         int                     level;
    private         boolean                 run;
    private final   Runa                    runa;
    private final   List<AbilityOffensive>  abilities;
    private final   List<MonsterCard>       monsterCards;


    public Game() {
        this.run = true;
        this.level = 1;
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

    public void nextLevel() {
        this.level++;
    }

    public void end() {
        this.run = false;
        if (runa.getHp() <= 0 ) {
            System.out.println(GameEndOutput.LOSE.getOutput());
        } else if (monsterCards.isEmpty()) {
            System.out.println(GameEndOutput.WON.getOutput());
        }
    }
}
