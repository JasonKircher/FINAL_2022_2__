package game.gameParts.cards.abilities;

public abstract class Ability {
    protected String name;
    protected int abilityLevel;
    protected boolean offensive;
    protected boolean physical;
    public Ability(int abilityLevel) {
        this.abilityLevel = abilityLevel;
    }
    public boolean isOffensive() {
        return this.offensive;
    }
    public boolean isPhysical() {
        return this.physical;
    }
    @Override
    public boolean equals(Object o) {
        if (o.getClass() != this.getClass()) return false;
        return this.name.equals(((Ability) o).name);
    }
}
