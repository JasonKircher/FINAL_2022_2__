package game.state;

import game.RunasStrive;
import game.gameParts.cards.abilities.Ability;
import game.gameParts.cards.abilities.DefensiveAbility;
import game.gameParts.cards.abilities.magical.Focus;
import game.gameParts.cards.monsters.Monster;
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
            List<Integer> input;
            input = getInput(max, 1, 1, NumInputRequest.ONE_INPUT_REQUEST.toString(max),
                    false);
            if (input == null) return false;
            int index = input.remove(0);
            ability = this.runasStrive.getPlayer().getAbilities().get(index);

            if (this.active.size() > 1 && ability.isOffensive()) {
                printTargets();
                max = this.active.size();
                input = getInput(max, 1, 1, NumInputRequest.ONE_INPUT_REQUEST.toString(max),
                        false);
                if (input == null) return false;
                index = input.remove(0);
                target = this.active.get(index);
            }
            System.out.printf("%s %s %s%n", CommonOutputs.PLAYER, CommonOutputs.USE, ability);

            if (ability.isOffensive() && ability.isPhysical()) {
                int diceMax = this.runasStrive.getPlayer().getCurrentDice().getMaxValue();
                input = getInput(diceMax, 1, 1,
                        NumInputRequest.DICE_INPUT_REQUEST.toString(diceMax), false);
                if (input == null) return false;
                // +1 to correct index
                diceRoll = input.remove(0) + 1;
            }
            else diceRoll = this.runasStrive.getPlayer().getFocusPoints();
            runaExecuteAbility(ability, target, diceRoll);

            // ----------- Monster turn ----------
            this.active.forEach(Monster::reset);
            for (Monster monster : this.active) {
                if (!monsterExecuteAttack(monster))
                    return false;
            }
            this.active.removeIf(this.toBeRemoved::contains);
            this.runasStrive.getPlayer().reset();
        }
        return true;
    }

    /**
     * function to execute runas ability
     * @param ability ability to be cast
     * @param target the target the ability is cast on
     * @param modifier either the number rolled on the dice or the current focus points depending on the ability
     */
    private void runaExecuteAbility(Ability ability, Monster target, int modifier) {
        // focus
        if (ability.getClass() == Focus.class)
            ((Focus) ability).focus(this.runasStrive.getPlayer());
        // offensive
        else if (ability.isOffensive()) {
            if (!ability.isPhysical())
                this.runasStrive.getPlayer().decreaseFocusPoints();
            if (!target.takeDamage(ability, modifier)) {
                this.active.remove(target);
                System.out.printf("%s %s%n", target, CommonOutputs.DIE);
            }
        }
        // defensive
        else
            ((DefensiveAbility) ability).calculateMitigationPlayer(this.runasStrive.getPlayer());
    }

    /**
     * function to execute an monster ability
     * @param initiator the monster casting the ability
     * @return true if the attack was successful and runa survived, false if runa dies
     */
    private boolean monsterExecuteAttack(Monster initiator) {
        this.runasStrive.getPlayer().resetReflect();
        Ability ability = initiator.nextAbility();
        // focus
        if (ability.getClass() == Focus.class) {
            System.out.printf("%s %s %s%n", initiator, CommonOutputs.USE, ability);
            ((Focus) ability).focus(initiator);
        }
        // offensive
        else if (ability.isOffensive()) {
            if (!ability.isPhysical() && ability.getAbilityLevel() > initiator.getFocusPoints())
                // getting next ability
                return monsterExecuteAttack(initiator);
            else if (!ability.isPhysical() && ability.getAbilityLevel() <= initiator.getFocusPoints())
                for (int i = 0; i < ability.getAbilityLevel(); i++)
                    initiator.decreaseFocusPoints();
            System.out.printf("%s %s %s%n", initiator, CommonOutputs.USE, ability);
            if (!this.runasStrive.getPlayer().takeDamage(ability)) {
                // runa dies during attack
                this.gameEnd();
                return false;
            }
            if (this.runasStrive.getPlayer().isReflecting() && !ability.isPhysical())
                if (!initiator.takeDamage(this.runasStrive.getPlayer().getReflectedDmg())) {
                    this.toBeRemoved.add(initiator);
                    System.out.printf("%s %s%n", initiator, CommonOutputs.DIE);
                    return true;
                }
        }
        // defensive
        else {
            System.out.printf("%s %s %s%n", initiator, CommonOutputs.USE, ability);
            ((DefensiveAbility) ability).calculateMitigationMonster(initiator);
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
