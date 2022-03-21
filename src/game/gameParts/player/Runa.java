package game.gameParts.player;

import game.gameParts.cards.abilities.Ability;
import game.gameParts.cards.abilities.OffensiveAbility;
import game.gameParts.player.parts.Dice;
import game.gameParts.player.parts.PlayerClass;
import game.state.output.CommonOutputs;
import game.state.output.Exceptions;

import java.util.LinkedList;
import java.util.List;

/**
 * class that resembles the player
 * @author upvlx
 * @version 0.1
 */
public class Runa {
    private         int                 hp;
    private         int                 focusPoints;
    private         int                 magicMitigation;
    private         int                 physicalMitigation;
    private         int                 focusBuffer;
    private         int                 reflectedDmg;
    private         boolean             reflecting;
    private         Dice                currentDice;
    private         PlayerClass         playerClass;
    private final   List<Ability>       abilities;

    /**
     * constructor which sets the starting values
     */
    public Runa() {
        this.abilities = new LinkedList<>();
        this.hp = PlayerStartingValues.STARTING_HP.getValue();
        this.magicMitigation = 0;
        this.physicalMitigation = 0;
        this.focusBuffer = 0;
        this.reflectedDmg = 0;
        this.reflecting = false;
        this.focusPoints = PlayerStartingValues.STARTING_FOCUS_POINTS.getValue();
        this.currentDice = Dice.D4;
    }

    /**
     * getter for the current paler dice
     * @return the dice that is currently wielded by the player
     */
    public Dice getCurrentDice() {
        return this.currentDice;
    }

    /**
     * getter for all abilities a Player has
     * @return the abilities of this player
     */
    public List<Ability> getAbilities() {
        return this.abilities;
    }

    /**
     * getter for the PlayerClass
     * @return the class of this player
     */
    public PlayerClass getPlayerClass() {
        return this.playerClass;
    }

    /**
     * getter for the focus points
     * @return the current amount of focus points
     */
    public int getFocusPoints() {
        return this.focusPoints;
    }

    /**
     * getter for the Hp of this player
     * @return the current amount of hp of this player
     */
    public int getHp() {
        return this.hp;
    }

    /**
     * getter for the dmg that is to be reflected
     * @return the dmg that is to be reflected
     */
    public int getReflectedDmg() {
        return this.reflectedDmg;
    }

    /**
     * getter for the reflecting flag
     * @return true if the player is reflecting, false if not
     */
    public boolean isReflecting() {
        return this.reflecting;
    }

    /**
     * function to add a ability card
     * @param card card that is to be added
     */
    public void addAbilityCard(Ability card) {
        System.out.printf("%s %s %s%n", CommonOutputs.PLAYER, CommonOutputs.GET, card);
        abilities.add(card);
    }

    /**
     * function to upgrade the current player dice
     */
    public void upgradeDice() {
        this.currentDice = this.currentDice.upgradeDice();
    }

    /**
     * function to heal the player
     * @param hp the amount the player is to be healed
     */
    public void heal(int hp) {
        int tmpHealing = hp;
        if (this.hp + tmpHealing > PlayerStartingValues.STARTING_HP.getValue()) {
            tmpHealing = PlayerStartingValues.STARTING_HP.getValue() - this.hp;
            this.hp = PlayerStartingValues.STARTING_HP.getValue();
        }
        else this.hp += hp;
        if (tmpHealing > 0) System.out.printf("%s %s %s health%n", CommonOutputs.PLAYER, CommonOutputs.GAIN,
                tmpHealing);
    }

    /**
     * setter for the physical mitigation
     * @param physicalMitigation the value the physical mitigation is to be set to
     */
    public void setPhysicalMitigation(int physicalMitigation) {
        this.physicalMitigation = physicalMitigation;
    }

    /**
     * function to add focus to the buffer
     * @param increase the value that is to be set to
     */
    public void focus(int increase) {
        this.focusBuffer = increase;
    }


    /**
     * function to decrease the focus points
     */
    public void decreaseFocusPoints() {
        if (this.focusPoints > 1) {
            this.focusPoints--;
        }
    }

    /**
     * function to reset the dmg that is to be reflected
     */
    public void resetReflect() {
        this.reflectedDmg = 0;
    }

    /**
     * function to set the class of the Player
     * @param playerClass the class the player is supposed to have
     */
    public void setPlayerClass(PlayerClass playerClass) {
        this.playerClass = playerClass;
    }

    /**
     * function to set the reflecting flag
     */
    public void setReflecting() {
        this.reflecting = true;
    }

    /**
     * function to set the magic mitigation
     * @param magicMitigation the value the mitigation is to be set to
     */
    public void setMagicMitigation(int magicMitigation) {
        this.magicMitigation = magicMitigation;
    }

    /**
     * function to reset the focus buffer -> no focus will be added after this
     */
    public void deBuff() {
        this.focusBuffer = 0;
    }

    /**
     * function to reset a player, this adds focus, resets the mitigation and resets the reflecting flag
     */
    public void reset() {
        if (this.focusBuffer != 0) {
            if (this.focusPoints <= this.currentDice.getMaxValue()) {
                if (this.focusBuffer + this.focusPoints > this.currentDice.getMaxValue()) {
                    this.focusBuffer = this.currentDice.getMaxValue() - this.focusPoints;
                }
                this.focusPoints += this.focusBuffer;
            }
            System.out.printf("%s %s %d %s%n", CommonOutputs.PLAYER, CommonOutputs.GAIN, this.focusBuffer,
                    CommonOutputs.FOCUS);
            this.focusBuffer = 0;
        }
        this.reflecting = false;
        this.reflectedDmg = 0;
        this.magicMitigation = 0;
        this.physicalMitigation = 0;
    }

    /**
     * function to take damage from an ability
     * @param ability ability that is damaging
     * @return true if runa survives
     */
    public boolean takeDamage(Ability ability) {
        if (!ability.isOffensive()) throw new RuntimeException(Exceptions.DMG_FROM_DEFENSIVE_ABILITY.getMsg());
        OffensiveAbility abilityParsed = (OffensiveAbility) ability;
        int damage = abilityParsed.calculateDamageMonster(this);
        if (abilityParsed.isPhysical()) damage = damage - this.physicalMitigation;
        else {
            damage = damage - this.magicMitigation;
            this.reflectedDmg = damage >= 0 ? this.magicMitigation : this.magicMitigation + damage;
        }
        if (damage > 0) this.hp -= damage;
        else damage = 0;
        String dmgType = ability.isPhysical() ? CommonOutputs.PHYSICAL.toString() : CommonOutputs.MAGICAL.toString();
        if (damage > 0) System.out.printf("%s takes %s %s damage%n", CommonOutputs.PLAYER, damage, dmgType);
        return this.hp > 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(CommonOutputs.PLAYER.toString());
        String hp = String.format("%d/%d HP", this.hp, PlayerStartingValues.STARTING_HP.getValue());
        String focus = String.format("%d/%d FP", this.focusPoints, this.currentDice.getMaxValue());
        String info = String.format(" (%s, %s)", hp, focus);
        builder.append(info);
        return builder.toString();
    }
}
