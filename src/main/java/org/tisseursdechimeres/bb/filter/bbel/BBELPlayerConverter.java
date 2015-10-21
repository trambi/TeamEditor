/*
Copyright (C) 2010  Bertrand MADET

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

import java.util.ArrayList;

import org.tisseursdechimeres.bb.Configuration;
import org.tisseursdechimeres.bb.Player;
import org.tisseursdechimeres.bb.Injuries;
import org.tisseursdechimeres.bb.Injury;
import org.tisseursdechimeres.bb.Position;
import org.tisseursdechimeres.bb.Skill;
import org.tisseursdechimeres.bb.Skills;

public class BBELPlayerConverter {

	protected int positionIndex;
	protected float mov;
	protected float st;
	protected float ag;
	protected float ar;
	protected String name;
	protected int value;
	protected int experience;
	protected int level;
	protected int number;
	
	protected ArrayList<Integer> skillIndexes;
	protected ArrayList<Integer> injuryIndexes;
	
	public static final float MOVEMENT_MULTIPLIER = (float) 8.3333;
	public static final float MOVEMENT_ADDITIONNER = (float) 0.0;
	public static final float STRENGTH_MULTIPLIER = (float) 10.0;
	public static final float STRENGTH_ADDITIONNER = (float)2.0;
	public static final float AGILITY_MULTIPLIER = (float) 16.6667;
	public static final float AGILITY_ADDITIONNER = (float) 0.0;
	
	public static final int ARMOUR_MIN = 3;
	public static final int ARMOUR_MAX = 10;
	public static final float ARMOUR_3 = (float) 8.3333;
	public static final float ARMOUR_4 = (float) 16.6666;
	public static final float ARMOUR_5 = (float) 27.7777;
	public static final float ARMOUR_6 = (float) 41.6666;
	public static final float ARMOUR_7 = (float) 58.3333;
	public static final float ARMOUR_8 = (float) 72.2222;
	public static final float ARMOUR_9 = (float) 83.3333;
	public static final float ARMOUR_10 = (float) 91.6666;
	public static final float ARMOUR[]={ARMOUR_3,ARMOUR_4,ARMOUR_5,ARMOUR_6,ARMOUR_7,ARMOUR_8,ARMOUR_9,ARMOUR_10};
	
	public BBELPlayerConverter(){
		injuryIndexes = new ArrayList<Integer>();
		skillIndexes = new ArrayList<Integer>();
	}
	
	protected void clear(){
		positionIndex = 0;
		mov = (float) 0.0;
		st = (float) 0.0;
		ag = (float) 0.0;
		ar = (float) 0.0;
		name = "";
		value = -1;
		experience = -1;
		level = -1;
		number = -1;
		
		skillIndexes.clear();
		injuryIndexes.clear();
	}
	
	public boolean fromPlayer(Player player){
		boolean bReturn = false;
		clear();
		BBELPositionConverter posConverter = new BBELPositionConverter();
		posConverter.fromPosition(player.getPosition());
		positionIndex  = posConverter.getIndex();
		if(0 != positionIndex){
			name = player.getName();
			mov = convertMovementFrom(player.getMov());
			st = convertStrengthFrom(player.getStr());
			ag = convertAgilityFrom(player.getAg());
			ar = convertArmourFrom(player.getAr());
			
			BBELSkillConverter bBELSkillConverter = new BBELSkillConverter();
			Skills extraSkills = player.getExtraSkills(); 
			for(Skill skill : extraSkills){
				bBELSkillConverter.fromSkill(skill);
				skillIndexes.add(bBELSkillConverter.getIndex());
			}
			
			BBELInjuryConverter injConverter = new BBELInjuryConverter();
			Injuries injuries = player.getInjuries(); 
			for(Injury inj : injuries){
				injConverter.fromInjury(inj);
				injuryIndexes.add(injConverter.getIndex());
			}
			value = player.getValue();
			experience = player.getSpp();
			level = Configuration.getPlayerPotentialExtraSkillsNumber(player);
			level ++;
			number = player.getNumber();
			bReturn = true;
		}
		return bReturn;
	}
	
	public void toPlayer(Player player){
		
		Position pos = null;
		BBELPositionConverter posConverter = new BBELPositionConverter();
		posConverter.setIndex(positionIndex);
		posConverter.toPosition(pos);
		player.setPosition(pos);
		

		BBELSkillConverter bBELSkillConverter = new BBELSkillConverter(); 
		for(int skillIndex : skillIndexes){
			Skill skill = null;
			bBELSkillConverter.setIndex(skillIndex);
			bBELSkillConverter.toSkill(skill);
			player.addSkill(skill);
		}
		
		BBELInjuryConverter injConverter = new BBELInjuryConverter(); 
		for(int injIndex : injuryIndexes){
			Injury inj = null;
			injConverter.setIndex(injIndex);
			injConverter.toInjury(inj);
			player.addInjury(inj);
		}
		player.setNumber(number);
	}
	
	static protected float convertValueFrom(int value,float add,float multi){
		float convertedValue = (value + add ) * multi;
		return convertedValue;
	}
	
	static protected int convertValueTo(float value,float add, float multi){
		Float temp = new Float( (value / multi) - add);
		return Math.round(temp);
	}
	
	public static float convertMovementFrom(int movement){
		return convertValueFrom(movement,MOVEMENT_ADDITIONNER,MOVEMENT_MULTIPLIER);
	}
	
	static public int convertMovementTo(float movement){
		return convertValueTo(movement,MOVEMENT_ADDITIONNER,MOVEMENT_MULTIPLIER);
	}

	static public float convertStrengthFrom(int strength){
		return convertValueFrom(strength,STRENGTH_ADDITIONNER,STRENGTH_MULTIPLIER);
	}
	
	static public int convertStrengthTo(float strength){
		return convertValueTo(strength,STRENGTH_ADDITIONNER,STRENGTH_MULTIPLIER);
	}

	static public float convertAgilityFrom(int agility){
		return convertValueFrom(agility,AGILITY_ADDITIONNER,AGILITY_MULTIPLIER);
	}
	
	static public int convertAgilityTo(float agility){
		return convertValueTo(agility,AGILITY_ADDITIONNER,AGILITY_MULTIPLIER);
	}
	
	static public float convertArmourFrom(int armour){
		float convertedArmour = (float) 0.0;
		if( (armour >= ARMOUR_MIN) && (armour <= ARMOUR_MAX) ){
			convertedArmour = ARMOUR[armour - ARMOUR_MIN];
		}
		return convertedArmour;
	}
	
	static public int convertArmourTo(float armour){
		int i;
		int n = ARMOUR.length;
		int convertedArmour = 0;
		float armour_bottom,armour_top;
		final float EPSILON = (float) 0.01;
		for(i=0 ; i< n ; i++){
			armour_bottom = ARMOUR[i] - EPSILON;
			armour_top = ARMOUR[i] + EPSILON;
			if( (armour < armour_top) && (armour > armour_bottom) ){
				convertedArmour = i + ARMOUR_MIN;
				break;
			}
		}
		return convertedArmour;
	}

	public int getPositionIndex() {
		return positionIndex;
	}

	public void setPositionIndex(int positionIndex) {
		this.positionIndex = positionIndex;
	}

	public float getMov() {
		return mov;
	}

	public void setMov(float mov) {
		this.mov = mov;
	}

	public float getSt() {
		return st;
	}

	public void setSt(float st) {
		this.st = st;
	}

	public float getAg() {
		return ag;
	}

	public void setAg(float ag) {
		this.ag = ag;
	}

	public float getAr() {
		return ar;
	}

	public void setAr(float ar) {
		this.ar = ar;
	}

	public ArrayList<Integer> getSkillIndexes() {
		return skillIndexes;
	}

	public void setSkillIndexes(ArrayList<Integer> skillIndexes) {
		this.skillIndexes = skillIndexes;
	}

	public ArrayList<Integer> getInjuryIndexes() {
		return injuryIndexes;
	}

	public void setInjuryIndexes(ArrayList<Integer> injuryIndexes) {
		this.injuryIndexes = injuryIndexes;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public int getValue(){
		return value;
	}
	
	public void setValue(int value){
		this.value = value;
	}
	
	public int getExperience(){
		return experience;
	}
	
	public void setExperience(int experience){
		this.experience = experience;
	}

	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level){
		this.level = level;
	}

	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number){
		this.number = number;
	}
}
