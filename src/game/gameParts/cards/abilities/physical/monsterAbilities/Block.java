package game.gameParts.cards.abilities.physical.monsterAbilities;

import game.gameParts.cards.abilities.DefensiveAbility;
import game.gameParts.cards.monsters.Monster;
import game.gameParts.player.Runa;

public class Block extends DefensiveAbility {
    public Block(int abilityLevel) {
        super(abilityLevel);
        this.name = "Block";
    }

    @Override
    public void calculatePlayerMitigation(Runa runa) {
    }

    @Override
    public void calculateMonsterMitigation(Monster monster) {
    }
}
