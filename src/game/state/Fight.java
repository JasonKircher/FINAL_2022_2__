package game.state;

import game.Game;
import game.gameParts.cards.abilities.Ability;
import game.gameParts.cards.abilities.DefensiveAbility;
import game.gameParts.cards.abilities.magical.Focus;
import game.gameParts.cards.monsters.Monster;
import game.gameParts.player.PlayerStartingValues;
import game.gameParts.player.Runa;
import game.state.initiationValues.GameSettings;
import game.state.initiationValues.MonstersLevels;
import game.state.output.ErrorMsg;
import game.state.output.NumInputRequest;

import java.util.LinkedList;
import java.util.List;

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
        else if (this.game.getRoom() == GameSettings.ROOMS.getValue()) this.active.add(boss);
        else {
            this.active.add(this.game.getMonsterCards().remove(0));
            this.active.add(this.game.getMonsterCards().remove(0));
        }
    }

    private boolean fight() {
        while (!this.active.isEmpty()) {

            Ability ability;
            Monster target = this.active.get(0);
            int diceRoll;
            printInfo();
            printCards();

            int max = this.game.getPlayer().getAbilities().size();
            int index = getNumInput(max, NumInputRequest.ONE_INPUT_REQUEST.getOutput(max));
            if (index == -1) return false;
            ability = this.game.getPlayer().getAbilities().get(index);

            if (ability.isOffensive()) {
                if (!ability.isPhysical() && this.game.getPlayer().getFocusPoints() <= 0) {
                    System.out.println(ErrorMsg.NOT_ENOUGH_FOCUS.getMsg());
                    continue;
                }
            }

            if (this.active.size() > 1 && ability.isOffensive()) {
                printTargets();
                max = this.active.size();
                index = getNumInput(max, NumInputRequest.ONE_INPUT_REQUEST.getOutput(max));
                if (index == -1) return false;
                target = this.active.get(index);
            }

            if (ability.isOffensive() && ability.isPhysical()) {
                int diceMax = this.game.getPlayer().getCurrentDice().getMaxValue();
                diceRoll = getNumInput(diceMax, NumInputRequest.DICE_INPUT_REQUEST.getOutput(diceMax)) + 1;
                if (diceRoll == -1) return false;
            }
            else diceRoll = this.game.getPlayer().getFocusPoints();

            executeAbility(this.game.getPlayer(), target, ability, diceRoll);
            this.active.forEach(Monster::reset);
            for (Monster monster : this.active) {
                // check for focus points
                if (!executeAbility(monster, this.game.getPlayer(), monster.nextAbility(), 0)) return false;
            }
            this.game.getPlayer().reset();
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
                    System.out.println(monster + " dies");
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
                if (!ability.isPhysical()) {
                    for (int i = 0; i < ability.getAbilityLevel(); i++) {
                        if (!monster.decreaseFocusPoints()) return true;
                    }
                }
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
        String separator = "----------------------------------------";
        System.out.println(separator);
        System.out.println(this.game.getPlayer());
        System.out.println("vs.");
        this.active.forEach(monster -> System.out.println(monster.extendedToString()));
        System.out.println(separator);
    }

    private void printTargets() {
        System.out.println("Select Runa's target");
        this.active.forEach(monster -> System.out.printf("%d) %s%n", this.active.indexOf(monster) + 1, monster));
    }

    private void printCards() {
        List<Ability> abilities = this.game.getPlayer().getAbilities();
        System.out.println("Select card to play");
        for (int i = 0; i < abilities.size(); i++) System.out.printf("%d) %s%n", i + 1, abilities.get(i));
    }

    private void welcomeText() {
        System.out.printf("Runa enters Stage %d of Level %d%n", this.game.getRoom(), this.game.getLevel());
    }
}
