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
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.BoxLayout;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import org.tisseursdechimeres.bb.Configuration;
import org.tisseursdechimeres.bb.Injuries;
import org.tisseursdechimeres.bb.Player;
import org.tisseursdechimeres.bb.Position;
import org.tisseursdechimeres.bb.Race;
import org.tisseursdechimeres.bb.RaceComparatorByI18nName;
import org.tisseursdechimeres.bb.SkillComparatorByCategory;
import org.tisseursdechimeres.bb.SkillComparatorByI18nName;
import org.tisseursdechimeres.bb.Skills;
import org.tisseursdechimeres.bb.Team;
import org.tisseursdechimeres.bb.filter.bbcode.BBCodeTeam;
import org.tisseursdechimeres.bb.filter.bbel.BBELTeamConverter;
import org.tisseursdechimeres.bb.filter.obblm.OBBLMTeamConverter;
import org.tisseursdechimeres.bb.filter.xml.TeamXMLReaderWriter;

public class TeamEditor 
		extends JFrame 
		implements ActionListener,ChangeListener,TableModelListener,ClipboardOwner{

	/**
	 * 
	 */
	protected Configuration config;
	protected JPanel staffPanel;
	protected JScrollPane playersScroll;
	protected JTable  playersTable;
	protected BoxLayout boxLayout;
	protected JTextField name;
	protected JSpinner rerollNumber;
	protected JLabel costPerReroll;
	protected JLabel rerollCost;
	protected JComboBox<Race> race;
	protected JSpinner ff;
	protected JLabel ffCost;
	protected JSpinner cheerleaders;
	protected JLabel cheerleadersCost;
	protected JSpinner assistants;
	protected JLabel assistantsCost;
	protected JSpinner apothicary;
	protected JLabel apothicaryCost;
	protected JTextField coach;
	protected JSpinner treasury;
	protected JLabel value;
	protected JLabel totalCost;
	protected JLabel availablePlayers;
	protected Team team;
	
	protected JMenuItem importTeamFromXmlItem;
	protected JMenuItem exportTeamToXmlItem;
	protected JMenuItem exportTeamToBBCodeItem;
	protected JMenuItem exportTeamToBBELItem;
	protected JMenuItem exportTeamToOBBLMItem;
	protected JMenuItem quitItem;
	protected JCheckBoxMenuItem skillCountItem;
	protected JCheckBoxMenuItem useSkillComparatorByCategoryItem;
	protected JMenuItem aboutItem;
	
	protected static TeamEditorVersion version;
	
	static private int INT_COLUMN_WIDTH = 10;
	static private int NAME_COLUMN_WIDTH = 150;
	static private int POSITION_COLUMN_WIDTH = 80;
	static private int SKILLS_COLUMN_WIDTH = 150;
	static private int INJURY_COLUMN_WIDTH = 80;
	
	protected TeamXMLReaderWriter xmlReaderWriter;
	protected BBELTeamConverter bbelReaderWriter;
	protected OBBLMTeamConverter obblmReaderWriter;
	
	protected StringSelection stringSelection;

	
	public TeamXMLReaderWriter getXmlReaderWriter() {
		return xmlReaderWriter;
	}

	protected boolean ready;
	protected ResourceBundle i18nResource;
				
	protected void init(){
		stringSelection = null;
		i18nResource = ResourceBundle.getBundle("org.tisseursdechimeres.bb.teameditor.TeamEditor",Locale.getDefault());
		ready = false;
		setTitle(i18nResource.getString("title"));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		boxLayout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
		getContentPane().setLayout(this.boxLayout);
		
		this.config = Configuration.getInstance();
		boolean exists = (new File(Configuration.BASENAME)).exists();
	    if (false == exists) {
	    	String confResourcePath = new String("/" + Configuration.BASENAME);
	    	this.config.extractFromXMLStream(this.getClass().getResourceAsStream(confResourcePath));
	    }else{
	    	this.config.extractFromXMLFile(Configuration.BASENAME);
	    }
	    
	    importTeamFromXmlItem = new JMenuItem();
		importTeamFromXmlItem.addActionListener(this);
		exportTeamToXmlItem = new JMenuItem();
		exportTeamToXmlItem.addActionListener(this);
		exportTeamToBBCodeItem = new JMenuItem();
		exportTeamToBBCodeItem.addActionListener(this);
		exportTeamToBBELItem = new JMenuItem();
		exportTeamToBBELItem.addActionListener(this);
		exportTeamToOBBLMItem = new JMenuItem();
		exportTeamToOBBLMItem.addActionListener(this);
		quitItem = new JMenuItem();
		quitItem.addActionListener(this);
		skillCountItem = new JCheckBoxMenuItem();
		skillCountItem.addActionListener(this);
		useSkillComparatorByCategoryItem = new JCheckBoxMenuItem();
		useSkillComparatorByCategoryItem.addActionListener(this);
		aboutItem = new JMenuItem();
		aboutItem.addActionListener(this);
	    
	    //playerWidgets = new ArrayList<PlayerWidget>(Team.MAX_PLAYER_NUMBER);
		name = new JTextField();
		rerollNumber = new JSpinner(new SpinnerNumberModel(0,0,8,1));
		rerollNumber.addChangeListener(this);
		costPerReroll = new JLabel();
		rerollCost = new JLabel();
		race = new JComboBox<Race>();
		race.setEditable(false);
		race.setRenderer(new RaceComboBoxRenderer());
		race.addActionListener(this);
		ff = new JSpinner(new SpinnerNumberModel(0,0,20,1));
		ff.addChangeListener(this);
		ffCost = new JLabel();
		cheerleaders = new JSpinner(new SpinnerNumberModel(0,0,10,1));
		cheerleaders.addChangeListener(this);
		cheerleadersCost = new JLabel();
		assistants = new JSpinner(new SpinnerNumberModel(0,0,10,1));
		assistants.addChangeListener(this);
		assistantsCost = new JLabel();
		apothicary = new JSpinner(new SpinnerNumberModel(0,0,1,1));
		apothicary.addChangeListener(this);
		apothicaryCost = new JLabel();
		coach = new JTextField();
		treasury = new JSpinner(new SpinnerNumberModel(0,0,1000000,10000));
		treasury.addChangeListener(this);
		value = new JLabel();
		totalCost = new JLabel();
		availablePlayers = new JLabel();
		
		team = new Team();
		playersTable = new JTable(new TeamTableModel(team,i18nResource,true));
		playersTable.getModel().addTableModelListener(this);
		playersScroll = new JScrollPane(playersTable);
		
		TableColumn column = null;
		TableColumnModel columnModel = playersTable.getColumnModel();
		
		int widths[]={INT_COLUMN_WIDTH,NAME_COLUMN_WIDTH,POSITION_COLUMN_WIDTH,
					  INT_COLUMN_WIDTH,INT_COLUMN_WIDTH,INT_COLUMN_WIDTH,INT_COLUMN_WIDTH,
					  SKILLS_COLUMN_WIDTH,SKILLS_COLUMN_WIDTH,INJURY_COLUMN_WIDTH,
					  INT_COLUMN_WIDTH,INT_COLUMN_WIDTH,INT_COLUMN_WIDTH,INT_COLUMN_WIDTH,
					  INT_COLUMN_WIDTH,INT_COLUMN_WIDTH,INT_COLUMN_WIDTH};
		int n = widths.length;
		for (int i = 0; i < n; i++) {
		    column = columnModel.getColumn(i);
		    column.setPreferredWidth(widths[i]);
		}
		
		xmlReaderWriter = new TeamXMLReaderWriter();
		bbelReaderWriter = new BBELTeamConverter();
		obblmReaderWriter = new OBBLMTeamConverter();

	}
	
	public TeamEditor(){
		super();
		init();
	}
	
	private static final long serialVersionUID = 3404819443663842824L;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File curDir = new File(".");
		try{
			System.out.println("Chemin absolu : "+ curDir.getCanonicalPath());
			System.out.println("Args : " + new Integer(args.length).toString());
		}catch(Exception e){
			e.printStackTrace();
		}
		TeamEditor self = new TeamEditor();
		self.prepareGui();
		Dimension menuDim = self.getJMenuBar().getPreferredSize();
		Dimension selfDim = self.getPreferredSize();
		selfDim.height += menuDim.height;
		selfDim.width += menuDim.width;
		self.setMinimumSize(selfDim);
		self.setExtendedState(self.getExtendedState() | Frame.MAXIMIZED_BOTH);
		if(args.length > 0){
			File toOpenFile = new File(args[0]);
			System.out.println("args[0] : |" + args[0]+"|");
			try{
				if(true == toOpenFile.canRead()){
				  self.getXmlReaderWriter().setTeamToWrite(self.team);
				  self.getXmlReaderWriter().readFile(toOpenFile);
				  self.refreshTeam();
				}
			}catch(Exception e){
			  e.printStackTrace();
			}
		}
		self.setVisible(true);
	}
	
	protected void prepareGui(){
		Race firstRace = null;
		
		ArrayList<Race> races = new ArrayList<Race>(config.getRaces());
		Collections.sort(races,new RaceComparatorByI18nName());
		for(Race race : races){
			if(null == firstRace){
				firstRace = race;
			}
			this.race.addItem(race);
		}
		team.setRace(firstRace);
		changeRace(firstRace);
		
		prepareMenuGui();
		preparePlayersGui();
		prepareStaffGui();
		
		guiToDataRerolls();
		guiToDataFanFactor();
		guiToDataAssistant();
		guiToDataCheerleader();
		guiToDataApothicary();
		guiToDataTreasury();
		refreshValue();
		ready = true;
	}

	private void prepareMenuGui() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu(i18nResource.getString("file_menu"));
		importTeamFromXmlItem.setText(i18nResource.getString("import_from_xml_menu_item"));
		fileMenu.add(importTeamFromXmlItem);
		exportTeamToXmlItem.setText(i18nResource.getString("export_to_xml_menu_item"));
		fileMenu.add(exportTeamToXmlItem);
		exportTeamToBBELItem.setText(i18nResource.getString("export_to_bbel_menu_item"));
		fileMenu.add(exportTeamToBBELItem);
		exportTeamToOBBLMItem.setText(i18nResource.getString("export_to_obblm_menu_item"));
		fileMenu.add(exportTeamToOBBLMItem);
		exportTeamToBBCodeItem.setText(i18nResource.getString("copy_bb_code_menu_item"));
		fileMenu.add(exportTeamToBBCodeItem);
		fileMenu.addSeparator();
		quitItem.setText(i18nResource.getString("quit_menu_item"));
		fileMenu.add(quitItem);
		menuBar.add(fileMenu);
		
		JMenu optionMenu = new JMenu(i18nResource.getString("options_menu"));
		skillCountItem.setText(i18nResource.getString("count_skill_menu_item"));
		skillCountItem.setState(true);
		optionMenu.add(skillCountItem);
		useSkillComparatorByCategoryItem.setText(i18nResource.getString("use_skill_locale_comparator_item"));
		useSkillComparatorByCategoryItem.setState(true);
		optionMenu.add(useSkillComparatorByCategoryItem);
		menuBar.add(optionMenu);
		
		JMenu helpMenu = new JMenu(i18nResource.getString("help_menu"));
		aboutItem.setText(i18nResource.getString("about_menu_item"));
		helpMenu.add(aboutItem);
		menuBar.add(helpMenu);
		
		setJMenuBar(menuBar);
	}

	private void preparePlayersGui() {
		Container container = this.getContentPane();
		playersTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		playersTable.setShowGrid(false);
		playersTable.setDefaultRenderer(Position.class, new PositionCellRenderer());
		playersTable.setDefaultRenderer(Object.class, new TeamCellRenderer());
		playersTable.setDefaultRenderer(Integer.class, new TeamCellRenderer());
		playersTable.setDefaultRenderer(Skills.class, new SkillsCellRenderer());
		playersTable.setDefaultEditor(Position.class, new PositionCellEditor(team,(ActionListener)this));
		SkillCellEditor skillEditor = new SkillCellEditor();
		skillEditor.setTeam(team);
		skillEditor.setChangeListener((ChangeListener)this);
		if(true == useSkillComparatorByCategoryItem.isSelected()){
			skillEditor.setSkillComparator(new SkillComparatorByCategory());	
		}else{
			skillEditor.setSkillComparator(new SkillComparatorByI18nName());
		}
		playersTable.setDefaultEditor(Skills.class, skillEditor);
		
		InjuryCellEditor injuryEditor = new InjuryCellEditor();
		injuryEditor.setTeam(team);
		injuryEditor.setChangeListener((ChangeListener)this);
		playersTable.setDefaultEditor(Injuries.class, injuryEditor);
		IntegerCellEditor integerEditor = new IntegerCellEditor();
		
		integerEditor.setChangeListener((ChangeListener)this);
		playersTable.setDefaultEditor(Integer.class, integerEditor);
		
		int rowHeight = playersTable.getRowHeight();
		rowHeight += rowHeight /2;
		playersTable.setRowHeight(rowHeight);

		container.add(playersScroll);
	}
	
	private void prepareStaffGui() {		
	    staffPanel = new JPanel();
		int i = 0;
		staffPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5,5,5,5);
		gbc.gridwidth=1;
		gbc.gridheight=1;
		gbc.fill = GridBagConstraints.BOTH;
		
		gbc.gridy = i;
		gbc.gridx = 0;
		staffPanel.add(new JLabel(i18nResource.getString("name_label")),gbc);
		gbc.gridx++;
		staffPanel.add(name,gbc);
		gbc.gridx++;
		gbc.gridwidth = 2;
		staffPanel.add(new JLabel(i18nResource.getString("rerolls_label")),gbc);
		gbc.gridx += gbc.gridwidth;		
		staffPanel.add(rerollNumber,gbc);
		gbc.gridx += gbc.gridwidth;
		gbc.gridwidth = 3;
		staffPanel.add(costPerReroll,gbc);
		gbc.gridx+= gbc.gridwidth;
		gbc.gridwidth = 1;
		staffPanel.add(rerollCost,gbc);
		
		i++;
		gbc.gridy=i;
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		staffPanel.add(new JLabel(i18nResource.getString("race_label")),gbc);
		gbc.gridx++;
		staffPanel.add(race,gbc);
		gbc.gridx++;
		gbc.gridwidth = 2; 
		staffPanel.add(new JLabel(i18nResource.getString("fan_factor_label")),gbc);
		gbc.gridx += gbc.gridwidth;
		gbc.gridwidth = 2;
		staffPanel.add(ff,gbc);
		gbc.gridx += gbc.gridwidth;
		gbc.gridwidth = 3; 
		staffPanel.add(new JLabel(i18nResource.getString("x10000gp")),gbc);
		gbc.gridx += gbc.gridwidth;
		gbc.gridwidth = 1;
		staffPanel.add(ffCost,gbc);
		
		i++;
		gbc.gridy = i;
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		staffPanel.add(new JLabel(i18nResource.getString("coach_label")),gbc);
		gbc.gridx++;
		staffPanel.add(coach,gbc);
		gbc.gridx++;
		gbc.gridwidth = 2; 
		staffPanel.add(new JLabel(i18nResource.getString("apothicary_label")),gbc);
		gbc.gridx += gbc.gridwidth;
		gbc.gridwidth = 2; 
		staffPanel.add(apothicary,gbc);
		gbc.gridx += gbc.gridwidth;
		gbc.gridwidth = 3; 
		staffPanel.add(new JLabel(i18nResource.getString("x50000gp")),gbc);
		gbc.gridx += gbc.gridwidth;
		gbc.gridwidth = 1;
		staffPanel.add(apothicaryCost,gbc);
		
		i++;
		gbc.gridy = i;
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		staffPanel.add(new JLabel(i18nResource.getString("treasury_label")),gbc);
		gbc.gridx++;
		staffPanel.add(treasury,gbc);
		gbc.gridx++;
		gbc.gridwidth = 2; 
		staffPanel.add(new JLabel(i18nResource.getString("assistants_label")),gbc);
		gbc.gridx += gbc.gridwidth;
		gbc.gridwidth = 2; 
		staffPanel.add(assistants,gbc);
		gbc.gridx += gbc.gridwidth;
		gbc.gridwidth = 3; 
		staffPanel.add(new JLabel(i18nResource.getString("x10000gp")),gbc);
		gbc.gridx += gbc.gridwidth;
		gbc.gridwidth = 1;
		staffPanel.add(assistantsCost,gbc);
		
		i++;
		gbc.gridy = i;
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		staffPanel.add(new JLabel(i18nResource.getString("teamvalue_label")),gbc);
		gbc.gridx++;
		staffPanel.add(value,gbc);
		gbc.gridx++;
		gbc.gridwidth = 2; 
		staffPanel.add(new JLabel(i18nResource.getString("cheerleaders_label")),gbc);
		gbc.gridx += gbc.gridwidth;
		gbc.gridwidth = 2; 
		staffPanel.add(cheerleaders,gbc);
		gbc.gridx += gbc.gridwidth;
		gbc.gridwidth = 3; 
		staffPanel.add(new JLabel(i18nResource.getString("x10000gp")),gbc);
		gbc.gridx += gbc.gridwidth;
		gbc.gridwidth = 1;
		staffPanel.add(cheerleadersCost,gbc);
		
		i++;
		gbc.gridy = i;
		gbc.gridx = 0;
		gbc.gridwidth = 7; 
		staffPanel.add(new JLabel(i18nResource.getString("total_label")),gbc);
		gbc.gridx += gbc.gridwidth;
		gbc.gridwidth = 1;
		staffPanel.add(totalCost,gbc);
		
		i++;
		gbc.gridy = i;
		gbc.gridx = 0;
		gbc.gridwidth = 7; 
		staffPanel.add(new JLabel(i18nResource.getString("available_players_label")),gbc);
		gbc.gridx += gbc.gridwidth;
		gbc.gridwidth = 1;
		staffPanel.add(availablePlayers,gbc);
		
		Container container = this.getContentPane();
		container.add(staffPanel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		boolean needToRefreshValue = false;
		if(true == ready){
			Object eventSource = e.getSource();
			if (race == eventSource){
				//System.out.println("Changement de race !");
				Object selectedObject = race.getSelectedItem();
				//System.out.println("selectedObject : "+selectedObject.getClass().getName());
				if(selectedObject.getClass() == Race.class){
					Race race = (Race) selectedObject;
					team.setRace(race);
					changeRace(race);
					needToRefreshValue = true;
				}
			}else if(importTeamFromXmlItem == eventSource ){
				importTeamFromXml();
			}else if(exportTeamToXmlItem == eventSource ){
				exportTeamToXml();
			}else if(exportTeamToBBCodeItem == eventSource ){
				exportTeamToBBCode();
			}else if(exportTeamToBBELItem == eventSource ){
				exportTeamToBBEL();
			}else if(exportTeamToOBBLMItem == eventSource ){
				exportTeamToOBBLM();
			}else if(quitItem == eventSource ){
				quit();
			}else if(skillCountItem == eventSource ){
				changeSkillCount();
			}else if( useSkillComparatorByCategoryItem == eventSource ){
				changeSkillComparator();
			}else if(aboutItem == eventSource ){
				about();
			}else{
				//System.out.println("Action performed >classe de la source : " + e.getSource().getClass().toString());
			}
		}
		if(true == needToRefreshValue){
			refreshValue();	
		}
	}
	


	private void about() {
		String title = i18nResource.getString("about_title");
		MessageFormat formatter = new MessageFormat("",Locale.getDefault());
		String jvmVersion = System.getProperty("java.version");
		String jvmVendor = System.getProperty("java.vendor");
		Object[] arguments={TeamEditorVersion.number,jvmVendor,jvmVersion};
		formatter.applyPattern(i18nResource.getString("about_template_content"));
		String content = formatter.format(arguments);
		JOptionPane.showMessageDialog(this,
			    content,
			    title,
			    JOptionPane.INFORMATION_MESSAGE);

	}

	private void changeSkillCount(){
		boolean  checkSkillCount = skillCountItem.isSelected();
		TableModel model = playersTable.getModel();
		if(model.getClass()== TeamTableModel.class){
			TeamTableModel teamModel = (TeamTableModel)model;
			teamModel.setCheckSkillCount(checkSkillCount);
		}
	}
	
	private void changeSkillComparator(){
		SkillCellEditor skillEditor = new SkillCellEditor();
		skillEditor.setTeam(team);
		skillEditor.setChangeListener((ChangeListener)this);
		if(true == useSkillComparatorByCategoryItem.isSelected()){
			skillEditor.setSkillComparator(new SkillComparatorByCategory());
		}else{
			skillEditor.setSkillComparator(new SkillComparatorByI18nName());
		}
		playersTable.setDefaultEditor(Skills.class, skillEditor);
	}
	
	private void quit() {
		dispose();
	}

	private void exportTeamToBBCode(){
		guiToDataNames();
		String bbCode = BBCodeTeam.writeTeam(team);
		stringSelection = new StringSelection(bbCode);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Cursor currentCursor = getCursor();
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR) );
		try {
			clipboard.setContents(stringSelection, this);    
		} catch( IllegalStateException e) {
	        e.printStackTrace();
		} 
		setCursor(currentCursor);
	}

	private void exportTeamToXml() {
		guiToDataNames();
		JFileChooser teamFileChooser = new JFileChooser("./");
		teamFileChooser.setFileFilter(xmlReaderWriter.getOutputFileFilter());
		int returnVal = teamFileChooser.showSaveDialog(this);
		if(JFileChooser.APPROVE_OPTION == returnVal){
			Cursor currentCursor = getCursor();
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR) );
			try{
			File teamFile = teamFileChooser.getSelectedFile();
			xmlReaderWriter.setTeamToWrite(team);
			xmlReaderWriter.writeFile(teamFile);
			}catch(Exception e){
				e.printStackTrace();
				JOptionPane.showMessageDialog(this,e.getMessage());
			}
			setCursor(currentCursor);
		}
	}
	
	private void exportTeamToBBEL() {
		guiToDataNames();
		JFileChooser teamFileChooser = new JFileChooser("./");
		teamFileChooser.setFileFilter(bbelReaderWriter.getOutputFileFilter());
		int returnVal = teamFileChooser.showSaveDialog(this);
		if(JFileChooser.APPROVE_OPTION == returnVal){
			Cursor currentCursor = getCursor();
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR) );
			try{
				File teamFile = teamFileChooser.getSelectedFile();
				bbelReaderWriter.setTeamToWrite(team);
				bbelReaderWriter.writeFile(teamFile);
			}catch(Exception e){
				JOptionPane.showMessageDialog(this,e.getMessage());
				e.printStackTrace();
			}
			setCursor(currentCursor);
		}
	}
	
	private void exportTeamToOBBLM() {
		guiToDataNames();
		JFileChooser teamFileChooser = new JFileChooser("./");
		teamFileChooser.setFileFilter(obblmReaderWriter.getOutputFileFilter());
		int returnVal = teamFileChooser.showSaveDialog(this);
		if(JFileChooser.APPROVE_OPTION == returnVal){
			Cursor currentCursor = getCursor();
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR) );
			try{
				File teamFile = teamFileChooser.getSelectedFile();
				obblmReaderWriter.setTeamToWrite(team);
				obblmReaderWriter.writeFile(teamFile);
			}catch(Exception e){
				JOptionPane.showMessageDialog(this,e.getMessage());
				e.printStackTrace();
			}
			setCursor(currentCursor);
		}
	}

	private void importTeamFromXml() {
	  JFileChooser teamFileChooser = new JFileChooser("./");
	  teamFileChooser.setFileFilter(xmlReaderWriter.getOutputFileFilter());
	  int returnVal = teamFileChooser.showOpenDialog(this);
	  if(JFileChooser.APPROVE_OPTION == returnVal){
		Cursor currentCursor = getCursor();
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR) );
		try{
		
		  ready = false;
		  File teamFile = teamFileChooser.getSelectedFile();
		  xmlReaderWriter.setTeamToWrite(team);
		  xmlReaderWriter.readFile(teamFile);
		  refreshTeam();
		  
		  ready = true;
		}catch(Exception e){
			 JOptionPane.showMessageDialog(this,e.getMessage());
			 e.printStackTrace();
		}
		setCursor(currentCursor);
	  }
	}
	
	private void refreshTeam() {
	  race.setSelectedItem(team.getRace());
	  changeRace(team.getRace());
	  
	  dataToGuiRerolls();
	  dataToGuiPlayers();
	  dataToGuiApothicary();
	  dataToGuiAssistants();
	  dataToGuiCheerleaders();
	  dataToGuiFanFactor();
	  dataToGuiTreasury();
	  dataToGuiNames();
	  refreshValue();
	}
	
	private void dataToGuiNames() {
	  name.setText(team.getName());
	  coach.setText(team.getCoach());
	}

	private void dataToGuiTreasury() {
	  treasury.setValue(team.getTreasury()*Team.BASIC_UNIT);
	}

	private void dataToGuiRerolls() {
		  rerollNumber.setValue(team.getRerolls());
	}
	
	private void dataToGuiFanFactor() {
	  ff.setValue(team.getFf());
	  refreshFf();
	}

	private void dataToGuiCheerleaders() {
	  cheerleaders.setValue(team.getCheerleaders());
	  refreshCheerleaders();
	}

	private void dataToGuiAssistants() {
	  assistants.setValue(team.getAssistants());
	  refreshAssistants();
	}

	private void dataToGuiApothicary() {
	  apothicary.setValue(team.getApothicary());
	  refreshApothicary();
	}

	private void dataToGuiPlayers() {
		TeamTableModel teamModel = (TeamTableModel)playersTable.getModel();
	    for(Player player : team.getPlayers()){
	    	int row  = player.getNumber() - 1;
	    	teamModel.setValueAt(row +1, row, 0);
	    	teamModel.setValueAt(player.getName(), row, 1);
	    	teamModel.setValueAt(player.getPosition(), row, 2);
	    	teamModel.setValueAt(player.getPosition().getSkills().getI18nNameList(), row, 3);
	    	teamModel.setValueAt(player.getExtraSkills().getI18nNameList(), row, 4);
       }
	   int rowCount = playersTable.getRowCount();
	   for(int row = 0 ; row < rowCount ;row++){
		   setPreferredRowHeight(row);
	   }
	}
	
	private void setPreferredRowHeight(int row) {
		// Get the current default height for all rows 
		int height = playersTable.getRowHeight(); 
		int margin = playersTable.getRowMargin();
		// Determine highest cell in the row 
		for (int c=0; c<playersTable.getColumnCount(); c++) { 
			TableCellRenderer renderer = playersTable.getCellRenderer(row, c);
			Component comp = playersTable.prepareRenderer(renderer, row, c);
			int h = comp.getPreferredSize().height + 2*margin; 
			height = Math.max(height, h);
		}
		playersTable.setRowHeight(row, height);
	}

	private void refreshValue() {
		Integer total = new Integer(0);
		team.computeValue();
		total = team.getTotalCost() * Team.BASIC_UNIT;
		totalCost.setText(total.toString() + team.getI18nUnit());
		value.setText(team.getValue().toString());
		if(team.getAvailablePlayers() < 11)
			availablePlayers.setText("<html><font color=\"red\">"+team.getAvailablePlayers().toString()+"</font></html>");
		else
			availablePlayers.setText(team.getAvailablePlayers().toString());
	}
	
	protected void refreshFanFactor(){
	  Integer ffValue = new Integer(0);
	  ffValue = team.getFf() * Team.VALUE_PER_FF; 
	  ffValue *= Team.BASIC_UNIT;
	  ffCost.setText(ffValue.toString()+ team.getI18nUnit());
	}
	
	protected void refreshAssistants(){
	  Integer assistantValue = new Integer(0);
	  assistantValue = team.getAssistants() * Team.VALUE_PER_ASSISTANT;
	  assistantValue *= Team.BASIC_UNIT;
	  assistantsCost.setText(assistantValue.toString()+ team.getI18nUnit());
	}
	
	protected void refreshCheerleaders(){
	  Integer cheerleaderValue = new Integer(0);
	  cheerleaderValue = team.getCheerleaders() * Team.VALUE_PER_CHEERLEADER;
	  cheerleaderValue *= Team.BASIC_UNIT;
	  cheerleadersCost.setText(cheerleaderValue.toString()+ team.getI18nUnit());
	}
	
	protected void refreshApothicary(){
	  Integer apothicaryValue = new Integer(0);
	  apothicaryValue = team.getApothicary()* Team.VALUE_PER_APOTHICARY;
	  apothicaryValue *= Team.BASIC_UNIT;
	  apothicaryCost.setText(apothicaryValue.toString()+ team.getI18nUnit());
	}
	
	private void guiToDataNames(){
	  for(int i=0;i<Team.MAX_PLAYER_NUMBER;i++){
		//playerWidgets.get(i).refreshName();
	  }
	  team.setName(name.getText());
	  team.setCoach(coach.getText());
	}

	public void changeRace(Race race){
		
		playersTable.setDefaultEditor(Position.class, new PositionCellEditor(team,(ActionListener)this));
		
		Integer costParRerollToDisplay;
		costParRerollToDisplay = team.getRace().getRerollCost() * Team.BASIC_UNIT;  
		costPerReroll.setText("* " + costParRerollToDisplay.toString() + team.getI18nUnit());
		refreshRerolls();
		
		boolean useApo;
		useApo = race.getUseApothicary();
		if(false == useApo){
			apothicary.setValue(0);
		}
		apothicary.setEnabled(useApo);
		
	}
	
	protected void guiToDataRerolls(){
		Object rerollNumberValue = rerollNumber.getValue();
		if(rerollNumberValue.getClass() == Integer.class){
			team.setRerolls((Integer)rerollNumberValue);
			refreshRerolls();
		}
	}
	
	protected void refreshRerolls(){
	  Integer rerollValue = new Integer(0);
	  rerollValue = team.getRerolls() * team.getRace().getRerollCost(); 
	  rerollValue *= Team.BASIC_UNIT;
	  rerollCost.setText(rerollValue.toString()+ team.getI18nUnit());
	}
	
	protected void refreshFf(){
	  Integer ffValue = new Integer(0);
	  ffValue = team.getFf() * Team.VALUE_PER_FF; 
	  ffValue *= Team.BASIC_UNIT;
	  ffCost.setText(ffValue.toString()+ team.getI18nUnit());
	}
	
	protected void guiToDataFanFactor(){
		Object ffNumberValue = ff.getValue();
		if(ffNumberValue.getClass() == Integer.class){
			team.setFf((Integer)ffNumberValue);
			refreshFf();
		}
	}
		
	protected void guiToDataAssistant(){
		Object assistantNumber = assistants.getValue();
		if(assistantNumber.getClass() == Integer.class){
			Integer assistantValue = new Integer(0);
			team.setAssistants((Integer)assistantNumber);
			assistantValue = team.getAssistants() * Team.VALUE_PER_ASSISTANT;
			assistantValue *= Team.BASIC_UNIT;
			assistantsCost.setText(assistantValue.toString()+ team.getI18nUnit());
		}
	}
	
	protected void guiToDataCheerleader(){
		Object cheerleaderNumber = cheerleaders.getValue();
		if(cheerleaderNumber.getClass() == Integer.class){
			Integer cheerleaderValue = new Integer(0);
			team.setCheerleaders((Integer)cheerleaderNumber);
			cheerleaderValue = team.getCheerleaders() * Team.VALUE_PER_CHEERLEADER;
			cheerleaderValue *= Team.BASIC_UNIT;
			cheerleadersCost.setText(cheerleaderValue.toString()+ team.getI18nUnit());
		}
	}
	
	protected void guiToDataApothicary(){
		Object apothicaryNumber = apothicary.getValue();
		if(apothicaryNumber.getClass() == Integer.class){
			Integer apothicaryValue = new Integer(0);
			team.setApothicary((Integer)apothicaryNumber);
			apothicaryValue = team.getApothicary()* Team.VALUE_PER_APOTHICARY;
			apothicaryValue *= Team.BASIC_UNIT;
			apothicaryCost.setText(apothicaryValue.toString()+ team.getI18nUnit());
		}
	}
	
	protected void guiToDataTreasury(){
		Object treasuryNumber = treasury.getValue();
		if(treasuryNumber.getClass() == Integer.class){
			Integer treasuryValue = new Integer((Integer)treasuryNumber);
			team.setTreasury(treasuryValue / Team.BASIC_UNIT);
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if(true == ready){
			boolean needToRefreshValue = false;
			if(e.getSource() == rerollNumber){
				needToRefreshValue = true;
				guiToDataRerolls();
			}else if (e.getSource() == ff){
				needToRefreshValue = true;
				guiToDataFanFactor();
			}else if (e.getSource() == assistants){
				needToRefreshValue = true;
				guiToDataAssistant();
			}else if (e.getSource() == cheerleaders){
				needToRefreshValue = true;
				guiToDataCheerleader();
			}else if (e.getSource() == apothicary){
				needToRefreshValue = true;
				guiToDataApothicary();
			}else if (e.getSource() == treasury){
				guiToDataTreasury();
				needToRefreshValue = true;
			}else if( e.getSource().getClass() == ExtraSkillDialog.class ){
				needToRefreshValue = true;
			}else if( e.getSource().getClass() == InjuryDialog.class ){
				needToRefreshValue = true;
			}
			
			if(true == needToRefreshValue){
				refreshValue();
			}
		}
	}

	@Override
	public void lostOwnership(Clipboard clipboard, Transferable contents) {
		stringSelection.lostOwnership(clipboard, contents);
		stringSelection = null;
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		refreshValue();
	}

}
