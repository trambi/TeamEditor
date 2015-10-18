package org.tisseursdechimeres.bb.filter.obblm.test;

import org.tisseursdechimeres.bb.Configuration;
import org.tisseursdechimeres.bb.Position;
import org.tisseursdechimeres.bb.Race;
import org.tisseursdechimeres.bb.filter.obblm.OBBLMPositionConverter;

import junit.framework.TestCase;

public class OBBLMPositionConverterTest extends TestCase {
	
protected Configuration conf;
	
	public OBBLMPositionConverterTest(){
		conf = Configuration.getInstance();
		conf.load();
	}
	
	public void testHumanPositionsFrom(){
		Position pos = new Position();
		Race race = new Race();
		OBBLMPositionConverter converter = new OBBLMPositionConverter();
		Configuration conf = Configuration.getInstance();
		int converted = 0;
		
		conf.load();
		race.setName("human");
		
		pos = conf.getPositionByNameAndRace("lineman", race);
		converter.fromPosition(pos);
		assertEquals(90,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("catcher", race);
		converter.fromPosition(pos);
		assertEquals(91,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("thrower", race);
		converter.fromPosition(pos);
		assertEquals(92,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("blitzer", race);
		converter.fromPosition(pos);
		assertEquals(93,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("ogre", race);
		converter.fromPosition(pos);
		assertEquals(94,converter.getIndex());
		converted++;
		
		assertEquals(5,converted);
	}
	
	public void testDwarfPositionsFrom(){
		Position pos = new Position();
		Race race = new Race();
		OBBLMPositionConverter converter = new OBBLMPositionConverter();
		Configuration conf = Configuration.getInstance();
		int converted = 0;
		
		conf.load();
		race.setName("dwarf");
		
		pos = conf.getPositionByNameAndRace("blocker", race);
		converter.fromPosition(pos);
		assertEquals(40,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("runner", race);
		converter.fromPosition(pos);
		assertEquals(41,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("blitzer", race);
		converter.fromPosition(pos);
		assertEquals(42,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("troll_slayer", race);
		converter.fromPosition(pos);
		assertEquals(43,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("deathroller", race);
		converter.fromPosition(pos);
		assertEquals(44,converter.getIndex());
		converted++;
		
		assertEquals(5,converted);
	}
	
	public void testWoodElfPositionsFrom(){
		Position pos = new Position();
		Race race = new Race();
		OBBLMPositionConverter converter = new OBBLMPositionConverter();
		Configuration conf = Configuration.getInstance();
		int converted = 0;
		
		conf.load();
		race.setName("wood_elf");
		
		pos = conf.getPositionByNameAndRace("lineman", race);
		converter.fromPosition(pos);
		assertEquals(200,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("catcher", race);
		converter.fromPosition(pos);
		assertEquals(201,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("thrower", race);
		converter.fromPosition(pos);
		assertEquals(202,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("wardancer", race);
		converter.fromPosition(pos);
		assertEquals(203,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("treeman", race);
		converter.fromPosition(pos);
		assertEquals(204,converter.getIndex());
		converted++;
		
		assertEquals(5,converted);
	}
	
	public void testSkavenPositionsFrom(){
		Position pos = new Position();
		Race race = new Race();
		OBBLMPositionConverter converter = new OBBLMPositionConverter();
		Configuration conf = Configuration.getInstance();
		int converted = 0;
		
		conf.load();
		race.setName("skaven");
		
		pos = conf.getPositionByNameAndRace("lineman", race);
		converter.fromPosition(pos);
		assertEquals(190,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("thrower", race);
		converter.fromPosition(pos);
		assertEquals(191,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("gutter_runner", race);
		converter.fromPosition(pos);
		assertEquals(192,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("storm_vermin", race);
		converter.fromPosition(pos);
		assertEquals(193,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("rat_ogre", race);
		converter.fromPosition(pos);
		assertEquals(194,converter.getIndex());
		converted++;
		
		assertEquals(5,converted);
	}
	
	public void testOrcPositionsFrom(){
		Position pos = new Position();
		Race race = new Race();
		OBBLMPositionConverter converter = new OBBLMPositionConverter();
		Configuration conf = Configuration.getInstance();
		int converted = 0;
		
		conf.load();
		race.setName("orc");
		
		pos = conf.getPositionByNameAndRace("lineman", race);
		converter.fromPosition(pos);
		assertEquals(120,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("goblin", race);
		converter.fromPosition(pos);
		assertEquals(121,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("thrower", race);
		converter.fromPosition(pos);
		assertEquals(122,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("black_orc", race);
		converter.fromPosition(pos);
		assertEquals(123,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("blitzer", race);
		converter.fromPosition(pos);
		assertEquals(124,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("troll", race);
		converter.fromPosition(pos);
		assertEquals(125,converter.getIndex());
		converted++;
		
		assertEquals(6,converted);
	}
	
	public void testLizarmanPositionsFrom(){
		Position pos = new Position();
		Race race = new Race();
		OBBLMPositionConverter converter = new OBBLMPositionConverter();
		Configuration conf = Configuration.getInstance();
		int converted = 0;
		
		conf.load();
		race.setName("lizardman");
		
		pos = conf.getPositionByNameAndRace("skink", race);
		converter.fromPosition(pos);
		assertEquals(110,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("saurus", race);
		converter.fromPosition(pos);
		assertEquals(111,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("kroxigor", race);
		converter.fromPosition(pos);
		assertEquals(112,converter.getIndex());
		converted++;
		
		assertEquals(3,converted);
	}
	
	public void testGoblinPositionsFrom(){
		Position pos = new Position();
		Race race = new Race();
		OBBLMPositionConverter converter = new OBBLMPositionConverter();
		Configuration conf = Configuration.getInstance();
		int converted = 0;
		
		conf.load();
		race.setName("goblin");
		
		pos = conf.getPositionByNameAndRace("goblin", race);
		converter.fromPosition(pos);
		assertEquals(60,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("loney", race);
		converter.fromPosition(pos);
		assertEquals(63,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("troll", race);
		converter.fromPosition(pos);
		assertEquals(65,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("pogoer", race);
		converter.fromPosition(pos);
		assertEquals(62,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("fanatic", race);
		converter.fromPosition(pos);
		assertEquals(64,converter.getIndex());
		converted++;
				
		pos = conf.getPositionByNameAndRace("bombardier", race);
		converter.fromPosition(pos);
		assertEquals(61,converter.getIndex());
		converted++;
		
		assertEquals(6,converted);
	}
	
	public void testChaosPositionsFrom(){
		Position pos = new Position();
		Race race = new Race();
		OBBLMPositionConverter converter = new OBBLMPositionConverter();
		Configuration conf = Configuration.getInstance();
		int converted = 0;
		
		conf.load();
		race.setName("chaos");
		
		pos = conf.getPositionByNameAndRace("beastman", race);
		converter.fromPosition(pos);
		assertEquals(10,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("chaos_warrior", race);
		converter.fromPosition(pos);
		assertEquals(11,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("minotaur", race);
		converter.fromPosition(pos);
		assertEquals(12,converter.getIndex());
		converted++;
		
		assertEquals(3,converted);
	}
	
	public void testDarkElfPositionsFrom(){
		Position pos = new Position();
		Race race = new Race();
		OBBLMPositionConverter converter = new OBBLMPositionConverter();
		Configuration conf = Configuration.getInstance();
		int converted = 0;
		
		conf.load();
		race.setName("dark_elf");
		
		pos = conf.getPositionByNameAndRace("lineman", race);
		converter.fromPosition(pos);
		assertEquals(30,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("runner", race);
		converter.fromPosition(pos);
		assertEquals(31,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("assassin", race);
		converter.fromPosition(pos);
		assertEquals(32,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("blitzer", race);
		converter.fromPosition(pos);
		assertEquals(33,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("witch", race);
		converter.fromPosition(pos);
		assertEquals(34,converter.getIndex());
		converted++;
		
		assertEquals(5,converted);
	}
	
	public void testUndeadPositionsFrom(){
		Position pos = new Position();
		Race race = new Race();
		OBBLMPositionConverter converter = new OBBLMPositionConverter();
		Configuration conf = Configuration.getInstance();
		int converted = 0;
		
		conf.load();
		race.setName("undead");
		
		pos = conf.getPositionByNameAndRace("skeleton", race);
		converter.fromPosition(pos);
		assertEquals(170,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("zombie", race);
		converter.fromPosition(pos);
		assertEquals(171,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("ghoul", race);
		converter.fromPosition(pos);
		assertEquals(172,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("wight", race);
		converter.fromPosition(pos);
		assertEquals(173,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("mummy", race);
		converter.fromPosition(pos);
		assertEquals(174,converter.getIndex());
		converted++;
		
		assertEquals(5,converted);
	}
	
	public void testHalflingPositionsFrom(){
		Position pos = new Position();
		Race race = new Race();
		OBBLMPositionConverter converter = new OBBLMPositionConverter();
		Configuration conf = Configuration.getInstance();
		int converted = 0;
		
		conf.load();
		race.setName("halfling");
		
		pos = conf.getPositionByNameAndRace("halfling", race);
		converter.fromPosition(pos);
		assertEquals(70,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("treeman", race);
		converter.fromPosition(pos);
		assertEquals(71,converter.getIndex());
		converted++;
		
		assertEquals(2,converted);
	}
	
	public void testNorsePositionsFrom(){
		Position pos = new Position();
		Race race = new Race();
		OBBLMPositionConverter converter = new OBBLMPositionConverter();
		Configuration conf = Configuration.getInstance();
		int converted = 0;
		
		conf.load();
		race.setName("norse");
		
		pos = conf.getPositionByNameAndRace("lineman", race);
		converter.fromPosition(pos);
		assertEquals(140,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("thrower", race);
		converter.fromPosition(pos);
		assertEquals(141,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("catcher", race);
		converter.fromPosition(pos);
		assertEquals(142,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("blitzer", race);
		converter.fromPosition(pos);
		assertEquals(143,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("ulfwerener", race);
		converter.fromPosition(pos);
		assertEquals(144,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("snow_troll", race);
		converter.fromPosition(pos);
		assertEquals(145,converter.getIndex());
		converted++;
		
		assertEquals(6,converted);
	}
	
	public void testAmazonPositionsFrom(){
		Position pos = new Position();
		Race race = new Race();
		OBBLMPositionConverter converter = new OBBLMPositionConverter();
		int converted = 0;
		
		conf.load();
		race.setName("amazon");
		
		pos = conf.getPositionByNameAndRace("linewoman", race);
		converter.fromPosition(pos);
		assertEquals(1,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("catcher", race);
		converter.fromPosition(pos);
		assertEquals(3,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("thrower", race);
		converter.fromPosition(pos);
		assertEquals(2,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("blitzer", race);
		converter.fromPosition(pos);
		assertEquals(4,converter.getIndex());
		converted++;
		
		assertEquals(4,converted);
	}
	
	public void testElfPositionsFrom(){
		Position pos = new Position();
		Race race = new Race();
		OBBLMPositionConverter converter = new OBBLMPositionConverter();
		int converted = 0;
		
		conf.load();
		race.setName("elf");
		
		pos = conf.getPositionByNameAndRace("lineman", race);
		converter.fromPosition(pos);
		assertEquals(50,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("thrower", race);
		converter.fromPosition(pos);
		assertEquals(51,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("catcher", race);
		converter.fromPosition(pos);
		assertEquals(52,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("blitzer", race);
		converter.fromPosition(pos);
		assertEquals(53,converter.getIndex());
		converted++;
		
		assertEquals(4,converted);
	}
	
	public void testHighElfPositionsFrom(){
		Position pos = new Position();
		Race race = new Race();
		OBBLMPositionConverter converter = new OBBLMPositionConverter();
		int converted = 0;
		
		conf.load();
		race.setName("high_elf");
		
		pos = conf.getPositionByNameAndRace("lineman", race);
		converter.fromPosition(pos);
		assertEquals(80,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("thrower", race);
		converter.fromPosition(pos);
		assertEquals(81,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("catcher", race);
		converter.fromPosition(pos);
		assertEquals(82,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("blitzer", race);
		converter.fromPosition(pos);
		assertEquals(83,converter.getIndex());
		converted++;
		
		assertEquals(4,converted);
	}
	
	public void testKhemriPositionsFrom(){
		Position pos = new Position();
		Race race = new Race();
		OBBLMPositionConverter converter = new OBBLMPositionConverter();
		int converted = 0;
		
		conf.load();
		race.setName("khemri");
		
		pos = conf.getPositionByNameAndRace("skeleton", race);
		converter.fromPosition(pos);
		assertEquals(100,converter.getIndex());
		converted++;

		pos = conf.getPositionByNameAndRace("thro_ra", race);
		converter.fromPosition(pos);
		assertEquals(101,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("blitz_ra", race);
		converter.fromPosition(pos);
		assertEquals(102,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("tomb_guardian", race);
		converter.fromPosition(pos);
		assertEquals(103,converter.getIndex());
		converted++;
		
		assertEquals(4,converted);
	}
	
	public void testNecromanticPositionsFrom(){
		Position pos = new Position();
		Race race = new Race();
		OBBLMPositionConverter converter = new OBBLMPositionConverter();
		int converted = 0;
		
		conf.load();
		race.setName("necromantic");
		
		pos = conf.getPositionByNameAndRace("zombie", race);
		converter.fromPosition(pos);
		assertEquals(130,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("ghoul", race);
		converter.fromPosition(pos);
		assertEquals(131,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("wight", race);
		converter.fromPosition(pos);
		assertEquals(132,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("flesh_golem", race);
		converter.fromPosition(pos);
		assertEquals(133,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("werewolf", race);
		converter.fromPosition(pos);
		assertEquals(134,converter.getIndex());
		converted++;
		
		assertEquals(5,converted);
	}
	
	public void testNurglePositionsFrom(){
		Position pos = new Position();
		Race race = new Race();
		OBBLMPositionConverter converter = new OBBLMPositionConverter();
		int converted = 0;
		
		conf.load();
		race.setName("nurgle");
		
		pos = conf.getPositionByNameAndRace("rotter", race);
		converter.fromPosition(pos);
		assertEquals(150,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("pestigor", race);
		converter.fromPosition(pos);
		assertEquals(151,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("nurgle_warrior", race);
		converter.fromPosition(pos);
		assertEquals(152,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("beast_of_nurgle", race);
		converter.fromPosition(pos);
		assertEquals(153,converter.getIndex());
		converted++;
		
		assertEquals(4,converted);
	}
	
	public void testOgrePositionsFrom(){
		Position pos = new Position();
		Race race = new Race();
		OBBLMPositionConverter converter = new OBBLMPositionConverter();
		int converted = 0;
		
		conf.load();
		race.setName("ogre");
		
		pos = conf.getPositionByNameAndRace("snotling", race);
		converter.fromPosition(pos);
		assertEquals(160,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("ogre", race);
		converter.fromPosition(pos);
		assertEquals(161,converter.getIndex());
		converted++;
		
		assertEquals(2,converted);
	}
	
	public void testVampirePositionsFrom(){
		Position pos = new Position();
		Race race = new Race();
		OBBLMPositionConverter converter = new OBBLMPositionConverter();
		int converted = 0;
		
		conf.load();
		race.setName("vampire");
		
		pos = conf.getPositionByNameAndRace("thrall", race);
		converter.fromPosition(pos);
		assertEquals(180,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("vampire", race);
		converter.fromPosition(pos);
		assertEquals(181,converter.getIndex());
		converted++;
		
		assertEquals(2,converted);
	}
	
	public void testSlannPositionsFrom(){
		Position pos = new Position();
		Race race = new Race();
		OBBLMPositionConverter converter = new OBBLMPositionConverter();
		int converted = 0;
		
		conf.load();
		race.setName("slann");
		
		pos = conf.getPositionByNameAndRace("lineman", race);
		converter.fromPosition(pos);
		assertEquals(220,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("catcher", race);
		converter.fromPosition(pos);
		assertEquals(221,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("blitzer", race);
		converter.fromPosition(pos);
		assertEquals(222,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("kroxigor", race);
		converter.fromPosition(pos);
		assertEquals(223,converter.getIndex());
		converted++;
		
		assertEquals(4,converted);
	}
	
	public void testChaosPactPositionsFrom(){
		Position pos = new Position();
		Race race = new Race();
		OBBLMPositionConverter converter = new OBBLMPositionConverter();
		int converted = 0;
		
		conf.load();
		race.setName("chaos_pact");
		
		pos = conf.getPositionByNameAndRace("marauder", race);
		converter.fromPosition(pos);
		assertEquals(210,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("goblin_renegade", race);
		converter.fromPosition(pos);
		assertEquals(211,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("skaven_renegade", race);
		converter.fromPosition(pos);
		assertEquals(212,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("dark_elf_renegade", race);
		converter.fromPosition(pos);
		assertEquals(213,converter.getIndex());
		converted++;

		pos = conf.getPositionByNameAndRace("troll", race);
		converter.fromPosition(pos);
		assertEquals(214,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("ogre", race);
		converter.fromPosition(pos);
		assertEquals(215,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("minotaur", race);
		converter.fromPosition(pos);
		assertEquals(216,converter.getIndex());
		converted++;
		
		assertEquals(7,converted);
	}
	
	public void testUnderworldPositionsFrom(){
		Position pos = new Position();
		Race race = new Race();
		OBBLMPositionConverter converter = new OBBLMPositionConverter();
		int converted = 0;
		
		conf.load();
		race.setName("underworld");
		
		pos = conf.getPositionByNameAndRace("underworld_goblin", race);
		converter.fromPosition(pos);
		assertEquals(230,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("lineman", race);
		converter.fromPosition(pos);
		assertEquals(231,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("thrower", race);
		converter.fromPosition(pos);
		assertEquals(232,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("storm_vermin", race);
		converter.fromPosition(pos);
		assertEquals(233,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("warpstone_troll", race);
		converter.fromPosition(pos);
		assertEquals(234,converter.getIndex());
		converted++;
		
		assertEquals(5,converted);
	}
}
