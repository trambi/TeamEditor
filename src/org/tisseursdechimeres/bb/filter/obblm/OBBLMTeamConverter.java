/*
Copyright (C) 2011  Bertrand MADET

This org.tisseursdechimeres.bb.filter.obblm is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This org.tisseursdechimeres.bb.filter.obblm is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this org.tisseursdechimeres.bb.filter.obblm.  If not, see <http://www.gnu.org/licenses/>.
*/
package org.tisseursdechimeres.bb.filter.obblm;

import java.io.File;
import java.io.FileOutputStream;
import java.io.StringWriter;

import javax.swing.filechooser.FileFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.tisseursdechimeres.bb.Player;
import org.tisseursdechimeres.bb.Race;
import org.tisseursdechimeres.bb.Team;
import org.tisseursdechimeres.bb.filter.TeamFileWriter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class OBBLMTeamConverter
  implements TeamFileWriter
{
  protected static final String XML_TEAMS_TAG = "teams";
  protected static final String XML_TEAM_TAG = "team";
  protected static final String XML_NAME_TAG = "name";
  protected static final String XML_COACH_TAG = "coach_id";
  protected static final String XML_RACE_ID_TAG = "race_id";
  protected static final String XML_LEAGUE_ID_TAG = "league_id";
  protected static final String XML_DIVISION_ID_TAG = "division_id";
  protected static final String XML_TREASURY_TAG = "treasury";
  protected static final String XML_APOTHICARY_TAG = "apothecary";
  protected static final String XML_REROLL_TAG = "rr";
  protected static final String XML_FF_TAG = "ff";
  protected static final String XML_ASSISTANTS_TAG = "ass_coaches";
  protected static final String XML_CHEERLEADERS_TAG = "cheerleaders";
  protected static final String XML_WON_TAG = "won";
  protected static final String XML_LOST_TAG = "lost";
  protected static final String XML_DRAW_TAG = "draw";
  protected static final String XML_WON_TOURNAMENT_TAG = "wt";
  protected static final String XML_GOAL_FOR_TAG = "gf";
  protected static final String XML_GOAL_AGAINST_TAG = "ga";
  protected static final String XML_PLAYERS_TAG = "players";
  protected FileFilter filter;
  protected Team team;

  public OBBLMTeamConverter()
  {
    this.filter = new OBBLMFileFilter();
  }

  public FileFilter getOutputFileFilter()
  {
    return this.filter;
  }

  public void setTeamToWrite(Team team)
  {
    this.team = team;
  }

  public boolean writeFile(File fileToWrite) throws Exception
  {
    boolean bReturn = false;
    File teamFile = fileToWrite;
    try {
      teamFile.createNewFile();
      FileOutputStream writer = new FileOutputStream(teamFile);
      String strXml = getXMLString();
      writer.write(strXml.getBytes("utf-8"), 0, strXml.length());
      writer.flush();
      writer.close();
      bReturn = true;
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
    return bReturn;
  }

  protected String getXMLString() throws Exception
  {
    String stringResult = null;
    try {
      DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();
      DocumentBuilder constructeur = fabrique.newDocumentBuilder();
      Document document = constructeur.newDocument();

      document.setXmlVersion("1.0");
      document.setXmlStandalone(true);

      Element racine = toDOMElement(document);
      document.appendChild(racine);

      Source domSource = new DOMSource(document);
      StringWriter writer = new StringWriter();
      StreamResult result = new StreamResult(writer);
      TransformerFactory tf = TransformerFactory.newInstance();
      Transformer transformer = tf.newTransformer();
      transformer.setOutputProperty("indent", "yes");
      transformer.setOutputProperty("encoding", "UTF-8");
      transformer.transform(domSource, result);
      stringResult = writer.toString();
    }
    catch (ParserConfigurationException pce) {
      Exception e = new Exception("Exception de configuration de l'extracteur");
      throw e;
    }
    catch (TransformerConfigurationException tce)
    {
      Exception e = new Exception("Exception de configuration du transformateur");
      throw e;
    }
    catch (TransformerException te)
    {
      Exception e = new Exception("Exception du transformateur");
      throw e;
    }

    return stringResult;
  }

  protected Element toDOMElement(Document iDocument)
  {
		Element teamsElt = iDocument.createElement(XML_TEAMS_TAG);
		Element teamElt = iDocument.createElement(XML_TEAM_TAG);
		  
		Element tmpElt = iDocument.createElement(XML_NAME_TAG);
		tmpElt.setTextContent(this.team.getName());
		teamElt.appendChild(tmpElt);
		  
		tmpElt = iDocument.createElement(XML_COACH_TAG);
		tmpElt.setTextContent(this.team.getCoach());
		teamElt.appendChild(tmpElt);
		  
		tmpElt = iDocument.createElement(XML_RACE_ID_TAG);
		OBBLMRaceConverter raceConverter = new OBBLMRaceConverter();
		raceConverter.fromRace(this.team.getRace());
		Integer raceIndex = new Integer(raceConverter.getIndex());
		tmpElt.setTextContent(raceIndex.toString());
		teamElt.appendChild(tmpElt);
		
		tmpElt = iDocument.createElement(XML_LEAGUE_ID_TAG);
		tmpElt.setTextContent("1");
		teamElt.appendChild(tmpElt);
	  
		tmpElt = iDocument.createElement(XML_DIVISION_ID_TAG);
		tmpElt.setTextContent("1");
		teamElt.appendChild(tmpElt);
	  
		tmpElt = iDocument.createElement(XML_TREASURY_TAG);
		Integer treasury = this.team.getTreasury();
		treasury *=10;
		tmpElt.setTextContent(treasury.toString());
		teamElt.appendChild(tmpElt);
		
		tmpElt = iDocument.createElement(XML_APOTHICARY_TAG);
		tmpElt.setTextContent(this.team.getApothicary().toString());
		teamElt.appendChild(tmpElt);
		
		tmpElt = iDocument.createElement(XML_REROLL_TAG);
		tmpElt.setTextContent(this.team.getRerolls().toString());
		teamElt.appendChild(tmpElt);
		
		tmpElt = iDocument.createElement(XML_FF_TAG);
		tmpElt.setTextContent(this.team.getFf().toString());
		teamElt.appendChild(tmpElt);
		
		tmpElt = iDocument.createElement(XML_ASSISTANTS_TAG);
		tmpElt.setTextContent(this.team.getAssistants().toString());
		teamElt.appendChild(tmpElt);
		
		tmpElt = iDocument.createElement(XML_CHEERLEADERS_TAG);
		tmpElt.setTextContent(this.team.getCheerleaders().toString());
		teamElt.appendChild(tmpElt);
		
		// @TODO Use an option before export
		tmpElt = iDocument.createElement(XML_WON_TAG);
		tmpElt.setTextContent("0");
		teamElt.appendChild(tmpElt);
		
		// @TODO Use an option before export
		tmpElt = iDocument.createElement(XML_LOST_TAG);
		tmpElt.setTextContent("0");
		teamElt.appendChild(tmpElt);
		
		// @TODO Use an option before export
		tmpElt = iDocument.createElement(XML_DRAW_TAG);
		tmpElt.setTextContent("0");
		teamElt.appendChild(tmpElt);
		
		// @TODO Use an option before export
		tmpElt = iDocument.createElement(XML_WON_TOURNAMENT_TAG);
		tmpElt.setTextContent("0");
		teamElt.appendChild(tmpElt);
		
		// @TODO Use an option before export
		tmpElt = iDocument.createElement(XML_GOAL_FOR_TAG);
		tmpElt.setTextContent("0");
		teamElt.appendChild(tmpElt);
		
		// @TODO Use an option before export
		tmpElt = iDocument.createElement(XML_GOAL_AGAINST_TAG);
		tmpElt.setTextContent("0");
		teamElt.appendChild(tmpElt);
		
		tmpElt = iDocument.createElement(XML_PLAYERS_TAG);
		OBBLMPlayerConverter playerConverter = new OBBLMPlayerConverter();
		Element playerElt;
		for(Player player : this.team.getPlayers()){
			playerConverter.setPlayer(player);
			try {
				playerElt = playerConverter.toDOMElement(iDocument);
				tmpElt.appendChild(playerElt);
			} catch (Exception e) {
				
			}
		}
		teamElt.appendChild(tmpElt);
		teamsElt.appendChild(teamElt);
		return teamsElt;
  }

@Override
	public boolean isRaceWriteable(Race race) {
		OBBLMRaceConverter raceConverter = new OBBLMRaceConverter();
		return raceConverter.isRaceWriteable(race);
	}
}