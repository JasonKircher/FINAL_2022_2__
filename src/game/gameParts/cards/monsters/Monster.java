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
    protected   boolean         boss;
    private     int             magicMitigation;
    private     int             physicalMitigation;
    private     int             focusPoints;

    public Monster() {
        this.magicMitigation = 0;
        this.physicalMitigation = 0;
    }

    public Ability nextAbility() {
        Ability current = preferredAbilities.remove(0);
        preferredAbilities.add(current);
        return current;
    }

    public boolean takeDamage(Ability ability) {
        if (!ability.isOffensive()) throw new RuntimeException(Exceptions.DMG_FROM_DEFENSIVE_ABILITY.getMsg());
        OffensiveAbility abilityParsed = (OffensiveAbility) ability;
        int damage = abilityParsed.calculateDamage(0,  this);
        if (abilityParsed.isPhysical()) damage = damage - this.physicalMitigation;
        else damage = damage - this.magicMitigation;
        if (damage > 0) this.hp -= damage;
        return this.hp > 0;
    }

    public void setMagicMitigation(int magicMitigation) {
        this.magicMitigation = magicMitigation;
    }

    public void resetMagicMitigation() {
        this.magicMitigation = 0;
    }

    public void setPhysicalMitigation(int physicalMitigation) {
        this.physicalMitigation = physicalMitigation;
    }

    public void resetPhysicalMitigation() {
        this.physicalMitigation = 0;
    }

    public String extendedToString() {
        String[] split = (""+this.getClass()).split("\\.");
        String name = split[split.length - 1];
        return name + " (" + hp + " HP, " + this.focusPoints + " FP): attempts " + preferredAbilities.get(0) + " next";
    }
    @Override
    public String toString() {
        String[] split = (""+this.getClass()).split("\\.");
        return split[split.length - 1];
    }
}