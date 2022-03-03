package game.gameParts.cards.abilities.magical;

import game.gameParts.cards.abilities.DefensiveAbility;

public class Reflect extends DefensiveAbility {
    public Reflect(int abilityLevel) {
        super(abilityLevel);
        this.name = "Reflect";
    }

    @Override
    public int calculatePlayerMitigation() {
        return 0;
    }

    @Override
    public int calculateMonsterMitigation() {
        return 0;
    }
}
