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
import org.tisseursdechimeres.bb.Position;

public class OBBLMPositionConverter
{
  protected int index;
  protected static Hashtable<Integer, String> nameByIndex;
  protected static Hashtable<String, Integer> indexByName;

  public OBBLMPositionConverter()
  {
    if (nameByIndex == null) {
      nameByIndex = new Hashtable<Integer, String>();
      indexByName = new Hashtable<String, Integer>();
      put(new Integer(1).intValue(), "linewoman@amazon");
      put(new Integer(2).intValue(), "thrower@amazon");
      put(new Integer(3).intValue(), "catcher@amazon");
      put(new Integer(4).intValue(), "blitzer@amazon");
      put(new Integer(10).intValue(), "beastman@chaos");
      put(new Integer(11).intValue(), "chaos_warrior@chaos");
      put(new Integer(12).intValue(), "minotaur@chaos");
      put(new Integer(20).intValue(), "hobgoblin@chaos_dwarf");
      put(new Integer(21).intValue(), "chaos_dwarf_blocker@chaos_dwarf");
      put(new Integer(22).intValue(), "bull_centaur@chaos_dwarf");
      put(new Integer(23).intValue(), "minotaur@chaos_dwarf");
      put(new Integer(210).intValue(), "marauder@chaos_pact");
      put(new Integer(211).intValue(), "goblin_renegade@chaos_pact");
      put(new Integer(212).intValue(), "skaven_renegade@chaos_pact");
      put(new Integer(213).intValue(), "dark_elf_renegade@chaos_pact");
      put(new Integer(214).intValue(), "troll@chaos_pact");
      put(new Integer(215).intValue(), "ogre@chaos_pact");
      put(new Integer(216).intValue(), "minotaur@chaos_pact");
      put(new Integer(30).intValue(), "lineman@dark_elf");
      put(new Integer(31).intValue(), "runner@dark_elf");
      put(new Integer(32).intValue(), "assassin@dark_elf");
      put(new Integer(33).intValue(), "blitzer@dark_elf");
      put(new Integer(34).intValue(), "witch@dark_elf");
      put(new Integer(40).intValue(), "blocker@dwarf");
      put(new Integer(41).intValue(), "runner@dwarf");
      put(new Integer(42).intValue(), "blitzer@dwarf");
      put(new Integer(43).intValue(), "troll_slayer@dwarf");
      put(new Integer(44).intValue(), "deathroller@dwarf");
      put(new Integer(50).intValue(), "lineman@elf");
      put(new Integer(51).intValue(), "thrower@elf");
      put(new Integer(52).intValue(), "catcher@elf");
      put(new Integer(53).intValue(), "blitzer@elf");
      put(new Integer(60).intValue(), "goblin@goblin");
      put(new Integer(61).intValue(), "bombardier@goblin");
      put(new Integer(62).intValue(), "pogoer@goblin");
      put(new Integer(63).intValue(), "loney@goblin");
      put(new Integer(64).intValue(), "fanatic@goblin");
      put(new Integer(65).intValue(), "troll@goblin");
      put(new Integer(70).intValue(), "halfling@halfling");
      put(new Integer(71).intValue(), "treeman@halfling");
      put(new Integer(80).intValue(), "lineman@high_elf");
      put(new Integer(81).intValue(), "thrower@high_elf");
      put(new Integer(82).intValue(), "catcher@high_elf");
      put(new Integer(83).intValue(), "blitzer@high_elf");
      put(new Integer(90).intValue(), "lineman@human");
      put(new Integer(91).intValue(), "catcher@human");
      put(new Integer(92).intValue(), "thrower@human");
      put(new Integer(93).intValue(), "blitzer@human");
      put(new Integer(94).intValue(), "ogre@human");
      put(new Integer(100).intValue(), "skeleton@khemri");
      put(new Integer(101).intValue(), "thro_ra@khemri");
      put(new Integer(102).intValue(), "blitz_ra@khemri");
      put(new Integer(103).intValue(), "tomb_guardian@khemri");
      put(new Integer(110).intValue(), "skink@lizardman");
      put(new Integer(111).intValue(), "saurus@lizardman");
      put(new Integer(112).intValue(), "kroxigor@lizardman");
      put(new Integer(130).intValue(), "zombie@necromantic");
      put(new Integer(131).intValue(), "ghoul@necromantic");
      put(new Integer(132).intValue(), "wight@necromantic");
      put(new Integer(133).intValue(), "flesh_golem@necromantic");
      put(new Integer(134).intValue(), "werewolf@necromantic");
      put(new Integer(140).intValue(), "lineman@norse");
      put(new Integer(141).intValue(), "thrower@norse");
      put(new Integer(144).intValue(), "ulfwerener@norse");
      put(new Integer(145).intValue(), "snow_troll@norse");
      put(new Integer(142).intValue(), "catcher@norse");
      put(new Integer(143).intValue(), "blitzer@norse");
      put(new Integer(150).intValue(), "rotter@nurgle");
      put(new Integer(151).intValue(), "pestigor@nurgle");
      put(new Integer(152).intValue(), "nurgle_warrior@nurgle");
      put(new Integer(153).intValue(), "beast_of_nurgle@nurgle");
      put(new Integer(160).intValue(), "snotling@ogre");
      put(new Integer(161).intValue(), "ogre@ogre");
      put(new Integer(120).intValue(), "lineman@orc");
      put(new Integer(121).intValue(), "goblin@orc");
      put(new Integer(122).intValue(), "thrower@orc");
      put(new Integer(123).intValue(), "black_orc@orc");
      put(new Integer(124).intValue(), "blitzer@orc");
      put(new Integer(125).intValue(), "troll@orc");
      put(new Integer(190).intValue(), "lineman@skaven");
      put(new Integer(191).intValue(), "thrower@skaven");
      put(new Integer(192).intValue(), "gutter_runner@skaven");
      put(new Integer(193).intValue(), "storm_vermin@skaven");
      put(new Integer(194).intValue(), "rat_ogre@skaven");
      put(new Integer(220).intValue(), "lineman@slann");
      put(new Integer(221).intValue(), "catcher@slann");
      put(new Integer(222).intValue(), "blitzer@slann");
      put(new Integer(223).intValue(), "kroxigor@slann");
      put(new Integer(170).intValue(), "skeleton@undead");
      put(new Integer(171).intValue(), "zombie@undead");
      put(new Integer(172).intValue(), "ghoul@undead");
      put(new Integer(173).intValue(), "wight@undead");
      put(new Integer(174).intValue(), "mummy@undead");
      put(new Integer(230).intValue(), "underworld_goblin@underworld");
      put(new Integer(231).intValue(), "lineman@underworld");
      put(new Integer(232).intValue(), "thrower@underworld");
      put(new Integer(233).intValue(), "storm_vermin@underworld");
      put(new Integer(234).intValue(), "warpstone_troll@underworld");
      put(new Integer(180).intValue(), "thrall@vampire");
      put(new Integer(181).intValue(), "vampire@vampire");
      put(new Integer(200).intValue(), "lineman@wood_elf");
      put(new Integer(201).intValue(), "catcher@wood_elf");
      put(new Integer(202).intValue(), "thrower@wood_elf");
      put(new Integer(203).intValue(), "wardancer@wood_elf");
      put(new Integer(204).intValue(), "treeman@wood_elf");
    }
  }

  protected void put(int index, String name)
  {
    Integer tmpInteger = new Integer(index);
    nameByIndex.put(tmpInteger, name);
    indexByName.put(name, tmpInteger);
  }

  public boolean fromPosition(Position pos)
  {
    boolean bReturn = false;
    String posName = pos.getName();
    String raceName = pos.getRaceName();
    String toFind = posName + "@" + raceName ;
    Integer tmpInteger = indexByName.get(toFind);
    if (tmpInteger != null) {
      bReturn = true;
      this.index = tmpInteger.intValue();
    }
    return bReturn;
  }

  public boolean toPosition(Position pos)
  {
    boolean bReturn = false;
    String key = (String)nameByIndex.get(Integer.valueOf(this.index));
    if (key != null) {
      Configuration conf = Configuration.getInstance();
      pos = conf.getPositionByKey(key);
      if (pos != null) {
        bReturn = true;
      }
    }
    return bReturn;
  }

  public int getIndex()
  {
    return this.index;
  }

  public void setIndex(int index) {
    this.index = index;
  }
}