package org.tisseursdechimeres.bb.filter.obblm.test;

import org.tisseursdechimeres.bb.Configuration;
import org.tisseursdechimeres.bb.Race;
import org.tisseursdechimeres.bb.filter.obblm.OBBLMRaceConverter;

import junit.framework.TestCase;

public class OBBLMRaceConverterTest extends TestCase {
	protected Configuration conf;
	
	public OBBLMRaceConverterTest(){
		conf = Configuration.getInstance();
		conf.load();
	}
	
	public void testAreWriteable()
	{
		testNameRaceAreWriteable("amazon",true);
		testNameRaceAreWriteable("chaos",true);
		testNameRaceAreWriteable("dark_elf",true);
		testNameRaceAreWriteable("dwarf",true);
		testNameRaceAreWriteable("elf",true);
		testNameRaceAreWriteable("goblin",true);
		testNameRaceAreWriteable("halfling",true);
		testNameRaceAreWriteable("high_elf",true);
		testNameRaceAreWriteable("human",true);
		testNameRaceAreWriteable("khemri",true);
		testNameRaceAreWriteable("lizardman",true);
		testNameRaceAreWriteable("necromantic",true);
		testNameRaceAreWriteable("norse",true);
		testNameRaceAreWriteable("nurgle",true);
		testNameRaceAreWriteable("ogre",true);
		testNameRaceAreWriteable("orc",true);
		testNameRaceAreWriteable("skaven",true);
		testNameRaceAreWriteable("undead",true);
		testNameRaceAreWriteable("vampire",true);
		testNameRaceAreWriteable("wood_elf",true);
		testNameRaceAreWriteable("chaos_pact",true);
		testNameRaceAreWriteable("chaos_dwarf",true);
		testNameRaceAreWriteable("underworld",true);
		testNameRaceAreWriteable("slann",true);
	}
	
	private void testNameRaceAreWriteable( String raceName, boolean shouldBeWritable)
	{
		OBBLMRaceConverter converter = new OBBLMRaceConverter();
		Race race = conf.getRaceByName(raceName);
		assertEquals(converter.isRaceWriteable(race),shouldBeWritable);
	}
	
	public void  testRaceFrom(){
		Race race;
		OBBLMRaceConverter converter = new OBBLMRaceConverter();
		int converted = 0;
				
		race = conf.getRaceByName("amazon");
		assertTrue(converter.fromRace(race));
		assertEquals(0,converter.getIndex());
		converted++;

		race = conf.getRaceByName("chaos");
		assertTrue(converter.fromRace(race));
		assertEquals(1,converter.getIndex());
		converted++;
		
		race = conf.getRaceByName("chaos_dwarf");
		assertTrue(converter.fromRace(race));
		assertEquals(2,converter.getIndex());
		converted++;
		
		race = conf.getRaceByName("dark_elf");
		assertTrue(converter.fromRace(race));
		assertEquals(3,converter.getIndex());
		converted++;
		
		race = conf.getRaceByName("dwarf");
		assertTrue(converter.fromRace(race));
		assertEquals(4,converter.getIndex());
		converted++;
		
		race = conf.getRaceByName("elf");
		assertTrue(converter.fromRace(race));
		assertEquals(5,converter.getIndex());
		converted++;
		
		race = conf.getRaceByName("goblin");
		assertTrue(converter.fromRace(race));
		assertEquals(6,converter.getIndex());
		converted++;
		
		race = conf.getRaceByName("halfling");
		assertTrue(converter.fromRace(race));
		assertEquals(7,converter.getIndex());
		converted++;
		
		race = conf.getRaceByName("high_elf");
		assertTrue(converter.fromRace(race));
		assertEquals(8,converter.getIndex());
		converted++;
		
		race = conf.getRaceByName("human");
		assertTrue(converter.fromRace(race));
		assertEquals(9,converter.getIndex());
		converted++;
		
		race = conf.getRaceByName("khemri");
		assertTrue(converter.fromRace(race));
		assertEquals(10,converter.getIndex());
		converted++;
		
		race = conf.getRaceByName("lizardman");
		assertTrue(converter.fromRace(race));
		assertEquals(11,converter.getIndex());
		converted++;
		
		race = conf.getRaceByName("orc");
		assertTrue(converter.fromRace(race));
		assertEquals(12,converter.getIndex());
		converted++;

		race = conf.getRaceByName("necromantic");
		assertTrue(converter.fromRace(race));
		assertEquals(13,converter.getIndex());
		converted++;
		
		race = conf.getRaceByName("norse");
		assertTrue(converter.fromRace(race));
		assertEquals(14,converter.getIndex());
		converted++;

		race = conf.getRaceByName("nurgle");
		assertTrue(converter.fromRace(race));
		assertEquals(15,converter.getIndex());
		converted++;
		
		race = conf.getRaceByName("ogre");
		assertTrue(converter.fromRace(race));
		assertEquals(16,converter.getIndex());
		converted++;
		
		race = conf.getRaceByName("undead");
		assertTrue(converter.fromRace(race));
		assertEquals(17,converter.getIndex());
		converted++;
		
		race = conf.getRaceByName("vampire");
		assertTrue(converter.fromRace(race));
		assertEquals(18,converter.getIndex());
		converted++;

		race = conf.getRaceByName("skaven");
		assertTrue(converter.fromRace(race));
		assertEquals(19,converter.getIndex());
		converted++;
		
		race = conf.getRaceByName("wood_elf");
		assertTrue(converter.fromRace(race));
		assertEquals(20,converter.getIndex());
		converted++;

		race = conf.getRaceByName("chaos_pact");
		assertTrue(converter.fromRace(race));
		assertEquals(21,converter.getIndex());
		converted++;
		
		race = conf.getRaceByName("slann");
		assertTrue(converter.fromRace(race));
		assertEquals(22,converter.getIndex());
		converted++;
		
		race = conf.getRaceByName("underworld");
		assertTrue(converter.fromRace(race));
		assertEquals(23,converter.getIndex());
		converted++;

		assertEquals(24,converted);
	}
	
