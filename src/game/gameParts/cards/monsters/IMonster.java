package game.gameParts.cards.monsters;

import game.gameParts.cards.abilities.Ability;

public interface IMonster {
    Ability nextAbility();
    boolean takeDamage(int damage);
    boolean takeDamage(Ability ability, int value);
    int getFocusPoints();
    void deBuff();
    void focus(int increase);
    void decreaseFocusPoints();
    MonsterType getType();
    void setMagicMitigation(int magicMitigation);
    void setPhysicalMitigation(int physicalMitigation);
    void reset();
    String extendedToString();
}
