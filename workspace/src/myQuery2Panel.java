import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class myQuery2Panel
{
    private JButton resetButton,searchButton;
    private JPanel panel3=new JPanel(new GridBagLayout());
    private GridBagConstraints panel3gbc= new GridBagConstraints();
    private JTextField field= new JTextField();
    private JLabel text= new JLabel("Threshold");

    public myQuery2Panel()
    {
        panel3gbc.insets= new Insets(10,10,10,10);
        prepareGui();
        colorize();
        buttonWorking();
    }

    private void colorize()
    {
        text.setForeground(Color.cyan);
        text.setBackground(Color.gray);
        resetButton.setBackground(Color.cyan);
        searchButton.setBackground(Color.cyan);
        panel3.setBackground(Color.gray);
    }


    public void prepareGui()
    {
       
        panel3gbc.gridx=0;
        panel3gbc.gridy=0;
        text.setPreferredSize(new Dimension(100,25));
        text.setFont(new Font("Arial", Font.BOLD, 15));
        panel3.add(text,panel3gbc);
        
        panel3gbc.gridx=0;
        panel3gbc.gridy=1;
        panel3gbc.fill= GridBagConstraints.HORIZONTAL;
        field.setFont(new Font("Arial", Font.BOLD, 12));
        panel3.add(field,panel3gbc);
//        JLabel l1=new JLabel("      ");
//        JLabel l2=new JLabel("      ");
//        panel3gbc.gridy=2;
//        panel3.add(l1,panel3gbc);
//        panel3gbc.gridy=3;
//        panel3.add(l2,panel3gbc);
        resetButton=new JButton("Reset");
        resetButton.setBackground(Color.gray);
        resetButton.setFont(new Font("Arial", Font.BOLD, 12));
        resetButton.setPreferredSize(new Dimension(100,25));
        searchButton=new JButton("Search");
        searchButton.setBackground(Color.gray);
		searchButton.setFont(new Font("Arial", Font.BOLD, 12));
        searchButton.setPreferredSize(new Dimension(100,25));
        panel3gbc.gridx=0;
        panel3gbc.gridy=4;
        panel3.add(searchButton,panel3gbc);
        panel3gbc.gridx=1;
        panel3gbc.gridy=4;
        panel3.add(resetButton,panel3gbc);
    }

    public void buttonWorking()
    {
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                field.setText("");
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Query2Handler q2= new Query2Handler(Integer.parseInt(field.getText()));
            }
        });
    }

    public JPanel getPanel()
    {
        return panel3;
    }
}
