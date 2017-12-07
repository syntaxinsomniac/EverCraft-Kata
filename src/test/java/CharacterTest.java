import org.junit.Test; //to use @Test
import org.junit.Ignore;
import org.junit.Before;
import org.junit.Assert; //to use assertEquals to make sure things are equal

import static org.junit.Assert.assertEquals;

public class CharacterTest {

	Character mainCharacter;
	Character worstCharacter;
	Character worstCharacter2;
	Character worstCharacter3;
	Character worstCharacter4;
	Character worstCharacter5;
	
	@Before
	public void setup(){
		
		mainCharacter = new Character();
		worstCharacter = new Character();
		worstCharacter2 = new Character();
		worstCharacter3 = new Character();
		worstCharacter4 = new Character();
		worstCharacter5 = new Character();
		
	}

	@Test
	public void testCanSetNGetName(){	
		mainCharacter.setName("Jandrew");
		
		assertEquals("Jandrew", mainCharacter.getName());
	}
	
	@Test
	public void testCanSetNGetAlignment(){  //uses the enum you made in Alignment.java
		mainCharacter.setAlignment(Alignment.EVIL);
		
		assertEquals(Alignment.EVIL, mainCharacter.getAlignment());
	}
	
	@Test
	public void testVerifyArmorNHitPointValue(){
	
		assertEquals(10, mainCharacter.armorClass);
		assertEquals(5, mainCharacter.hitPoints);
	
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
		assertEquals(10, mainCharacter.getStrength());
		assertEquals(10, mainCharacter.getDexterity());
		assertEquals(10, mainCharacter.getConstitution());
		assertEquals(10, mainCharacter.getWisdom());
		assertEquals(10, mainCharacter.getIntelligence());
		assertEquals(10, mainCharacter.getCharisma());
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
		
		assertEquals(13, mainCharacter.armorClass);
	}
	
	@Test
	public void constitutionModifierAddedToHitPoints() {
		mainCharacter = new Character(CharacterClass.UNCLASSED, 10, 10, 17, 10, 10, 10);
		
		assertEquals(8, mainCharacter.hitPoints);
	}
	
	@Test
	public void hitpointsNeverLessThanOne() {
		mainCharacter = new Character(CharacterClass.UNCLASSED, 10, 10, 1, 10, 10, 10);
		
		assertEquals(1, mainCharacter.hitPoints);
	}
	
	@Test
	public void characterBeginsWithZeroXp() {
		assertEquals(0, mainCharacter.xp);
	}
	
	@Test
	public void onSuccessfulAttackCharacterGains10Xp() {
		mainCharacter.attack(worstCharacter, 10);
		assertEquals(10, mainCharacter.xp);
	}
	
	@Test
	public void onCriticalSuccessfulAttackCharacterGains10Xp() {
		mainCharacter.attack(worstCharacter, 20);
		assertEquals(10, mainCharacter.xp);
	}

	@Test
	public void onMissNoXpGained() {
		mainCharacter.attack(worstCharacter, 1);
		assertEquals(0, mainCharacter.xp);
	}
	
	@Test
	public void defaultLevelIsOne() {
		assertEquals(1, mainCharacter.level);
	}
	
	@Test
	public void characterLevelsUpEvery1000Xp(){
		levelUp(mainCharacter);
		assertEquals(2, mainCharacter.level);
		
		levelUp(mainCharacter);
		assertEquals(3, mainCharacter.level);
	}
	
	@Test
	public void hitPointsIncreaseByFiveWithLevelUp(){
		levelUp(mainCharacter);
		
		assertEquals(10, mainCharacter.hitPoints);
	}
	
	@Test
	public void constitutionModifierAddedToLevelUpHp(){
		mainCharacter = new Character(CharacterClass.UNCLASSED, 10,10,17,10,10,10);
		levelUp(mainCharacter);
		
		assertEquals(16, mainCharacter.hitPoints);
	}
	
	@Test
	public void levelingUpGivesAtLeastAHitpoint(){
		mainCharacter = new Character(CharacterClass.UNCLASSED, 10,10,1,10,10,10);
		levelUp(mainCharacter);
		
		assertEquals(2, mainCharacter.hitPoints);
	}
	
