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
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import org.tisseursdechimeres.bb.Player;
import org.tisseursdechimeres.bb.Position;
import org.tisseursdechimeres.bb.Skill;

public class SkillComboBoxRenderer extends JLabel implements ListCellRenderer<Skill> {

	private Position position; 
	private static final long serialVersionUID = -6980567602256706083L;

	public SkillComboBoxRenderer(Player player) {
		position = player.getPosition();
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Skill> list, Skill skill,
			int index, boolean isSelected, boolean cellHasFocus) {
		
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        //Set the icon and text.  If icon was null, say so.
        if (null != skill) {
            setText(skill.getI18nName());
            if(Position.SIMPLE_SKILL_VALUE < position.getValueOfSkill(skill)){
            	if(true == skill.isBonus()){
            		setForeground(Color.red);
            	}else{
               		setForeground(Color.red.darker());
            	}
            }
        } else {
        	setText("--------------");
        }
        setFont(list.getFont());

        return this;

	}

}
