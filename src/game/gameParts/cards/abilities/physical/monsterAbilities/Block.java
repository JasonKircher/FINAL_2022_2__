package game.gameParts.cards.abilities.physical.monsterAbilities;

import game.gameParts.cards.abilities.DefensiveAbility;

public class Block extends DefensiveAbility {
    public Block(int abilityLevel) {
        super(abilityLevel);
        this.name = "Block";
    }

    @Override
    public int calculatePlayerMitigation() {
        return 0;
    }

    @Override
    public int calculateMonsterMitigation() {
        return 7 * this.abilityLevel;
    }
}
