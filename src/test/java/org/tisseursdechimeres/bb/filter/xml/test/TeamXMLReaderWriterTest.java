package org.tisseursdechimeres.bb.filter.xml.test;

import java.io.File;

import org.tisseursdechimeres.bb.Configuration;
import org.tisseursdechimeres.bb.Race;
import org.tisseursdechimeres.bb.filter.obblm.OBBLMRaceConverter;

import junit.framework.TestCase;

public class TeamXMLReaderWriterTest extends TestCase {
	protected Configuration conf;
	
	public TeamXMLReaderWriterTest(){
		conf = Configuration.getInstance();
		loadConfiguration(conf);
	}
	
	protected void loadConfiguration(Configuration conf){
		if(0 == conf.getSkillNimber()){
			String confFile = Configuration.BASENAME;
			boolean exists = (new File(confFile)).exists();
			if (false == exists) {
			  	String confResourcePath = new String("/" + confFile);
				conf.extractFromXMLStream(conf.getClass().getResourceAsStream(confResourcePath));
			}else{
				conf.extractFromXMLFile(confFile);
			}
		}
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
}
