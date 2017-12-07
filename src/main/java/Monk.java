public class Monk extends Character {
    public Monk(int strength, int dexterity, int constitution, int wisdom, int intelligence, int charisma) {
        super(strength, dexterity, constitution, wisdom, intelligence, charisma);
    }

    protected int getLevelBonus() {
        int levelDieBonus = 0;
        for (int i = 1; i <= level; i++)
            if (i % 2 == 0 || i % 3 == 0)
                levelDieBonus++;
        return levelDieBonus;
    }

    protected int baseHp() {
        return 6;
    }

    protected int baseDamage() {
        return 3 + attackModifier();
    }

    public int getArmorClass() {
        return 10 + getModifierFor(wisdom);
    }
}
