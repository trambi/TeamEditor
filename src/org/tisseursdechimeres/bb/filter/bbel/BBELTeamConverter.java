/*
Copyright (C) 2011  Bertrand MADET

This org.tisseursdechimeres.bb.filter.bbel is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This org.tisseursdechimeres.bb.filter.bbel is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this org.tisseursdechimeres.bb.filter.bbel.  If not, see <http://www.gnu.org/licenses/>.
*/
package org.tisseursdechimeres.bb.filter.bbel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.HashMap;

import org.tisseursdechimeres.bb.Player;
import org.tisseursdechimeres.bb.Race;
import org.tisseursdechimeres.bb.Team;
import org.tisseursdechimeres.bb.filter.TeamFileWriter;

import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

public class BBELTeamConverter implements TeamFileWriter {
	protected Team team;
	protected BBELFileFilter filter;
	protected Connection conn;
	protected int idTeam;
	protected int idRace;
	protected HashMap<Integer,Integer> helmetsByPosition;
	protected HashMap<Integer,Integer> pauldronsByPosition;
	protected HashMap<Integer,Integer> gauntletsByPosition;
	protected HashMap<Integer,Integer> botsByPosition;
	
	public BBELTeamConverter(){
		this.filter = new BBELFileFilter();
		helmetsByPosition = new HashMap<Integer,Integer>();
		pauldronsByPosition = new HashMap<Integer,Integer>();
		gauntletsByPosition = new HashMap<Integer,Integer>();
		botsByPosition = new HashMap<Integer,Integer>();
		idTeam = 0;
	}
	
	public FileFilter getOutputFileFilter(){
		return filter;
	}
	
	public void setTeamToWrite(Team team){
		this.team = team;
	}
	
