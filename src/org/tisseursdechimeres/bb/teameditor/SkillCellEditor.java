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

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import org.tisseursdechimeres.bb.Configuration;
import org.tisseursdechimeres.bb.Player;
import org.tisseursdechimeres.bb.Team;
import org.tisseursdechimeres.bb.Skill;

public class SkillCellEditor 
	extends AbstractCellEditor 
	implements TableCellEditor,ActionListener, ChangeListener {
	protected Player player;
	protected JButton button;
	protected Team team;
	protected boolean checkSkillCount;
	private JTable table;
	private int row;
	private ChangeListener changeListener;
	private Comparator<Skill> skillComparator;

	/**
	 * 
	 */
	private static final long serialVersionUID = -1220830493687324869L;

	public SkillCellEditor() {
		super();
	}

	@Override
	public Object getCellEditorValue() {
		String skills = new String("");
		if( null != player ){
			skills = player.getExtraSkills().getI18nNameList(); 
		}
		return skills;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		boolean enabled = false;
		button = new JButton("+");
		button.addActionListener(this);
		player = team.getPlayerByIndex(row);
		this.table = table;
		this.row = row;
		if(null != player){
			TableModel model = table.getModel();
			if(model.getClass() == TeamTableModel.class){
				TeamTableModel teamModel = (TeamTableModel)model;
				checkSkillCount = teamModel.getCheckSkillCount(); 
				if( true ==  checkSkillCount ){
					int potentialExtraSkills = Configuration.getPlayerPotentialExtraSkillsNumber(player);
					enabled = (0 != potentialExtraSkills);
				}else{
					enabled = true;
				}
			}
		}
		button.setEnabled(enabled);
		return (Component)button;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(button == e.getSource()){
			ExtraSkillDialog dlg = new ExtraSkillDialog();
			dlg.setPlayer(player);
			dlg.setSkillComparator(skillComparator);
			dlg.addChangeListener((ChangeListener)this);
			dlg.setCheckSkillCount(checkSkillCount);
			dlg.prepareGui();
			dlg.setVisible(true);
			fireEditingStopped();
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// Get the current default height for all rows 
		int height = table.getRowHeight(); 
		int margin = table.getRowMargin();
		// Determine highest cell in the row 
		for (int c=0; c<table.getColumnCount(); c++) { 
			TableCellRenderer renderer = table.getCellRenderer(row, c);
			Component comp = table.prepareRenderer(renderer, row, c);
			int h = comp.getPreferredSize().height + 2*margin; 
			height = Math.max(height, h);
		}
		table.setRowHeight(row, height);
		// On propage les changements
		changeListener.stateChanged(e);
		
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public void setChangeListener(ChangeListener changeListener) {
		this.changeListener = changeListener;
	}

	public void setSkillComparator(Comparator<Skill> skillComparator) {
		this.skillComparator = skillComparator;
	}
}
