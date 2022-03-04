package game.gameParts.cards.abilities.magical;

import game.gameParts.cards.monsters.Monster;

public class Water extends OffensiveMagicAbility{
    public Water(int abilityLevel) {
        super(abilityLevel);
        this.name = "Water";
    }

    @Override
    public int calculatePlayerDamage(int value, Monster target) {
        return 0;
    }


    public int calculatePlayerDamage(int value) {
        return (2 * this.abilityLevel + 4) * value;
    }

    @Override
    public int calculateMonsterDamage() {
        return 8 * this.abilityLevel + 2;
    }
}
