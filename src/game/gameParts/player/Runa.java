package game.gameParts.player;

import game.gameParts.cards.abilities.Ability;
import game.gameParts.cards.abilities.OffensiveAbility;
import game.gameParts.player.parts.Dice;
import game.gameParts.player.parts.PlayerClass;
import game.state.output.CommonOutputs;
import game.state.output.Exceptions;

import java.util.LinkedList;
import java.util.List;

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

    public void addAbilityCard(Ability card) {
        System.out.printf("%s %s %s%n", CommonOutputs.PLAYER.toString(), CommonOutputs.GET.toString(), card);
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

    public void decreaseFocusPoints() {
        if (this.focusPoints > 1) {
            this.focusPoints--;
        }
    }

    public void resetReflect() {
        this.reflectedDmg = 0;
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
            System.out.printf("%s %s %d %s%n", CommonOutputs.PLAYER.toString(), CommonOutputs.GAIN.toString(),
                    this.focusBuffer, CommonOutputs.FOCUS.toString());
            this.focusBuffer = 0;
        }
        this.reflecting = false;
        this.reflectedDmg = 0;
        this.magicMitigation = 0;
        this.physicalMitigation = 0;
    }

    public void heal(int hp) {
        int tmpHealing = hp;
        if (this.hp + tmpHealing > PlayerStartingValues.STARTING_HP.getValue()) {
            tmpHealing = PlayerStartingValues.STARTING_HP.getValue() - this.hp;
            this.hp = PlayerStartingValues.STARTING_HP.getValue();
        }
        else this.hp += hp;
        if (tmpHealing > 0) System.out.printf("%s %s %s health%n", CommonOutputs.PLAYER.toString(),
                CommonOutputs.GAIN.toString(), tmpHealing);
    }

    public void setPhysicalMitigation(int physicalMitigation) {
        this.physicalMitigation = physicalMitigation;
    }

    public boolean takeDamage(Ability ability) {
        if (!ability.isOffensive()) throw new RuntimeException(Exceptions.DMG_FROM_DEFENSIVE_ABILITY.getMsg());
        OffensiveAbility abilityParsed = (OffensiveAbility) ability;
        int damage = abilityParsed.calculateDamage(0,  this);
        if (abilityParsed.isPhysical()) damage = damage - this.physicalMitigation;
        else {
            damage = damage - this.magicMitigation;
            this.reflectedDmg = damage >= 0 ? this.magicMitigation : this.magicMitigation + damage;
        }
        if (damage > 0) this.hp -= damage;
        else damage = 0;
        String dmgType = ability.isPhysical() ? CommonOutputs.PHYSICAL.toString() : CommonOutputs.MAGICAL.toString();
        if (damage > 0) System.out.printf("%s takes %s %s damage%n", CommonOutputs.PLAYER.toString(), damage, dmgType);
        return this.hp > 0;
    }

    public void setReflecting() {
        this.reflecting = true;
    }

    public boolean isReflecting() {
        return this.reflecting;
    }

    public int getHp() {
        return this.hp;
    }

    public int getReflectedDmg() {
        return this.reflectedDmg;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Runa");
        String hp = String.format("%d/%d HP", this.hp, PlayerStartingValues.STARTING_HP.getValue());
        String focus = String.format("%d/%d FP", this.focusPoints, this.currentDice.getMaxValue());
        String info = String.format(" (%s, %s)", hp, focus);
        builder.append(info);
        return builder.toString();
    }
}
