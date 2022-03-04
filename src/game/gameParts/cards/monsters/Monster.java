package game.gameParts.cards.monsters;

import game.gameParts.cards.abilities.Ability;
import game.gameParts.cards.monsters.firstLevel.MonsterType;

import java.util.List;

public abstract class Monster {
    protected   List<Ability>   preferredAbilities;
    protected   int             hp;
    protected   MonsterType     type;
    protected   boolean         boss;
    private     int             magicMitigation;
    private     int             physicalMitigation;

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
}
