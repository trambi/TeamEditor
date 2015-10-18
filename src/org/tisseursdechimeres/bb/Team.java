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
import java.util.Collections;
import java.util.List;

public class Team {
	protected String name;
	protected Race race;
	protected Integer totalCost;
	protected Integer value;
	protected List<Player> players;
	protected Integer ff;
	protected Integer cheerleaders;
	protected Integer assistants;
	protected Integer apothicary;
	protected Integer treasury;
	protected Integer rerolls;
	protected Integer availablePlayers;
	protected String coach;
	protected String i18nUnit;
	
	static public int MAX_PLAYER_NUMBER = 16;
	static public int MIN_PLAYER_NUMBER = 11;
	static public int VALUE_PER_FF = 1;
	static public int VALUE_PER_CHEERLEADER = 1;
	static public int VALUE_PER_ASSISTANT = 1;
	static public int VALUE_PER_APOTHICARY = 5;
	
	static public int BASIC_UNIT=10000;
	static public int NON_VALUABLE_TREASURY=10;
	static public int VALUE_PER_TREASURY = 1;
	
	public static final String BB_TAG= "bbteam";
	
	protected void init(){
		name = new String();
		totalCost = new Integer(0);
		value = new Integer(0);
		players = new ArrayList<Player> (MAX_PLAYER_NUMBER);
		ff = new Integer(0);
		cheerleaders = new Integer(0);
		assistants = new Integer(0);
		apothicary = new Integer(0);
		treasury = new Integer(0);
		rerolls = new Integer(0);
		coach = new String();
		i18nUnit = new String("po");
		availablePlayers = new Integer(0);
	}
	
	public Team(){
		init();
	}

	public Integer getApothicary() {
		return apothicary;
	}

	public void setApothicary(Integer apothicary) {
		if(race.getUseApothicary()){
			this.apothicary = apothicary;
		}else{
			this.apothicary = 0;
		}
	}

	public Integer getAssistants() {
		return assistants;
	}

	public void setAssistants(Integer assistants) {
		this.assistants = assistants;
	}

	public Integer getCheerleaders() {
		return cheerleaders;
	}

	public void setCheerleaders(Integer cheerleaders) {
		this.cheerleaders = cheerleaders;
	}

	public String getCoach() {
		return coach;
	}

	public void setCoach(String coach) {
		this.coach = coach;
	}

	public Integer getFf() {
		return ff;
	}

	public void setFf(Integer ff) {
		this.ff = ff;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
		if( false == race.getUseApothicary() ){
			this.apothicary = 0;
		}
		players.clear();
	}

	public Integer getTreasury() {
		return treasury;
	}

	public void setTreasury(Integer treasury) {
		this.treasury = treasury;
	}

	public Integer getValue() {
		return value;
	}

	public Integer getTotalCost() {
		return totalCost;
	}
	
	public void setValue(Integer value) {
		this.totalCost = value;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public Integer getRerolls() {
		return rerolls;
	}

	public void setRerolls(Integer rerolls) {
		this.rerolls = rerolls;
	}
	
	public void computeValue(){
		totalCost = ff * VALUE_PER_FF;
		totalCost += cheerleaders * VALUE_PER_CHEERLEADER;
		totalCost += assistants * VALUE_PER_ASSISTANT;
		totalCost += apothicary * VALUE_PER_APOTHICARY;
		if(null != race)
			totalCost += rerolls * race.getRerollCost();
		
		value = totalCost;
		availablePlayers = 0;
		for(Player player : players){
			if(null != player){
				if(false == player.missNextGame()){
					value += player.getValue();
					if(null != player.position)
						availablePlayers ++;
				}
				totalCost += player.getValue();
			}
		}
	}

	public void addPlayer(Player player) {
		players.add(player);
	}

	public String getI18nUnit() {
		return i18nUnit;
	}
	
	public Integer getAvailablePlayers(){
		return availablePlayers;
	}
		
	public void sortPlayers(){
		Collections.sort(players,new PlayerComparatorByNumber());
	}
	
	public Player getPlayerByIndex(int number) {
		if(number >= players.size()){
			return null;
		}else{
			return players.get(number);
		}
	}
	
	public boolean removePlayer(Player player) {
		return players.remove(player);
	}

	public Player getPlayerByInjuries(Injuries injuries) {
		Player foundPlayer = null;
		for(Player player : players){
			if( injuries == player.getInjuries() ){
				foundPlayer = player;
				break;
			}
		}
		return foundPlayer;
	}
}
