package game.state;

import game.Game;
import game.gameParts.cards.abilities.Ability;
import game.gameParts.cards.abilities.DefensiveAbility;
import game.gameParts.cards.abilities.magical.Focus;
import game.gameParts.cards.monsters.Monster;
import game.gameParts.player.PlayerStartingValues;
import game.state.initiationValues.MonstersLevels;
import game.state.output.NumInputRequest;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ConcurrentSkipListSet;

public class Fight extends GameState {
    List<Monster> active;
    public Fight(Game game) {
        super(game);
        active = new LinkedList<>();
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
        Scanner scanner = new Scanner(System.in);
        while (!this.active.isEmpty()) {
            Ability ability = null;
            Monster target = this.active.get(0);
            printInfo();
            printCards();
            this.game.getPlayer().resetMitigation();

            // TODO kapseln && immer letzte eingabe wiederholen bei falscher eingabe - magic abiliteis kosten Focus
            String input = scanner.nextLine();
            if (input.equals("quit")) {
                this.gameEnd();
                return false;
            } else {
                try {
                    int abilityIndex = Integer.parseInt(input) - 1;
                    if (abilityIndex >= this.game.getPlayer().getAbilities().size()) continue;
                    ability = this.game.getPlayer().getAbilities().get(abilityIndex);
                } catch (NumberFormatException ignored) {
                    // cannot be parsed
                    continue;
                }
            }
            if (this.active.size() > 1) {
                printTargets();

                input = scanner.nextLine();
                if (input.equals("quit")) {
                    this.gameEnd();
                    return false;
                }else {
                    try {
                        int monsterIndex = Integer.parseInt(input) - 1;
                        if (monsterIndex >= this.active.size()) continue;
                        target = this.active.get(monsterIndex);
                    } catch (NumberFormatException ignored) {
                        // cannot be parsed
                        continue;
                    }
                }
            }
            // TODO end

            if (ability.isOffensive()) {
                if (!target.takeDamage(ability)) this.active.remove(target);
            }
            else if (ability instanceof Focus) this.game.getPlayer().increaseFocusPoints();
            else {
                ((DefensiveAbility) ability).calculateMitigation(this.game.getPlayer());
            }
            for (Monster monster : this.active) {
                monster.resetMitigation();
                ability = monster.nextAbility();
                if (ability.isOffensive()) {
                    if (!this.game.getPlayer().takeDamage(ability)) {
                        this.gameEnd();
                        return false;
                    }
                }
                else if (ability instanceof Focus) monster.increaseFocusPoints();
                else ((DefensiveAbility) ability).calculateMitigation(monster);
                }
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
        System.out.println(NumInputRequest.ONE_INPUT_REQUEST.getOutput(abilities.size()));
    }

    private void welcomeText() {
        System.out.println("Runa enters Stage " + this.game.getRoom() + " of Level " + this.game.getLevel());
    }
}
