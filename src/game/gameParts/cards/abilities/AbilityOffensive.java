package game.gameParts.cards.abilities;

import game.gameParts.cards.Card;
import game.gameParts.cards.monsters.MonsterCard;
import game.gameParts.player.Runa;

public abstract class AbilityOffensive implements Card {
    protected String name;
    public abstract int calculatePlayerDamage(Runa runa);
    public abstract int calculateMonsterDamage(MonsterCard monster);
}
