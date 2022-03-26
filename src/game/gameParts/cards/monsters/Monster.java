package game.gameParts.cards.monsters;

import game.gameParts.cards.abilities.Ability;
import game.gameParts.cards.abilities.OffensiveAbility;
import game.state.output.CommonOutputs;
import game.state.output.Exceptions;

import java.util.List;

/**
 * class resembles a Monster in the Game this class contains all implemented methods for monsters, so the Monsters only
 * need to set their specific properties
 * @author upvlx
 * @version 0.1
 */
public abstract class Monster {
    /**
     * preferred abilities of the corresponding Monster
     */
    protected   List<Ability>   preferredAbilities;
    /**
     * hp of the corresponding Monster
     */
    protected   int             hp;
    /**
     * Type of the Monster
     */
    protected   MonsterType     type;
    /**
     * Name of the Monster
     */
    protected   String          name;
    private     int             magicMitigation;
    private     int             physicalMitigation;
    private     int             focusPoints;
    private     int             focusBuffer;

    /**
     * Constructor for a Monster this sets the initial values for a generic Monster
     */
    public Monster() {
        this.magicMitigation = 0;
        this.physicalMitigation = 0;
        this.focusPoints = 0;
        this.focusBuffer = 0;
    }

    /**
     * selects the next ability the Monster is about to do
     * @return the ability the Monster is about to cast
     */
    public Ability nextAbility() {
        Ability current = preferredAbilities.remove(0);
        preferredAbilities.add(current);
        return current;
    }

    /**
     * function for a Monster to take damage
     * @param damage the dmg the monster is supposed to take
     * @return true if the monster survived, false if the monster dies due to this attack
     */
    public boolean takeDamage(int damage) {
        int damageTmp = damage - this.magicMitigation;
        if (damageTmp <= 0) return true;
        this.hp -= damageTmp;
        System.out.printf("%s %s %d %s %s%n", this.name, CommonOutputs.TAKE, damageTmp, CommonOutputs.MAGICAL,
                CommonOutputs.DAMAGE);
        return this.hp > 0;
    }

    /**
     * function for a Monster to take damage
     * @param ability the ability that damages the Monster
     * @param value the dice roll or ability points
     * @return true if the monster survived, false if the monster dies due to this attack
     */
    public boolean takeDamage(Ability ability, int value) {
        if (!ability.isOffensive()) throw new RuntimeException(Exceptions.DMG_FROM_DEFENSIVE_ABILITY.getMsg());
        OffensiveAbility abilityParsed = (OffensiveAbility) ability;
        int damage = abilityParsed.calculateDamagePlayer(value,  this);
        if (abilityParsed.isPhysical()) damage = damage - this.physicalMitigation;
        else damage = damage - this.magicMitigation;
        if (damage > 0) this.hp -= damage;
        else damage = 0;
        String dmgType = ability.isPhysical() ? CommonOutputs.PHYSICAL.toString() : CommonOutputs.MAGICAL.toString();
        if (damage > 0)
            System.out.printf("%s %s %d %s %s%n", this.name, CommonOutputs.TAKE, damage, dmgType, CommonOutputs.DAMAGE);
        return this.hp > 0;
    }

    /**
     * getter for the focus points
     * @return the focus points atm
     */
    public int getFocusPoints() {
        return this.focusPoints;
    }

    /**
     * resets the focus buffer, no focus will be added after this
     */
    public void deBuff() {
        this.focusBuffer = 0;
    }

    /**
     * sets the focus buffer
     * @param increase the value the buffer is to be set to
     */
    public void focus(int increase) {
        this.focusBuffer = increase;
    }

    /**
     * function to decrease focus points
     */
    public void decreaseFocusPoints() {
        if (this.focusPoints > 0) {
            this.focusPoints--;
        }
    }

    /**
     * getter for the Monster Type
     * @return the type which the Monster inherits
     */
    public MonsterType getType() {
        return this.type;
    }

    /**
     * sets the magic mitigation for the monster
     * @param magicMitigation the value the mitigation is supposed to be set to
     */
    public void setMagicMitigation(int magicMitigation) {
        this.magicMitigation = magicMitigation;
    }

    /**
     * sets the physical mitigation for the monster
     * @param physicalMitigation the value the mitigation is supposed to be set to
     */
    public void setPhysicalMitigation(int physicalMitigation) {
        this.physicalMitigation = physicalMitigation;
    }

    /**
     * function to reset a monster, this adds focus, resets the mitigation
     */
    public void reset() {
        if (this.focusBuffer != 0) {
            this.focusPoints += this.focusBuffer;
            System.out.printf("%s %s %s %s%n", this.name, CommonOutputs.GAIN, this.focusBuffer, CommonOutputs.FOCUS);
            this.focusBuffer = 0;
        }
        this.magicMitigation = 0;
        this.physicalMitigation = 0;
    }

    /**
     * function to get all information from a monster
     * @return a string containing all information
     */
    public String extendedToString() {
        return String.format("%s (%d HP, %d FP): attempts %s next",
                this.name, this.hp, this.focusPoints, this.preferredAbilities.get(0));
    }

    @Override
    public String toString() {
        return this.name;
    }
}
