package game;

import game.gameParts.cards.monsters.IMonster;
import game.gameParts.cards.abilities.Ability;
import game.gameParts.player.Runa;
import game.state.GameState;
import game.state.InitSetUp;
import game.state.output.GameEndOutput;

import java.util.LinkedList;
import java.util.List;

/**
 * class that resembles a Game of Runas Strive
 * @author upvlx
 * @version 0.1
 */
public class RunasStrive {
    private         int                 level;
    private         int                 room;
    private         boolean             run;
    private         GameState           state;
    private final   Runa                runa;
    private final   List<Ability>       abilities;
    private final   List<IMonster>       monsterCards;

    /**
     * constructor for the Game, sets initial values
     */
    public RunasStrive() {
        this.run = true;
        this.level = 1;
        this.room = 0;
        this.runa = new Runa();
        this.state = new InitSetUp(this);
        this.abilities = new LinkedList<>();
        this.monsterCards = new LinkedList<>();
    }

    /**
     * starts the loop of the game
     */
    public void start() {
        while (run) {
            this.state.executeState();
        }
    }

    /**
     * getter for the ability cards in the Game
     * @return the ability cards which are not in players hands or removed from the game after looting
     */
    public List<Ability> getAbilityCards() {
        return this.abilities;
    }

    /**
     * getter for the Monster cards currently in the game
     * @return all monster cards that are currently in the game excluding monsters that are active
     */
    public List<IMonster> getMonsterCards() {
        return monsterCards;
    }

    /**
     * getter for the room number
     * @return the room number which is active
     */
    public int getRoom() {
        return this.room;
    }

    /**
     * getter for level
     * @return returns the current level
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * getter for the Player playing this game
     * @return  the active player
     */
    public Runa getPlayer() {
        return this.runa;
    }

    /**
     * function to change the state of the Game
     * @param state the wanted state on which the game should be continued
     */
    public void setState(GameState state) {
        this.state = state;
    }

    /**
     * sets next lvl, sets rooms back to 0
     */
    public void nextLevel() {
        this.room = 0;
        this.level++;
    }

    /**
     * sets next room
     */
    public void nextRoom() {
        this.room++;
    }

    /**
     * function to end a game with matching output
     */
    public void end() {
        this.run = false;
        if (runa.getHp() <= 0 ) {
            System.out.println(GameEndOutput.LOSE.getOutput());
        } else if (this.monsterCards.isEmpty() && this.level > 2) {
            System.out.println(GameEndOutput.WON.getOutput());
        }
    }
}
