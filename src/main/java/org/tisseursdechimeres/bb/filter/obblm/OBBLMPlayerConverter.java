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

import org.tisseursdechimeres.bb.Injury;
import org.tisseursdechimeres.bb.Player;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class OBBLMPlayerConverter
{
  public static final String XML_PLAYER_TAG = "player";
  public static final String XML_NAME_TAG = "name";
  public static final String XML_NUMBER_TAG = "nr";
  public static final String XML_POSITION_TAG = "pos_id";
  public static final String XML_STATUS_TAG = "status";
  public static final String XML_MISS_NEXT_GAME_STATUS = "mng";
  public static final String XML_READY_STATUS = "none";
  public static final String XML_COMP_TAG = "cp";
  public static final String XML_TD_TAG = "td";
  public static final String XML_INTER_TAG = "intcpt";
  public static final String XML_CAS_TAG = "bh";
  public static final String XML_KILLED_TAG = "ki";
  public static final String XML_SERIOUSLY_INJURED_TAG = "si";
  public static final String XML_MVP_TAG = "mvp";
  public static final String XML_MOVEMENT_INJURY_TAG = "ma";
  public static final String XML_STRENGTH_INJURY_TAG = "st";
  public static final String XML_AGILITY_INJURY_TAG = "ag";
  public static final String XML_ARMOR_INJURY_TAG = "av";
  public static final String XML_NIGGLING_INJURY_TAG = "ni";
  private Player player;

  public void setPlayer(Player player)
  {
    this.player = player;
  }

  protected Element toDOMElement(Document iDocument) throws Exception {
    Element playerElt = iDocument.createElement(XML_PLAYER_TAG);

    Element tmpElt = iDocument.createElement(XML_NAME_TAG);
    tmpElt.setTextContent(this.player.getName());
    playerElt.appendChild(tmpElt);

    tmpElt = iDocument.createElement(XML_NUMBER_TAG);
    tmpElt.setTextContent(this.player.getNumber().toString());
    playerElt.appendChild(tmpElt);

    OBBLMPositionConverter posConverter = new OBBLMPositionConverter();
    boolean result = posConverter.fromPosition(this.player.getPosition());
    if(false == result){
    	throw new Exception();
    }
    Integer positionIndex = new Integer(posConverter.getIndex());
    tmpElt = iDocument.createElement(XML_POSITION_TAG);
    tmpElt.setTextContent(positionIndex.toString());
    playerElt.appendChild(tmpElt);

    tmpElt = iDocument.createElement(XML_STATUS_TAG);
    tmpElt.setTextContent(this.player.missNextGame() ? XML_MISS_NEXT_GAME_STATUS : XML_READY_STATUS);
    playerElt.appendChild(tmpElt);

    tmpElt = iDocument.createElement(XML_COMP_TAG);
    tmpElt.setTextContent(this.player.getComp().toString());
    playerElt.appendChild(tmpElt);

    tmpElt = iDocument.createElement(XML_TD_TAG);
    tmpElt.setTextContent(this.player.getTd().toString());
    playerElt.appendChild(tmpElt);

    tmpElt = iDocument.createElement(XML_INTER_TAG);
    tmpElt.setTextContent(this.player.getInter().toString());
    playerElt.appendChild(tmpElt);

    tmpElt = iDocument.createElement(XML_CAS_TAG);
    tmpElt.setTextContent(this.player.getCas().toString());
    playerElt.appendChild(tmpElt);

    tmpElt = iDocument.createElement(XML_KILLED_TAG);
    tmpElt.setTextContent(new Integer(0).toString());
    playerElt.appendChild(tmpElt);

    tmpElt = iDocument.createElement(XML_SERIOUSLY_INJURED_TAG);
    tmpElt.setTextContent(new Integer(0).toString());
    playerElt.appendChild(tmpElt);

    tmpElt = iDocument.createElement(XML_MVP_TAG);
    tmpElt.setTextContent(this.player.getMvp().toString());
    playerElt.appendChild(tmpElt);

    Integer movInj = Integer.valueOf(0); Integer stInj = Integer.valueOf(0); Integer agInj = Integer.valueOf(0); Integer arInj = Integer.valueOf(0); Integer niInj = Integer.valueOf(0);
    for (Injury inj : this.player.getInjuries()) {
      movInj = Integer.valueOf(movInj.intValue() + inj.getMovMalus());
      stInj = Integer.valueOf(stInj.intValue() + inj.getStrMalus());
      agInj = Integer.valueOf(agInj.intValue() + inj.getAgMalus());
      arInj = Integer.valueOf(arInj.intValue() + inj.getArMalus());
      if (inj.getMaxNumber() > 2) {
        niInj = Integer.valueOf(niInj.intValue() + 1);
      }
    }
    tmpElt = iDocument.createElement(XML_MOVEMENT_INJURY_TAG);
    tmpElt.setTextContent(movInj.toString());
    playerElt.appendChild(tmpElt);

    tmpElt = iDocument.createElement(XML_STRENGTH_INJURY_TAG);
    tmpElt.setTextContent(stInj.toString());
    playerElt.appendChild(tmpElt);

    tmpElt = iDocument.createElement(XML_AGILITY_INJURY_TAG);
    tmpElt.setTextContent(agInj.toString());
    playerElt.appendChild(tmpElt);

    tmpElt = iDocument.createElement(XML_ARMOR_INJURY_TAG);
    tmpElt.setTextContent(arInj.toString());
    playerElt.appendChild(tmpElt);

    tmpElt = iDocument.createElement(XML_NIGGLING_INJURY_TAG);
    tmpElt.setTextContent(niInj.toString());
    playerElt.appendChild(tmpElt);
    return playerElt;
  }
}