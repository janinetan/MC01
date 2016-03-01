package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

import Model.Column;
import Model.ComboBoxConstants;
import Model.Constants;
import Model.TimeConstants;

public class Menu extends JFrame{
	
	private ButtonGroup group;
	private Constants constants;
	private ComboBoxConstants columnConstants;
	private TimeConstants timeConstants;
	
	private JPanel filteringPanel;
	private ArrayList<Column> cols;
	private JButton btnAdd;
	private JButton btnSearch;
	
	private ResultsPanel resultPanel1;
	private ResultsPanel resultPanel2;
	private ResultsPanel resultPanel3;
	private ResultsPanel resultPanel4;
	private ResultsPanel resultPanel5;
	
	public Menu() throws SQLException {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 50, 1000, 650);
		this.setTitle("ADVANDB MCO1 - Health Questions PH");
		this.setLayout(new BorderLayout());
		
		this.add(getControlPanel(), BorderLayout.NORTH);
		this.add(getResultsPanels(), BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	private JPanel getControlPanel() {
		JPanel controlPanel = new JPanel(); 
		controlPanel.setLayout(new GridLayout(0,2));
		controlPanel.add(getQueryOptionsPanel());
		controlPanel.add(getFilteringOptionsPanel());
		return controlPanel;
	}
	
	private JPanel getQueryOptionsPanel(){
		JPanel leftPanel = new JPanel();
	    leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
	    
	    JRadioButton option1 = new JRadioButton(constants.QUERY_TITLE1);
		option1.addActionListener(new querySelectorListener());
	    option1.setSelected(true);
	    JRadioButton option2 = new JRadioButton(constants.QUERY_TITLE2);
	    option2.addActionListener(new querySelectorListener());
	    JRadioButton option3 = new JRadioButton(constants.QUERY_TITLE3);
	    option3.addActionListener(new querySelectorListener());
	    JRadioButton option4 = new JRadioButton(constants.QUERY_TITLE4);
	    option4.addActionListener(new querySelectorListener());
	    JRadioButton option5 = new JRadioButton(constants.QUERY_TITLE5);
	    option5.addActionListener(new querySelectorListener());
	    JRadioButton option6 = new JRadioButton(constants.QUERY_TITLE6);
	    option6.addActionListener(new querySelectorListener());
	    JRadioButton option7 = new JRadioButton(constants.QUERY_TITLE7);
	    option7.addActionListener(new querySelectorListener());

	    group = new ButtonGroup();
	    group.add(option1);
	    group.add(option2);
	    group.add(option3);
	    group.add(option4);
	    group.add(option5);
	    group.add(option6);
	    group.add(option7);

	    leftPanel.add(option1);
	    leftPanel.add(option2);
	    leftPanel.add(option3);
	    leftPanel.add(option4);
	    leftPanel.add(option5);
	    leftPanel.add(option6);
	    leftPanel.add(option7);
	    
		Border border = BorderFactory.createTitledBorder("About");
		Border margin = BorderFactory.createEmptyBorder(10,10,10,10);
		leftPanel.setBorder(new CompoundBorder(border, margin));
		
		return leftPanel;
	}

	private JPanel getFilteringOptionsPanel(){
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());
	    
	    JPanel upperRightPanel = new JPanel();
	    upperRightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
	    btnAdd = new JButton("+");
	    btnAdd.addActionListener(new filteringListener());
	    upperRightPanel.add(btnAdd);
	    rightPanel.add(upperRightPanel, BorderLayout.NORTH);
	    
	    filteringPanel = new JPanel();
	    filteringPanel.setLayout(new BoxLayout(filteringPanel, BoxLayout.Y_AXIS));
	    JScrollPane scroll = new JScrollPane(filteringPanel);
	    scroll.setPreferredSize(new Dimension(50,50));
	    filteringPanel.setBackground(Color.LIGHT_GRAY);
	    rightPanel.add(scroll, BorderLayout.CENTER);
	    
	    JPanel lowerRightPanel = new JPanel();
	    lowerRightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
	    btnSearch = new JButton("Search");
	    btnSearch.addActionListener(new filteringListener());
	    lowerRightPanel.add(btnSearch);
	    rightPanel.add(lowerRightPanel, BorderLayout.SOUTH);
	    
	    Border border = BorderFactory.createTitledBorder("Filter Options");
		Border margin = BorderFactory.createEmptyBorder(0,10,0,10);
		rightPanel.setBorder(new CompoundBorder(border, margin));
	    return rightPanel;
	}
	
