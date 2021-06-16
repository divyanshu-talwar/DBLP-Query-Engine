
/**
 *
 * @author Mridul Gupta | Divyanshu Talwar
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class myQuery3Panel {

	private JButton resetButton; /**< Button to Reset */
	private JButton searchButton; /**< Button to Start search */
	private JPanel panel4 = new JPanel(new GridBagLayout()); /**< New GridBag layout for Query3 Panel */
	private GridBagConstraints panel4gbc = new GridBagConstraints(); /**< New GridBag Constraints */ 
	private JTextField ThrasholdField = new JTextField(); /**< JTextField for "Threshold" Year */ 
	private JLabel thresholdText = new JLabel("Till Year"); /**< JLabel for "Threshold" Year */
	private JTextField nameField = new JTextField(); /**< JTextField for Author name */ 
	private JLabel nameText = new JLabel("Name"); /**< JLabel for Author name */
	
	/**
	 * Constructor. Setting up GUI. Preparing buttons
	 */
	public myQuery3Panel() {
		panel4gbc.insets = new Insets(10, 10, 10, 10);
		prepareGui();
		// colorize();
		buttonWorking();
	}

	// private void colorize() {
	// thresholdText.setForeground(Color.cyan);
	// thresholdText.setBackground(Color.gray);
	// nameText.setForeground(Color.cyan);
	// nameText.setBackground(Color.gray);
	// resetButton.setBackground(Color.cyan);
	// searchButton.setBackground(Color.cyan);
	// panel4.setBackground(Color.gray);
	// }

	/**
	 * Preparing buttons and texts and shizz
	 */
	public void prepareGui() {
		ThrasholdField.setPreferredSize(new Dimension(200, 50));
		thresholdText.setPreferredSize(new Dimension(200, 50));
		thresholdText.setFont(new Font("Arial", Font.BOLD, 30));
		ThrasholdField.setFont(new Font("Arial", Font.BOLD, 30));
		panel4gbc.gridx = 0;
		panel4gbc.gridy = 0;
		panel4.add(thresholdText, panel4gbc);
		panel4gbc.gridx = 0;
		panel4gbc.gridy = 1;
		panel4.add(ThrasholdField, panel4gbc);
		JLabel l1 = new JLabel("      ");
		JLabel l2 = new JLabel("      ");
		panel4gbc.gridy = 2;
		panel4.add(l1, panel4gbc);
		panel4gbc.gridy = 3;
		panel4.add(l2, panel4gbc);
		resetButton = new JButton("Reset");
		// resetButton.setBackground(Color.gray);
		resetButton.setFont(new Font("Arial", Font.BOLD, 30));
		resetButton.setPreferredSize(new Dimension(200, 50));
		searchButton = new JButton("Search");
		// searchButton.setBackground(Color.gray);
		searchButton.setFont(new Font("Arial", Font.BOLD, 30));
		searchButton.setPreferredSize(new Dimension(200, 50));
		panel4gbc.gridx = 0;
		panel4gbc.gridy = 4;
		panel4.add(searchButton, panel4gbc);
		panel4gbc.gridx = 1;
		panel4gbc.gridy = 4;
		panel4.add(resetButton, panel4gbc);
	}

	/**
	 * Adds ActionListener to the Search and Reset buttons
	 */
	public void buttonWorking() {
		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ThrasholdField.setText("");
			}
		});

		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// query2Handler q2= new
				// query2Handler(Integer.parseInt(ThrasholdField.getText()));
			}
		});
	}

	/**
	 * Returns the Query 3Panel
	 * 
	 * @return JPanel panel4
	 */
	public JPanel getPanel() {
		return panel4;
	}
}
