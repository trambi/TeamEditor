/*
Copyright (C) 2010  Bertrand MADET

This org.tisseursdechimeres.bb is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This org.tisseursdechimeres.bb is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this org.tisseursdechimeres.bb.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.tisseursdechimeres.bb;

import java.util.ArrayList;
import java.util.List;

public class Player {
	Integer number;
	String name;
	Skills extraSkills;
	Position position;
	Integer mov;
	Integer str;
	Integer ag;
	Integer ar;
	Injuries injuries;
	Integer comp;
	Integer inter;
	Integer cas;
	Integer td;
	Integer mvp;
	Integer spp;
	Integer value;
	Race race;

	static public int SPP_COMP = 1;
	static public int SPP_INTER = 2;
	static public int SPP_CAS = 2;
	static public int SPP_TD = 3;
	static public int SPP_MVP = 5;
		
	private void init(Position position){
		number = new Integer(1);
		name = new String();
		extraSkills = new Skills();
		this.position = position;
		mov = new Integer(0);
		str = new Integer(0);
		ag = new Integer(0);
		ar  = new Integer(0);
		injuries = new Injuries();
		comp  = new Integer(0);
		inter = new Integer(0);
		cas = new Integer(0);
		td = new Integer(0);
		mvp = new Integer(0);
		spp = new Integer(0);
		value = new Integer(0);
	}
	
	public Player(){
		init(null);
	}
	
	public Player(Position position){
		init(position);
	}

	public Integer getAg() {
		return ag;
	}

	public Integer getAr() {
		return ar;
	}

	public Integer getCas() {
		return cas;
	}

	public void setCas(Integer cas) {
		this.cas = cas;
		computeSPP();
	}


	public Integer getComp() {
		return comp;
	}


	public void setComp(Integer comp) {
		this.comp = comp;
		computeSPP();
	}


	public Integer getValue() {
		return value;
	}

	public Skills getExtraSkills() {
		return extraSkills;
	}

	public int getExtraSkillNumber() {
		return extraSkills.size();
	}
	
	public void setExtraSkills(Skills extraSkills) {
		this.extraSkills = extraSkills;
	}
	
	public Injuries getInjuries() {
		return injuries;
	}

	public int getInjuriesNumber() {
		return injuries.size();
	}
	
	public void setInjuries(Injuries injuries) {
		this.injuries = injuries;
	}
	
	public void clearInjuries() {
		injuries.clear();
	}
	
	public void addInjury(Injury inj) {
		injuries.add(inj);
		computeStats();
	}

	public String getInjurySummary() {
		String injurySummary = new String();
		for(Injury injury : injuries ){
			injurySummary += injury.getI18nSymbol();
		}
		return injurySummary;
	}

	public Integer getInter() {
		return inter;
	}
	
	public Integer getInjuryNumber(Injury inj){
		Integer injuryNumber = new Integer(0);
		for(Injury injury : injuries ){
			if(inj == injury)
				injuryNumber ++;
		}
		return injuryNumber;
	}

	public void setInter(Integer inter) {
		this.inter = inter;
		computeSPP();
	}

	public Integer getMov() {
		return mov;
	}

	public Integer getMvp() {
		return mvp;
	}

	public void setMvp(Integer mvp) {
		this.mvp = mvp;
		computeSPP();
	}

	public String getName() {
		String displayName;
		if(null == name){
			displayName = number.toString();
		}else{
			displayName = name;
		}
		return displayName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
		computeStats();
	}

	public Integer getSpp() {
		return spp;
	}

	public Integer getStr() {
		return str;
	}

	public Integer getTd() {
		return td;
	}

	public void setTd(Integer td) {
		this.td = td;
		computeSPP();
	}

	public void computeSPP(){
		spp = (comp * SPP_COMP) + (inter * SPP_INTER) + (cas * SPP_CAS) + (td * SPP_TD) + (mvp * SPP_MVP);
	}
	
	public void computeStats(){
		int modMov = 0,modStr = 0,modAg = 0,modAr = 0,modValue=0;
		int mov = 0,str = 0,ag = 0,ar = 0,value=0;
		if(null != position){
			mov = position.getMov();
			str = position.getStr();
			ag = position.getAg();
			ar = position.getAr();
			value = position.getCost();
		}
		
		for(Skill skill : extraSkills){
			modMov += skill.movBonus;
			modStr += skill.strBonus;
			modAg += skill.agBonus;
			modAr += skill.arBonus;
			if(null != position)
				modValue += position.getValueOfSkill(skill);
		}
		
		for(Injury inj : injuries){
			modMov -= inj.getMovMalus();
			modStr -= inj.getStrMalus();
			modAg -= inj.getAgMalus();
			modAr -= inj.getArMalus();
		}
		
		this.mov = mov + modMov;
		this.str = str + modStr;
		this.ag = ag + modAg;
		this.ar = ar + modAr;
		this.value = value + modValue;
	}
	
	public boolean addSkill(Skill skill){
		boolean returnValue = false;
		if( (true == skill.getCanBeGainedTwice()) || (false == extraSkills.contains(skill)) ){
			if( (1 == skill.getArBonus()) && (10 == ar) ){
				returnValue = false;
			}else if( (1==skill.getMovBonus()) && (10==mov) ){
				returnValue = false;
			}else{
				returnValue = true;
				extraSkills.add(skill);
				computeStats();
			}
		}
		return returnValue;
	}
	
	public void clearExtraSkills(){
		extraSkills.clear();
		computeStats();
	}

	public List<Skill> getPositionSkills() {
		if(null != position){
			return position.getSkills();
		}else{
			return new ArrayList<Skill>();	
		}
	}
	
	public boolean missNextGame(){
		boolean bReturn = false;
		for(Injury inj : injuries){
			if(true == inj.isMissNextGame()){
				bReturn = true;
				break;
			}
		}
		return bReturn;
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}

	public void reset() {
		Integer n = getNumber();
		init(null);
		setNumber(n);
		setName(n.toString());
	}
}