	private void addFilteringOption(ArrayList<Column> columns){
	    JPanel filterOption = new JPanel();
	    filterOption.setLayout(new FlowLayout(FlowLayout.LEFT));
	    if (filteringPanel.getComponents().length != 0){
	    	JComboBox opList = new JComboBox(new String[] {"and" , "or"});
		    opList.setSelectedIndex(0);
		    filterOption.add(opList);
	    }
	    
	    ArrayList<String> cols = new ArrayList<String>();
	    for (Column c: columns){
	    	cols.add(c.getName());
	    }
	    JComboBox colList = new JComboBox(cols.toArray());
	    colList.setSelectedIndex(0);
	    filterOption.add(colList);
	    
	    JComboBox funcList = new JComboBox();
	    colList.setSelectedIndex(0);
	    filterOption.add(funcList);
	    
	    JTextField text = new JTextField(10);
	    filterOption.add(text);
	    
	    JButton btnRemove = new JButton("-");
	    btnRemove.addActionListener(new filteringListener());
	    filterOption.add(btnRemove);
	    
	    filteringPanel.add(filterOption);
	    filteringPanel.revalidate();
	    filteringPanel.repaint();
	    setFunc(filterOption, columns.get(0).getType());
	    colList.addActionListener(new comboListener());
	}
	
	private void setFunc(JPanel panel, int i){
		JComboBox func = null;
		if (filteringPanel.getComponentZOrder(panel) == 0){
			func = (JComboBox) panel.getComponent(1); 
		}
		else{
			func = (JComboBox) panel.getComponent(2);
		}
		func.setModel(new JComboBox(getFunctions(i)).getModel());
	}
	
	public class comboListener implements ActionListener{
	    @Override
		public void actionPerformed(ActionEvent e) {
	    	setFunc((JPanel)((Component)e.getSource()).getParent(), columnConstants.findColumn(setComboBoxFilter(), (String) ((JComboBox)e.getSource()).getSelectedItem() ).getType());
		}
	}
	
	private String[] getFunctions(int type){
		ArrayList<String> funcList = new ArrayList<String>();
		if (type == constants.TYPE_STRING){
			funcList.add("=");
			funcList.add("LIKE");
		}
		else if (type == constants.TYPE_INT){
			funcList.add("=");
			funcList.add(">");
			funcList.add("<");
			funcList.add(">=");
			funcList.add("<=");
			funcList.add("NOT EQUAL");
		}
		return funcList.stream().toArray(String[]::new);
	}
	
	private void removeAllFilterOptions(){
		filteringPanel.removeAll();
		filteringPanel.revalidate();
		filteringPanel.repaint();
	}
	
	private void removeFilteringOption(JPanel panel){
		if (filteringPanel.getComponentZOrder(panel) == 0 && filteringPanel.getComponentCount() > 1){
			((JPanel)filteringPanel.getComponent(1)).remove(((JPanel)filteringPanel.getComponent(1)).getComponent(0));
		}
		filteringPanel.remove(panel);
		filteringPanel.revalidate();
		filteringPanel.repaint();
	}
	
