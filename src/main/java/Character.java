public class Character {
	private String name;
	private Alignment alignment; //enum you made in Alignment.java
	private CharacterClass characterClass;
	private int strength = 10, dexterity = 10, constitution = 10, wisdom = 10, intelligence = 10, charisma = 10;
	
	public int armorClass = 10;
	public int hitPoints = 5;
	public int xp = 0;
	public int level = 1;
	
	public Character(){}
	public Character(int strength, int dexterity, int constitution, int wisdom, int intelligence, int charisma){
		this.strength = strength;
		this.dexterity = dexterity;
		this.constitution = constitution;
		this.wisdom = wisdom;
		this.intelligence = intelligence;
		this.charisma = charisma;
		this.armorClass += getModifierFor(dexterity);
		this.hitPoints = this.getHitpointsPerLevel();
	}
	
	public Character(CharacterClass characterClass){
		this.characterClass = characterClass;
		this.hitPoints = this.getHitpointsPerLevel();
	}
	
	public void attack(Character opponent, int dieRoll){
		if (dieRoll == 20) {
			opponent.hitPoints = opponent.hitPoints - Math.max((2 + (2 * getModifierFor(strength))), 1);
			xp += 10;
		}
		else if (attackHits(opponent, dieRoll)) {
			opponent.hitPoints = opponent.hitPoints - Math.max((1 + getModifierFor(strength)), 1);
			xp += 10;
		}
		int previousLevel = level;
		level = xp/1000 + 1;
		boolean levelUp = level > previousLevel;
		if (levelUp)
			hitPoints += getHitpointsPerLevel();
	}
	
	private int getHitpointsPerLevel() {
		int baseHp = characterClass == CharacterClass.FIGHTER ? 10 : 5;
		return Math.max(baseHp + getModifierFor(constitution), 1);
	}
	
	private int getModifierFor(int ability){
		return ability/2 - 5;
	}
	
	private boolean attackHits(Character opponent, int dieRoll) {
		int levelDieBonus = characterClass == CharacterClass.FIGHTER ? level - 1 : level/2;
		return dieRoll + levelDieBonus + getModifierFor(strength) >= opponent.armorClass;
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
	
	public void setCharacterClass(CharacterClass characterClass) {
		if (characterClass == CharacterClass.FIGHTER)
			this.hitPoints = 10;
		this.characterClass = characterClass;
	}
	
	public CharacterClass getCharacterClass() {
		return this.characterClass;
	}
	
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