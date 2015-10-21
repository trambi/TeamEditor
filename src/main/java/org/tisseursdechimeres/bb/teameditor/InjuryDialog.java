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
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.tisseursdechimeres.bb.Injury;
import org.tisseursdechimeres.bb.Player;
import org.tisseursdechimeres.bb.Configuration;



public class InjuryDialog extends JDialog implements ActionListener {
	
	/**
	 * 
	 */
	List<InjuryWidget> injuryWidgets;
	JButton validButton;
	Player player;
	List<ChangeListener> changeListeners;
	private static final long serialVersionUID = 2456325950529918086L;
	
	public InjuryDialog(){
		super();
		this.setModal(true);
		init();
	}
	
	protected void init(){
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		injuryWidgets = new ArrayList <InjuryWidget>();
		validButton = new JButton("Ok");
		changeListeners = new ArrayList<ChangeListener>();
	}
	
	public void addChangeListener(ChangeListener listener){
		changeListeners.add(listener);
	}
	
	protected void prepareGui(){
		Configuration conf = Configuration.getInstance(); 
		Collection<Injury> injuries = conf.getInjuries();
		int i = 0;
		Container container = this.getContentPane();
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.LINE_START; // ou BASELINE_LEADING mais pas WEST.
		gbc.insets = new Insets(5,10,5,10);
		//gbc.fill = GridBagConstraints.BOTH;
		
		for(Injury inj : injuries){
			InjuryWidget widget = new InjuryWidget(this,i,inj,player.getInjuryNumber(inj));
			widget.addWidgetsToContainer(container,gbc);
			injuryWidgets.add(widget);
			i++;
		}
		validButton.addActionListener(this);
		gbc.gridy = i;
		gbc.gridx = 0;
		add(validButton,gbc);
		
		Dimension selfDim = getPreferredSize();
		selfDim.height += 30;
		selfDim.width += 0;
		setSize(selfDim);
		setMinimumSize(selfDim);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == validButton){
			player.clearInjuries();
			for(InjuryWidget widget : injuryWidgets){
				int i = 0;
				int nb = widget.getNumberOfInjury();
				for(i=0;i<nb;i++){
					player.addInjury(widget.getInjury());					
				}
			}
			player.computeStats();
			emitChangePerformed(new ChangeEvent(this));
			dispose();
		}
	}
	
	protected void emitChangePerformed(ChangeEvent evt){
		for(ChangeListener listener : changeListeners){
			listener.stateChanged(evt);
		}
	}

	public void setPlayer(Player player) {
		this.player = player;
		ResourceBundle i18nResource = ResourceBundle.getBundle("org.tisseursdechimeres.bb.teameditor.TeamEditor",Locale.getDefault());
		Object[] arguments = {player.getName()};
		String title = MessageFormat.format(i18nResource.getString("injury_dialog_title"),arguments);
		setTitle(title);
	}
}
