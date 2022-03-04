package game.gameParts.cards.monsters;

import game.gameParts.cards.abilities.Ability;
import game.gameParts.cards.monsters.firstLevel.MonsterType;

import java.util.List;

public abstract class Monster {
    protected List<Ability> preferredAbilities;
    protected int           hp;
    protected MonsterType type;
    protected boolean       boss;
}