	@Test
	public void characterHasPlusOneAttackAtLevelTwo() {
		levelUp(mainCharacter);
		
		mainCharacter.attack(worstCharacter, 9);
		
		assertHpLost(1, worstCharacter);
	}
	
	@Test
	public void fighterGainsOneEveryLevelToAttackRollBonus() {
		mainCharacter = new Character(CharacterClass.FIGHTER, 10, 10, 10, 10, 10, 10);
		
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
		
		assertEquals(10, mainCharacter.hitPoints);
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
		
		assertHpLost(4, worstCharacter);
	}
	
	@Test(expected = IllegalStateException.class) //jesse doesn't understand this shit
	public void rogueCannotBeGood() {
		mainCharacter = new Character(CharacterClass.ROGUE, 10, 10, 10, 10, 10, 10);
		
		mainCharacter.setAlignment(Alignment.GOOD);
	}
	
	@Test
	public void monkHasSixHitPointsPerLevel() {
		mainCharacter = new Character(CharacterClass.MONK, 10, 10, 10, 10, 10, 10);
		
		assertEquals(6, mainCharacter.hitPoints);
		
		levelUp(mainCharacter);
		
		assertEquals(12, mainCharacter.hitPoints);
	}
	
	@Test
	public void monkDoesThreeDamageByDefault() {
		mainCharacter = new Character(CharacterClass.MONK, 10, 10, 10, 10, 10, 10);
		
		mainCharacter.attack(worstCharacter, 19);
		
		assertHpLost(3, worstCharacter);
		
		worstCharacter = new Character();
		
		mainCharacter.attack(worstCharacter, 20);
		
		assertHpLost(6, worstCharacter);
	}
	
	@Test
	public void monkAddsPositiveWisdomModifierToArmorClassInsteadOfDexterity() {
		worstCharacter = new Character(CharacterClass.MONK, 10, 10, 10, 16, 10, 10);
		
		mainCharacter.attack(worstCharacter, 12);
		
		assertHpLost(0, worstCharacter);
		
		mainCharacter.attack(worstCharacter, 13);
		
		assertHpLost(1, worstCharacter);
	}
	
	@Test
	public void monkAttackRollIncreasedByOneEvery2ndAnd3rdLevel() {
		mainCharacter = new Character(CharacterClass.MONK, 10, 10, 10, 10, 10, 10);
		
		levelUp(mainCharacter); //level 2
		mainCharacter.attack(worstCharacter, 9);
		
		assertHpLost(3, worstCharacter);
		
		levelUp(mainCharacter); //level 3
		mainCharacter.attack(worstCharacter2, 8);
		
		assertHpLost(3, worstCharacter2);
		
		levelUp(mainCharacter); //level 4
		mainCharacter.attack(worstCharacter3, 7);
		
		assertHpLost(3, worstCharacter3);
		
		levelUp(mainCharacter); //level 5
		mainCharacter.attack(worstCharacter4, 6);
		
		assertHpLost(0, worstCharacter4);
		
		levelUp(mainCharacter); //level 6
		mainCharacter.attack(worstCharacter4, 6);
		
		assertHpLost(3, worstCharacter4);
		
		levelUp(mainCharacter); //level 7
		mainCharacter.attack(worstCharacter5, 5);
		
		assertHpLost(0, worstCharacter5);  //andrew must make test pass
	}

	@Test
	public void paladinHasEightHitPointsPerLevel() throws Exception {
		mainCharacter = new Character(CharacterClass.PALADIN, 10, 10, 10, 10, 10, 10);

		assertEquals(8, mainCharacter.hitPoints);
		assertEquals(8, mainCharacter.maxHitPoints);

		levelUp(mainCharacter);

		assertEquals(16, mainCharacter.hitPoints);
		assertEquals(16, mainCharacter.maxHitPoints);
	}

	private void levelUp(Character character){
		for(int i = 0; i < 100; i++)
			character.attack(new Character(), 10);
	}
	
	private void assertHpLost(int hpLost, Character character) {
		assertEquals("Mismatch in expected HP", character.maxHitPoints - hpLost, character.hitPoints);
	}
	
	
}