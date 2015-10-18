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
import javax.swing.AbstractCellEditor;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableCellEditor;

public class IntegerCellEditor extends AbstractCellEditor 
implements TableCellEditor{

	JSpinner spinner;
	JTable table;
	int row;

	private static final long serialVersionUID = -1575662047516755772L;
	
	public IntegerCellEditor(){
		spinner = new JSpinner(new SpinnerNumberModel(0,0,999,1));
	}
	
	@Override
	public Object getCellEditorValue() {
		return spinner.getValue();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		Component objReturn = null;
		if( (null != value) && (value.getClass() == Integer.class) ){
			spinner.setValue(value);
			objReturn = (Component) spinner;
			this.table = table;
			this.row = row;
		}
		return objReturn;
	}

	public void setChangeListener(ChangeListener listener) {
		spinner.addChangeListener(listener);
		
	}
}
