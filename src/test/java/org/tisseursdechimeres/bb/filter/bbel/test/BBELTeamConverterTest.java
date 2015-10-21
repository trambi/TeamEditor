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

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.tisseursdechimeres.bb.Configuration;
import org.tisseursdechimeres.bb.Player;
import org.tisseursdechimeres.bb.Race;
import org.tisseursdechimeres.bb.Team;
import org.tisseursdechimeres.bb.filter.bbel.BBELTeamConverter;

import junit.framework.TestCase;

public class BBELTeamConverterTest extends TestCase {
	protected Configuration conf;
	
	public BBELTeamConverterTest(){
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
		
		testNameRaceAreWriteable("chaos_pact",false);
		testNameRaceAreWriteable("chaos_dwarf",false);
		testNameRaceAreWriteable("underworld",false);
		testNameRaceAreWriteable("slann",false);
	}
	
	private void testNameRaceAreWriteable( String raceName, boolean shouldBeWritable)
	{
		BBELTeamConverter converter = new BBELTeamConverter();
		Race race = conf.getRaceByName(raceName);
		assertEquals(converter.isRaceWriteable(race),shouldBeWritable);
	}
	
	public void testWrite(){
		boolean noSQLException = true;
		Team team = new Team();
		team.setRace(conf.getRaceByName("orc"));
		team.setName("Orc_test");
		team.setApothicary(1);
		team.setAssistants(2);
		team.setCheerleaders(3);
		team.setFf(4);
		team.setTreasury(5);
		Player player1 = new Player();
		player1.setName("player1");
		player1.setPosition(conf.getPositionByNameAndRace("lineman", team.getRace()));
		player1.setCas(50);
		player1.addSkill(conf.getSkillByName("movement_bonus"));
		player1.addSkill(conf.getSkillByName("strength_bonus"));
		player1.addSkill(conf.getSkillByName("agility_bonus"));
		player1.addSkill(conf.getSkillByName("armour_bonus"));
		player1.addInjury(conf.getInjuryByName("niggling"));
		team.addPlayer(player1);
		Player player2 = new Player();
		player2.setName("player2");
		player2.setPosition(conf.getPositionByNameAndRace("journeyman", team.getRace()));
		player2.setCas(50);
		player2.addSkill(conf.getSkillByName("movement_bonus"));
		player2.addSkill(conf.getSkillByName("strength_bonus"));
		player2.addSkill(conf.getSkillByName("agility_bonus"));
		player2.addSkill(conf.getSkillByName("armour_bonus"));
		player2.addInjury(conf.getInjuryByName("niggling"));
		team.addPlayer(player2);
		team.computeValue();
		
		BBELTeamConverter converter = new BBELTeamConverter();
		converter.setTeamToWrite(team);
		File file = new File("orc_test.sqlite");
		converter.writeFile(file);
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:"+file.getAbsolutePath());
			ResultSet rs;
			int idTeam = 0,idRace = 0;
			PreparedStatement prep = conn.prepareStatement("SELECT * FROM Team_Listing" );
			rs = prep.executeQuery();
			while (rs.next()) {
				assertEquals("Orc_test",rs.getString("strName"));
				assertEquals(540,rs.getInt("iValue"));
				assertEquals(true,rs.getBoolean("bApothecary"));
				assertEquals(2,rs.getInt("iAssistantCoaches"));
				assertEquals(3,rs.getInt("iCheerleaders"));
				assertEquals(4,rs.getInt("iPopularity"));
				assertEquals(50000,rs.getInt("iCash"));
				idTeam = rs.getInt("ID");
				idRace = rs.getInt("idRaces");
		    }
		    rs.close();
		    int idPlayer = 0;
		    prep = conn.prepareStatement("SELECT * FROM Player_Listing");
		    rs = prep.executeQuery();
			while (rs.next()) {
				idPlayer = rs.getInt("ID");
				assertEquals("player1",rs.getString("strName"));
				assertEquals(21,rs.getInt("idPlayer_Types"));
				assertEquals(200,rs.getInt("iValue"));
				assertEquals(idTeam,rs.getInt("idTeam_Listing"));
				assertEquals(idRace,rs.getInt("idRaces"));
		    }
		    rs.close();
		    prep = conn.prepareStatement("SELECT COUNT(ID) FROM Player_Skills WHERE idPlayer_Listing = ?");
		    prep.setInt(1, idPlayer);
		    rs = prep.executeQuery();
			while (rs.next()) {
				assertEquals(4,rs.getInt("COUNT(ID)"));
		    }
		    rs.close();
		    prep = conn.prepareStatement("SELECT * FROM Player_Casualties WHERE idPlayer_Listing = ?");
		    prep.setInt(1, idPlayer);
		    rs = prep.executeQuery();
			while (rs.next()) {
				assertEquals(10,rs.getInt("idPlayer_Casualty_Types"));
		    }
		    rs.close(); 
			conn.close();
		} catch (SQLException e) {
			noSQLException = true;
		}
		assertTrue(noSQLException);
	}
}
