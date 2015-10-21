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
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import org.tisseursdechimeres.bb.Race;

public class RaceComboBoxRenderer extends JLabel implements
		ListCellRenderer<Race> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8871649162390009393L;



	@Override
	public Component getListCellRendererComponent(JList<? extends Race> list, Race race,
			int index, boolean isSelected, boolean cellHasFocus) {
		
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        if (null != race) {
            setText(race.getI18nName());
        } else {
        	setText("--------------");
        }
        setFont(list.getFont());

        return this;

	}

}
