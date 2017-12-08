package classes;

public class FighterClass implements CharacterClass {
    public int baseHp() {
        return 10;
    }

    public int baseDamage() {
        return 1;
    }

    public int critMultiplier() {
        return 2;
    }

    public int levelAttackBonus(int level) {
        return level - 1;
    }
}
