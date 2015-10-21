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
import org.tisseursdechimeres.bb.Position;

public class BBELPositionConverter {

protected int index;
	
	public BBELPositionConverter(){
		index = 0;
	}
	
	public static final String[] positions = {
		"",
		"human@lineman",
		"human@catcher",
		"human@thrower",
		"human@blitzer",
		"human@ogre",
		"dwarf@blocker",
		"dwarf@runner",
		"dwarf@blitzer",
		"dwarf@troll_slayer",
		"dwarf@deathroller",
		"wood_elf@lineman",
		"wood_elf@catcher",
		"wood_elf@thrower",
		"wood_elf@wardancer",
		"wood_elf@treeman",
		"skaven@lineman",
		"skaven@thrower",
		"skaven@gutter_runner",
		"skaven@storm_vermin",
		"skaven@rat_ogre",
		"orc@lineman",
		"orc@goblin",
		"orc@thrower",
		"orc@black_orc",
		"orc@blitzer",
		"orc@troll",
		"lizardman@skink",
		"lizardman@saurus",
		"lizardman@kroxigor",
		"goblin@goblin",
		"goblin@loney",
		"chaos@beastman",
		"chaos@chaos_warrior",
		"chaos@minotaur",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"goblin@troll",
		"goblin@pogoer",
		"goblin@fanatic",
		"dark_elf@lineman",
		"dark_elf@runner",
		"dark_elf@assassin",
		"dark_elf@blitzer",
		"dark_elf@witch",
		"",
		"",
		"undead@skeleton",
		"undead@zombie",
		"undead@ghoul",
		"undead@wight",
		"undead@mummy",
		"",
		"halfling@halfling",
		"halfling@treeman",
		"norse@lineman",
		"norse@thrower",
		"norse@catcher",
		"norse@blitzer",
		"norse@ulfwerener",
		"norse@snow_troll",
		"amazon@linewoman",
		"amazon@catcher",
		"amazon@thrower",
		"amazon@blitzer",
		"elf@lineman",
		"elf@thrower",
		"elf@catcher",
		"elf@blitzer",
		"",
		"high_elf@lineman",
		"high_elf@thrower",
		"high_elf@catcher",
		"high_elf@blitzer",
		"khemri@skeleton",
		"khemri@thro_ra",
		"khemri@blitz_ra",
		"khemri@tomb_guardian",
		"",
		"necromantic@zombie",
		"necromantic@ghoul",
		"necromantic@wight",
		"necromantic@flesh_golem",
		"necromantic@werewolf",
		"nurgle@rotter",
		"nurgle@pestigor",
		"nurgle@nurgle_warrior",
		"nurgle@beast_of_nurgle",
		"ogre@snotling",
		"ogre@ogre",
		"vampire@thrall",
		"vampire@vampire",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"goblin@bombardier"
	};
	
	public boolean fromPosition(Position pos){
		boolean bReturn = false;
		String posName = pos.getName();
		String raceName = pos.getRaceName();
		String toFind = raceName + "@" + posName; 
		int i = 0,n = positions.length;
		index = 0;
		for(i=0;i<n;i++){
			if(true == positions[i].equals(toFind)){
				index = i;
				bReturn = true;
				break;
			}
		}
		return bReturn;
	}
	
	public boolean toPosition(Position pos){
		boolean bReturn = false;
		String key = positions[index]; 
		Configuration conf = Configuration.getInstance();
		pos = conf.getPositionByKey(key);
		if(pos != null){
			bReturn = true;
		}
		return bReturn;
	}
	
	
	public int getIndex(){
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
