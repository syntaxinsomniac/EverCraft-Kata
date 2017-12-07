public class Character {
	private String name;
	private Alignment alignment; //enum you made in Alignment.java
	private CharacterClass characterClass = CharacterClass.UNCLASSED;
	private int strength = 10, dexterity = 10, constitution = 10, wisdom = 10, intelligence = 10, charisma = 10;
	
	public int armorClass = 10;
	public int hitPoints = 5;
	public int maxHitPoints = 5;
	public int xp = 0;
	public int level = 1;
	
	public Character(){}
	
	public Character(CharacterClass characterClass, int strength, int dexterity, int constitution, int wisdom, int intelligence, int charisma){
		this.strength = strength;
		this.dexterity = dexterity;
		this.constitution = constitution;
		this.wisdom = wisdom;
		this.intelligence = intelligence;
		this.charisma = charisma;
		this.characterClass = characterClass;
		// this one is a bit tricky to do
		this.armorClass = characterClass == CharacterClass.MONK ? (armorClass + getModifierFor(wisdom)) : (armorClass + getModifierFor(dexterity));
		this.hitPoints = this.getHitpointsPerLevel();
		this.maxHitPoints = this.getHitpointsPerLevel();
	}
	
	public void attack(Character opponent, int dieRoll){
		if (dieRoll == 20) {
			opponent.hitPoints = opponent.hitPoints - Math.max(characterClass.critMultiplier() * baseDamage(), 1);
			xp += 10;
			checkAndUpdateLevel();
		} else if (attackHits(opponent, dieRoll)) {
			opponent.hitPoints = opponent.hitPoints - Math.max(baseDamage(), 1);
			xp += 10;
			checkAndUpdateLevel();
		}
	}
	
	private boolean attackHits(Character opponent, int dieRoll) {
		return dieRoll + getLevelBonus() + attackModifier() >= opponent.armorClassVersus(this);
	}

	private int getLevelBonus() {
		return characterClass.levelAttackBonus(level);
	}

	private int attackModifier() {
		// hmm.  a little tricky, this one
		return characterClass == CharacterClass.ROGUE ? getModifierFor(dexterity) : getModifierFor(strength);
	}
	
	private int baseDamage() {
		return characterClass.baseDamage() + attackModifier();
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
		return Math.max(characterClass.baseHp() + getModifierFor(constitution), 1);
	}

	private int getModifierFor(int ability){
		return ability/2 - 5;
	}
	
	private int armorClassVersus(Character attacker){
		// also tricky
		if (attacker.characterClass == CharacterClass.ROGUE && getModifierFor(dexterity) > 0)
			return armorClass - getModifierFor(dexterity);
		else 
			return armorClass;
		
	}
	
	public void setName(String name){
		this.name = name; //private name = the one specific to this method
	};
	
	public String getName(){ //object instead of void, because it returns an object
		return name;
	};
	
	public void setAlignment(Alignment alignment){
		// also tricky
		if (characterClass == CharacterClass.ROGUE && alignment == Alignment.GOOD)
			throw new IllegalStateException("Rogues cannot be good!");
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
		
}