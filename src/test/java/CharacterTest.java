import org.junit.Test; //to use @Test
import org.junit.Ignore;
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
		
		assertHpLost(1, worstCharacter);
	}
	
	@Test
	public void testWeakAttack(){
	
		int dieRoll = 3;
		
		mainCharacter.attack(worstCharacter,dieRoll);
		
		assertHpLost(0, worstCharacter);
	}
	
	@Test
	public void testPerfectAttack(){
	
		int dieRoll = 20;
		
		mainCharacter.attack(worstCharacter,dieRoll);
		
		assertHpLost(2, worstCharacter);
	}
	
	@Test
	public void opponentWithHighArmorClassIsHardToHit(){
	
		worstCharacter.armorClass = 20;
		int dieRoll = 11;
		
		mainCharacter.attack(worstCharacter,dieRoll);
		
		assertHpLost(0, worstCharacter);
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
		mainCharacter = new Character(CharacterClass.UNCLASSED, 17, 10, 10, 10, 10, 10);
		
		mainCharacter.attack(worstCharacter, 7);
		
		assertHpLost(4, worstCharacter);
	}
	
	@Test
	public void strengthModifierDoubledForAttackAndDamageOnCriticalHit() {
		// 17 should have +3 modifier
		mainCharacter = new Character(CharacterClass.UNCLASSED, 17, 10, 10, 10, 10, 10);
		
		mainCharacter.attack(worstCharacter, 20);
		
		assertHpLost(8, worstCharacter);
	}
	
	@Test
	public void attackDamageCannotBeModifiedBelowOne() {
		mainCharacter = new Character(CharacterClass.UNCLASSED, 1, 10, 10, 10, 10, 10);
		
		mainCharacter.attack(worstCharacter, 19);
		
		assertHpLost(1, worstCharacter);
	}
	
	@Test
	public void criticalDamageCannotBeModifiedBelowOne() {
		mainCharacter = new Character(CharacterClass.UNCLASSED, 1, 10, 10, 10, 10, 10);
		
		mainCharacter.attack(worstCharacter, 20);
		
		assertHpLost(1, worstCharacter);
	}
	
	@Test
	public void dexterityModifierAddedToArmor() {
		mainCharacter = new Character(CharacterClass.UNCLASSED, 10, 17, 10, 10, 10, 10);
		
		Assert.assertEquals(13, mainCharacter.armorClass);
	}
	
	@Test
	public void constitutionModifierAddedToHitPoints() {
		mainCharacter = new Character(CharacterClass.UNCLASSED, 10, 10, 17, 10, 10, 10);
		
		Assert.assertEquals(8, mainCharacter.hitPoints);
	}
	
	@Test
	public void hitpointsNeverLessThanOne() {
		mainCharacter = new Character(CharacterClass.UNCLASSED, 10, 10, 1, 10, 10, 10);
		
		Assert.assertEquals(1, mainCharacter.hitPoints);
	}
	
	@Test
	public void characterBeginsWithZeroXp() {
		Assert.assertEquals(0, mainCharacter.xp);
	}
	
	@Test
	public void onSuccessfulAttackCharacterGains10Xp() {
		mainCharacter.attack(worstCharacter, 10);
		Assert.assertEquals(10, mainCharacter.xp);
	}
	
	@Test
	public void onCriticalSuccessfulAttackCharacterGains10Xp() {
		mainCharacter.attack(worstCharacter, 20);
		Assert.assertEquals(10, mainCharacter.xp);
	}

	@Test
	public void onMissNoXpGained() {
		mainCharacter.attack(worstCharacter, 1);
		Assert.assertEquals(0, mainCharacter.xp);
	}
	
	@Test
	public void defaultLevelIsOne() {
		Assert.assertEquals(1, mainCharacter.level);
	}
	
	@Test
	public void characterLevelsUpEvery1000Xp(){
		levelUp(mainCharacter);
		Assert.assertEquals(2, mainCharacter.level);
		
		levelUp(mainCharacter);
		Assert.assertEquals(3, mainCharacter.level);
	}
	
	@Test
	public void hitPointsIncreaseByFiveWithLevelUp(){
		levelUp(mainCharacter);
		
		Assert.assertEquals(10, mainCharacter.hitPoints);
	}
	
	@Test
	public void constitutionModifierAddedToLevelUpHp(){
		mainCharacter = new Character(CharacterClass.UNCLASSED, 10,10,17,10,10,10);
		levelUp(mainCharacter);
		
		Assert.assertEquals(16, mainCharacter.hitPoints);
	}
	
	@Test
	public void levelingUpGivesAtLeastAHitpoint(){
		mainCharacter = new Character(CharacterClass.UNCLASSED, 10,10,1,10,10,10);
		levelUp(mainCharacter);
		
		Assert.assertEquals(2, mainCharacter.hitPoints);
	}
	
	@Test
	public void characterHasPlusOneAttackAtLevelTwo() {
		levelUp(mainCharacter);
		
		mainCharacter.attack(worstCharacter, 9);
		
		assertHpLost(1, worstCharacter);
	}
	
	@Test
	public void canSetAndGetCharacterClass() {
		mainCharacter.setCharacterClass(CharacterClass.FIGHTER);
		
		Assert.assertEquals(CharacterClass.FIGHTER, mainCharacter.getCharacterClass());
	}
	
	@Test
	public void fighterGainsOneEveryLevelToAttackRollBonus() {
		mainCharacter.setCharacterClass(CharacterClass.FIGHTER);
		
		levelUp(mainCharacter);
		mainCharacter.attack(worstCharacter, 9);
		assertHpLost(1, worstCharacter);
		
		worstCharacter = new Character();
		levelUp(mainCharacter);
		
		mainCharacter.attack(worstCharacter, 8);
		assertHpLost(1, worstCharacter);
	}
	
	@Test
	public void fighterStartsWithTenHitpoints() {
		mainCharacter = new Character(CharacterClass.FIGHTER, 10, 10, 10, 10, 10, 10);
		
		Assert.assertEquals(10, mainCharacter.hitPoints);
	}
	
	@Test
	public void rogueDoesTripleDamageOnCriticalHit() {
		mainCharacter = new Character(CharacterClass.ROGUE, 10, 10, 10, 10, 10, 10);
		mainCharacter.attack(worstCharacter, 20);
		assertHpLost(3, worstCharacter);
	}
	
	@Test
	public void rogueIgnoresEnemyDexterityACBonusWhenAttacking() {
		mainCharacter = new Character(CharacterClass.ROGUE, 10, 10, 10, 10, 10, 10);
		worstCharacter = new Character(CharacterClass.UNCLASSED, 10,17,10,10,10,10);
		
		mainCharacter.attack(worstCharacter, 10);
		
		assertHpLost(1, worstCharacter);
	}

	@Test
	public void rogueAttacksNegativeDexterityModifierNormally() {
		mainCharacter = new Character(CharacterClass.ROGUE, 10, 10, 10, 10, 10, 10);
		worstCharacter = new Character(CharacterClass.UNCLASSED, 10,1,10,10,10,10);
		
		mainCharacter.attack(worstCharacter, 4);
		
		assertHpLost(0, worstCharacter);
		
		mainCharacter.attack(worstCharacter, 5);
		
		assertHpLost(1, worstCharacter);
	}
	
	@Test
	public void rogueAddsDexModifierToAttacksInsteadOfStrength() {
		mainCharacter = new Character(CharacterClass.ROGUE, 10, 16, 10, 10, 10, 10);
		
		mainCharacter.attack(worstCharacter, 7);
		
		assertHpLost(1, worstCharacter); //jesse's turn to pass test
	}
	
	private void levelUp(Character character){
		for(int i = 0; i < 100; i++)
			character.attack(new Character(), 10);
	}
	
	private void assertHpLost(int hpLost, Character character) {
		Assert.assertEquals("Mismatch in expected HP", 5 - hpLost, character.hitPoints);
	}
	
	
}