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

import java.util.Locale;
import java.util.ResourceBundle;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Skill {
	private String name;
	private String i18nName;
	int movBonus;
	int strBonus;
	int agBonus;
	int arBonus;
	int value;
	private boolean canBeGainedTwice;
	private boolean alwaysAvailable;
	private int categoryIndex;
	
	public static final String XML_TAG= "skill";
	public static final String XML_COST_ATTRIBUTE= "cost";
	public static final String XML_MOV_BONUS_ATTRIBUTE= "mov_bonus";
	public static final String XML_STR_BONUS_ATTRIBUTE= "str_bonus";
	public static final String XML_AG_BONUS_ATTRIBUTE= "ag_bonus";
	public static final String XML_AR_BONUS_ATTRIBUTE= "ar_bonus";
	
	public Skill(){
		name = new String();
		movBonus = 0;
		strBonus = 0;
		agBonus = 0;
		arBonus = 0;
		value = 0;
		canBeGainedTwice = false;
		alwaysAvailable = false;
		
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setMovBonus(Integer movBonus) {
		this.movBonus = movBonus;
	}
	
	public Integer getStrBonus() {
		return strBonus;
	}
	
	public void setStrBonus(Integer strBonus) {
		this.strBonus = strBonus;
	}
	
	public Integer getAgBonus() {
		return agBonus;
	}
	
	public void setAgBonus(Integer agBonus) {
		this.agBonus = agBonus;
	}
	
	public Integer getMovBonus() {
		return movBonus;
	}
	
	public Integer getArBonus() {
		return arBonus;
	}
	
	public void setArBonus(Integer arBonus) {
		this.arBonus = arBonus;
	}
	
	public Integer getValue() {
		return value;
	}
	
	public void setValue(Integer cost) {
		this.value = cost;
	}
	
	public void extractFromElement(Element elt){
		if( XML_TAG.equals(elt.getTagName()) ){
			setName(elt.getTextContent());
			if(elt.hasAttribute(XML_COST_ATTRIBUTE)){
				setValue(Integer.valueOf(elt.getAttribute(XML_COST_ATTRIBUTE)));
			}
			if(elt.hasAttribute(XML_MOV_BONUS_ATTRIBUTE)){
				setMovBonus(Integer.valueOf(elt.getAttribute(XML_MOV_BONUS_ATTRIBUTE)));
			}
			if(elt.hasAttribute(XML_STR_BONUS_ATTRIBUTE)){
				setStrBonus(Integer.valueOf(elt.getAttribute(XML_STR_BONUS_ATTRIBUTE)));
			}
			if(elt.hasAttribute(XML_AG_BONUS_ATTRIBUTE)){
				setAgBonus(Integer.valueOf(elt.getAttribute(XML_AG_BONUS_ATTRIBUTE)));
			}
			if(elt.hasAttribute(XML_AR_BONUS_ATTRIBUTE)){
				setArBonus(Integer.valueOf(elt.getAttribute(XML_AR_BONUS_ATTRIBUTE)));
			}
		}
	}
	
	public Boolean isBonus(){
		Boolean bReturn = new Boolean(false);
		Integer bonus = getMovBonus();
		bonus += getStrBonus();
		bonus += getAgBonus();
		bonus += getArBonus();
		if(0 != bonus){
			bReturn = true;
		}
		
		return bReturn;
	}
	
	public Boolean getCanBeGainedTwice() {
		return canBeGainedTwice;
	}

	public void setCanBeGainedTwice(Boolean canBeGainedTwice) {
		this.canBeGainedTwice = canBeGainedTwice;
	}

	public String getI18nName() {
		if(null == i18nName){
			ResourceBundle i18nRessource = ResourceBundle.getBundle("org.tisseursdechimeres.bb.bloodbowl",Locale.getDefault());
			i18nName = i18nRessource.getString(name);	
		}
		
		return i18nName;
	}

	public void setAlwaysAvailable(boolean alwaysAvailable) {
		this.alwaysAvailable = alwaysAvailable;
	}

	public boolean getAlwaysAvailable() {
		return alwaysAvailable;
	}
	
	public Element toDOMLinkElement(Document iDocument){
		Element skillElt = iDocument.createElement(XML_TAG);
		skillElt.setTextContent(getName());
		return skillElt;
	}

	public void setCategoryIndex(int categoryIndex){
		this.categoryIndex = categoryIndex;
	}
	
	public Integer getCategoryIndex(){
		return Integer.valueOf(categoryIndex);
	}
	
}
