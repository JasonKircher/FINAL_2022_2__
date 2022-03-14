package game.gameParts.cards.abilities;

import game.gameParts.cards.abilities.magical.Focus;
import game.gameParts.cards.abilities.magical.Reflect;
import game.gameParts.cards.abilities.magical.Water;
import game.gameParts.cards.abilities.physical.playerAbilities.Parry;
import game.gameParts.cards.abilities.physical.playerAbilities.Slash;
import game.gameParts.cards.abilities.physical.playerAbilities.Thrust;

/**
 * class that resembles an ability
 * @author upvlx
 * @version 0.1
 */
public abstract class Ability {
    /**
     * String that resembles that name of an ability
     */
    protected String name;
    /**
     * level of the ability
     */
    protected int abilityLevel;
    /**
     * boolean weather or not the ability is offensive
     */
    protected boolean offensive;
    /**
     * boolean weather or not the ability is physical
     */
    protected boolean physical;

    /**
     * constructor for an ability
     * @param abilityLevel  level the ability is supposed to have
     */
    public Ability(int abilityLevel) {
        this.abilityLevel = abilityLevel;
    }

    /**
     * getter for offensive
     * @return the boolean value of offensive
     */
    public boolean isOffensive() {
        return this.offensive;
    }

    /**
     * getter for physical
     * @return the boolean value of physical
     */
    public boolean isPhysical() {
        return this.physical;
    }

    /**
     * getter for the ability level
     * @return the ability level
     */
    public int getAbilityLevel() {
        return this.abilityLevel;
    }

    /**
     * upgrades an ability to the next level
     */
    public void upgrade() {
        this.abilityLevel++;
    }

    /**
     * function to copy the default abilities
     * @return a copy of the ability
     */
    public Ability copy() {
        if (Focus.class.equals(this.getClass())) return new Focus(this.abilityLevel);
        else if (Water.class.equals(this.getClass())) return new Water(this.abilityLevel);
        else if (Thrust.class.equals(this.getClass())) return new Thrust(this.abilityLevel);
        else if (Parry.class.equals(this.getClass())) return new Parry(this.abilityLevel);
        else if (Slash.class.equals(this.getClass())) return new Slash(this.abilityLevel);
        else if (Reflect.class.equals(this.getClass())) return new Reflect(this.abilityLevel);
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() != this.getClass()) return false;
        return this.name.equals(((Ability) o).name);
    }

    @Override
    public String toString() {
        return this.name + "(" + this.abilityLevel + ")";
    }
}
