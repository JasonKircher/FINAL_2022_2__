package game.gameParts.player;

import game.gameParts.cards.abilities.Ability;
import game.gameParts.cards.abilities.OffensiveAbility;
import game.gameParts.player.parts.Dice;
import game.gameParts.player.parts.PlayerClass;
import game.state.output.Exceptions;

import java.util.LinkedList;
import java.util.List;

public class Runa {
    private         int                 hp;
    private         int                 focusPoints;
    private         int                 magicMitigation;
    private         int                 physicalMitigation;
    private         int                 focusBuffer;
    private         Dice                currentDice;
    private final   List<Ability>       abilities;
    private         PlayerClass         playerClass;

    public Runa() {
        this.abilities = new LinkedList<>();
        this.hp = PlayerStartingValues.STARTING_HP.getValue();
        this.magicMitigation = 0;
        this.physicalMitigation = 0;
        this.focusBuffer = 0;
        this.focusPoints = PlayerStartingValues.STARTING_FOCUS_POINTS.getValue();
        this.currentDice = Dice.D4;
    }

    public boolean takeDamage(int damage) {
        this.hp -= damage;
        return hp >= 0;
    }

    public void addAbilityCard(Ability card) {
        abilities.add(card);
    }
    public List<Ability> getAbilities() {
        return this.abilities;
    }
    public void upgradeDice() {
        this.currentDice = this.currentDice.upgradeDice();
    }
    public Dice getCurrentDice() {
        return this.currentDice;
    }

    public void focus(int increase) {
        this.focusBuffer = increase;
    }
    public boolean decreaseFocusPoints() {
        if (this.focusPoints > 0) {
            this.focusPoints--;
            return true;
        }
        return false;
    }

    public void setPlayerClass(PlayerClass playerClass) {
        this.playerClass = playerClass;
    }

    public PlayerClass getPlayerClass() {
        return this.playerClass;
    }

    public void deBuff() {
        this.focusBuffer = 0;
    }

    public int getFocusPoints() {
        return this.focusPoints;
    }

    public void setMagicMitigation(int magicMitigation) {
        this.magicMitigation = magicMitigation;
    }

    public void reset() {
        if (this.focusBuffer != 0) {
            if (this.focusPoints <= this.currentDice.getMaxValue()) {
                if (this.focusBuffer + this.focusPoints > this.currentDice.getMaxValue()) {
                    this.focusBuffer = this.currentDice.getMaxValue() - this.focusPoints;
                }
                this.focusPoints += this.focusBuffer;
            }
            System.out.println("Runa gains " + this.focusBuffer + " focus");
            this.focusBuffer = 0;
        }
        this.magicMitigation = 0;
        this.physicalMitigation = 0;
    }

    public void heal(int hp) {
        if (this.hp + hp > PlayerStartingValues.STARTING_HP.getValue())
            this.hp = PlayerStartingValues.STARTING_HP.getValue();
        else this.hp += hp;
    }

    public void setPhysicalMitigation(int physicalMitigation) {
        this.physicalMitigation = physicalMitigation;
    }

    public boolean takeDamage(Ability ability) {
        if (!ability.isOffensive()) throw new RuntimeException(Exceptions.DMG_FROM_DEFENSIVE_ABILITY.getMsg());
        OffensiveAbility abilityParsed = (OffensiveAbility) ability;
        int damage = abilityParsed.calculateDamage(0,  this);
        if (abilityParsed.isPhysical()) damage = damage - this.physicalMitigation;
        else damage = damage - this.magicMitigation;
        if (damage > 0) this.hp -= damage;
        System.out.println("Runa takes " + damage + " damage");
        return this.hp > 0;
    }

    public int getHp() {
        return this.hp;
    }
}