	public boolean writeFile(File fileToWrite){
		boolean bReturn = false;
		if(null != this.team){
			createFile(fileToWrite);
			try {
				Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null,e.getMessage());
				e.printStackTrace();
			}
			openConnection(fileToWrite);
			getInfos();
			updateTeam();
			updatePlayers();
			closeConnection();
			
		}
		return bReturn;
	}
	
	private void createFile(File fileToWrite) {
		InputStream from = getClass().getResourceAsStream(team.getRace().getName()+".db");
		OutputStream to = null;
		try {
	      to = new FileOutputStream(fileToWrite);
	      byte[] buffer = new byte[4096];
	      int bytesRead;
	
	      while ((bytesRead = from.read(buffer)) != -1){
	        to.write(buffer, 0, bytesRead); // write
	      }
	    } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		  if (from != null)
		    try {
		      from.close();
		    } catch (IOException e) {
		      ;
		    }
		  if (to != null)
		    try {
		      to.close();
		    } catch (IOException e) {
		      ;
		    }
	    }
	}
	
	private boolean openConnection(File file){
		boolean bReturn = false;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:"+file.getAbsolutePath());
			conn.setAutoCommit(false);
			bReturn = true;
		} catch (SQLException e) {
			;
		}
		
		return bReturn;
	}
	
	private boolean closeConnection(){
		boolean bReturn = false;
		try {
			conn.setAutoCommit(true);
			conn.close();
			bReturn = true;
		} catch (SQLException e) {
			;
		}
		
		return bReturn;
	}
	
	private boolean getInfos() {
		boolean bReturn = false;
		try {
			ResultSet rs;
			PreparedStatement prep = conn.prepareStatement("SELECT ID,IdRaces FROM Team_Listing ");
			rs = prep.executeQuery();
			while (rs.next()) {
				idTeam = rs.getInt("ID");
				idRace = rs.getInt("IdRaces");
		    }
		    rs.close();
		    bReturn = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bReturn;
	}
	
	private boolean updateTeam() {
		boolean bReturn = false;
		try {
			int control = 0;
			PreparedStatement prep = conn.prepareStatement("UPDATE SavedGameInfo SET strName = ?;");
		    prep.setString(1, team.getName());
		    control = prep.executeUpdate();
		    String request = "UPDATE Team_Listing SET strName = ?,iValue = ?,iPopularity = ?,iCash = ?,";
		    request += "iCheerleaders = ?,bApothecary = ?,iRerolls = ?,iAssistantCoaches = ?";
		    request += " WHERE ID = ?;";
		    prep = conn.prepareStatement(request);
		    prep.setString(1, team.getName());
		    prep.setInt(2, team.getValue()*10);
		    prep.setInt(3, team.getFf());
		    prep.setInt(4, team.getTreasury()*10000);
		    prep.setInt(5, team.getCheerleaders());
		    prep.setInt(6, team.getApothicary());
		    prep.setInt(7, team.getRerolls());
		    prep.setInt(8, team.getAssistants());
		    prep.setInt(9, idTeam);
		    //control = stat.executeUpdate(request);
		    control += prep.executeUpdate();
		   	if(2 == control){
		   		bReturn = true;	
		   	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bReturn;
	}
	
	private boolean updatePlayers() {
		boolean bReturn = false;
		try {
			boolean playerConverted = false;
			BBELPlayerConverter converter = new BBELPlayerConverter();
			int idPlayer = 0;
			
			String insertPlayerRequest = new String("INSERT INTO Player_Listing ");
			insertPlayerRequest += "(strName,idPlayer_Types,idTeam_Listing,idRaces,iNumber,";
			insertPlayerRequest += "Characteristics_fMovementAllowance,Characteristics_fStrength,";
			insertPlayerRequest += "Characteristics_fAgility,Characteristics_fArmourValue,";
			insertPlayerRequest += "idPlayer_Levels,iExperience,iValue,iSalary,idEquipment_Listing_Helmet,";
			insertPlayerRequest += "idEquipment_Listing_Pauldron,idEquipment_Listing_Gauntlet,idEquipment_Listing_Boot,";
			insertPlayerRequest += "idPlayer_Names,idTeam_Listing_Previous,iPlayerColor,";
			insertPlayerRequest += "iSkinScalePercent,iSkinMeshVariant,iSkinTextureVariant,fAgeing,";
			insertPlayerRequest += "Durability_iHelmet,Durability_iPauldron,Durability_iGauntlet,Durability_iBoot,";
			insertPlayerRequest += "Contract_iDuration,Contract_iSeasonRemaining,";
			insertPlayerRequest += "idNegotiation_Condition_Types,Negotiation_iRemainingTries,";
			insertPlayerRequest += "Negotiation_iConditionDemand,iMatchSuspended,iNbLevelsUp,";
			insertPlayerRequest += "LevelUp_iRollResult,LevelUp_iRollResult2,LevelUp_bDouble,";
			insertPlayerRequest += "bGenerated,bStar,bEdited,bDead,strLevelUp) ";
			insertPlayerRequest += "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,";
			insertPlayerRequest += "0,0,0,-1,-1,-1,100,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,''); ";
			
			String insertSkillRequest = new String("INSERT INTO Player_Skills");
			insertSkillRequest += " (idPlayer_Listing,idSkill_Listing) VALUES(?,?);";
			
			String insertInjuryRequest = new String("INSERT INTO Player_Casualties");
			insertInjuryRequest += " (idPlayer_Listing,idPlayer_Casualty_Types) VALUES(?,?);";
			
			String getEquipmentRequest = new String("SELECT ID,idEquipment_Types FROM Equipment_Listing ");
			getEquipmentRequest += " WHERE idPlayer_Types=? AND idEquipment_Visuals=1;";
			
			PreparedStatement insertPlayer = conn.prepareStatement(insertPlayerRequest);
			PreparedStatement insertSkill = conn.prepareStatement(insertSkillRequest);
			PreparedStatement insertInjury = conn.prepareStatement(insertInjuryRequest);
			PreparedStatement getEquipment = conn.prepareStatement(getEquipmentRequest);
			
			for(Player player : team.getPlayers()){
				ResultSet rs;
				playerConverted = converter.fromPlayer(player);
				int posIndex = converter.getPositionIndex();
				if(true == playerConverted){
					int helmetId = 0, pauldronId = 0, gauntletId = 0, botId = 0;
					if( null == helmetsByPosition.get(posIndex)){
						int equipmentType = 0;
						getEquipment.setInt(1, posIndex);
						rs = getEquipment.executeQuery();
						while (rs.next()) {
							equipmentType = rs.getInt("idEquipment_Types");
							if(equipmentType == 1){
								helmetId = rs.getInt("ID");								
								helmetsByPosition.put(new Integer(posIndex), new Integer(helmetId));
							}else if(equipmentType == 2){
								pauldronId = rs.getInt("ID");								
								pauldronsByPosition.put(new Integer(posIndex), new Integer(pauldronId));
							}else if(equipmentType == 3){
								gauntletId = rs.getInt("ID");								
								gauntletsByPosition.put(new Integer(posIndex), new Integer(gauntletId));
							}else if(equipmentType == 4){
								botId = rs.getInt("ID");								
								botsByPosition.put(new Integer(posIndex), new Integer(botId));
							}
					    }
					}else{
						helmetId = helmetsByPosition.get(Integer.valueOf(posIndex)).intValue();
						pauldronId = pauldronsByPosition.get(Integer.valueOf(posIndex)).intValue();
						gauntletId = gauntletsByPosition.get(Integer.valueOf(posIndex)).intValue();
						botId = botsByPosition.get(Integer.valueOf(posIndex)).intValue();
					}
					insertPlayer.setString(1, converter.getName());
					insertPlayer.setInt(2, posIndex);
					insertPlayer.setInt(3, idTeam);
					insertPlayer.setInt(4, idRace);
					insertPlayer.setInt(5, converter.getNumber());
					insertPlayer.setFloat(6, converter.getMov());
					insertPlayer.setFloat(7, converter.getSt());
					insertPlayer.setFloat(8, converter.getAg());
					insertPlayer.setFloat(9, converter.getAr());
					insertPlayer.setInt(10, converter.getLevel());
					insertPlayer.setInt(11, converter.getExperience());
					int value = converter.getValue();
					insertPlayer.setInt(12, value*10);
					insertPlayer.setInt(13, value*10000);
					insertPlayer.setInt(14, helmetId);
					insertPlayer.setInt(15, pauldronId);
					insertPlayer.setInt(16, gauntletId);
					insertPlayer.setInt(17, botId);
					
					insertPlayer.addBatch();
					insertPlayer.executeBatch();
					
					rs = insertPlayer.getGeneratedKeys();
					while (rs.next()) {
						idPlayer = rs.getInt(1);
				    }
				    rs.close();
				    for(int skillIndex : converter.getSkillIndexes()){
				    	insertSkill.setInt(1,idPlayer);
				    	insertSkill.setInt(2,skillIndex);
				    	insertSkill.addBatch();
				    }
				    for(int injIndex : converter.getInjuryIndexes()){
				    	insertInjury.setInt(1,idPlayer);
				    	insertInjury.setInt(2,injIndex);
				    	insertInjury.addBatch();
				    }
				}
			}
		    insertSkill.executeBatch();
		    insertInjury.executeBatch();
			
		    bReturn = true;
		    
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bReturn;
	}
	
	public boolean readFile(File fileToRead){
		boolean bReturn = false;
		if(null != this.team){
			
		}
		return bReturn;
	}

	@Override
	public boolean isRaceWriteable(Race race) {
		boolean returnValue = false;
		InputStream from = getClass().getResourceAsStream(race.getName()+".db");
		if(null != from){
			returnValue = true;
		}
		return returnValue;
	}
}
