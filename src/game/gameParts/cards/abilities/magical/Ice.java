package game.gameParts.cards.abilities.magical;

import game.gameParts.cards.monsters.Monster;

public class Ice extends OffensiveMagicAbility {
    public Ice(int abilityLevel) {
        super(abilityLevel);
        this.name = "Ice";
    }

    @Override
    public int calculatePlayerDamage(int value, Monster target) {
        return 0;
    }

    public int calculatePlayerDamage(int value) {
        return (2);
    }

    @Override
    public int calculateMonsterDamage() {
        return 0;
    }
}