	private JTabbedPane getResultsPanels() throws SQLException{
		JTabbedPane tabbedPane = new JTabbedPane();
		resultPanel1 = new ResultsPanel();
		resultPanel2 = new ResultsPanel();
		resultPanel3 = new ResultsPanel();
		resultPanel4 = new ResultsPanel();
		resultPanel5 = new ResultsPanel();
		tabbedPane.addTab("Implemenation 1", resultPanel1 );
		tabbedPane.addTab("Implemenation 2", resultPanel2 );
		tabbedPane.addTab("Implemenation 3", resultPanel3 );
		tabbedPane.addTab("Implemenation 4", resultPanel4 );
		tabbedPane.addTab("Implemenation 5", resultPanel5 );
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		setTableResults("");
		return tabbedPane;
	}
	
	public String getFilterOptions(){
		String clause = "";
		boolean first = true;
		for(Component c : filteringPanel.getComponents()){
			JPanel filterOption = (JPanel) c;
			String operand = "", column = "", function="", text = "";
			if (first){
				column = columnConstants.findColumn(setComboBoxFilter(), (String) ((JComboBox)filterOption.getComponent(0)).getSelectedItem() ).getColName();
				function = (String) ((JComboBox)filterOption.getComponent(1)).getSelectedItem();
				text = "'" + (String) ((JTextField)filterOption.getComponent(2)).getText() + "'";
				first = false;
			}
			else{ 
				operand = " " + (String) ((JComboBox)filterOption.getComponent(0)).getSelectedItem();
				column = columnConstants.findColumn(setComboBoxFilter(), (String) ((JComboBox)filterOption.getComponent(1)).getSelectedItem() ).getColName();
				function = (String) ((JComboBox)filterOption.getComponent(2)).getSelectedItem();
				text = "'" + (String) ((JTextField)filterOption.getComponent(3)).getText() + "'";
			}
			
			if (!text.equals("''")){
				clause += operand + " " + column + " " + function + " " + text +" ";
			}
		}
		return clause;
	}
	
