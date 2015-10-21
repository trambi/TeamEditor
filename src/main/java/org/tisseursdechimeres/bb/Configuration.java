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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Configuration {

  public static final String BASENAME = "configuration.xml";	
  protected Hashtable<String,Skill> skillsByName;
  protected Hashtable<String,SkillCategory> skillCategoriesByName;
  protected Hashtable<String,Position> positionsByNameAndRace;
  protected Hashtable<String,Race> racesByName;
  protected List<Skill> alwaysAvailableSkills;
  protected Hashtable<String,Injury> injuriesByName;
  
  Integer lrbEdition;
  public static final String XML_TAG= "configuration";
  public static final String XML_LRB_EDITION_ATTRIBUTE = "lrb_edition";
  static public int[]PLAYER_MIN_SPP = {0,6,16,31,51,76,176};
  static public int PLAYER_MAX_EXTRA_SKILL = 6;
  
  static private Configuration uniqueInstance = null;
  
  private Configuration(){
	  //this.skillCategories = new ArrayList<SkillCategory>();
	  //this.races = new ArrayList<Race>();
	  skillCategoriesByName =new Hashtable<String,SkillCategory>();
	  skillsByName = new Hashtable<String,Skill>();
	  positionsByNameAndRace = new Hashtable<String,Position>();
	  racesByName = new Hashtable<String,Race>();
	  alwaysAvailableSkills = new ArrayList<Skill>();
	  injuriesByName = new Hashtable<String,Injury>();
  }
  
  static public Configuration getInstance() {
     if(null == uniqueInstance) {
    	 uniqueInstance = new Configuration();
     }
     return uniqueInstance;
  }
  
  public boolean extractFromXMLStream(InputStream input){
    boolean bReturn = false;
    try{
      // création d'une fabrique de documents
      DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();
                    
      // création d'un constructeur de documents
      DocumentBuilder constructeur = fabrique.newDocumentBuilder();
                    
      // lecture du contenu d'un fichier XML avec DOM
      Document document = constructeur.parse(input);
      bReturn = parseDocument(document);
    }catch(ParserConfigurationException pce){
      System.out.println("Erreur de configuration du parseur DOM lors de l'appel à fabrique.newDocumentBuilder();");
      System.out.println(pce.getMessage());
    }catch(SAXException se){
      System.out.println("Erreur lors du parsing du document lors de l'appel à construteur.parse(xml)");
      System.out.println(se.getMessage());
    }catch(IOException ioe){
      System.out.println("Erreur d'entrée/sortie lors de l'appel à constructeur.parse(xml)");
      System.out.println(ioe.getMessage());
    }
    return bReturn;
  }
  
  public boolean extractFromXMLFile(String iFilename){
	 boolean bReturn = false;
	 try{
		 bReturn = extractFromXMLStream(new FileInputStream(iFilename));
	 }catch(FileNotFoundException e){
		 System.out.println("Impossible de trouver le fichier : "+ iFilename);
	 }
	 return bReturn;
  }
	  
  public boolean parseDocument(Document iDoc){
    return extractFromElement(iDoc.getDocumentElement());
  }
	
  public boolean extractFromElement(Element iElement){
	  	boolean bReturn = false;
		if( XML_TAG.equals(iElement.getTagName()) ){
			
		    setLrbEdition(Integer.valueOf(iElement.getAttribute(XML_LRB_EDITION_ATTRIBUTE)));
		    NodeList nodes = iElement.getChildNodes();
			int i = 0;
			int n = nodes.getLength();
			int skillCategoryIndex = 0;
      for(i=0;i<n;i++){
        if(Injury.XML_TAG.equals(nodes.item(i).getNodeName()) == true){
          Element elt = (Element)nodes.item(i);
          Injury injury = new Injury();
          injury.extractFromElement(elt);
          injuriesByName.put(injury.getName(),injury);
        }
        if(SkillCategory.XML_TAG.equals(nodes.item(i).getNodeName()) == true){
          Element elt = (Element)nodes.item(i);
          SkillCategory skillCategory = new SkillCategory();
          skillCategory.extractFromElement(elt);
          skillCategoriesByName.put(skillCategory.getName(),skillCategory);
          for(Skill skill : skillCategory.getSkills()){
        	skill.setCategoryIndex(skillCategoryIndex);
            skillsByName.put(skill.getName(),skill);
            if(true == skill.getAlwaysAvailable())
              alwaysAvailableSkills.add(skill);
          }
          skillCategoryIndex++;
        }else if(Race.XML_TAG.equals(nodes.item(i).getNodeName()) == true){
          Element elt = (Element)nodes.item(i);
          Race race = new Race();
          race.extractFromElement(elt);
          racesByName.put(race.getName(),race);
          for(Position position : race.getPositions()){
            positionsByNameAndRace.put(position.getName()+'@'+race.getName(),position);
          }
        }
      }
      bReturn = true;
		}
		return bReturn;
	}		

  	public Integer getLrbEdition() {
		return lrbEdition;
	}
	
	public void setLrbEdition(Integer lrbEdition) {
		this.lrbEdition = lrbEdition;
	}
	
	public Skill getSkillByName(String name){
		return skillsByName.get(name);
	}
	
	public Collection<Skill> getSkills(){
		return skillsByName.values();
	}
	
	public SkillCategory getSkillCategoryByName(String name){
		return skillCategoriesByName.get(name);
	}

	public Race getRaceByName(String name){
		return racesByName.get(name);
	}
	
	public Position getPositionByNameAndRace(String name,Race race){
		return positionsByNameAndRace.get(name+'@'+race.getName());
	}
	
	public Position getPositionByKey(String key){
		return positionsByNameAndRace.get(key);
	}
	
	public Collection<Race> getRaces() {
		return racesByName.values();
	}
	
	public Collection<Injury> getInjuries(){
		return injuriesByName.values();
	}
	
	public Injury getInjuryByName(String name){
		return injuriesByName.get(name);
	}
	
	public boolean playerCanGainExtraSkills(Player player) {
		boolean returnValue = false;
		int extraSkillCount = player.getExtraSkills().size();
		if(playerMinSpp(extraSkillCount+1) <= player.getSpp())
			returnValue = true;
		return returnValue;
	}
	
	public static int getPlayerPotentialExtraSkillsNumber(Player player) {
		int returnValue = 0;
		boolean found = false;
		int spp = player.getSpp();
		
		int length = playerMinSppLength();
		
		if(null == player.getPosition())
			return 0;
		
		for(returnValue = 0 ; returnValue < length ; returnValue++){
			if(playerMinSpp(returnValue) > spp){
				returnValue--;
				found = true;
				break;
			}
		}
		if(false == found){
			returnValue = playerGetMaxExtraSkill();
		}
		return returnValue;
	}
	
	public static int playerGetMaxExtraSkill(){
		return PLAYER_MAX_EXTRA_SKILL;
	}
	
	public static int playerMinSppLength(){
		return PLAYER_MIN_SPP.length;
	}
	
	public static int playerMinSpp(int index){
		return PLAYER_MIN_SPP[index];
	}
	
	public List<Skill> getAlwaysAvailableSkills(){
		return alwaysAvailableSkills;
	}

	public int getSkillNimber() {
		return skillsByName.size();
	}
	
	public void load(){
		if(0 == getSkillNimber()){
			String confFile = Configuration.BASENAME;
			boolean exists = (new File(confFile)).exists();
			if (false == exists) {
			  	String confResourcePath = new String("/" + confFile);
				extractFromXMLStream(getClass().getResourceAsStream(confResourcePath));
			}else{
				extractFromXMLFile(confFile);
			}
		}
	}
	
}
