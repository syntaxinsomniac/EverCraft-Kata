public class Monk extends Character {
    public Monk(int strength, int dexterity, int constitution, int wisdom, int intelligence, int charisma) {
        super(strength, dexterity, constitution, wisdom, intelligence, charisma);
    }

    @Override
    protected int getLevelBonus() {
        int levelDieBonus = 0;
        for (int i = 1; i <= level; i++)
            if (i % 2 == 0 || i % 3 == 0)
                levelDieBonus++;
        return levelDieBonus;
    }

    @Override
    protected int baseHp() {
        return 6;
    }

    @Override
    protected int baseDamage() {
        return 3 + attackModifier();
    }

    @Override
    public int getArmorClass() {
        return 10 + getModifierFor(wisdom);
    }
}
