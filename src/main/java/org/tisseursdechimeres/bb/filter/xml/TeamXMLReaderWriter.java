/*
Copyright (C) 2010-2011  Bertrand MADET

This org.tisseursdechimeres.bb.filter.xml is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This org.tisseursdechimeres.bb.filter.xml is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this org.tisseursdechimeres.bb.filter.xml.  If not, see <http://www.gnu.org/licenses/>.
*/
package org.tisseursdechimeres.bb.filter.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;

import javax.swing.filechooser.FileFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.tisseursdechimeres.bb.Configuration;
import org.tisseursdechimeres.bb.Injury;
import org.tisseursdechimeres.bb.Player;
import org.tisseursdechimeres.bb.Position;
import org.tisseursdechimeres.bb.Race;
import org.tisseursdechimeres.bb.Skill;
import org.tisseursdechimeres.bb.Team;
import org.tisseursdechimeres.bb.filter.TeamFileWriter;
import org.tisseursdechimeres.bb.filter.TeamFileReader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TeamXMLReaderWriter implements TeamFileWriter,TeamFileReader {

	protected static final String XML_TEAM_TAG= "team";
	protected static final String XML_TEAM_APOTHICARY_ATTRIBUTE= "apothicary";
	protected static final String XML_TEAM_ASSISTANTS_ATTRIBUTE= "assistants";
	protected static final String XML_TEAM_CHEERLEADERS_ATTRIBUTE= "cheerleaders";
	protected static final String XML_TEAM_COACH_ATTRIBUTE= "coach";
	protected static final String XML_TEAM_FF_ATTRIBUTE= "ff";
	protected static final String XML_TEAM_NAME_ATTRIBUTE= "name";
	protected static final String XML_TEAM_REROLL_ATTRIBUTE= "rerolls";
	protected static final String XML_TEAM_TREASURY_ATTRIBUTE= "treasury";
	protected static final String XML_TEAM_RACE_ATTRIBUTE= "race";
	
	public static final String XML_PLAYER_TAG= "player";
	public static final String XML_PLAYER_NAME_ATTRIBUTE= "name";
	public static final String XML_PLAYER_NUMBER_ATTRIBUTE= "number";
	public static final String XML_PLAYER_COMP_ATTRIBUTE= "comp";
	public static final String XML_PLAYER_INTER_ATTRIBUTE= "inter";
	public static final String XML_PLAYER_CAS_ATTRIBUTE= "cas";
	public static final String XML_PLAYER_TD_ATTRIBUTE= "td";
	public static final String XML_PLAYER_MVP_ATTRIBUTE= "mvp";
	public static final String XML_PLAYER_POSITION_ATTRIBUTE = "position";
	public static final String XML_PLAYER_EXTRA_SKILL_TAG = "extra_skill";
	public static final String XML_PLAYER_INJURY_TAG = "injury";
	public static final String XML_INJURY_NAME_ATTRIBUTE = "name";
	public static final String XML_SKILL_NAME_ATTRIBUTE = "name";
	
	protected FileFilter filter;
	protected Team team;
	public TeamXMLReaderWriter(){
		this.filter = new TeamXMLFileFilter();
	}
	@Override
	public boolean writeFile(File fileToWrite) throws Exception{
		boolean bReturn = false;
		File teamFile = fileToWrite;
		try{
			teamFile.createNewFile();
			FileOutputStream writer = new FileOutputStream(teamFile);
			String strXml = getXMLString();
			writer.write(strXml.getBytes("utf-8"),0,strXml.length());
			writer.flush();
			writer.close();
			bReturn = true;
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return bReturn;
	}
	
	
	@Override
	public boolean readFile(File fileToRead) throws Exception{
		boolean bReturn = false;
		try{
			// création d'une fabrique de documents
			DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();
			                
			// création d'un constructeur de documents
			DocumentBuilder constructeur = fabrique.newDocumentBuilder();
			                
			// lecture du contenu d'un fichier XML avec DOM
			Document document = constructeur.parse(fileToRead);
			parseDocument(document);
			bReturn = true;
		  }catch(ParserConfigurationException pce){
			Exception e = new Exception("Erreur de configuration du parseur DOM lors de l'appel à fabrique.newDocumentBuilder()");
			throw e;
			//System.out.println("Erreur de configuration du parseur DOM");
			//System.out.println("lors de l'appel à fabrique.newDocumentBuilder();");
		  }catch(SAXException se){
			Exception e = new Exception("Erreur lors du parsing du document lors de l'appel à construteur.parse(xml)");
			throw e;
			//System.out.println("Erreur lors du parsing du document");
			//System.out.println("lors de l'appel à construteur.parse(xml)");
		  }catch(IOException ioe){
			Exception e = new Exception("Erreur d'entrée/sortie lors de l'appel à constructeur.parse(xml)");  
			throw e;
			//System.out.println("Erreur d'entrée/sortie");
			//System.out.println("lors de l'appel à constructeur.parse(xml)");
		}
		return bReturn;
	}

	@Override
	public FileFilter getOutputFileFilter() {
		return this.filter;
	}

	@Override
	public void setTeamToWrite(Team team) {
		this.team = team;

	}
	public FileFilter getInputFileFilter() {
		return this.filter;
	}

	@Override
	public void setTeamToRead(Team team) {
		this.team = team;

	}
	
	protected String getXMLString() throws Exception {
		// Création d'un nouveau DOM
		String stringResult = null;
		try{
			DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();
			DocumentBuilder constructeur = fabrique.newDocumentBuilder();
			Document document = constructeur.newDocument();

			// Propriétés du DOM
			document.setXmlVersion("1.0");
			document.setXmlStandalone(true);
			
			Element racine = teamToDOMElement(document);
			document.appendChild(racine);
			
			Source domSource = new DOMSource(document);
			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.transform(domSource, result);
			stringResult = writer.toString();
		}
		catch(ParserConfigurationException pce){
			Exception e = new Exception("Exception de configuration de l'extracteur");
			throw e;
			//System.out.println("Exception de configuration de l'extracteur");
		}
		catch(TransformerConfigurationException tce){
			Exception e = new Exception("Exception de configuration du transformateur");
			throw e;
			//System.out.println("Exception de configuration du transformateur");
		}
		catch(TransformerException te){
			Exception e = new Exception("Exception du transformateur");
			throw e;
			//System.out.println("Exception du transformateur");
		}
		return stringResult;
	}
	
	protected Element teamToDOMElement(Document iDocument){
		team.sortPlayers();
		Race race = team.getRace();
		Element teamElt = iDocument.createElement(XML_TEAM_TAG);
		teamElt.setAttribute(XML_TEAM_APOTHICARY_ATTRIBUTE, team.getApothicary().toString());
		teamElt.setAttribute(XML_TEAM_ASSISTANTS_ATTRIBUTE, team.getAssistants().toString());
		teamElt.setAttribute(XML_TEAM_CHEERLEADERS_ATTRIBUTE, team.getCheerleaders().toString());
		teamElt.setAttribute(XML_TEAM_COACH_ATTRIBUTE, team.getCoach());
		teamElt.setAttribute(XML_TEAM_FF_ATTRIBUTE, team.getFf().toString());
		teamElt.setAttribute(XML_TEAM_NAME_ATTRIBUTE, team.getName());
		teamElt.setAttribute(XML_TEAM_REROLL_ATTRIBUTE, team.getRerolls().toString());
		teamElt.setAttribute(XML_TEAM_TREASURY_ATTRIBUTE, team.getTreasury().toString());
		teamElt.setAttribute(XML_TEAM_RACE_ATTRIBUTE, race.getName());
		for(Player player : team.getPlayers() ){
			if( (null != player) && (null != player.getPosition())){
				player.setRace(race);
				teamElt.appendChild(playerToDOMElement(iDocument,player));
			}
		}
		return teamElt;
	}
	
	protected void extractTeamFromElement(Element teamElt){
		if( XML_TEAM_TAG.equals(teamElt.getTagName()) ){
		  team.setApothicary(Integer.valueOf(teamElt.getAttribute(XML_TEAM_APOTHICARY_ATTRIBUTE)));
		  team.setAssistants(Integer.valueOf(teamElt.getAttribute(XML_TEAM_ASSISTANTS_ATTRIBUTE)));
		  team.setCheerleaders(Integer.valueOf(teamElt.getAttribute(XML_TEAM_CHEERLEADERS_ATTRIBUTE)));
		  team.setCoach(teamElt.getAttribute(XML_TEAM_COACH_ATTRIBUTE));
		  team.setFf(Integer.valueOf(teamElt.getAttribute(XML_TEAM_FF_ATTRIBUTE)));
		  team.setName(teamElt.getAttribute(XML_TEAM_NAME_ATTRIBUTE));
		  team.setRerolls(Integer.valueOf(teamElt.getAttribute(XML_TEAM_REROLL_ATTRIBUTE)));
		  team.setTreasury(Integer.valueOf(teamElt.getAttribute(XML_TEAM_TREASURY_ATTRIBUTE)));
		  Race race = Configuration.getInstance().getRaceByName(teamElt.getAttribute(XML_TEAM_RACE_ATTRIBUTE));
		  if(null != race){
			  team.setRace(race);
		  }else{
			System.out.println("Impossible de trouver la race : "+ teamElt.getAttribute(XML_TEAM_RACE_ATTRIBUTE) );
		  }
		  team.getPlayers().clear();
		  NodeList nodes = teamElt.getChildNodes();
		  int i = 0;
		  int n = nodes.getLength();
		  
		  for(i=0;i<n;i++){
			if(XML_PLAYER_TAG.equals(nodes.item(i).getNodeName()) == true){
			  Element elt = (Element)nodes.item(i);
			  Player player = new Player();
			  player.setRace(race);
			  extractPlayerFromElement(player,elt);
			  team.addPlayer(player);
		    }
		  }
		  team.sortPlayers();
		}
	}
	
	protected void parseDocument(Document iDoc){
	  extractTeamFromElement(iDoc.getDocumentElement());
	}
	
	protected void extractPlayerFromElement(Player player,Element elt){
		if( XML_PLAYER_TAG.equals(elt.getTagName()) ){
			player.setName(elt.getAttribute(XML_PLAYER_NAME_ATTRIBUTE));
			player.setComp(Integer.valueOf(elt.getAttribute(XML_PLAYER_COMP_ATTRIBUTE)));
			player.setInter(Integer.valueOf(elt.getAttribute(XML_PLAYER_INTER_ATTRIBUTE)));
			player.setCas(Integer.valueOf(elt.getAttribute(XML_PLAYER_CAS_ATTRIBUTE)));
			player.setTd(Integer.valueOf(elt.getAttribute(XML_PLAYER_TD_ATTRIBUTE)));
			player.setMvp(Integer.valueOf(elt.getAttribute(XML_PLAYER_MVP_ATTRIBUTE)));
			player.setNumber(Integer.valueOf(elt.getAttribute(XML_PLAYER_NUMBER_ATTRIBUTE)));
			Position position = Configuration.getInstance().getPositionByNameAndRace(elt.getAttribute(XML_PLAYER_POSITION_ATTRIBUTE),team.getRace());
			if(null != position){
				player.setPosition(position);
			}else{
				System.out.println("Impossible de trouver la position : "+ elt.getAttribute(XML_PLAYER_POSITION_ATTRIBUTE) );
			}
		    
			NodeList nodes = elt.getChildNodes();
			int i = 0;
			int n = nodes.getLength();
		    for(i=0;i<n;i++){
			  if(XML_PLAYER_EXTRA_SKILL_TAG.equals(nodes.item(i).getNodeName()) == true){
				  Element child = (Element)nodes.item(i);
				  Skill skill = Configuration.getInstance().getSkillByName(child.getAttribute(XML_SKILL_NAME_ATTRIBUTE));
				  if(null != skill){
					  player.addSkill(skill);
				  }else{
					  System.out.println("Impossible de trouver la competence : "+child.getAttribute(XML_SKILL_NAME_ATTRIBUTE) );
				  }
			  }else if(XML_PLAYER_INJURY_TAG.equals(nodes.item(i).getNodeName()) == true){
				  Element child = (Element)nodes.item(i);
				  Injury inj = Configuration.getInstance().getInjuryByName(child.getAttribute(XML_INJURY_NAME_ATTRIBUTE));
				  if(null != inj){
					  player.addInjury(inj);
				  }else{
					  System.out.println("Impossible de trouver la blessure : "+child.getAttribute(XML_INJURY_NAME_ATTRIBUTE) );
				  }
			  }
		    }
		}
	}
	
	protected Element playerToDOMElement(Document iDocument,Player player){
		Element playerElt = iDocument.createElement(XML_PLAYER_TAG);
		playerElt.setAttribute(XML_PLAYER_NAME_ATTRIBUTE, player.getName());
		playerElt.setAttribute(XML_PLAYER_COMP_ATTRIBUTE, player.getComp().toString());
		playerElt.setAttribute(XML_PLAYER_INTER_ATTRIBUTE, player.getInter().toString());
		playerElt.setAttribute(XML_PLAYER_CAS_ATTRIBUTE, player.getCas().toString());
		playerElt.setAttribute(XML_PLAYER_TD_ATTRIBUTE, player.getTd().toString());
		playerElt.setAttribute(XML_PLAYER_MVP_ATTRIBUTE, player.getMvp().toString());
		playerElt.setAttribute(XML_PLAYER_NUMBER_ATTRIBUTE, player.getNumber().toString());
		playerElt.setAttribute(XML_PLAYER_POSITION_ATTRIBUTE, player.getPosition().getName());
		for(Skill skill : player.getExtraSkills()){
			Element skillElt = iDocument.createElement(XML_PLAYER_EXTRA_SKILL_TAG);
			skillElt.setAttribute(XML_SKILL_NAME_ATTRIBUTE, skill.getName());
			playerElt.appendChild(skillElt);
		}
		for(Injury inj : player.getInjuries()){
			Element injElt = iDocument.createElement(XML_PLAYER_INJURY_TAG);
			injElt.setAttribute(XML_INJURY_NAME_ATTRIBUTE, inj.getName());
			playerElt.appendChild(injElt);
		}
		return playerElt;
	}
	@Override
	public boolean isRaceWriteable(Race race) {
		return true;
	}

}
