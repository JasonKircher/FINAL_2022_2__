package game;

import game.gameParts.cards.abilities.Ability;
import game.gameParts.cards.monsters.MonsterCard;
import game.gameParts.player.Rauna;
import game.state.GameState;
import game.state.InitSetUp;

import java.util.LinkedList;
import java.util.List;

public class Game {
    private         GameState           state;
    private         int                 level;
    private final   Rauna               rauna;
    private final   List<Ability> abilities;
    private final   List<MonsterCard>   monsterCards;


    public Game() {
        this.level = 1;
        this.rauna = new Rauna();
        this.state = new InitSetUp(this);
        this.abilities = new LinkedList<>();
        this.monsterCards = new LinkedList<>();
    }

    public void start() {
        while(!end()) {
            this.state.executeState();
        }
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public void nextLevel() {
        this.level++;
    }

    private boolean end() {
        // TODO
        // win, lose, output if one of them otherwise nothing return false
        return false;
    }
}
