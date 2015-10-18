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

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TeamCellRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = -3377149577526735087L;

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if(1 == (row % 2) )
        	setBackground(new Color(0xE0,0xE0,0xE0));
        else
        	setBackground(Color.white);
        return this;
    }

}
