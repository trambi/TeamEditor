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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Race {
	protected String name;
	protected Boolean useApothicary;
	protected List<Position> positions;
	protected Integer rerollCost;
	private String i18nName;
	public static final String XML_TAG= "race";
	public static final String XML_NAME_ATTRIBUTE= "name";
	public static final String XML_USE_APOTHICARY_ATTRIBUTE= "use_apothicary";
	public static final String XML_REROLL_COST_ATTRIBUTE= "reroll_cost";
	
	public Race(){
		this.positions = new ArrayList<Position>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Position> getPositions() {
		return positions;
	}
	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}
	public Integer getRerollCost() {
		return rerollCost;
	}
	public void setRerollCost(Integer rerollCost) {
		this.rerollCost = rerollCost;
	}
	public Boolean getUseApothicary() {
		return useApothicary;
	}
	public void setUseApothicary(Boolean useApothicary) {
		this.useApothicary = useApothicary;
	}
	public void extractFromElement(Element iElement){
		if( XML_TAG.equals(iElement.getTagName()) ){
			String name = iElement.getAttribute(XML_NAME_ATTRIBUTE); 
			setName(name);
			if(true == iElement.getAttribute(XML_USE_APOTHICARY_ATTRIBUTE).equals("1") ){
				setUseApothicary(Boolean.TRUE);
			}else{
				setUseApothicary(Boolean.FALSE);
			}
			setRerollCost(Integer.valueOf(iElement.getAttribute(XML_REROLL_COST_ATTRIBUTE)));
		    NodeList nodes = iElement.getChildNodes();
			int i = 0;
			int n = nodes.getLength();
		    for(i=0;i<n;i++){
			  if(Position.XML_TAG.equals(nodes.item(i).getNodeName()) == true){
				  Element elt = (Element)nodes.item(i);
				  Position position = new Position();
				  position.extractFromElement(elt);
				  position.setRaceName(name);
				  positions.add(position);
			  }
		    }
		}
	}

	public String getI18nName() {
		if(null == i18nName){
			ResourceBundle i18nRessource = ResourceBundle.getBundle("org.tisseursdechimeres.bb.bloodbowl",Locale.getDefault());
			i18nName = i18nRessource.getString(name);	
		}
		return i18nName;
	}

	public Element toDOMLinkElement(Document iDocument){
		Element raceElt = iDocument.createElement(XML_TAG);
		raceElt.setTextContent(getName());
		return raceElt;
	}
}
