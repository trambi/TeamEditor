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

public class BBCodePlayer {
	
	public static final String PLAYER_BB_TAG = "bbplayer";
	
	static public String writePlayer(Player player){
		String strReturn = new String();
		if(null != player.getPosition() ){
			strReturn = "[" + PLAYER_BB_TAG + " ";
			strReturn += "number=\""+player.getNumber().toString()+"\" ";
			strReturn += "mov=\""+player.getMov().toString()+"\" ";
			strReturn += "str=\""+player.getStr().toString()+"\" ";
			strReturn += "ag=\""+player.getAg().toString()+"\" ";
			strReturn += "ar=\""+player.getAr().toString()+"\" ";
			strReturn += "position=\""+player.getPosition().getI18nName()+"\" ";
			strReturn += "skills=\""+BBCodeUtils.toSimpleText(player.getPosition().getSkills().getI18nNameList())+"\" ";
			strReturn += "completion=\""+player.getComp().toString()+"\" ";
			strReturn += "casualty=\""+player.getCas().toString()+"\" ";
			strReturn += "interception=\""+player.getInter().toString()+"\" ";
			strReturn += "td=\""+player.getTd().toString()+"\" ";
			strReturn += "mvp=\""+player.getMvp().toString()+"\" ";
			strReturn += "extra_skills=\""+BBCodeUtils.toSimpleText(player.getExtraSkills().getI18nNameList())+" \" ";
			strReturn += "injury=\""+player.getInjuries().getSummary()+" \" ";
			strReturn += "cost=\""+player.getValue().toString()+"0\"]";
			strReturn += player.getName().toString()+"[/"+PLAYER_BB_TAG+"]";
		}
		return strReturn;
	}

}
