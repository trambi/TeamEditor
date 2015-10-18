/*
Copyright (C) 2010  Bertrand MADET

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

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableCellEditor;

import org.tisseursdechimeres.bb.Injuries;
import org.tisseursdechimeres.bb.Player;
import org.tisseursdechimeres.bb.Team;

public class InjuryCellEditor extends AbstractCellEditor implements
		TableCellEditor, ActionListener,ChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8113734024253149296L;
	protected Team team;
	/*protected Player player;*/
	protected Player player;
	protected JButton button;
	protected ChangeListener changeListener;

	public InjuryCellEditor() {
		
	}
	
	@Override
	public Object getCellEditorValue() {
		String summary = new String("");
		if( null != player ){
			summary = player.getInjurySummary(); 
		}
		return summary;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(button == e.getSource()){
			InjuryDialog dlg = new InjuryDialog();
			dlg.setPlayer(player);
			dlg.addChangeListener((ChangeListener)this);
			dlg.prepareGui();
			dlg.setVisible(true);
			fireEditingStopped();


		}
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		boolean enabled = false;
		player = team.getPlayerByInjuries((Injuries)value);
		button = new JButton("+");
		button.addActionListener(this);
		if(null != player){
			enabled = true;
		}
		button.setEnabled(enabled);
		
		return (Component)button;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public void setChangeListener(ChangeListener changeListener) {
		this.changeListener = changeListener;
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		changeListener.stateChanged(e);
	}

}
