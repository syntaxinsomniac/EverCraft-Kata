public enum CharacterClass {
	UNCLASSED( 5, 1, 2, new DefaultLevelBonusCalculator()),
	FIGHTER  (10, 1, 2, new FighterLevelBonusCalculator()),
	ROGUE    ( 5, 1, 3, new DefaultLevelBonusCalculator()),
	PALADIN  ( 5, 1, 2, new DefaultLevelBonusCalculator()),
	MONK     ( 6, 3, 2, new MonkLevelBonusCalculator());

	private final int baseHp;
	private final int baseDamage;
	private final int critMultiplier;
    private LevelBonusCalculator levelBonusCalculator;

    CharacterClass(int baseHp, int baseDamage, int critMultiplier, LevelBonusCalculator levelBonusCalculator) {
		this.baseHp = baseHp;
		this.baseDamage = baseDamage;
		this.critMultiplier = critMultiplier;
        this.levelBonusCalculator = levelBonusCalculator;
    }

	public int baseHp() {
		return baseHp;
	}

	public int baseDamage() {
		return baseDamage;
	}

	public int critMultiplier() {
		return critMultiplier;
	}

    public int levelAttackBonus(int level) {
        return levelBonusCalculator.calculateBonus(level);
    }


    interface LevelBonusCalculator {
	    int calculateBonus(int level);
    }

    static class MonkLevelBonusCalculator implements LevelBonusCalculator {
        public int calculateBonus(int level) {
            int bonus = 0;
            for (int i = 1; i <= level; i++)
                if (i % 2 == 0 || i % 3 == 0)
                    bonus++;
            return bonus;
        }
    }

    static class FighterLevelBonusCalculator implements LevelBonusCalculator {
        public int calculateBonus(int level) {
            return level - 1;
        }
    }

    static class DefaultLevelBonusCalculator implements LevelBonusCalculator {
        public int calculateBonus(int level) {
            return level / 2;
        }
    }
}