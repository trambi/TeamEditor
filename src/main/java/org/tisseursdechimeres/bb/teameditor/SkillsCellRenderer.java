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
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.tisseursdechimeres.bb.Skills;

public class SkillsCellRenderer extends TeamCellRenderer {
	private static final long serialVersionUID = -5162390539163442231L;
	protected boolean in = false;
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if( (null != value) && (Skills.class == value.getClass()) ){
        	Skills skills = (Skills)value;
        	int cutToDo = 0;
        	String skillNameList = skills.getI18nNameList(); 
        	setText(skillNameList);
        	cutToDo = getCutToDo(table,skills,column);
        	if(0 != cutToDo){
        		setText(transformText(cutToDo,skillNameList));
        		System.out.println("transformation de "+skillNameList);
            }
        }
        return this;
    }
	
	private int getCutToDo(JTable table, Skills skills, int column) {
		int cutToDo = 0;
		TableColumnModel columnModel = table.getColumnModel();
    	TableColumn tableColumn = columnModel.getColumn(column);
    	int columnWidth = tableColumn.getWidth();
        Dimension preferredDim = new Dimension();
        preferredDim = getPreferredSize();
        if(columnWidth < preferredDim.width){
        	double ratio = (double)preferredDim.width / (double)columnWidth;
        	cutToDo = Double.valueOf(Math.floor(ratio)).intValue();
        	if(cutToDo >= skills.size()){
        		cutToDo = 0;
        	}
        }
        return cutToDo;
	}

	protected String transformText(int cutToDo, String textToTransform){
		String transformedText =  new String("<html>");
		int lengthBetweenCut = textToTransform.length() / (cutToDo +1);
		int lastIndex = 0,index = 0,leftIndex = 0,rightIndex = 0;
		
		for(int i = 0 ; i < cutToDo ; i++){
			index = lastIndex + lengthBetweenCut; 
			rightIndex = textToTransform.indexOf(",",index);
			leftIndex = textToTransform.substring(lastIndex, index).lastIndexOf(",");
			if( (-1 == leftIndex) && (-1 ==rightIndex) ){
				;
			}else if((-1 == leftIndex)){
				index = rightIndex;
			}else if((-1 == rightIndex)){
				index = leftIndex;
			}else{
				if( (index - leftIndex) > (rightIndex - index)){
					index = rightIndex;
				}else{
					index = leftIndex;
				}
			}
			index ++;
			transformedText += textToTransform.substring(lastIndex, index);
			transformedText += "<br />";
			lastIndex = index;
		}
		transformedText += textToTransform.substring(lastIndex);
		transformedText += "</html>";
		return transformedText;
	}
}
