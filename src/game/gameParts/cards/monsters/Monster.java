package game.gameParts.cards.monsters;

import game.gameParts.cards.abilities.Ability;
import game.gameParts.cards.abilities.OffensiveAbility;
import game.gameParts.cards.monsters.firstLevel.MonsterType;
import game.state.output.Exceptions;

import java.util.List;

public abstract class Monster {
    protected   List<Ability>   preferredAbilities;
    protected   int             hp;
    protected   MonsterType     type;
    protected   String          name;
    private     int             magicMitigation;
    private     int             physicalMitigation;
    private     int             focusPoints;
    private     boolean         deBuffed;

    public Monster() {
        this.magicMitigation = 0;
        this.physicalMitigation = 0;
        this.focusPoints = 0;
        this.deBuffed = false;
    }

    public Ability nextAbility() {
        Ability current = preferredAbilities.remove(0);
        preferredAbilities.add(current);
        return current;
    }

    public boolean takeDamage(Ability ability, int value) {
        if (!ability.isOffensive()) throw new RuntimeException(Exceptions.DMG_FROM_DEFENSIVE_ABILITY.getMsg());
        OffensiveAbility abilityParsed = (OffensiveAbility) ability;
        int damage = abilityParsed.calculateDamage(value,  this);
        if (abilityParsed.isPhysical()) damage = damage - this.physicalMitigation;
        else damage = damage - this.magicMitigation;
        if (damage > 0) this.hp -= damage;
        return this.hp > 0;
    }

    public int getFocusPoints() {
        return this.focusPoints;
    }

    public void increaseFocusPoints() {
        this.focusPoints++;
    }

    public boolean isDeBuffed() {
        return this.deBuffed;
    }

    public boolean decreaseFocusPoints() {
        if (this.focusPoints > 0) {
            this.focusPoints--;
            return true;
        }
        return false;
    }

    public void setMagicMitigation(int magicMitigation) {
        this.magicMitigation = magicMitigation;
    }


    public void setPhysicalMitigation(int physicalMitigation) {
        this.physicalMitigation = physicalMitigation;
    }

    public void resetMitigation() {
        this.magicMitigation = 0;
        this.physicalMitigation = 0;
    }

    public void resetDeBuff() {
        this.deBuffed = false;
    }

    public String extendedToString() {
        return this.name + " (" + hp + " HP, " + this.focusPoints + " FP): attempts "
                + preferredAbilities.get(0) + " next";
    }
    @Override
    public String toString() {
        return this.name;
    }
}
