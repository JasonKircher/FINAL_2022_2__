package game.gameParts.cards.abilities;

import game.gameParts.cards.abilities.magical.Focus;
import game.gameParts.cards.abilities.magical.Reflect;
import game.gameParts.cards.abilities.magical.Water;
import game.gameParts.cards.abilities.physical.monsterAbilities.Bite;
import game.gameParts.cards.abilities.physical.playerAbilities.Parry;
import game.gameParts.cards.abilities.physical.playerAbilities.Slash;
import game.gameParts.cards.abilities.physical.playerAbilities.Thrust;

public abstract class Ability {
    protected String name;
    protected int abilityLevel;
    protected boolean offensive;
    protected boolean physical;

    public Ability(int abilityLevel) {
        this.abilityLevel = abilityLevel;
    }


    public boolean isOffensive() {
        return this.offensive;
    }
    public boolean isPhysical() {
        return this.physical;
    }
    public void upgrade() {
        this.abilityLevel++;
    }

    public int getAbilityLevel() {
        return abilityLevel;
    }

    public Ability copy() {
        if (Focus.class.equals(this.getClass())) {
            return new Focus(this.abilityLevel);
        } else if (Water.class.equals(this.getClass())) {
            return new Water(this.abilityLevel);
        } else if (Thrust.class.equals(this.getClass())) {
            return new Thrust(this.abilityLevel);
        } else if (Parry.class.equals(this.getClass())) {
            return new Parry(this.abilityLevel);
        } else if (Slash.class.equals(this.getClass())) {
            return new Slash(this.abilityLevel);
        } else if (Reflect.class.equals(this.getClass())) {
            return new Reflect(this.abilityLevel);
        }
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
