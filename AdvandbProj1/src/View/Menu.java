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

import Model.Constants;

public class Menu extends JFrame{
	
	private ButtonGroup group;
	private Constants constants;
	
	private JPanel filteringPanel;
	private JButton btnAdd;
	private JButton btnSearch;
	
	private ResultsPanel resultPanel1;
	private ResultsPanel resultPanel2;
	private ResultsPanel resultPanel3;
	private ResultsPanel resultPanel4;
	private ResultsPanel resultPanel5;

	String sql="select * from hpq_aquani,hpq_alp where hpq_aquani.hpq_hh_id = hpq_alp.hpq_hh_id";
	String time = "0.005s\n0.009s";
	
	public Menu() throws SQLException {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 50, 800, 650);
		this.setTitle("ADVANDB MCO1");
		this.setLayout(new BorderLayout());
		
		this.add(getControlPanel(), BorderLayout.NORTH);
		//this.add(resultsPanel, BorderLayout.CENTER);
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
	
	private void addFilteringOption(String[] columns){
	    JPanel filterOption = new JPanel();
	    filterOption.setLayout(new FlowLayout(FlowLayout.LEFT));
		JComboBox colList = new JComboBox(columns);
	    colList.setSelectedIndex(0);
	    filterOption.add(colList);
	    
	    JComboBox funcList = new JComboBox(getFunctions(constants.TYPE_INT));
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
		resultPanel2.setTablePanel(constants.QUERY1_2);
		tabbedPane.addTab("Implemenation 1", resultPanel1 );
		tabbedPane.addTab("Implemenation 2", resultPanel2 );
		tabbedPane.addTab("Implemenation 3", resultPanel3 );
		tabbedPane.addTab("Implemenation 4", resultPanel4 );
		tabbedPane.addTab("Implemenation 5", resultPanel5 );
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		return tabbedPane;
	}
	
	public void getFilterOptions(){
		for(Component c : filteringPanel.getComponents()){
			JPanel filterOption = (JPanel) c;
			String column = (String) ((JComboBox)filterOption.getComponent(0)).getSelectedItem();
			String function = (String) ((JComboBox)filterOption.getComponent(1)).getSelectedItem();
			String text = (String) ((JTextField)filterOption.getComponent(2)).getText();
		}
	}
	
	public class querySelectorListener implements ActionListener{
	    @Override
		public void actionPerformed(ActionEvent e) {
	    	removeAllFilterOptions();
			try {
				System.out.println(getQuerySelected());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public class filteringListener implements ActionListener{
	    @Override
		public void actionPerformed(ActionEvent e) {
	    	JButton button = (JButton) e.getSource();
	    	if (button == btnAdd){
				String[] petStrings = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };
				addFilteringOption(petStrings);
	    	}
	    	else if (button == btnSearch){
	    		System.out.println("searching");
	    		getFilterOptions();
	    	}
	    	else {
	    		JPanel panel = (JPanel)button.getParent();
	    		removeFilteringOption(panel);
	    	}
		}
	}
	
	public int getQuerySelected() throws SQLException{
        for (Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
            	if (button.getText() == constants.QUERY_TITLE1){
            		resultPanel2.setTablePanel(constants.QUERY1_2);
            		return 1;
            	}
            	else if (button.getText() == constants.QUERY_TITLE2){
            		resultPanel2.setTablePanel(constants.QUERY2_2);
            		return 2;
            	}
            	else if (button.getText() == constants.QUERY_TITLE3){
            		resultPanel2.setTablePanel(constants.QUERY3_2);
            		return 3;
            	}
            	else if (button.getText() == constants.QUERY_TITLE4){
            		resultPanel2.setTablePanel(constants.QUERY4_2);
            		return 4;
            	}
            	else if (button.getText() == constants.QUERY_TITLE5){
            		resultPanel2.setTablePanel(constants.QUERY5_2);
            		return 5;
            	}
            	else if (button.getText() == constants.QUERY_TITLE6){
            		resultPanel2.setTablePanel(constants.QUERY6_2);
            		return 6;
            	}
            	else if (button.getText() == constants.QUERY_TITLE7){
            		resultPanel2.setTablePanel(constants.QUERY7_2);
            		return 7;
            	}
            	break;
            }
        }
        return -1;
	}
}
