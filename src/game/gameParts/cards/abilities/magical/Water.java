package game.gameParts.cards.abilities.magical;

import game.gameParts.cards.monsters.Monster;
import game.gameParts.cards.monsters.MonsterType;
import game.gameParts.player.Runa;

/**
 * resembles the ability "Water"
 * @author upvlx
 * @version 0.1
 */
public class Water extends OffensiveMagicAbility {
    private static final int ABILITY_LEVEL_MODIFIER = 8;
    private static final int DAMAGE_TO_ADD = 2;
    private static final int MONSTER_ABILITY_MULTIPLIER = 2;
    private static final int MONSTER_DAMAGE_TO_ADD = 4;

    /**
     * constructor
     * @param abilityLevel the ability level the ability is supposed to have
     */
    public Water(int abilityLevel) {
        super(abilityLevel);
        this.name = "Water";
    }

    @Override
    public int calculateDamagePlayer(int value, Monster target) {
        int defaultDmg = (MONSTER_ABILITY_MULTIPLIER * this.abilityLevel + MONSTER_DAMAGE_TO_ADD) * value;
        return target.getType() == MonsterType.Lightning ? defaultDmg + MONSTER_ABILITY_MULTIPLIER * this.abilityLevel
                : defaultDmg;

    }

    @Override
    public int calculateDamageMonster(Runa target) {
        return ABILITY_LEVEL_MODIFIER * this.abilityLevel + DAMAGE_TO_ADD;
    }
}
