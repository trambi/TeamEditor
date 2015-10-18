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

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.ListCellRenderer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.tisseursdechimeres.bb.Skill;
import org.tisseursdechimeres.bb.Player;
import org.tisseursdechimeres.bb.Configuration;

public class ExtraSkillDialog extends JDialog implements ActionListener {
	
	/**
	 * 
	 */
	List<JComboBox<Skill> > skills;
	JButton validButton;
	List<ChangeListener> changeListeners;
	boolean checkSkillCount;
	Player player;
	Comparator<Skill> skillComparator;
	private static final long serialVersionUID = 6837957602324196055L;
	
	public ExtraSkillDialog(){
		super();
		this.setModal(true);
		init();
	}
	
	protected void init(){
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		skills = new ArrayList <JComboBox<Skill>>(Configuration.playerGetMaxExtraSkill());
		validButton = new JButton("Ok");
		changeListeners = new ArrayList<ChangeListener>();
		checkSkillCount = true;
	}
	
	public void setCheckSkillCount(Boolean checkSkillCount){
		this.checkSkillCount = checkSkillCount;
	}
	
	public void addChangeListener(ChangeListener listener){
		changeListeners.add(listener);
	}
	
	protected void prepareGui(){
		int maxSkillNumber = Configuration.playerGetMaxExtraSkill();
		int maxSkillForPlayer = Configuration.getPlayerPotentialExtraSkillsNumber(player);
		int skillsForPlayer = player.getExtraSkillNumber();
		Vector<Skill> vector = new Vector<Skill>();
		vector.add(null);
		List<Skill> sortedSkills = player.getPosition().getAvailableSkills();
		Collections.sort(sortedSkills,skillComparator);
		vector.addAll(sortedSkills);
		ListCellRenderer<Skill> renderer = new SkillComboBoxRenderer(player);
		ResourceBundle i18nResource = ResourceBundle.getBundle("org.tisseursdechimeres.bb.teameditor.TeamEditor",Locale.getDefault());
		setLayout(new GridLayout(maxSkillNumber+1,2,10,5));
		for(int i =0 ; i< maxSkillNumber ; i++){
			JComboBox<Skill> comboBox = new JComboBox<Skill>(vector);
			comboBox.setRenderer(renderer);
			if(i < skillsForPlayer){
				comboBox.setSelectedItem(player.getExtraSkills().get(i));
			}
			comboBox.setEnabled( (i < maxSkillForPlayer) || (false == checkSkillCount) );
			comboBox.addActionListener(this);
			Object[] arguments = {new Integer(i+1)};
			String label = MessageFormat.format(i18nResource.getString("extra_skill_dialog_label"),arguments);
			add(new JLabel(label));
			skills.add(comboBox);	
			add(comboBox);
		}
		validButton.addActionListener(this);
		add(validButton);
		setSize(getPreferredSize());
		setMinimumSize(getPreferredSize());
		setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == validButton){
			player.clearExtraSkills();
			int maxSkillForPlayer = Configuration.getPlayerPotentialExtraSkillsNumber(player);
			if(false == checkSkillCount)
				maxSkillForPlayer = Configuration.playerGetMaxExtraSkill();
			for(int i =0 ; i< maxSkillForPlayer ; i++){
				JComboBox<Skill> comboBox = skills.get(i);
				Object selected = comboBox.getSelectedItem();
				if( (null!= selected) && (selected.getClass() == Skill.class) ){
					player.addSkill((Skill)selected);
				}
			}
			emitChangePerformed(new ChangeEvent(this));
			dispose();
		}
		
	}
	
	protected void emitChangePerformed(ChangeEvent evt){
		for(ChangeListener listener : changeListeners){
			listener.stateChanged(evt);
		}
	}

	public void setSkillComparator(Comparator<Skill> skillComparator) {
		this.skillComparator = skillComparator;
	}

	public void setPlayer(Player player) {
		this.player = player;
		refreshTitle();
	}

	protected void refreshTitle() {
		ResourceBundle i18nResource = ResourceBundle.getBundle("org.tisseursdechimeres.bb.teameditor.TeamEditor",Locale.getDefault());
		Object[] arguments = {player.getName()};
		String title = MessageFormat.format(i18nResource.getString("extra_skill_dialog_title"),arguments);
		
		setTitle(title);
	}
}
