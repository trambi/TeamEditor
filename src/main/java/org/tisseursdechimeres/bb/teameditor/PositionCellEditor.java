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

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;

import org.tisseursdechimeres.bb.Position;
import org.tisseursdechimeres.bb.Race;
import org.tisseursdechimeres.bb.Team;

public class PositionCellEditor extends DefaultCellEditor {

	private static final long serialVersionUID = 8958475447369451540L;
	public PositionCellEditor(Team team,ActionListener actionListener) {
        super(new JComboBox<Position>());
        JComboBox<Position> positionBox = (JComboBox<Position>)this.editorComponent;
        positionBox.setEditable(false);
		positionBox.setRenderer(new PositionComboBoxRenderer());
		positionBox.removeAllItems();
		positionBox.addItem(null);
		positionBox.addActionListener(actionListener);
		Race race = team.getRace();
		List<Position> positions = race.getPositions();
		for(Position position : positions){
			positionBox.addItem(position);
		}
    }
}
