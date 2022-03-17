package game.state;

import game.RunasStrive;
import game.gameParts.cards.abilities.Ability;
import game.gameParts.cards.abilities.DefensiveAbility;
import game.gameParts.cards.abilities.magical.Focus;
import game.gameParts.cards.monsters.Monster;
import game.gameParts.player.Runa;
import game.state.initiationValues.GameSettings;
import game.state.initiationValues.MonstersLevels;
import game.state.output.CommonOutputs;
import game.state.output.NumInputRequest;

import java.util.LinkedList;
import java.util.List;

/**
 * State that resembles the Fight interaction (2.3)
 * @author upvlx
 * @version 0.1
 */
public class Fight extends GameState {
    private final List<Monster> active;
    private final List<Monster> toBeRemoved;

    /**
     * constructor for the GameState
     * @param runasStrive  Game on which the state should be executed
     */
    public Fight(RunasStrive runasStrive) {
        super(runasStrive);
        this.active = new LinkedList<>();
        this.toBeRemoved = new LinkedList<>();
    }

    @Override
    public void executeState() {
        this.runasStrive.nextRoom();

        selectMonsters();
        welcomeText();
        if (!fight()) return;

        // set next state
        this.runasStrive.setState(new FightAftermath(this.runasStrive));
    }

    /**
     * function to set the active monster that are supposed to be in this room
     */
    private void selectMonsters() {
        Monster boss = this.runasStrive.getLevel() == 1 ? MonstersLevels.FIRST.getBoss()
                : MonstersLevels.SECOND.getBoss();
        if (this.runasStrive.getRoom() == 1) this.active.add(this.runasStrive.getMonsterCards().remove(0));
        else if (this.runasStrive.getRoom() == GameSettings.ROOMS.getValue()) this.active.add(boss);
        else {
            this.active.add(this.runasStrive.getMonsterCards().remove(0));
            this.active.add(this.runasStrive.getMonsterCards().remove(0));
        }
    }

    private boolean fight() {
        while (!this.active.isEmpty()) {
            // ----------- Setup ----------
            Ability ability;
            Monster target = this.active.get(0);
            int diceRoll;
            printInfo();
            printCards();
            // ----------- Player turn ----------
            int max = this.runasStrive.getPlayer().getAbilities().size();
            int index = getNumInput(max, NumInputRequest.ONE_INPUT_REQUEST.toString(max));
            if (index == -1) return false;
            ability = this.runasStrive.getPlayer().getAbilities().get(index);

            if (this.active.size() > 1 && ability.isOffensive()) {
                printTargets();
                max = this.active.size();
                index = getNumInput(max, NumInputRequest.ONE_INPUT_REQUEST.toString(max));
                if (index == -1) return false;
                target = this.active.get(index);
            }
            System.out.printf("%s %s %s%n", CommonOutputs.PLAYER, CommonOutputs.USE, ability);

            if (ability.isOffensive() && ability.isPhysical()) {
                int diceMax = this.runasStrive.getPlayer().getCurrentDice().getMaxValue();
                diceRoll = getNumInput(diceMax, NumInputRequest.DICE_INPUT_REQUEST.toString(diceMax)) + 1;
                if (diceRoll == 0) return false;
            }
            else diceRoll = this.runasStrive.getPlayer().getFocusPoints();
            executeAbility(this.runasStrive.getPlayer(), target, ability, diceRoll);

            // ----------- Monster turn ----------
            this.active.forEach(Monster::reset);
            for (Monster monster : this.active) {
                if (!executeAbility(monster, this.runasStrive.getPlayer(), monster.nextAbility(), 0))
                    return false;
            }
            this.active.removeIf(this.toBeRemoved::contains);
            this.runasStrive.getPlayer().reset();
        }
        return true;
    }

    /**
     * function to execute an ability
     * @param initiator the entity that casted the ability
     * @param target the entity that is supposed to be hit by the ability
     * @param ability the ability that was cast
     * @param diceRoll the Rolled dice for physical abilities or focus points for magical
     * @return true if the ability was executed and no one died, false if the target dies
     */
    private boolean executeAbility(Object initiator, Object target, Ability ability, int diceRoll) {
        this.runasStrive.getPlayer().resetReflect();
        // Runa
        if (initiator instanceof Runa) {
            Runa runa = (Runa) initiator;
            Monster monster = (Monster) target;
            if (ability instanceof Focus) ((Focus) ability).focus(runa);
            else if (ability.isOffensive()) {
                if (!ability.isPhysical()) {
                    runa.decreaseFocusPoints();
                }
                if (!monster.takeDamage(ability, diceRoll)) {
                    this.active.remove(monster);
                    System.out.printf("%s %s%n", monster, CommonOutputs.DIE);
                    return false;
                }
            }
            else ((DefensiveAbility) ability).calculateMitigation(initiator);
        }
        // Monster
        else if (initiator instanceof Monster) {
            Monster monster = (Monster) initiator;
            Runa runa = (Runa) target;
            if (ability instanceof Focus) {
                System.out.printf("%s %s %s%n", monster, CommonOutputs.USE, ability);
                ((Focus) ability).focus(monster);
            }
            else if (ability.isOffensive()) {
                if (!ability.isPhysical()) {
                    if (ability.getAbilityLevel() > monster.getFocusPoints())
                        return executeAbility(initiator, target, monster.nextAbility(), diceRoll);
                    else
                        for (int i = 0; i < ability.getAbilityLevel(); i++)
                            monster.decreaseFocusPoints();
                }
                System.out.printf("%s %s %s%n", monster, CommonOutputs.USE, ability);
                if (!runa.takeDamage(ability)) {
                    this.gameEnd();
                    return false;
                }
                if (runa.isReflecting() && !ability.isPhysical()) {
                    if (!monster.takeDamage(runa.getReflectedDmg())) {
                        this.toBeRemoved.add(monster);
                        System.out.printf("%s %s%n", monster, CommonOutputs.DIE);
                        return true;
                    }
                }
            }
            else {
                System.out.printf("%s %s %s%n", monster, CommonOutputs.USE, ability);
                ((DefensiveAbility) ability).calculateMitigation(initiator);
            }
        }
        return true;
    }

    private void printInfo() {
        System.out.println(CommonOutputs.FIGHT_ROUND_SEPARATOR);
        System.out.println(this.runasStrive.getPlayer());
        System.out.println(CommonOutputs.VS);
        this.active.forEach(monster -> System.out.println(monster.extendedToString()));
        System.out.println(CommonOutputs.FIGHT_ROUND_SEPARATOR);
    }

    private void printTargets() {
        System.out.println(CommonOutputs.SELECT_TARGET);
        this.active.forEach(monster -> System.out.printf("%d) %s%n", this.active.indexOf(monster) + 1, monster));
    }

    private void printCards() {
        List<Ability> abilities = this.runasStrive.getPlayer().getAbilities();
        System.out.println(CommonOutputs.SELECT_CARD);
        for (int i = 0; i < abilities.size(); i++) System.out.printf("%d) %s%n", i + 1, abilities.get(i));
    }

    private void welcomeText() {
        System.out.printf("%s %d %s %d%n", CommonOutputs.STAGE, this.runasStrive.getRoom(),
                CommonOutputs.LEVEL, this.runasStrive.getLevel());
    }
}
