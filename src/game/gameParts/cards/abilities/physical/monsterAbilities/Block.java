package game.gameParts.cards.abilities.physical.monsterAbilities;

import game.gameParts.cards.abilities.physical.PhysicalDefensiveAbility;
import game.gameParts.cards.monsters.Monster;

public class Block extends PhysicalDefensiveAbility {
    public Block(int abilityLevel) {
        super(abilityLevel);
        this.name = "Block";
    }

    @Override
    public void calculateMitigation(Object target) {
        if (target instanceof Monster monster) {
            monster.setPhysicalMitigation(7 * this.abilityLevel);
        }
    }
}
