package classes;

public interface CharacterClass {
    int baseHp();

    int baseDamage();

    int critMultiplier();

    int levelAttackBonus(int level);
}
