/*
Copyright (C) 2011  Bertrand MADET

This org.tisseursdechimeres.bb.filter.bbcode is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by 
the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

This org.tisseursdechimeres.bb.teameditor is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this org.tisseursdechimeres.bb.filter.bbcode.  If not, see <http://www.gnu.org/licenses/>.
*/
package org.tisseursdechimeres.bb.filter.bbcode;

import org.tisseursdechimeres.bb.Player;
import org.tisseursdechimeres.bb.Team;

public class BBCodeTeam {

	public static final String TEAM_BB_TAG = "bbteam";
	
	static public String writeTeam(Team team){
		team.sortPlayers();
		String bbCode = new String();
		bbCode = "[" + TEAM_BB_TAG + " ";
		bbCode += "name=\""+BBCodeUtils.toSimpleText(team.getName())+"\" ";
		bbCode += "coach=\""+team.getCoach()+"\" ";
		bbCode += "race=\""+BBCodeUtils.toSimpleText(team.getRace().getI18nName())+"\" ";
		bbCode += "fanfactor=\""+team.getFf().toString()+"\" ";
		bbCode += "reroll=\""+team.getRerolls().toString()+"\" ";
		bbCode += "reroll-cost=\""+team.getRace().getRerollCost().toString()+"0\" ";
		bbCode += "apothicary=\""+team.getApothicary().toString()+"\" ";
		bbCode += "assistant=\""+team.getAssistants().toString()+"\" ";
		bbCode += "cheerleader=\""+team.getCheerleaders().toString()+"\" ";
		bbCode += "tv=\""+team.getValue().toString()+"\" ";
		bbCode += "treasury=\""+team.getTreasury().toString()+"0000\"]";
		for(Player player : team.getPlayers()){
			if( (null != player) && (null != player.getPosition()) )
				bbCode += BBCodePlayer.writePlayer(player);
		}
		bbCode += "[/"+TEAM_BB_TAG+"]";
		
		return bbCode;
	}
}