	public class querySelectorListener implements ActionListener{
	    @Override
		public void actionPerformed(ActionEvent e) {
	    	removeAllFilterOptions();
	    	try {
				setTableResults("");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public ArrayList<Column> setComboBoxFilter(){
		ArrayList<Column> combo = null;
		switch(getQuerySelected()){
			case 1: combo = columnConstants.OPTIONS_QUERY1; break;
			case 2: combo = columnConstants.OPTIONS_QUERY2; break;
			case 3: combo = columnConstants.OPTIONS_QUERY3; break;
			case 4: combo = columnConstants.OPTIONS_QUERY4; break;
			case 5: combo = columnConstants.OPTIONS_QUERY5; break;
			case 6: combo = columnConstants.OPTIONS_QUERY6; break;
			case 7: combo = columnConstants.OPTIONS_QUERY7; break;
		}
		return combo;
	}
	
	public class filteringListener implements ActionListener{
	    @Override
		public void actionPerformed(ActionEvent e) {
	    	JButton button = (JButton) e.getSource();
	    	if (button == btnAdd){
				addFilteringOption(setComboBoxFilter());
	    	}
	    	else if (button == btnSearch){
				try {
					setTableResults(getFilterOptions());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
	    	}
	    	else {
	    		JPanel panel = (JPanel)button.getParent();
	    		removeFilteringOption(panel);
	    	}
		}
	}
	
	public void setTableResults(String addWhere) throws SQLException{
		String query1 = "", query2 = "", query3 = "",query3_1 ="",query3_2 ="", query4 = "",query4_1 ="",query4_2 ="", query5 = "",query5_1 ="",query5_2 ="",query5_3="",query5_4="";
		String add="",where="",into="",having="";
		String temp = addWhere;
		String[] temps = temp.split("and|or");
		String param = "";
		for (int i = 0; i < temps.length; i++){
			System.out.println(temps[i]);
			String[] tempss = temps[i].split(" +");
			try{
				param += "," + tempss[1] + " integer";
			}
			catch(Exception e){
				
			}
		}
		ArrayList<String> times1 = null, times2 = null, times3 = null, times4 = null, times5 = null;
		switch(getQuerySelected()){
			case 1:
				if(!addWhere.isEmpty()){
					add = " AND ";
				}
				query1 = constants.QUERY1_1_w1 + add +addWhere + constants.QUERY1_1_w2 + add +addWhere + constants.QUERY1_1_w3;
				query2 = constants.QUERY1_2_w1 +add +addWhere + constants.QUERY1_2_w2 + add +addWhere + constants.QUERY1_2_w3;
				query3 = constants.QUERY1_3_w1 +add +addWhere + constants.QUERY1_3_w2 +add +addWhere + constants.QUERY1_3_w3;
				query3_1 = constants.QUERY1_3_1;
				query3_2 = constants.QUERY1_3_2;
				query4 = constants.QUERY1_4_w1 +add +addWhere + constants.QUERY1_4_w2 +add +addWhere + constants.QUERY1_4_w3;
				query4_1 = constants.QUERY1_4_1;
				query4_2 = constants.QUERY1_4_2;
				query5 = constants.QUERY1_5_w1 + param + constants.QUERY1_5_w2;
				query5_1 = constants.QUERY1_5_1;
				query5_2 = constants.QUERY1_5_2_w1 + param + constants.QUERY1_5_2_w2+ addWhere + constants.QUERY1_5_2_w3 +addWhere +constants.QUERY1_5_2_w4;
				query5_3 = constants.QUERY1_5_3;
				query5_4 = constants.QUERY1_5_4;
				times1 = timeConstants.TIMES_QUERY1_1;
				times2 = timeConstants.TIMES_QUERY1_2;
				times3 = timeConstants.TIMES_QUERY1_3;
				times4 = timeConstants.TIMES_QUERY1_4;
				times5 = timeConstants.TIMES_QUERY1_5;
				break;
			case 2:
				if(!addWhere.isEmpty()){
					where =" WHERE ";
					add = " AND ";
				}
				query1 = constants.QUERY2_1_w1 + where +addWhere +constants.QUERY2_1_w2 + where +addWhere +constants.QUERY2_1_w3 ;
				query2 = constants.QUERY2_2 + add + addWhere;
				query3 = constants.QUERY2_3 + add + addWhere;
				query3_1 = constants.QUERY2_3_1;
				query3_2 = constants.QUERY2_3_2;
				query4 = constants.QUERY2_4;
				query4_1 = constants.QUERY2_4_1 + add + addWhere;
				query4_2 = constants.QUERY2_4_2;
				query5 = constants.QUERY2_5;
				query5_1 = constants.QUERY2_5_1 + add + addWhere;
				query5_2 = constants.QUERY2_5_2;
				query5_3 = constants.QUERY2_5_3;
				query5_4 = constants.QUERY2_5_4;
				times1 = timeConstants.TIMES_QUERY2_1;
				times2 = timeConstants.TIMES_QUERY2_2;
				times3 = timeConstants.TIMES_QUERY2_3;
				times4 = timeConstants.TIMES_QUERY2_4;
				times5 = timeConstants.TIMES_QUERY2_5;
				break;
			case 3:
				if(!addWhere.isEmpty()){
					add = " AND ";
				}
				query1 = constants.QUERY3_1_w1 + add +addWhere + constants.QUERY3_1_w2;
				query2 = constants.QUERY3_2_w1 +add +addWhere +constants.QUERY3_2_w2;
				query3 = constants.QUERY3_3_w1 +add +addWhere +constants.QUERY3_3_w2;
				query3_1 = constants.QUERY3_3_1;
				query3_2 = constants.QUERY3_3_2;
				query4 = constants.QUERY3_4;
				query4_1 = constants.QUERY3_4_1 + add + addWhere;
				query4_2 = constants.QUERY3_4_2;
				query5 = constants.QUERY3_5;
				query5_1 = constants.QUERY3_5_1 +add + addWhere;
				query5_2 = constants.QUERY3_5_2;
				query5_3 = constants.QUERY3_5_3;
				query5_4 = constants.QUERY3_5_4;
				times1 = timeConstants.TIMES_QUERY3_1;
				times2 = timeConstants.TIMES_QUERY3_2;
				times3 = timeConstants.TIMES_QUERY3_3;
				times4 = timeConstants.TIMES_QUERY3_4;
				times5 = timeConstants.TIMES_QUERY3_5;
				break;
			case 4:
				if(!addWhere.isEmpty()){
					where =" WHERE ";
				}
				query1 = constants.QUERY4_1_w1 + where +addWhere + constants.QUERY4_1_w2;
				query2 = constants.QUERY4_2_w1 +where +addWhere + constants.QUERY4_2_w2;
				query3 = constants.QUERY4_3_w1 +where +addWhere + constants.QUERY4_3_w2;
				query3_1 = constants.QUERY4_3_1;
				query3_2 = constants.QUERY4_3_2;
				query4 = constants.QUERY4_4_w1 +where +addWhere + constants.QUERY4_4_w2;
				query4_1 = constants.QUERY4_4_1;
				query4_2 = constants.QUERY4_4_2;
				query5 = constants.QUERY4_5_w1 +where +addWhere + constants.QUERY4_5_w2;
				query5_1 = constants.QUERY4_5_1;
				query5_2 = constants.QUERY4_5_2_w1 + param + constants.QUERY4_5_2_w2 + addWhere + constants.QUERY4_5_2_w3;
				query5_3 = constants.QUERY4_5_3;
				query5_4 = constants.QUERY4_5_4;
				times1 = timeConstants.TIMES_QUERY4_1;
				times2 = timeConstants.TIMES_QUERY4_2;
				times3 = timeConstants.TIMES_QUERY4_3;
				times4 = timeConstants.TIMES_QUERY4_4;
				times5 = timeConstants.TIMES_QUERY4_5;
				break;
			case 5:
				if(!addWhere.isEmpty()){
					add = " AND ";
				}
				query1 = constants.QUERY5_1_w1+ add +addWhere +constants.QUERY5_1_w2;
				query2 = constants.QUERY5_2_w1 +add +addWhere +constants.QUERY5_2_w2;
				query3 = constants.QUERY5_3_w1 +add +addWhere+ constants.QUERY5_3_w2;
				query3_1 = constants.QUERY5_3_1;
				query3_2 = constants.QUERY5_3_2;
				query4 = constants.QUERY5_4_w1 +add +addWhere +constants.QUERY5_4_w2;
				query4_1 = constants.QUERY5_4_1;
				query4_2 = constants.QUERY5_4_2;
				query5 = constants.QUERY5_5_w1 + param + constants.QUERY5_5_w2;
				query5_1 = constants.QUERY5_5_1;
				query5_2 = constants.QUERY5_5_2_w1 + param + constants.QUERY5_5_2_w2 + addWhere + constants.QUERY5_5_2_w3;
				query5_3 = constants.QUERY5_5_3;
				query5_4 = constants.QUERY5_5_4;
				times1 = timeConstants.TIMES_QUERY5_1;
				times2 = timeConstants.TIMES_QUERY5_2;
				times3 = timeConstants.TIMES_QUERY5_3;
				times4 = timeConstants.TIMES_QUERY5_4;
				times5 = timeConstants.TIMES_QUERY5_5;
				break;
			case 6:
				if(!addWhere.isEmpty()){
					having = " HAVING ";
				}
				query1 = constants.QUERY6_1 + having +addWhere;
				query2 = constants.QUERY6_2 + having +addWhere;
				query3 = constants.QUERY6_3 + having +addWhere;
				query3_1 = constants.QUERY6_3_1;
				query3_2 = constants.QUERY6_3_2;
				query4 = constants.QUERY6_4 + having +addWhere;
				query4_1 = constants.QUERY6_4_1;
				query4_2 = constants.QUERY6_4_2;
				query5 = constants.QUERY6_5_w1 + param + constants.QUERY6_5_w2;
				query5_1 = constants.QUERY6_5_1;
				query5_2 = constants.QUERY6_5_2_w1 + param + constants.QUERY6_5_2_w2 + having +addWhere;
				query5_3 = constants.QUERY6_5_3;
				query5_4 = constants.QUERY6_5_4;
				times1 = timeConstants.TIMES_QUERY6_1;
				times2 = timeConstants.TIMES_QUERY6_2;
				times3 = timeConstants.TIMES_QUERY6_3;
				times4 = timeConstants.TIMES_QUERY6_4;
				times5 = timeConstants.TIMES_QUERY6_5;
				break;
			case 7:
				if(!addWhere.isEmpty()){
					add = " AND ";
				}
				query1 = constants.QUERY7_1 + add +addWhere;
				query2 = constants.QUERY7_2 +add +addWhere;
				query3 = constants.QUERY7_3 +add +addWhere;
				query3_1 = constants.QUERY7_3_1;
				query3_2 = constants.QUERY7_3_2;
				query4 = constants.QUERY7_4 +add +addWhere;
				query4_1 = constants.QUERY7_4_1;
				query4_2 = constants.QUERY7_4_2;
				query5 = constants.QUERY7_5_w1 + param + constants.QUERY7_5_w2;
				query5_1 = constants.QUERY7_5_1;
				query5_2 = constants.QUERY7_5_2_w1 + param + constants.QUERY7_5_2_w2 + add + addWhere;
				query5_3 = constants.QUERY7_5_3;
				query5_4 = constants.QUERY7_5_4;
				times1 = timeConstants.TIMES_QUERY7_1;
				times2 = timeConstants.TIMES_QUERY7_2;
				times3 = timeConstants.TIMES_QUERY7_3;
				times4 = timeConstants.TIMES_QUERY7_4;
				times5 = timeConstants.TIMES_QUERY7_5;
				break;
		}
		resultPanel1.setQuery(query1);
		resultPanel1.setTablePanel(query1);
		resultPanel1.setTimes(times1);
		
		resultPanel2.setQuery(query2);
		resultPanel2.setTablePanel(query2);
		resultPanel2.setTimes(times2);
		
		resultPanel3.setQuery(query3_1+query3+query3_2);
		resultPanel3.execBatch(query3_1);
		resultPanel3.setTablePanel(query3);
		resultPanel3.execBatch(query3_2);
		resultPanel3.setTimes(times3);
		
		resultPanel4.setQuery(query4_1+query4+query4_2);
		resultPanel4.execUpdate(query4_1);
		resultPanel4.setTablePanel(query4);
		resultPanel4.execUpdate(query4_2);
		resultPanel4.setTimes(times4);

		resultPanel5.setQuery(query5_1+query5_2+query5+query5_3+query5_4);
		resultPanel5.execUpdate(query5_1);
		resultPanel5.execUpdate(query5_2);
		resultPanel5.setTablePanel(query5);
		resultPanel5.execUpdate(query5_3);
		resultPanel5.execUpdate(query5_4);
		resultPanel5.setTimes(times5);
	}
	
	public int getQuerySelected() {
        for (Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
            	if (button.getText() == constants.QUERY_TITLE1){
            		return 1;
            	}
            	else if (button.getText() == constants.QUERY_TITLE2){
            		return 2;
            	}
            	else if (button.getText() == constants.QUERY_TITLE3){
            		return 3;
            	}
            	else if (button.getText() == constants.QUERY_TITLE4){
            		return 4;
            	}
            	else if (button.getText() == constants.QUERY_TITLE5){
            		return 5;
            	}
            	else if (button.getText() == constants.QUERY_TITLE6){
            		return 6;
            	}
            	else if (button.getText() == constants.QUERY_TITLE7){
            		return 7;
            	}
            	break;
            }
        }
        return -1;
	}
}