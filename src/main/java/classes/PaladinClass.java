package classes;

public class PaladinClass implements CharacterClass {
    public int baseHp() {
        return 5;
    }

    public int baseDamage() {
        return 1;
    }

    public int critMultiplier() {
        return 2;
    }

    public int levelAttackBonus(int level) {
        return level / 2;
    }
}
