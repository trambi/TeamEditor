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

import org.w3c.dom.Element;

public class Injury {
	protected int movMalus;
	protected int strMalus;
	protected int agMalus;
	protected int arMalus;
	protected int maxNumber;
	protected boolean missNextGame;
	protected String name;
	protected String symbol;
	protected String i18nName;
	protected String i18nSymbol;
	
	public static final String XML_TAG= "injury";
	public static final String XML_MISS_NEXT_GAME_ATTRIBUTE = "miss_next_game";
	public static final String XML_MOV_MALUS_ATTRIBUTE = "mov_malus";
	public static final String XML_STR_MALUS_ATTRIBUTE = "str_malus";
	public static final String XML_AG_MALUS_ATTRIBUTE = "ag_malus";
	public static final String XML_AR_MALUS_ATTRIBUTE = "ar_malus";
	public static final String XML_MAX_NUMBER_ATTRIBUTE = "max_number";
	public static final String XML_SYMBOL_ATTRIBUTE = "symbol";
	
	public Injury(){
		movMalus = 0;
		strMalus = 0;
		agMalus = 0;
		arMalus = 0;
		maxNumber = 8;
		name = new String();
		symbol = new String();
		missNextGame = false;
	}
	
	public int getMovMalus() {
		return movMalus;
	}
	public void setMovMalus(int movMalus) {
		this.movMalus = movMalus;
	}
	public int getStrMalus() {
		return strMalus;
	}
	public void setStrMalus(int strMalus) {
		this.strMalus = strMalus;
	}
	public int getAgMalus() {
		return agMalus;
	}
	public void setAgMalus(int agMalus) {
		this.agMalus = agMalus;
	}
	public int getArMalus() {
		return arMalus;
	}
	public void setArMalus(int arMalus) {
		this.arMalus = arMalus;
	}
	public int getMaxNumber() {
		return maxNumber;
	}
	public void setMaxNumber(int maxNumber) {
		this.maxNumber = maxNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getI18nName(){
		if(null == i18nName){
			ResourceBundle i18nRessource = ResourceBundle.getBundle("org.tisseursdechimeres.bb.bloodbowl",Locale.getDefault());
			i18nName = i18nRessource.getString(name);	
		}
		
		return i18nName;
	}
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public String getI18nSymbol(){
		if(null == i18nSymbol){
			ResourceBundle i18nRessource = ResourceBundle.getBundle("org.tisseursdechimeres.bb.bloodbowl",Locale.getDefault());
			i18nSymbol = i18nRessource.getString(symbol);	
		}
		
		return i18nSymbol;
	}
	
	public void extractFromElement(Element elt){
		if( XML_TAG.equals(elt.getTagName()) ){
			setName(elt.getTextContent());
			if(elt.hasAttribute(XML_MISS_NEXT_GAME_ATTRIBUTE))
				setMissNextGame(Boolean.valueOf(elt.getAttribute(XML_MISS_NEXT_GAME_ATTRIBUTE)));
			if(elt.hasAttribute(XML_MOV_MALUS_ATTRIBUTE))
				setMovMalus(Integer.valueOf(elt.getAttribute(XML_MOV_MALUS_ATTRIBUTE)));
			if(elt.hasAttribute(XML_STR_MALUS_ATTRIBUTE))
				setStrMalus(Integer.valueOf(elt.getAttribute(XML_STR_MALUS_ATTRIBUTE)));
			if(elt.hasAttribute(XML_AG_MALUS_ATTRIBUTE))
				setAgMalus(Integer.valueOf(elt.getAttribute(XML_AG_MALUS_ATTRIBUTE)));
			if(elt.hasAttribute(XML_AR_MALUS_ATTRIBUTE))
				setArMalus(Integer.valueOf(elt.getAttribute(XML_AR_MALUS_ATTRIBUTE)));
			if(elt.hasAttribute(XML_MAX_NUMBER_ATTRIBUTE))
				setMaxNumber(Integer.valueOf(elt.getAttribute(XML_MAX_NUMBER_ATTRIBUTE)));
			if(elt.hasAttribute(XML_SYMBOL_ATTRIBUTE))
				setSymbol(elt.getAttribute(XML_SYMBOL_ATTRIBUTE));
		}
	}

	public boolean isMissNextGame() {
		return missNextGame;
	}

	public void setMissNextGame(boolean missNextGame) {
		this.missNextGame = missNextGame;
	}
}

