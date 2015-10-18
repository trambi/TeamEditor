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

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class SkillCategory {
	protected String name;
	protected Boolean alwaysAvailable;
	protected List<Skill> skills;
	public static final String XML_TAG= "skill_category";
	public static final String XML_NAME_ATTRIBUTE= "name";
	public static final String XML_ALWAYS_AVAILABLE_ATTRIBUTE= "always_available";
	public static final String XML_CAN_BE_GAINED_TWICE_ATTRIBUTE= "can_be_gained_twice";
		
	public SkillCategory(){
		this.skills = new ArrayList<Skill>();
	}
	public Boolean getAlwaysAvailable() {
		return alwaysAvailable;
	}
	public void setAlwaysAvailable(Boolean alwaysAvailable) {
		this.alwaysAvailable = alwaysAvailable;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Skill> getSkills() {
		return skills;
	}
	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}
	public void extractFromElement(Element iElement){
		if( XML_TAG.equals(iElement.getTagName()) ){
			
			setName(iElement.getAttribute(XML_NAME_ATTRIBUTE));
			setAlwaysAvailable(Boolean.valueOf(iElement.getAttribute(XML_ALWAYS_AVAILABLE_ATTRIBUTE)));
			boolean canBeGainedTwice = false;
			if(iElement.hasAttribute(XML_NAME_ATTRIBUTE)== true){
				canBeGainedTwice = Boolean.valueOf(iElement.getAttribute(XML_ALWAYS_AVAILABLE_ATTRIBUTE));
			}
		    NodeList nodes = iElement.getChildNodes();
			int i = 0;
			int n = nodes.getLength();
		    for(i=0;i<n;i++){
			  if(Skill.XML_TAG.equals(nodes.item(i).getNodeName()) == true){
				  Element elt = (Element)nodes.item(i);
				  Skill skill = new Skill();
				  skill.extractFromElement(elt);
				  skill.setAlwaysAvailable(getAlwaysAvailable());
				  skill.setCanBeGainedTwice(canBeGainedTwice);
				  skills.add(skill);
			  }
		    }
		}
	}
	public int getNbSkills(){
		return this.skills.size();
	}
	public Skill getSkill(int i){
		return this.skills.get(i);
	}
	public Boolean hasSkill(Skill skill){
		return skills.contains(skill);
	}
}
