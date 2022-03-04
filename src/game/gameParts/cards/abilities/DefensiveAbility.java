package game.gameParts.cards.abilities;

import game.gameParts.cards.monsters.Monster;
import game.gameParts.player.Runa;

public abstract class DefensiveAbility extends Ability {
    public DefensiveAbility(int abilityLevel) {
        super(abilityLevel);
        this.offensive = false;
    }

    public abstract void calculatePlayerMitigation(Runa runa);
    public abstract void calculateMonsterMitigation(Monster monster);
}
