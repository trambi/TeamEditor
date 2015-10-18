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
import java.util.Locale;
import java.util.ResourceBundle;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Position {
	protected String name;
	protected List<SkillCategory> simpleSkillChoices;
	protected List<SkillCategory> doubleSkillChoices;
	protected Skills skills;
	protected Integer mov;
	protected Integer str;
	protected Integer ag;
	protected Integer ar;
	protected Integer min;
	protected Integer max;
	protected Integer cost;
	private String i18nName;
	protected String raceName;
	
	public static final int SIMPLE_SKILL_VALUE = 2;
	public static final int DOUBLE_SKILL_VALUE = 3;

	public static final String XML_TAG= "position";
	public static final String XML_NAME_ATTRIBUTE= "name";
	public static final String XML_COST_ATTRIBUTE= "cost";
	public static final String XML_MIN_ATTRIBUTE= "min";
	public static final String XML_MAX_ATTRIBUTE= "max";
	public static final String XML_MOV_ATTRIBUTE= "mov";
	public static final String XML_STR_ATTRIBUTE= "str";
	public static final String XML_AG_ATTRIBUTE= "ag";
	public static final String XML_AR_ATTRIBUTE= "ar";
	public static final String XML_SKILL_TAG= "position_skill";
	public static final String XML_SKILL_ID_ATTRIBUTE= "name";
	public static final String XML_CATEGORY_CHOICE_TAG= "category_choice";
	public static final String XML_CATEGORY_CHOICE_ID_ATTRIBUTE= "name";
	public static final String XML_CATEGORY_CHOICE_IS_SIMPLE_ATTRIBUTE= "is_simple";

	public Position(){
		this.skills = new Skills();
		this.doubleSkillChoices = new ArrayList<SkillCategory>();
		this.simpleSkillChoices = new ArrayList<SkillCategory>();
	}
	public Integer getAg() {
		return ag;
	}
	public void setAg(Integer ag) {
		this.ag = ag;
	}
	public Integer getAr() {
		return ar;
	}
	public void setAr(Integer ar) {
		this.ar = ar;
	}
	public List<SkillCategory> getDoubleSkillChoices() {
		return doubleSkillChoices;
	}
	public void setDoubleSkillChoices(List<SkillCategory> doubleSkillChoices) {
		this.doubleSkillChoices = doubleSkillChoices;
	}
	public Integer getMax() {
		return max;
	}
	public void setMax(Integer max) {
		this.max = max;
	}
	public Integer getMin() {
		return min;
	}
	public void setMin(Integer min) {
		this.min = min;
	}
	public Integer getMov() {
		return mov;
	}
	public void setMov(Integer mov) {
		this.mov = mov;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<SkillCategory> getSimpleSkillChoices() {
		return simpleSkillChoices;
	}
	public void setSimpleSkillChoices(List<SkillCategory> simpleSkillChoices) {
		this.simpleSkillChoices = simpleSkillChoices;
	}
	public Skills getSkills() {
		return skills;
	}
	public void setSkills(Skills skills) {
		this.skills = skills;
	}
	public Integer getStr() {
		return str;
	}
	public void setStr(Integer str) {
		this.str = str;
	}
	
	public void extractFromElement(Element elt){
		if( XML_TAG.equals(elt.getTagName()) ){
			setName(elt.getAttribute(XML_NAME_ATTRIBUTE));
			setCost(Integer.valueOf(elt.getAttribute(XML_COST_ATTRIBUTE)));
			setMin(Integer.valueOf(elt.getAttribute(XML_MIN_ATTRIBUTE)));
			setMax(Integer.valueOf(elt.getAttribute(XML_MAX_ATTRIBUTE)));
			setMov(Integer.valueOf(elt.getAttribute(XML_MOV_ATTRIBUTE)));
			setStr(Integer.valueOf(elt.getAttribute(XML_STR_ATTRIBUTE)));
			setAg(Integer.valueOf(elt.getAttribute(XML_AG_ATTRIBUTE)));
			setAr(Integer.valueOf(elt.getAttribute(XML_AR_ATTRIBUTE)));
		    NodeList nodes = elt.getChildNodes();
			int i = 0;
			int n = nodes.getLength();
		    for(i=0;i<n;i++){
			  if(XML_SKILL_TAG.equals(nodes.item(i).getNodeName()) == true){
				  Element child = (Element)nodes.item(i);
				  Skill skill = Configuration.getInstance().getSkillByName(child.getAttribute(XML_SKILL_ID_ATTRIBUTE));
				  if(null != skill){
					  skills.add(skill);
				  }else{
					  System.out.println("Impossible de trouver la competence : "+child.getAttribute(XML_SKILL_ID_ATTRIBUTE) );
				  }
			  }else if(XML_CATEGORY_CHOICE_TAG.equals(nodes.item(i).getNodeName()) == true){
				  Element child = (Element)nodes.item(i);
				  String skillCatName = child.getAttribute(XML_CATEGORY_CHOICE_ID_ATTRIBUTE);
				  SkillCategory skillCategory = Configuration.getInstance().getSkillCategoryByName(skillCatName);
				  if(Boolean.valueOf(child.getAttribute(XML_CATEGORY_CHOICE_IS_SIMPLE_ATTRIBUTE)) == true){
					  simpleSkillChoices.add(skillCategory);
				  }else{
					  doubleSkillChoices.add(skillCategory);
				  }
			  }
		    }
		}
	}
	
	public Integer getCost() {
		return cost;
	}
	
	public void setCost(Integer cost) {
		this.cost = cost;
	}
	
	public int getValueOfSkill(Skill skill){
		int value = skill.getValue(); 
		if(0 == value){
			for (SkillCategory skillCat : simpleSkillChoices){
				if(true == skillCat.hasSkill(skill)){
					value = SIMPLE_SKILL_VALUE;
				}
			}
		}
		if(0 == value){
			for (SkillCategory skillCat : doubleSkillChoices){
				if(true == skillCat.hasSkill(skill)){
					value = DOUBLE_SKILL_VALUE;
				}
			}
		}
		
		return value;
	}
	public String getI18nName() {
		if(null == i18nName){
			ResourceBundle i18nRessource = ResourceBundle.getBundle("org.tisseursdechimeres.bb.bloodbowl",Locale.getDefault());
			i18nName = i18nRessource.getString(name);	
		}
		return i18nName;
	}
	
	public boolean canGetSkill(Skill skill){
		boolean returnValue = false;
		returnValue = skill.getAlwaysAvailable();
		if(false == returnValue){
			for(SkillCategory skillCat : simpleSkillChoices){
				if(true == skillCat.hasSkill(skill)){
					returnValue = true;
					break;
				}
			}
		}
		if(false == returnValue){
			for(SkillCategory skillCat : doubleSkillChoices){
				if(true == skillCat.hasSkill(skill)){
					returnValue = true;
					break;
				}
			}
		}
		return returnValue;
	}
	
	public List<Skill> getAvailableSkills(){
		List<Skill> availableSkills = new ArrayList<Skill>(0);
		for(SkillCategory skillCat : simpleSkillChoices){
			availableSkills.addAll(skillCat.getSkills());
		}
		for(SkillCategory skillCat : doubleSkillChoices){
			availableSkills.addAll(skillCat.getSkills());
		}
		availableSkills.addAll(Configuration.getInstance().getAlwaysAvailableSkills());
		for(Skill skill : skills){
			if(false == skill.getCanBeGainedTwice())
				availableSkills.remove(skill);
		}
		return availableSkills;
	}
	public String getRaceName() {
		return raceName;
	}
	public void setRaceName(String raceName) {
		this.raceName = raceName;
		
	}
}