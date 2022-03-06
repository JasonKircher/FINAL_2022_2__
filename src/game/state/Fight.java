package game.state;

import game.Game;
import game.gameParts.cards.abilities.Ability;
import game.gameParts.cards.abilities.DefensiveAbility;
import game.gameParts.cards.abilities.magical.Focus;
import game.gameParts.cards.monsters.Monster;
import game.gameParts.player.PlayerStartingValues;
import game.gameParts.player.Runa;
import game.state.initiationValues.MonstersLevels;
import game.state.output.ErrorMsg;
import game.state.output.NumInputRequest;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Fight extends GameState {
    List<Monster> active;
    public Fight(Game game) {
        super(game);
        this.active = new LinkedList<>();
    }

    @Override
    public void executeState() {
        // 2.3
        this.game.nextRoom();

        selectMonsters();
        welcomeText();
        if (!fight()) return;

        // set next state
        this.game.setState(new FightAftermath(this.game));
    }

    private void selectMonsters() {
        Monster boss = this.game.getLevel() == 1 ? MonstersLevels.FIRST.getBoss() : MonstersLevels.SECOND.getBoss();
        if (this.game.getRoom() == 1) this.active.add(this.game.getMonsterCards().remove(0));
        else if (this.game.getRoom() == 4) this.active.add(boss);
        else {
            this.active.add(this.game.getMonsterCards().remove(0));
            this.active.add(this.game.getMonsterCards().remove(0));
        }
    }

    private boolean fight() {
        while (!this.active.isEmpty()) {

            Ability ability;
            Monster target = this.active.get(0);
            int diceRoll = 0;
            printInfo();
            printCards();
            this.game.getPlayer().reset();

            int max = this.game.getPlayer().getAbilities().size();
            int index = getNumInput(max, NumInputRequest.ONE_INPUT_REQUEST.getOutput(max));
            if (index == -1) return false;
            ability = this.game.getPlayer().getAbilities().get(index);

            if (ability.isOffensive()) {
                if (!ability.isPhysical() && this.game.getPlayer().getFocusPoints() <= 0) {
                    System.out.println(ErrorMsg.NOT_ENOUGH_FOCUS.getMsg());
                    continue;
                }
                int diceMax = this.game.getPlayer().getCurrentDice().getMaxValue();
                diceRoll = getNumInput(diceMax, NumInputRequest.DICE_INPUT_REQUEST.getOutput(diceMax));
                if (diceRoll == -1) return false;
            }

            if (this.active.size() > 1) {
                printTargets();
                max = this.active.size();
                index = getNumInput(max, NumInputRequest.ONE_INPUT_REQUEST.getOutput(max));
                if (index == -1) return false;
                target = this.active.get(index);
            }

            executeAbility(this.game.getPlayer(), target, ability, diceRoll);
            this.active.forEach(Monster::reset);
            for (Monster monster : this.active) {
                if (!executeAbility(monster, this.game.getPlayer(), monster.nextAbility(), 0)) return false;
            }
        }
        return true;
    }

    private boolean executeAbility(Object initiator, Object target, Ability ability, int diceRoll) {
        if (initiator instanceof Runa runa) {
            Monster monster = (Monster) target;
            System.out.println("Runa uses " + ability);
            if (ability instanceof Focus) ((Focus) ability).focus(runa);
            else if (ability.isOffensive()) {
                if (!ability.isPhysical()) if (!runa.decreaseFocusPoints()) return true;
                if (!monster.takeDamage(ability, diceRoll)) {
                    this.active.remove(monster);
                    return false;
                }
            }
            else ((DefensiveAbility) ability).calculateMitigation(initiator);
        }
        else if (initiator instanceof Monster monster) {
            Runa runa = (Runa) target;
            System.out.println(monster + " uses " + ability);
            if (ability instanceof Focus) ((Focus) ability).focus(monster);
            else if (ability.isOffensive()) {
                if (!ability.isPhysical()) if (!monster.decreaseFocusPoints()) return true;
                if (!runa.takeDamage(ability)) {
                    this.gameEnd();
                    return false;
                }
            }
            else ((DefensiveAbility) ability).calculateMitigation(initiator);
        }
        return true;
    }

    private void printInfo() {
        System.out.println("----------------------------------------");
        System.out.println("Runa (" + this.game.getPlayer().getHp() + "/" + PlayerStartingValues.STARTING_HP.getValue()
                + ", " + this.game.getPlayer().getFocusPoints() + "/"
                + this.game.getPlayer().getCurrentDice().getMaxValue() + " FP)");
        System.out.println("vs.");
        this.active.forEach(monster -> System.out.println(monster.extendedToString()));
        System.out.println("----------------------------------------");
    }

    private void printTargets() {
        System.out.println("Select Runa's target");
        this.active.forEach(monster -> System.out.println(this.active.indexOf(monster) + 1 + ") " + monster));
    }

    private void printCards() {
        List<Ability> abilities = this.game.getPlayer().getAbilities();
        System.out.println("Select card to play");
        abilities.forEach(ability -> System.out.println(abilities.indexOf(ability) + 1 + ") " + ability));
    }

    private void welcomeText() {
        System.out.println("Runa enters Stage " + this.game.getRoom() + " of Level " + this.game.getLevel());
    }
}
