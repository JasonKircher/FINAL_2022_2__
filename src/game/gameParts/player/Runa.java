package game.gameParts.player;

import game.gameParts.cards.abilities.Ability;
import game.gameParts.cards.abilities.OffensiveAbility;
import game.state.output.Exceptions;

import java.util.LinkedList;
import java.util.List;

public class Runa {
    private         int                 hp;
    private         int                 abilityLevel;
    private         int                 focusPoints;
    private         int                 magicMitigation;
    private         int                 physicalMitigation;
    private         boolean             deBuffed;
    private final   List<Ability>       abilities;

    public Runa() {
        this.abilities = new LinkedList<>();
        this.hp = PlayerStartingValues.STARTING_HP.getValue();
        this.abilityLevel = PlayerStartingValues.STARTING_LEVEL.getValue();
        this.magicMitigation = 0;
        this.physicalMitigation = 0;
        this.deBuffed = false;
        this.focusPoints = 1;
    }

    public boolean takeDamage(int damage) {
        this.hp -= damage;
        return hp >= 0;
    }

    public void increaseLevel() {
        this.abilityLevel++;
    }

    public void deBuff() {
        this.deBuffed = true;
    }

    public void cleanseDeBuffs() {
        this.deBuffed = false;
    }

    public boolean isDeBuffed() {
        return deBuffed;
    }

    public int getAbilityLevel() {
        return this.abilityLevel;
    }
    public void addAbilityCard(Ability card) {
        abilities.add(card);
    }
    public List<Ability> getAbilities() {
        return this.abilities;
    }

    public void setFocusPoints(int focusPoints) {
        this.focusPoints = focusPoints;
    }

    public int getFocusPoints() {
        return this.focusPoints;
    }
    public void resetMagicMitigation() {
        this.magicMitigation = 0;
    }

    public void setMagicMitigation(int magicMitigation) {
        this.magicMitigation = magicMitigation;
    }

    public void resetPhysicalMitigation() {
        this.physicalMitigation = 0;
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
        return this.hp > 0;
    }

    public int getHp() {
        return this.hp;
    }
}