	public void  testRaceTo(){
		Race race = new Race();
		OBBLMRaceConverter converter = new OBBLMRaceConverter();
		int converted = 0;
		
		converter.setIndex(0);
		race = converter.toRace();
		assertNotNull(race);
		assertEquals("amazon",race.getName());
		converted++;

		converter.setIndex(1);
		race = converter.toRace();
		assertNotNull(race);
		assertEquals("chaos",race.getName());
		converted++;
		
		converter.setIndex(2);
		race = converter.toRace();
		assertNotNull(race);
		assertEquals("chaos_dwarf",race.getName());
		converted++;
		
		converter.setIndex(3);
		race = converter.toRace();
		assertNotNull(race);
		assertEquals("dark_elf",race.getName());
		converted++;
		
		converter.setIndex(4);
		race = converter.toRace();
		assertNotNull(race);
		assertEquals("dwarf",race.getName());
		converted++;
		
		converter.setIndex(5);
		race = converter.toRace();
		assertNotNull(race);
		assertEquals("elf",race.getName());
		converted++;
		
		converter.setIndex(6);
		race = converter.toRace();
		assertNotNull(race);
		assertEquals("goblin",race.getName());
		converted++;
		
		converter.setIndex(7);
		race = converter.toRace();
		assertNotNull(race);
		assertEquals("halfling",race.getName());
		converted++;
		
		converter.setIndex(8);
		race = converter.toRace();
		assertNotNull(race);
		assertEquals("high_elf",race.getName());
		converted++;
		
		converter.setIndex(9);
		race = converter.toRace();
		assertNotNull(race);
		assertEquals("human",race.getName());
		converted++;
		
		converter.setIndex(10);
		race = converter.toRace();
		assertNotNull(race);
		assertEquals("khemri",race.getName());
		converted++;
		
		converter.setIndex(11);
		race = converter.toRace();
		assertNotNull(race);
		assertEquals("lizardman",race.getName());
		converted++;
		
		converter.setIndex(12);
		race = converter.toRace();
		assertNotNull(race);
		assertEquals("orc",race.getName());
		converted++;
		
		converter.setIndex(13);
		race = converter.toRace();
		assertNotNull(race);
		assertEquals("necromantic",race.getName());
		converted++;

		converter.setIndex(14);
		race = converter.toRace();
		assertNotNull(race);
		assertEquals("norse",race.getName());
		converted++;
		
		converter.setIndex(15);
		race = converter.toRace();
		assertNotNull(race);
		assertEquals("nurgle",race.getName());
		converted++;

		converter.setIndex(16);
		race = converter.toRace();
		assertNotNull(race);
		assertEquals("ogre",race.getName());
		converted++;
		
		converter.setIndex(17);
		race = converter.toRace();
		assertNotNull(race);
		assertEquals("undead",race.getName());
		converted++;
		
		converter.setIndex(18);
		race = converter.toRace();
		assertNotNull(race);
		assertEquals("vampire",race.getName());
		converted++;
		
		converter.setIndex(19);
		race = converter.toRace();
		assertNotNull(race);
		assertEquals("skaven",race.getName());
		converted++;

		converter.setIndex(20);
		race = converter.toRace();
		assertNotNull(race);
		assertEquals("wood_elf",race.getName());
		converted++;
		
		converter.setIndex(21);
		race = converter.toRace();
		assertNotNull(race);
		assertEquals("chaos_pact",race.getName());
		converted++;

		converter.setIndex(22);
		race = converter.toRace();
		assertNotNull(race);
		assertEquals("slann",race.getName());
		converted++;
		
		converter.setIndex(23);
		race = converter.toRace();
		assertNotNull(race);
		assertEquals("underworld",race.getName());
		converted++;

		assertEquals(24,converted);
	}
}
