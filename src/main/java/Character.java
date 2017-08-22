public class Character {
	private String name;
	private Alignment alignment; //enum you made in Alignment.java
	private int strength = 10, dexterity = 10, constitution = 10, wisdom = 10, intelligence = 10, charisma = 10;
	
	public int armorClass = 10;
	public int hitPoints = 5;
	
	public Character(){}
	public Character(int strength, int dexterity, int constitution, int wisdom, int intelligence, int charisma){
		this.strength = strength;
		this.dexterity = dexterity;
		this.constitution = constitution;
		this.wisdom = wisdom;
		this.intelligence = intelligence;
		this.charisma = charisma;
	
	}
	
	public void attack(Character opponent, int dieRoll){
		int strengthModifier = strength/2 - 5;
		
		if (dieRoll == 20)
			opponent.hitPoints = opponent.hitPoints - (2 + 2 * strengthModifier);
		else if (dieRoll + strengthModifier >= opponent.armorClass)
			opponent.hitPoints = opponent.hitPoints - (1 + strengthModifier);
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
		
}