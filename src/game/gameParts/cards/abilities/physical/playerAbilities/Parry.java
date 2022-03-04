package game.gameParts.cards.abilities.physical.playerAbilities;

import game.gameParts.cards.abilities.DefensiveAbility;
import game.gameParts.cards.abilities.physical.PhysicalDefensiveAbility;
import game.gameParts.cards.monsters.Monster;
import game.gameParts.player.Runa;

public class Parry extends PhysicalDefensiveAbility {
    public Parry(int abilityLevel) {
        super(abilityLevel);
        this.name = "Parry";
    }

    @Override
    public void calculateMitigation(Object target) {

    }
}
