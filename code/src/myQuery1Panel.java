/**
 * Query 1 = Search by Name and Title
 * GUI Panel
 * @author Mridul Gupta | Divyanshu Talwar
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
public class myQuery1Panel {
	protected JPanel panel2=new JPanel(new GridBagLayout()); /**< New GridBag layout for Query1 Panel */ 
    private GridBagConstraints panel2gbc= new GridBagConstraints(); /**< New GridBag Constraints */ 
    private JLabel sinceYear; /**< JLabel for "Since" year */ 
    private JLabel from; /**< JLabel for "From" year */ 
    private JLabel to; /**< JLabel for "To" year */ 
    protected JButton resetButton; /**< Button to Reset */
    protected JButton searchButton; /**< Button to start search */
    protected JTextField sinceYearTextField; /**< JTextField for "Since" year */ 
    protected JTextField fromTextField; /**< JTextField for "Since" year */ 
    protected JTextField toTextField; /**< JTextField for "Since" year */ 
    protected JTextField nameTitleTextField; /**< JTextField for "Name/Title Search" */
    protected JComboBox yearCombo; /**< JComboBox for "Since/ FromTo" year */
    protected JComboBox searchByCombo; /**< JComboBox for "Year/Relevance" search */
    protected Checkbox chkSortByYear; /**< Checkbox for "Year/Relevance" search */
    protected Checkbox chkSortByRelevance; /**< Checkbox for "Year/Relevance" search */
    protected CheckboxGroup sort; /**< Checkbox for sort */
	/**
	 * Constructor. Setting up GUI. Preparing buttons
	 */
	public myQuery1Panel() {
		panel2gbc.insets = new Insets(5, 5, 5, 5);
		prepareSearchByComboBox();
		prepareYearSearchComboBox();
		prepareCheckBoxUI();
		prepareButtons();
		colorize();
	}
	/**
	 * Sets up colors and opacity for panel
	 * 
	 */
	private void colorize() {
		panel2.setOpaque(false);
	}
	/**
	 * Method to prepare buttons. Set font and size and text fields
	 * 
	 */
	public void prepareButtons() {
		resetButton = new JButton("Reset");
		resetButton.setFont(new Font("Arial", Font.BOLD, 12));
		resetButton.setPreferredSize(new Dimension(100, 25));
		searchButton = new JButton("Search");
		searchButton.setFont(new Font("Arial", Font.BOLD, 12));
		searchButton.setPreferredSize(new Dimension(100, 25));
		panel2gbc.gridx = 0; panel2gbc.gridy = 8;
		panel2.add(searchButton, panel2gbc);
		panel2gbc.gridx = 1; panel2gbc.gridy = 8;
		panel2.add(resetButton, panel2gbc);
	}

	/**
	 * Method to prepare Checkbox UI for Sort
	 * 
	 */
	private void prepareCheckBoxUI() {
		panel2gbc.gridy = 4; panel2gbc.gridx = 0;
		panel2.add(new JLabel("         "), panel2gbc);
		panel2gbc.gridy = 5;
		panel2.add(new JLabel("         "), panel2gbc);
		sort = new CheckboxGroup();
		chkSortByYear = new Checkbox("Sort By Year", sort, false);
		chkSortByYear.setFont(new Font("Arial", Font.BOLD, 12));
		chkSortByYear.setPreferredSize(new Dimension(150, 25));
		chkSortByRelevance = new Checkbox("Sort By Relevence", sort, false);
		chkSortByRelevance.setFont(new Font("Arial", Font.BOLD, 12));
		chkSortByRelevance.setPreferredSize(new Dimension(150, 25));
		panel2gbc.gridwidth = 2;
		panel2gbc.gridx = 0; panel2gbc.gridy = 6;
		panel2.add(chkSortByYear, panel2gbc);
		panel2gbc.gridx = 0; panel2gbc.gridy = 7;
		panel2.add(chkSortByRelevance, panel2gbc);
		panel2gbc.gridwidth = 1;
	}
	/**
	 * Combobox for Year Select
	 * 
	 */
	private void prepareYearSearchComboBox() {
		final DefaultComboBoxModel<String> yearSelect = new DefaultComboBoxModel();
		yearSelect.addElement("Year Select");
		yearSelect.addElement("Since Year");
		yearSelect.addElement("Custom Range");
		yearCombo = new JComboBox(yearSelect);
		yearCombo.setSelectedIndex(0);
		yearCombo.setPreferredSize(new Dimension(300, 25));
		yearCombo.setFont(new Font("Arial", Font.BOLD, 12));
		panel2gbc.fill = GridBagConstraints.HORIZONTAL;
		panel2gbc.gridx = 0; panel2gbc.gridy = 2;
		panel2.add(yearCombo, panel2gbc);
		YearSearchUI();
		yearCombo.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				Object temp = yearCombo.getSelectedIndex();
				if (temp.equals(1)) {
					setVisibilityFields(true,true,false,false,false,false);
				} else if (temp.equals(0)) {
					setVisibilityFields(false,false,false,false,false,false);
				} else if (temp.equals(2)) {
					setVisibilityFields(false,false,true,true,true,true);
				}
			}
		});
	}
	/**
	 * Setting Visibility fields based on combobox option selected
	 * 
	 */
	public void setVisibilityFields(boolean a, boolean b, boolean c, boolean d, boolean e, boolean f){
		sinceYear.setVisible(a);
		sinceYearTextField.setVisible(b);
		from.setVisible(c);
		fromTextField.setVisible(d);
		to.setVisible(e);
		toTextField.setVisible(f);
	}
	/**
	 * Combobox for Year Search. Textfield set up.
	 * 
	 */
	private void YearSearchUI() {
		sinceYear = new JLabel("From");
		sinceYearTextField = new JTextField("");
		sinceYearTextField.setPreferredSize(new Dimension(100, 25));
		sinceYear.setPreferredSize(new Dimension(100, 25));
		sinceYear.setFont(new Font("Arial", Font.BOLD, 12));
		sinceYearTextField.setFont(new Font("Arial", Font.BOLD, 12));
		panel2gbc.gridx = 0; panel2gbc.gridy = 3;
		panel2.add(sinceYear, panel2gbc);
		panel2gbc.gridx = 1; panel2gbc.gridy = 3;
		panel2.add(sinceYearTextField, panel2gbc);
		sinceYear.setVisible(false);
		sinceYearTextField.setVisible(false);
		from = new JLabel("From");
		fromTextField = new JTextField("");
		fromTextField.setPreferredSize(new Dimension(100, 25));
		from.setPreferredSize(new Dimension(100, 25));
		from.setFont(new Font("Arial", Font.BOLD, 12));
		fromTextField.setFont(new Font("Arial", Font.BOLD, 12));
		panel2gbc.gridx = 0; panel2gbc.gridy = 3;
		panel2.add(from, panel2gbc);
		panel2gbc.gridx = 1;
		panel2.add(fromTextField, panel2gbc);
		from.setVisible(false);
		fromTextField.setVisible(false);
		to = new JLabel("To");
		toTextField = new JTextField("");
		toTextField.setPreferredSize(new Dimension(100, 25));
		to.setPreferredSize(new Dimension(100, 25));
		to.setFont(new Font("Arial", Font.BOLD, 12));
		toTextField.setFont(new Font("Arial", Font.BOLD, 12));
		panel2gbc.gridx = 0; panel2gbc.gridy = 4;
		panel2.add(to, panel2gbc);
		panel2gbc.gridx = 1; panel2gbc.gridy = 4;
		panel2.add(toTextField, panel2gbc);
		to.setVisible(false);
		toTextField.setVisible(false);
	}
	/**
	 * Combobox for Search by Name/Title.
	 * 
	 */
	private void prepareSearchByComboBox() {
		final DefaultComboBoxModel<String> searchBy = new DefaultComboBoxModel();
		searchBy.addElement("Search By");
		searchBy.addElement("Name");
		searchBy.addElement("Title");
		searchByCombo = new JComboBox(searchBy);
		searchByCombo.setSelectedIndex(0);
		searchByCombo.setPreferredSize(new Dimension(100, 25));
		searchByCombo.setFont(new Font("Arial", Font.BOLD, 12));
		panel2gbc.gridx = 0;
		panel2gbc.gridy = 0;
		panel2gbc.fill = GridBagConstraints.HORIZONTAL;
		panel2.add(searchByCombo, panel2gbc);
		JLabel nameTitleLabel = new JLabel("Name/Title");
		nameTitleTextField = new JTextField("");
		nameTitleTextField.setPreferredSize(new Dimension(100, 25));
		nameTitleLabel.setPreferredSize(new Dimension(100, 25));
		nameTitleLabel.setFont(new Font("Arial", Font.BOLD, 12));
		nameTitleTextField.setFont(new Font("Arial", Font.BOLD, 12));
		panel2gbc.gridy = 1;
		panel2.add(nameTitleLabel, panel2gbc);
		panel2gbc.gridx = 1;
		panel2.add(nameTitleTextField, panel2gbc);
	}
}
