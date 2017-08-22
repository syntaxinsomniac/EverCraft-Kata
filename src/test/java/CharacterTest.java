import org.junit.Test; //to use @Test
import org.junit.Before;
import org.junit.Assert; //to use assertEquals to make sure things are equal

public class CharacterTest {

	Character mainCharacter;
	Character worstCharacter;
	
	@Before
	public void setup(){
		
		mainCharacter = new Character();
		worstCharacter = new Character();
		
	}

	@Test
	public void testCanSetNGetName(){
		
		mainCharacter.setName("Jandrew");
		
		Assert.assertEquals("Jandrew", mainCharacter.getName());
	}
	
	@Test
	public void testCanSetNGetAlignment(){  //uses the enum you made in Alignment.java
		
		mainCharacter.setAlignment(Alignment.EVIL);
		
		Assert.assertEquals(Alignment.EVIL, mainCharacter.getAlignment());
	}
	
	@Test
	public void testVerifyArmorNHitPointValue(){
	
		Assert.assertEquals(10, mainCharacter.armorClass);
		Assert.assertEquals(5, mainCharacter.hitPoints);
	
	}
	
	@Test
	public void testStrongAttack(){
	
		int dieRoll = 19;
		
		mainCharacter.attack(worstCharacter,dieRoll);
		
		Assert.assertEquals(4, worstCharacter.hitPoints);
	
	}
	
	@Test
	public void testWeakAttack(){
	
		int dieRoll = 3;
		
		mainCharacter.attack(worstCharacter,dieRoll);
		
		Assert.assertEquals(5, worstCharacter.hitPoints);
	
	}
	
	@Test
	public void testPerfectAttack(){
	
		int dieRoll = 20;
		
		mainCharacter.attack(worstCharacter,dieRoll);
		
		Assert.assertEquals(3, worstCharacter.hitPoints);
	
	}
	
	@Test
	public void opponentWithHighArmorClassIsHardToHit(){
	
		worstCharacter.armorClass = 20;
		int dieRoll = 11;
		
		mainCharacter.attack(worstCharacter,dieRoll);
		
		Assert.assertEquals(5, worstCharacter.hitPoints);
	
	}

	@Test
	public void abilityScoresDefaultToTen(){
		Assert.assertEquals(10, mainCharacter.getStrength());
		Assert.assertEquals(10, mainCharacter.getDexterity());
		Assert.assertEquals(10, mainCharacter.getConstitution());
		Assert.assertEquals(10, mainCharacter.getWisdom());
		Assert.assertEquals(10, mainCharacter.getIntelligence());
		Assert.assertEquals(10, mainCharacter.getCharisma());
	}
	
	@Test
	public void strengthModifierAddedToAttackAndDamageOnNormalHit() {
		// 17 should have +3 modifier
		mainCharacter = new Character(17, 10, 10, 10, 10, 10);
		
		mainCharacter.attack(worstCharacter, 7);
		
		Assert.assertEquals(1, worstCharacter.hitPoints);
	}
	
	@Test
	public void strengthModifierDoubledForAttackAndDamageOnCriticalHit() {
		// 17 should have +3 modifier
		mainCharacter = new Character(17, 10, 10, 10, 10, 10);
		
		mainCharacter.attack(worstCharacter, 20);
		
		Assert.assertEquals(-3, worstCharacter.hitPoints);
	}
	
	@Test
	public void attackDamageCannotBeModifiedBelowOne() {
		mainCharacter = new Character(1, 10, 10, 10, 10, 10);
		
		mainCharacter.attack(worstCharacter, 19);
		
		Assert.assertEquals(4, worstCharacter.hitPoints);
	}
	
}