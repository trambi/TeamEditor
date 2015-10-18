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

import org.tisseursdechimeres.bb.Configuration;
import org.tisseursdechimeres.bb.Injury;

public class BBELInjuryConverter {

	protected int index;
	
	public static final int MIN_MISS_NEXT_GAME_INDEX = 2;
	public static final int MAX_MISS_NEXT_GAME_INDEX = 9; 
	public static final int MIN_NIGGLING_INDEX = 10;
	public static final int MAX_NIGGLING_INDEX = 11;
	public static final int MIN_MOV_MALUS_INDEX = 12;
	public static final int MAX_MOV_MALUS_INDEX = 13;
	public static final int MIN_STR_MALUS_INDEX = 17;
	public static final int MAX_STR_MALUS_INDEX = 17;
	public static final int MIN_AG_MALUS_INDEX = 16;
	public static final int MAX_AG_MALUS_INDEX = 16; 
	public static final int MIN_AR_MALUS_INDEX = 14;
	public static final int MAX_AR_MALUS_INDEX = 15;

	public BBELInjuryConverter(){
		index = 0;
	}
	
	public boolean fromInjury(Injury inj){
		boolean bReturn = true;
		if(true == inj.isMissNextGame()){
			index = MIN_MISS_NEXT_GAME_INDEX;
		}else if(1 == inj.getMovMalus()){
			index = MIN_MOV_MALUS_INDEX;
		}else if(1 == inj.getStrMalus()){
			index = MIN_STR_MALUS_INDEX;
		}else if(1 == inj.getAgMalus()){
			index = MIN_AG_MALUS_INDEX;
		}else if(1 == inj.getArMalus()){
			index = MIN_AR_MALUS_INDEX;
		}else{
			index = MIN_NIGGLING_INDEX;
		}
		return bReturn;
	}
	
	public int getIndex(){
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public boolean toInjury(Injury inj) {
		String injName = null;
		boolean bReturn = false;
		if( (index >= MIN_MISS_NEXT_GAME_INDEX) && (index <= MAX_MISS_NEXT_GAME_INDEX) ){
			injName = new String("miss_next_game");
		}else if( (index >= MIN_NIGGLING_INDEX) && (index <= MAX_NIGGLING_INDEX) ){
			injName = new String("niggling");
		}else if( (index >= MIN_MOV_MALUS_INDEX) && (index <= MAX_MOV_MALUS_INDEX) ){
			injName = new String("movement_-1");
		}else if( (index >= MIN_STR_MALUS_INDEX) && (index <= MAX_STR_MALUS_INDEX) ){
			injName = new String("strength_-1");
		}else if( (index >= MIN_AG_MALUS_INDEX) && (index <= MAX_AG_MALUS_INDEX) ){
			injName = new String("agility_-1");
		}else if( (index >= MIN_AR_MALUS_INDEX) && (index <= MAX_AR_MALUS_INDEX) ){
			injName = new String("armour_-1");
		}
		if(null != injName){
			Configuration conf = Configuration.getInstance();
			inj = conf.getInjuryByName(injName);
			if(null != inj){
				bReturn = true;
			}
		}
		return bReturn;
		
	}
	
}
