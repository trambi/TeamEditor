/*
Copyright (C) 2011  Bertrand MADET

This org.tisseursdechimeres.bb.filter.bbel.test is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This org.tisseursdechimeres.bb.filter.bbel.test is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this org.tisseursdechimeres.bb.filter.bbel.test.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.tisseursdechimeres.bb.filter.bbel.test;

import org.tisseursdechimeres.bb.Configuration;
import org.tisseursdechimeres.bb.Position;
import org.tisseursdechimeres.bb.Race;
import org.tisseursdechimeres.bb.filter.bbel.BBELPositionConverter;

import junit.framework.TestCase;

public class BBELPositionConverterTest extends TestCase {
	
	public void testHumanPositionsFrom(){
		Position pos = new Position();
		Race race = new Race();
		BBELPositionConverter converter = new BBELPositionConverter();
		Configuration conf = Configuration.getInstance();
		int converted = 0;
		
		conf.load();
		race.setName("human");
		
		pos = conf.getPositionByNameAndRace("lineman", race);
		converter.fromPosition(pos);
		assertEquals(1,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("catcher", race);
		converter.fromPosition(pos);
		assertEquals(2,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("thrower", race);
		converter.fromPosition(pos);
		assertEquals(3,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("blitzer", race);
		converter.fromPosition(pos);
		assertEquals(4,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("ogre", race);
		converter.fromPosition(pos);
		assertEquals(5,converter.getIndex());
		converted++;
		
		assertEquals(5,converted);
	}
	
	public void testDwarfPositionsFrom(){
		Position pos = new Position();
		Race race = new Race();
		BBELPositionConverter converter = new BBELPositionConverter();
		Configuration conf = Configuration.getInstance();
		int converted = 0;
		
		conf.load();
		race.setName("dwarf");
		
		pos = conf.getPositionByNameAndRace("blocker", race);
		converter.fromPosition(pos);
		assertEquals(6,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("runner", race);
		converter.fromPosition(pos);
		assertEquals(7,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("blitzer", race);
		converter.fromPosition(pos);
		assertEquals(8,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("troll_slayer", race);
		converter.fromPosition(pos);
		assertEquals(9,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("deathroller", race);
		converter.fromPosition(pos);
		assertEquals(10,converter.getIndex());
		converted++;
		
		assertEquals(5,converted);
	}
	
	public void testWoodElfPositionsFrom(){
		Position pos = new Position();
		Race race = new Race();
		BBELPositionConverter converter = new BBELPositionConverter();
		Configuration conf = Configuration.getInstance();
		int converted = 0;
		
		conf.load();
		race.setName("wood_elf");
		
		pos = conf.getPositionByNameAndRace("lineman", race);
		converter.fromPosition(pos);
		assertEquals(11,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("catcher", race);
		converter.fromPosition(pos);
		assertEquals(12,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("thrower", race);
		converter.fromPosition(pos);
		assertEquals(13,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("wardancer", race);
		converter.fromPosition(pos);
		assertEquals(14,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("treeman", race);
		converter.fromPosition(pos);
		assertEquals(15,converter.getIndex());
		converted++;
		
		assertEquals(5,converted);
	}
	
	public void testSkavenPositionsFrom(){
		Position pos = new Position();
		Race race = new Race();
		BBELPositionConverter converter = new BBELPositionConverter();
		Configuration conf = Configuration.getInstance();
		int converted = 0;
		
		conf.load();
		race.setName("skaven");
		
		pos = conf.getPositionByNameAndRace("lineman", race);
		converter.fromPosition(pos);
		assertEquals(16,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("thrower", race);
		converter.fromPosition(pos);
		assertEquals(17,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("gutter_runner", race);
		converter.fromPosition(pos);
		assertEquals(18,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("storm_vermin", race);
		converter.fromPosition(pos);
		assertEquals(19,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("rat_ogre", race);
		converter.fromPosition(pos);
		assertEquals(20,converter.getIndex());
		converted++;
		
		assertEquals(5,converted);
	}
	
	public void testOrcPositionsFrom(){
		Position pos = new Position();
		Race race = new Race();
		BBELPositionConverter converter = new BBELPositionConverter();
		Configuration conf = Configuration.getInstance();
		int converted = 0;
		
		conf.load();
		race.setName("orc");
		
		pos = conf.getPositionByNameAndRace("lineman", race);
		converter.fromPosition(pos);
		assertEquals(21,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("goblin", race);
		converter.fromPosition(pos);
		assertEquals(22,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("thrower", race);
		converter.fromPosition(pos);
		assertEquals(23,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("black_orc", race);
		converter.fromPosition(pos);
		assertEquals(24,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("blitzer", race);
		converter.fromPosition(pos);
		assertEquals(25,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("troll", race);
		converter.fromPosition(pos);
		assertEquals(26,converter.getIndex());
		converted++;
		
		assertEquals(6,converted);
	}
	
	public void testLizarmanPositionsFrom(){
		Position pos = new Position();
		Race race = new Race();
		BBELPositionConverter converter = new BBELPositionConverter();
		Configuration conf = Configuration.getInstance();
		int converted = 0;
		
		conf.load();
		race.setName("lizardman");
		
		pos = conf.getPositionByNameAndRace("skink", race);
		converter.fromPosition(pos);
		assertEquals(27,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("saurus", race);
		converter.fromPosition(pos);
		assertEquals(28,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("kroxigor", race);
		converter.fromPosition(pos);
		assertEquals(29,converter.getIndex());
		converted++;
		
		assertEquals(3,converted);
	}
	
	public void testGoblinPositionsFrom(){
		Position pos = new Position();
		Race race = new Race();
		BBELPositionConverter converter = new BBELPositionConverter();
		Configuration conf = Configuration.getInstance();
		int converted = 0;
		
		conf.load();
		race.setName("goblin");
		
		pos = conf.getPositionByNameAndRace("goblin", race);
		converter.fromPosition(pos);
		assertEquals(30,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("loney", race);
		converter.fromPosition(pos);
		assertEquals(31,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("troll", race);
		converter.fromPosition(pos);
		assertEquals(44,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("pogoer", race);
		converter.fromPosition(pos);
		assertEquals(45,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("fanatic", race);
		converter.fromPosition(pos);
		assertEquals(46,converter.getIndex());
		converted++;
				
		pos = conf.getPositionByNameAndRace("bombardier", race);
		converter.fromPosition(pos);
		assertEquals(107,converter.getIndex());
		converted++;
		
		assertEquals(6,converted);
	}
	
	public void testChaosPositionsFrom(){
		Position pos = new Position();
		Race race = new Race();
		BBELPositionConverter converter = new BBELPositionConverter();
		Configuration conf = Configuration.getInstance();
		int converted = 0;
		
		conf.load();
		race.setName("chaos");
		
		pos = conf.getPositionByNameAndRace("beastman", race);
		converter.fromPosition(pos);
		assertEquals(32,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("chaos_warrior", race);
		converter.fromPosition(pos);
		assertEquals(33,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("minotaur", race);
		converter.fromPosition(pos);
		assertEquals(34,converter.getIndex());
		converted++;
		
		assertEquals(3,converted);
	}
	
	public void testDarkElfPositionsFrom(){
		Position pos = new Position();
		Race race = new Race();
		BBELPositionConverter converter = new BBELPositionConverter();
		Configuration conf = Configuration.getInstance();
		int converted = 0;
		
		conf.load();
		race.setName("dark_elf");
		
		pos = conf.getPositionByNameAndRace("lineman", race);
		converter.fromPosition(pos);
		assertEquals(47,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("runner", race);
		converter.fromPosition(pos);
		assertEquals(48,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("assassin", race);
		converter.fromPosition(pos);
		assertEquals(49,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("blitzer", race);
		converter.fromPosition(pos);
		assertEquals(50,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("witch", race);
		converter.fromPosition(pos);
		assertEquals(51,converter.getIndex());
		converted++;
		
		assertEquals(5,converted);
	}
	
	public void testUndeadPositionsFrom(){
		Position pos = new Position();
		Race race = new Race();
		BBELPositionConverter converter = new BBELPositionConverter();
		Configuration conf = Configuration.getInstance();
		int converted = 0;
		
		conf.load();
		race.setName("undead");
		
		pos = conf.getPositionByNameAndRace("skeleton", race);
		converter.fromPosition(pos);
		assertEquals(54,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("zombie", race);
		converter.fromPosition(pos);
		assertEquals(55,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("ghoul", race);
		converter.fromPosition(pos);
		assertEquals(56,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("wight", race);
		converter.fromPosition(pos);
		assertEquals(57,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("mummy", race);
		converter.fromPosition(pos);
		assertEquals(58,converter.getIndex());
		converted++;
		
		assertEquals(5,converted);
	}
	
	public void testHalflingPositionsFrom(){
		Position pos = new Position();
		Race race = new Race();
		BBELPositionConverter converter = new BBELPositionConverter();
		Configuration conf = Configuration.getInstance();
		int converted = 0;
		
		conf.load();
		race.setName("halfling");
		
		pos = conf.getPositionByNameAndRace("halfling", race);
		converter.fromPosition(pos);
		assertEquals(60,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("treeman", race);
		converter.fromPosition(pos);
		assertEquals(61,converter.getIndex());
		converted++;
		
		assertEquals(2,converted);
	}
	
	public void testNorsePositionsFrom(){
		Position pos = new Position();
		Race race = new Race();
		BBELPositionConverter converter = new BBELPositionConverter();
		Configuration conf = Configuration.getInstance();
		int converted = 0;
		
		conf.load();
		race.setName("norse");
		
		pos = conf.getPositionByNameAndRace("lineman", race);
		converter.fromPosition(pos);
		assertEquals(62,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("thrower", race);
		converter.fromPosition(pos);
		assertEquals(63,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("catcher", race);
		converter.fromPosition(pos);
		assertEquals(64,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("blitzer", race);
		converter.fromPosition(pos);
		assertEquals(65,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("ulfwerener", race);
		converter.fromPosition(pos);
		assertEquals(66,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("snow_troll", race);
		converter.fromPosition(pos);
		assertEquals(67,converter.getIndex());
		converted++;
		
		assertEquals(6,converted);
	}
	
	public void testAmazonPositionsFrom(){
		Position pos = new Position();
		Race race = new Race();
		BBELPositionConverter converter = new BBELPositionConverter();
		Configuration conf = Configuration.getInstance();
		int converted = 0;
		
		conf.load();
		race.setName("amazon");
		
		pos = conf.getPositionByNameAndRace("linewoman", race);
		converter.fromPosition(pos);
		assertEquals(68,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("catcher", race);
		converter.fromPosition(pos);
		assertEquals(69,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("thrower", race);
		converter.fromPosition(pos);
		assertEquals(70,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("blitzer", race);
		converter.fromPosition(pos);
		assertEquals(71,converter.getIndex());
		converted++;
		
		assertEquals(4,converted);
	}
	
	public void testElfPositionsFrom(){
		Position pos = new Position();
		Race race = new Race();
		BBELPositionConverter converter = new BBELPositionConverter();
		Configuration conf = Configuration.getInstance();
		int converted = 0;
		
		conf.load();
		race.setName("elf");
		
		pos = conf.getPositionByNameAndRace("lineman", race);
		converter.fromPosition(pos);
		assertEquals(72,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("thrower", race);
		converter.fromPosition(pos);
		assertEquals(73,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("catcher", race);
		converter.fromPosition(pos);
		assertEquals(74,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("blitzer", race);
		converter.fromPosition(pos);
		assertEquals(75,converter.getIndex());
		converted++;
		
		assertEquals(4,converted);
	}
	
	public void testHighElfPositionsFrom(){
		Position pos = new Position();
		Race race = new Race();
		BBELPositionConverter converter = new BBELPositionConverter();
		Configuration conf = Configuration.getInstance();
		int converted = 0;
		
		conf.load();
		race.setName("high_elf");
		
		pos = conf.getPositionByNameAndRace("lineman", race);
		converter.fromPosition(pos);
		assertEquals(77,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("thrower", race);
		converter.fromPosition(pos);
		assertEquals(78,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("catcher", race);
		converter.fromPosition(pos);
		assertEquals(79,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("blitzer", race);
		converter.fromPosition(pos);
		assertEquals(80,converter.getIndex());
		converted++;
		
		assertEquals(4,converted);
	}
	
	public void testKhemriPositionsFrom(){
		Position pos = new Position();
		Race race = new Race();
		BBELPositionConverter converter = new BBELPositionConverter();
		Configuration conf = Configuration.getInstance();
		int converted = 0;
		
		conf.load();
		race.setName("khemri");
		
		pos = conf.getPositionByNameAndRace("skeleton", race);
		converter.fromPosition(pos);
		assertEquals(81,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("thro_ra", race);
		converter.fromPosition(pos);
		assertEquals(82,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("blitz_ra", race);
		converter.fromPosition(pos);
		assertEquals(83,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("tomb_guardian", race);
		converter.fromPosition(pos);
		assertEquals(84,converter.getIndex());
		converted++;
		
		assertEquals(4,converted);
	}
	
	public void testNecromanticPositionsFrom(){
		Position pos = new Position();
		Race race = new Race();
		BBELPositionConverter converter = new BBELPositionConverter();
		Configuration conf = Configuration.getInstance();
		int converted = 0;
		
		conf.load();
		race.setName("necromantic");
		
		pos = conf.getPositionByNameAndRace("zombie", race);
		converter.fromPosition(pos);
		assertEquals(86,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("ghoul", race);
		converter.fromPosition(pos);
		assertEquals(87,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("wight", race);
		converter.fromPosition(pos);
		assertEquals(88,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("flesh_golem", race);
		converter.fromPosition(pos);
		assertEquals(89,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("werewolf", race);
		converter.fromPosition(pos);
		assertEquals(90,converter.getIndex());
		converted++;
		
		assertEquals(5,converted);
	}
	
	public void testNurglePositionsFrom(){
		Position pos = new Position();
		Race race = new Race();
		BBELPositionConverter converter = new BBELPositionConverter();
		Configuration conf = Configuration.getInstance();
		int converted = 0;
		
		conf.load();
		race.setName("nurgle");
		
		pos = conf.getPositionByNameAndRace("rotter", race);
		converter.fromPosition(pos);
		assertEquals(91,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("pestigor", race);
		converter.fromPosition(pos);
		assertEquals(92,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("nurgle_warrior", race);
		converter.fromPosition(pos);
		assertEquals(93,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("beast_of_nurgle", race);
		converter.fromPosition(pos);
		assertEquals(94,converter.getIndex());
		converted++;
		
		assertEquals(4,converted);
	}
	
	public void testOgrePositionsFrom(){
		Position pos = new Position();
		Race race = new Race();
		BBELPositionConverter converter = new BBELPositionConverter();
		Configuration conf = Configuration.getInstance();
		int converted = 0;
		
		conf.load();
		race.setName("ogre");
		
		pos = conf.getPositionByNameAndRace("snotling", race);
		converter.fromPosition(pos);
		assertEquals(95,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("ogre", race);
		converter.fromPosition(pos);
		assertEquals(96,converter.getIndex());
		converted++;
		
		assertEquals(2,converted);
	}
	
	public void testVampirePositionsFrom(){
		Position pos = new Position();
		Race race = new Race();
		BBELPositionConverter converter = new BBELPositionConverter();
		Configuration conf = Configuration.getInstance();
		int converted = 0;
		
		conf.load();
		race.setName("vampire");
		
		pos = conf.getPositionByNameAndRace("thrall", race);
		converter.fromPosition(pos);
		assertEquals(97,converter.getIndex());
		converted++;
		
		pos = conf.getPositionByNameAndRace("vampire", race);
		converter.fromPosition(pos);
		assertEquals(98,converter.getIndex());
		converted++;
		
		assertEquals(2,converted);
	}
	
}
