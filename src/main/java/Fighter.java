public class Fighter extends Character {
    public Fighter(int strength, int dexterity, int constitution, int wisdom, int intelligence, int charisma) {
        super(strength, dexterity, constitution, wisdom, intelligence, charisma);
    }

    protected int getLevelBonus() {
        return level - 1;
    }

    protected int baseHp() {
        return 10;
    }
}
