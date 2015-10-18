/*
Copyright (C) 2010-2011  Bertrand MADET

This org.tisseursdechimeres.bb.teameditor is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This org.tisseursdechimeres.bb.teameditor is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this org.tisseursdechimeres.bb.teameditor.  If not, see <http://www.gnu.org/licenses/>.
*/
package org.tisseursdechimeres.bb.teameditor;


import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.event.ChangeListener;
import javax.swing.table.AbstractTableModel;

import org.tisseursdechimeres.bb.Injuries;
import org.tisseursdechimeres.bb.Player;
import org.tisseursdechimeres.bb.Position;
import org.tisseursdechimeres.bb.Skills;
import org.tisseursdechimeres.bb.Team;

public class TeamTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 5166524693246755871L;
	protected Vector <String> columns;
	protected LinkedList <ChangeListener> changeListeners;
	protected Team team;
	protected boolean checkSkillCount;
	
	public enum Column {
		NUMBER(0),
		NAME(1),
		POSITION(2),
		MV(3),
		STR(4),
		AG(5),
		AV(6),
		SKILLS(7),
		EXTRA_SKILLS(8),
		INJURY(9),
		COMPLETION(10),
		TOUCHDOWN(11),
		INTERCEPTION(12),
		CASUALTY(13),
		MVP(14),
		SPP(15),
		COST(16);
		
		private final int value;
		private Column(int value) {
			this.value = value;
		}
		public int getValue() {
			return this.value;
		}
	};
	
	public TeamTableModel(Team team, ResourceBundle i18nResource,boolean checkSkillCount){
		columns = new Vector<String>();
		columns.add(i18nResource.getString("number_column"));
		columns.add(i18nResource.getString("name_column"));
		columns.add(i18nResource.getString("position_column"));
		columns.add(i18nResource.getString("mv_column"));
		columns.add(i18nResource.getString("str_column"));
		columns.add(i18nResource.getString("ag_column"));
		columns.add(i18nResource.getString("ar_column"));
		columns.add(i18nResource.getString("skills_column"));
		columns.add(i18nResource.getString("extra_skills_column"));
		columns.add(i18nResource.getString("injury_column"));
		columns.add(i18nResource.getString("completion_column"));
		columns.add(i18nResource.getString("touchdown_column"));
		columns.add(i18nResource.getString("interception_column"));
		columns.add(i18nResource.getString("casualty_column"));
		columns.add(i18nResource.getString("mvp_column"));
		columns.add(i18nResource.getString("spp_column"));
		columns.add(i18nResource.getString("cost_column"));
		
		changeListeners = new LinkedList<ChangeListener>();
		this.checkSkillCount = checkSkillCount;
		this.team = team; 
	}

	public String getColumnName(int col) {
        return columns.get(col);
    }
	
	@Override
	public int getColumnCount() {
		return columns.size();
	}

	@Override
	public int getRowCount() {
		return Team.MAX_PLAYER_NUMBER;
	}
	
	public boolean isCellEditable(int row, int col) {
		boolean bReturn = false;
		if (Column.NUMBER.getValue() == col){
			bReturn = true;
		}else if (Column.NAME.getValue() == col){
			bReturn = true;
		}else if (Column.POSITION.getValue() == col){
			bReturn = true;
		}else if (Column.EXTRA_SKILLS.getValue() == col){
			bReturn = true;
		}else if (Column.INJURY.getValue() == col){
			bReturn = true;
		}else if (Column.COMPLETION.getValue() == col){
			bReturn = true;
		}else if (Column.TOUCHDOWN.getValue() == col){
			bReturn = true;
		}else if (Column.INTERCEPTION.getValue() == col){
			bReturn = true;
		}else if (Column.CASUALTY.getValue() == col){
			bReturn = true;
		}else if (Column.MVP.getValue() == col){
			bReturn = true;
		}		
		return bReturn;
    }

	@Override
	public Object getValueAt(int row, int col) {
		Object returnedObject = null;
		if (-1 == row){
			returnedObject = columns.get(col);
		}else{
			Player player = team.getPlayerByIndex(row);
			if(null != player){
				if(Column.NUMBER.getValue() == col){
					returnedObject = player.getNumber();
				}else if(Column.NAME.getValue() == col){
					returnedObject = player.getName();
				}else if(Column.POSITION.getValue() == col){
					returnedObject = player.getPosition();
				}else if(Column.MV.getValue() == col){
					returnedObject = player.getMov();
				}else if(Column.STR.getValue() == col){
					returnedObject = player.getStr();
				}else if(Column.AG.getValue() == col){
					returnedObject = player.getAg();
				}else if(Column.AV.getValue() == col){
					returnedObject = player.getAr();
				}else if(Column.SKILLS.getValue() == col){
					Position position = player.getPosition();
					returnedObject = position.getSkills();
				}else if(Column.EXTRA_SKILLS.getValue() == col){
					returnedObject = player.getExtraSkills();
				}else if(Column.INJURY.getValue() == col){
					returnedObject = player.getInjuries();
				}else if(Column.COMPLETION.getValue() == col){
					returnedObject = player.getComp();
				}else if(Column.TOUCHDOWN.getValue() == col){
					returnedObject = player.getTd();
				}else if(Column.INTERCEPTION.getValue() == col){
					returnedObject = player.getInter();
				}else if(Column.CASUALTY.getValue() == col){
					returnedObject = player.getCas();
				}else if(Column.MVP.getValue() == col){
					returnedObject = player.getMvp();
				}else if(Column.SPP.getValue() == col){
					returnedObject = player.getSpp();
				}else if(Column.COST.getValue() == col){
					returnedObject = player.getValue();
				}
			}else{
				// TODO Ajouter le cas sans joueur
			}
		}
		return returnedObject;
	}
	
	public Class<?> getColumnClass(int col){
		if( (Column.NUMBER.getValue() == col) || (Column.COMPLETION.getValue() <= col && Column.MVP.getValue() >= col) ){
			return Integer.class;
		}else if(Column.POSITION.getValue() == col){
			return Position.class;
		}else if ( (Column.SKILLS.getValue() == col) || (Column.EXTRA_SKILLS.getValue() == col) ){
			return Skills.class;
		}else if ((Column.INJURY.getValue() == col)){
			return Injuries.class;
		}else{
			return Object.class;
		}
	}
	
	public void setValueAt(Object value, int row, int col) {
		Player player = team.getPlayerByIndex(row);
		if(null != player){
			if(Column.NUMBER.getValue() == col){
				player.setNumber((Integer)value);
			}else if(Column.NAME.getValue() == col){
				player.setName((String)value);
			}else if(Column.POSITION.getValue() == col){
				if( (null != value) && (Position.class == value.getClass()) ){
					Position position = (Position)value;
					player.setPosition(position);
				}
				else{
					team.removePlayer(player);
				}
				fireTableRowsUpdated(row,row);
			}else if(Column.COMPLETION.getValue() == col){
				player.setComp((Integer)value);
				fireTableCellUpdated(row,Column.SPP.getValue());
			}else if(Column.TOUCHDOWN.getValue() == col){
				player.setTd((Integer)value);
				fireTableCellUpdated(row,Column.SPP.getValue());
			}else if(Column.INTERCEPTION.getValue() == col){
				player.setInter((Integer)value);
				fireTableCellUpdated(row,Column.SPP.getValue());
			}else if(Column.CASUALTY.getValue() == col){
				player.setCas((Integer)value);
				fireTableCellUpdated(row,Column.SPP.getValue());
			}else if(Column.MVP.getValue() == col){
				player.setMvp((Integer)value);
				fireTableCellUpdated(row,Column.SPP.getValue());
			}
		}else if(Column.POSITION.getValue() == col){
			if( (null != value) && (Position.class == value.getClass()) ){
				Position position = (Position)value;
				player = new Player();
				player.setPosition(position);
				player.setNumber(row+1);
				team.addPlayer(player);
				fireTableCellUpdated(row, col);
			}
			
		}
		
    }

	public void setCheckSkillCount(boolean checkSkillCount) {
		this.checkSkillCount = checkSkillCount;
	}
	
	public boolean getCheckSkillCount(){
		return checkSkillCount;
	}

}
