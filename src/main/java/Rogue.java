public class Rogue extends Character {
    public Rogue(int strength, int dexterity, int constitution, int wisdom, int intelligence, int charisma) {
        super(strength, dexterity, constitution, wisdom, intelligence, charisma);
    }

    @Override
    protected int getCritMultiplier() {
        return 3;
    }

    @Override
    protected int attackModifier() {
        return getModifierFor(dexterity);
    }

    @Override
    public void setAlignment(Alignment alignment){
        if (alignment == Alignment.GOOD)
            throw new IllegalStateException("Rogues cannot be good!");
        else
            super.setAlignment(alignment);
    }
}
