public class Character {
	private String name;
	private Alignment alignment; //enum you made in Alignment.java
	protected int strength = 10, dexterity = 10, constitution = 10, wisdom = 10, intelligence = 10, charisma = 10;

	public int hitPoints = 5;
	public int maxHitPoints = 5;
	public int xp = 0;
	public int level = 1;

	public Character(){}

	public Character(int strength, int dexterity, int constitution, int wisdom, int intelligence, int charisma) {
		this.strength = strength;
		this.dexterity = dexterity;
		this.constitution = constitution;
		this.wisdom = wisdom;
		this.intelligence = intelligence;
		this.charisma = charisma;
		this.hitPoints = this.getHitpointsPerLevel();
		this.maxHitPoints = this.getHitpointsPerLevel();
	}

	public void attack(Character opponent, int dieRoll){
		if (dieRoll == 20) {
            opponent.hitPoints = opponent.hitPoints - Math.max(getCritMultiplier() * baseDamage(), 1);
			xp += 10;
			checkAndUpdateLevel();
		} else if (attackHits(opponent, dieRoll)) {
			opponent.hitPoints = opponent.hitPoints - Math.max(baseDamage(), 1);
			xp += 10;
			checkAndUpdateLevel();
		}
	}
    protected int getCritMultiplier() {
        return 2;
    }

	private boolean attackHits(Character opponent, int dieRoll) {
		return dieRoll + getLevelBonus() + attackModifier() >= opponent.armorClassVersus(this);
	}

	protected int getLevelBonus() {
        return level / 2;
	}

	protected int attackModifier() {
		return getModifierFor(strength);
	}

	protected int baseDamage() {
		return 1 + attackModifier();
	}

	private void checkAndUpdateLevel() {
		int previousLevel = level;
		level = xp/1000 + 1;
		boolean levelUp = level > previousLevel;
		if (levelUp) {
			hitPoints += getHitpointsPerLevel();
			maxHitPoints +=getHitpointsPerLevel();
		}
	}

	private int getHitpointsPerLevel() {
		return Math.max(baseHp() + getModifierFor(constitution), 1);
	}

	protected int baseHp() {
		return 5;
	}

	protected int getModifierFor(int ability){
		return ability/2 - 5;
	}

	private int armorClassVersus(Character attacker){
		if (attacker instanceof Rogue && getModifierFor(dexterity) > 0)
			return getArmorClass() - getModifierFor(dexterity);
		else
			return getArmorClass();
	}

	public void setName(String name){
		this.name = name; //private name = the one specific to this method
	};

	public String getName(){ //object instead of void, because it returns an object
		return name;
	};

	public void setAlignment(Alignment alignment){
		this.alignment = alignment; //private alignment = one specific to method
	};

	public Alignment getAlignment(){
		return alignment;
	};

	public int getStrength(){
		return strength;
	}

	public int getDexterity(){
		return dexterity;
	}
	
	public int getConstitution(){
		return constitution;
	}

	public int getWisdom(){
		return wisdom;
	}

	public int getIntelligence(){
		return intelligence;
	}

	public int getCharisma(){
		return charisma;
	}

    public int getArmorClass() {
        return 10 + getModifierFor(dexterity);
    }

}