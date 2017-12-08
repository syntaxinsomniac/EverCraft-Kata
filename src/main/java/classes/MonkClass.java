package classes;

public class MonkClass implements CharacterClass {
    public int baseHp() {
        return 6;
    }

    public int baseDamage() {
        return 3;
    }

    public int critMultiplier() {
        return 2;
    }

    public int levelAttackBonus(int level) {
        int bonus = 0;
        for (int i = 1; i <= level; i++)
            if (i % 2 == 0 || i % 3 == 0)
                bonus++;
        return bonus;
    }
}
