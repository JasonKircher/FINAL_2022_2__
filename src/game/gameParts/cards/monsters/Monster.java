package game.gameParts.cards.monsters;

import game.gameParts.cards.abilities.Ability;
import game.gameParts.cards.abilities.OffensiveAbility;
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
    private     int             focusBuffer;

    public Monster() {
        this.magicMitigation = 0;
        this.physicalMitigation = 0;
        this.focusPoints = 0;
        this.focusBuffer = 0;
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
        else damage = 0;
        String dmgType = ability.isPhysical() ? "phy." : "mag.";
        System.out.printf("%s takes %s %s damage%n", this.name, damage, dmgType);
        return this.hp > 0;
    }

    public void deBuff() {
        this.focusBuffer = 0;
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

    public MonsterType getType() {
        return this.type;
    }

    public void setMagicMitigation(int magicMitigation) {
        this.magicMitigation = magicMitigation;
    }


    public void setPhysicalMitigation(int physicalMitigation) {
        this.physicalMitigation = physicalMitigation;
    }

    public void reset() {
        if (this.focusBuffer != 0) {
            this.focusPoints += this.focusBuffer;
            System.out.println(this.name + " gains " + this.focusBuffer + " focus");
            this.focusBuffer = 0;
        }
        this.magicMitigation = 0;
        this.physicalMitigation = 0;
    }

    public String extendedToString() {
        return String.format("%s (%d HP, %d FP): attempts %s next",
                this.name, this.hp, this.focusPoints, this.preferredAbilities.get(0));
    }

    @Override
    public String toString() {
        return this.name;
    }
}
