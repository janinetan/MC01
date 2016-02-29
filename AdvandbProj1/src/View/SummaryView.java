package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

public class SummaryView extends JFrame{
	
	private JPanel panel;
	
	public SummaryView(ArrayList<String> times) {
		this.setLayout(new BorderLayout());
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		Border border = BorderFactory.createTitledBorder("Summary");
		Border margin = BorderFactory.createEmptyBorder(3,2,3,2);
		panel.setBorder(new CompoundBorder(border, margin));
		this.setView(times);
		this.add(panel, BorderLayout.CENTER);
		this.setVisible(true);
		this.setBounds(100, 50, 150, 400);
	}
	
	public void setView(ArrayList<String> times){
		float ave = 0;
		for (String time: times){
			JLabel label = new JLabel(time + " milliseconds");
			label.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(label);
			ave += Integer.parseInt(time);
		}
		ave /= times.size();
		JLabel label = new JLabel(ave + " milliseconds");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label);
	}

}
