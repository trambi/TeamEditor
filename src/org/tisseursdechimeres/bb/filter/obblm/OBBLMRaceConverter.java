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

import java.util.Hashtable;
import org.tisseursdechimeres.bb.Configuration;
import org.tisseursdechimeres.bb.Race;

public class OBBLMRaceConverter
{
  protected int index;
  protected static Hashtable<Integer, String> nameByIndex;
  protected static Hashtable<String, Integer> indexByName;

  public boolean isRaceWriteable(Race race)
  {
	  boolean bReturn = false;
	  String toFind = race.getName();
	  Integer tmpInteger = Integer.valueOf(((Integer)indexByName.get(toFind)).intValue());
	  if (tmpInteger != null) {
	    bReturn = true;
	  }
	  return bReturn;
  }
  
  public OBBLMRaceConverter()
  {
    if (nameByIndex == null) {
      nameByIndex = new Hashtable<Integer, String>();
      indexByName = new Hashtable<String, Integer>();
      put(new Integer(0).intValue(), "amazon");
      put(new Integer(1).intValue(), "chaos");
      put(new Integer(2).intValue(), "chaos_dwarf");
      put(new Integer(3).intValue(), "dark_elf");
      put(new Integer(4).intValue(), "dwarf");
      put(new Integer(5).intValue(), "elf");
      put(new Integer(6).intValue(), "goblin");
      put(new Integer(7).intValue(), "halfling");
      put(new Integer(8).intValue(), "high_elf");
      put(new Integer(9).intValue(), "human");
      put(new Integer(10).intValue(), "khemri");
      put(new Integer(11).intValue(), "lizardman");
      put(new Integer(12).intValue(), "orc");
      put(new Integer(13).intValue(), "necromantic");
      put(new Integer(14).intValue(), "norse");
      put(new Integer(15).intValue(), "nurgle");
      put(new Integer(16).intValue(), "ogre");
      put(new Integer(17).intValue(), "undead");
      put(new Integer(18).intValue(), "vampire");
      put(new Integer(19).intValue(), "skaven");
      put(new Integer(20).intValue(), "wood_elf");
      put(new Integer(21).intValue(), "chaos_pact");
      put(new Integer(22).intValue(), "slann");
      put(new Integer(23).intValue(), "underworld");
    }
  }

  protected void put(int index, String name)
  {
    Integer tmpInteger = new Integer(index);
    nameByIndex.put(tmpInteger, name);
    indexByName.put(name, tmpInteger);
  }

  public boolean fromRace(Race race) {
    boolean bReturn = false;
    String toFind = race.getName();
    Integer tmpInteger = Integer.valueOf(((Integer)indexByName.get(toFind)).intValue());
    if (tmpInteger != null) {
      bReturn = true;
      this.index = tmpInteger.intValue();
    }
    return bReturn;
  }

  public Race toRace() {
	  Race raceReturn = null;
    String key = (String)nameByIndex.get(Integer.valueOf(this.index));
    if (key != null) {
      Configuration conf = Configuration.getInstance();
      raceReturn = conf.getRaceByName(key);
    }
    return raceReturn;
  }

  public int getIndex()
  {
    return this.index;
  }

  public void setIndex(int index) {
    this.index = index;
  }
}