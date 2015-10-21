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

import java.awt.Container;
import java.awt.GridBagConstraints;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import org.tisseursdechimeres.bb.Injury;

public class InjuryWidget {
	Injury injury;
	JCheckBox monoInjury;
	JLabel multiInjuryLabel;
	JSpinner multiInjurySpin;
	int line;

	public InjuryWidget(JDialog parent,int line,Injury injury,Integer injNb){
		this.line = line;
		this.injury = injury;
		if(1 == injury.getMaxNumber()){
			monoInjury = new JCheckBox(injury.getI18nName());
			monoInjury.setSelected(!injNb.equals(new Integer(0)));
		}else{
			SpinnerModel spinModel = new SpinnerNumberModel(injNb.intValue(),0,injury.getMaxNumber(),1);
			multiInjuryLabel = new JLabel(injury.getI18nName());
			multiInjurySpin = new JSpinner(spinModel);
		}
	}
	
	public void addWidgetsToContainer(Container container,GridBagConstraints gbc){
		gbc.gridx = 0;
		gbc.gridy = line;
		if(1 == injury.getMaxNumber()){
			gbc.gridwidth = 2;
			container.add(monoInjury,gbc);
		}else{
			//SpinnerModel spinModel = new SpinnerNumberModel(0,0,injury.getMaxNumber(),1);
			//multiInjuryLabel = new JLabel(injury.getI18nName());
			//multiInjurySpin = new JSpinner(spinModel);
			container.add(multiInjuryLabel,gbc);
			gbc.gridx++;
			container.add(multiInjurySpin,gbc);
			gbc.gridx++;
		}
	}
	
	public int getNumberOfInjury(){
		int injNb = 0;
		if(1 == injury.getMaxNumber()){
			if(true == monoInjury.isSelected())
				injNb = 1;
		}else{
			Object value = multiInjurySpin.getValue();
			if(value.getClass() == Integer.class){
				Integer nb = (Integer)value;
				injNb = nb.intValue();
			}
		}
		return injNb;
	}
	
	public void setNumberOfInjury(Integer injuryNb){
		if(1 == injury.getMaxNumber())
			monoInjury.setSelected( !injuryNb.equals(new Integer(0)) );
		else
			multiInjurySpin.setValue(injuryNb);
	}
	

	public Injury getInjury() {
		return injury;
	}
}
