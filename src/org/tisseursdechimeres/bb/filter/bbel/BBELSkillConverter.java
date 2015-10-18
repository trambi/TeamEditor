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
import org.tisseursdechimeres.bb.Skill;

public class BBELSkillConverter {
	
	protected int index;
	
	public BBELSkillConverter(){
		index = 0;
	}
	
	public static final String[] skills = {
		"",
		"strip_ball",
		"strength_bonus",
		"agility_bonus",
		"movement_bonus",
		"armour_bonus",
		"catch",
		"dodge",
		"sprint",
		"pass_block",
		"foul_appearance",
		"leap",
		"extra_arm",
		"mighty_blow",
		"leader",
		"horns",
		"two_heads",
		"stand_firm",
		"always_hungry",
		"regeneration",
		"take_root",
		"accurate",
		"break_tackle",
		"sneaky_git",
		"",
		"chainsaw",
		"dauntless",
		"dirty_player",
		"diving_catch",
		"dump_off",
		"block",
		"bone_head",
		"long_legs",
		"disturbing_presence",
		"diving_tackle",
		"fend",
		"frenzy",
		"grab",
		"guard",
		"hail_mary_pass",
		"juggernaut",
		"jump_up",
		"",
		"",
		"loner",
		"nerves_of_steel",
		"no_hands",
		"pass",
		"piling_on",
		"prehensive_tail",
		"pro",
		"really_stupid",
		"right_stuff",
		"safe_throw",
		"secret_weapon",
		"shadowing",
		"side_step",
		"tackle",
		"strong_arm",
		"stunty",
		"sure_feet",
		"sure_hands",
		"",
		"thick_skull",
		"throw_team_mate",
		"",
		"",
		"wild_animal",
		"wrestle",
		"tentacles",
		"multiple_block",
		"kick",
		"kick_off_return",
		"",
		"big_hand",
		"claw",
		"ball_chain",
		"stab",
		"hypnotic_gaze",
		"stakes",
		"bombardier",
		"decay",
		"nurgles_rot",
		"titchy",
		"blood_lust",
		"fan_favourite"
	};
	
	public boolean fromSkill(Skill skill){
		boolean bReturn = false;
		String name = skill.getName();
		int i = 0,n = skills.length;
		index = 0;
		for(i=0;i<n;i++){
			if(true == skills[i].equals(name)){
				index = i;
				bReturn = true;
				break;
			}
		}
		return bReturn;
	}
	
	public int getIndex(){
		return index;
	}

	public void setIndex(int index) {
		this.index =  index;
	}

	public boolean toSkill(Skill skill) {
		boolean bReturn = false;
		String skillName = skills[index];
		if(null != skillName){
			Configuration conf = Configuration.getInstance();
			skill = conf.getSkillByName(skillName);
			if(null != skill){
				bReturn = true;
			}
		}
		return bReturn;		
	}

}
