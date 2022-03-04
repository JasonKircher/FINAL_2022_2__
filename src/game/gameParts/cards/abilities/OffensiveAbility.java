package game.gameParts.cards.abilities;

import game.gameParts.cards.monsters.Monster;

public abstract class OffensiveAbility extends Ability {
    public OffensiveAbility(int abilityLevel) {
        super(abilityLevel);
        this.offensive = true;
    }
    public abstract int calculatePlayerDamage(int value, Monster target);
    public abstract int calculateMonsterDamage();
}
