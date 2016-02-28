package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Model.DBConnection;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;

public class ResultsPanel extends JPanel {
	
	public static Connection conn;
	
	private JTextArea queryDisplayer;
	private JLabel timeDisplayer;
	private JButton btnSummary;
	private JTable resultTable;
	private JPanel tablePane;
	
	public ResultsPanel() throws SQLException {
		this.setLayout(new BorderLayout());
		this.add(setInfoPanel(), BorderLayout.NORTH);
		createTablePanel();
		this.add(tablePane, BorderLayout.CENTER);
	}
	
	public JPanel setInfoPanel(){
		JPanel infoPanel = new JPanel();
		infoPanel.setPreferredSize(new Dimension(500,100));
		infoPanel.setLayout(new GridLayout(0,2));
		
		JPanel queryPanel = new JPanel();
		queryPanel.setLayout(new BorderLayout());
		Border border = BorderFactory.createTitledBorder("Query");
		Border margin = BorderFactory.createEmptyBorder(3,2,3,2);
		queryPanel.setBorder(new CompoundBorder(border, margin));
		queryDisplayer = new JTextArea();
		queryDisplayer.setLineWrap(true);
		queryDisplayer.setWrapStyleWord(true);
		queryDisplayer.setEditable(false);
		queryDisplayer.setOpaque(false);
		queryDisplayer.setFont(new Font("monospaced", Font.PLAIN, 12));
		JScrollPane scroll = new JScrollPane(queryDisplayer);
		queryPanel.add(scroll, BorderLayout.CENTER);
		
		JPanel timePanel = new JPanel();
		timePanel.setBorder(BorderFactory.createTitledBorder("Execution Time"));
		timePanel.setLayout(new BorderLayout());
		JPanel innerTimePanel = new JPanel();
		innerTimePanel.setLayout(new BorderLayout());
		timeDisplayer = new JLabel();
		timeDisplayer.setHorizontalAlignment(SwingConstants.CENTER);
		innerTimePanel.add(timeDisplayer, BorderLayout.CENTER);
		btnSummary = new JButton("Summary");
		btnSummary.addActionListener(new summaryListener());
		innerTimePanel.add(btnSummary, BorderLayout.EAST);
		timePanel.add(innerTimePanel, BorderLayout.NORTH);
		
		infoPanel.add(queryPanel);
		infoPanel.add(timePanel);
		return infoPanel;
	}
	
	public void createTablePanel()
	{
		tablePane = new JPanel();
	    tablePane.setLayout(new BorderLayout());
	}
	
	public void setTablePanel(String q) throws SQLException
	{
		this.remove(tablePane);
		JTable table = resultTable(q);
	    tablePane = new JPanel();
	    tablePane.setLayout(new BorderLayout());
	    JScrollPane scroll = new JScrollPane(table);
	    tablePane.add(scroll);
	    this.add(tablePane);
	    this.revalidate();
	    this.repaint();
	}
	
	public JTable resultTable(String q) throws SQLException
	{
		// The Connection is obtained
		conn = (Connection) DBConnection.getConnection();
		PreparedStatement stmt;
		ResultSet rs;
		stmt = (PreparedStatement) conn.prepareStatement(q);
		long startTime = System.currentTimeMillis();
		rs = stmt.executeQuery();
		long estimatedTime = System.currentTimeMillis() - startTime;
		String time = estimatedTime +"";
		setTime(time + " milliseconds");
		setQuery(q);
		
		JTable table = new JTable(buildTableModel(rs));
	    table.setEnabled(false);

	    ColumnsAutoSizer.sizeColumnsToFit(table);
	    
	    //an event listener to automatically resize the columns every time data is added or changed in the table, like this
	    table.getModel().addTableModelListener(new TableModelListener() {
	        public void tableChanged(TableModelEvent e) {
	            ColumnsAutoSizer.sizeColumnsToFit(table);
	        }
	    });
	    conn.close();
	    return table;
	}
	
	public void execUpdate(String q) throws SQLException
	{
		conn = (Connection) DBConnection.getConnection();
		PreparedStatement stmt;
		stmt = (PreparedStatement) conn.prepareStatement(q);
		stmt.execute();
		conn.close();
	}
	
	public static DefaultTableModel buildTableModel(ResultSet rs)
	        throws SQLException {

	    ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();

	    // names of columns
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	    }

	    // data of the table
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        data.add(vector);
	    }

	    return new DefaultTableModel(data, columnNames);

	}
	
	public void setTime(String time ){
		timeDisplayer.setText(time);
	}
	
	public void setQuery(String query){
		queryDisplayer.setText(query);
	}

	public class summaryListener implements ActionListener{
	    @Override
		public void actionPerformed(ActionEvent e) {
		}
	}
}
