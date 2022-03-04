package game.gameParts.cards.abilities.magical;

import game.gameParts.cards.monsters.Monster;

public class Fire extends OffensiveMagicAbility {
    public Fire(int abilityLevel) {
        super(abilityLevel);
        this.name = "Fire";
    }

    @Override
    public int calculatePlayerDamage(int value, Monster target) {
        return 0;
    }

    @Override
    public int calculateMonsterDamage() {
        return 0;
    }
}
