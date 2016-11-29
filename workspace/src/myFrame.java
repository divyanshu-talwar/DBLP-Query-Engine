/**
 *
 * @author Mridul Gupta | Divyanshu Talwar
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class myFrame extends JFrame
{
    private JFrame frame;
    private JLabel heading;
    private static JProgressBar bar;


    public myFrame(myPanel panel)
    {

        frame=new JFrame();
        frame.setLayout(new GridBagLayout());
        GridBagConstraints framegbc=new GridBagConstraints();
        framegbc.insets=new Insets(10,10,20,40);

        framegbc.fill= GridBagConstraints.HORIZONTAL;
        framegbc.gridwidth = GridBagConstraints.REMAINDER;
        heading=new JLabel("DBLP Query Engine", SwingConstants.CENTER);
		heading.setFont(new Font("Arial", Font.BOLD, 30));
//        heading.setBackground(Color.black);
//        heading.setForeground(Color.cyan);
        framegbc.gridx=0;
        framegbc.gridy=0;
//        framegbc.gridwidth=5;
        frame.add(heading,framegbc);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });

        framegbc.fill= GridBagConstraints.VERTICAL;
        framegbc.gridwidth=1;
        framegbc.weighty = 100;
        framegbc.weightx = 10;
        framegbc.gridx=0;
        framegbc.gridy=1;
        frame.add(panel.getPanel(),framegbc);
        
        JScrollPane pane= new ResultPanel().getPane();
        pane.setPreferredSize(new Dimension(800,800));
        
        final JButton next = new JButton("next");
        final JButton prev = new JButton("prev");
        ActionListener al = new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                Rectangle rect = pane.getVisibleRect();
                JScrollBar  bar = pane.getVerticalScrollBar();
                int blockIncr = pane.getViewport().getViewRect().height;
                if (e.getSource() == next) {
                    bar.setValue(bar.getValue() + blockIncr);
                } else if (e.getSource() == prev) {
                    bar.setValue(bar.getValue() - blockIncr);
                }
                pane.scrollRectToVisible(rect);
            }
        };

        next.addActionListener(al);
        prev.addActionListener(al);

        JPanel Rpanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(prev);
        buttonPanel.add(next);
        Rpanel.add(panel.statusBar, BorderLayout.NORTH);
        Rpanel.add(buttonPanel, BorderLayout.SOUTH);
        Rpanel.add(pane, BorderLayout.CENTER);
        framegbc.fill= GridBagConstraints.BOTH;
        framegbc.gridwidth=10;
        framegbc.gridheight=20;
	    framegbc.weightx = 100;
	    framegbc.weighty = 100;
        framegbc.gridx=1;
        framegbc.gridy=1;
        frame.add(Rpanel,framegbc);

       /* bar = new JProgressBar(0, 1523000);
        bar.setValue(0);
        bar.setStringPainted(true);
        framegbc.gridwidth=5;
        framegbc.gridheight=1;
        framegbc.gridx=0;
        framegbc.gridy=2;
        frame.add(bar,framegbc);*/


//        frame.getContentPane().setBackground(Color.GRAY);
        frame.setSize(1800,1500);
        frame.setVisible(true);
    }

    public static JProgressBar getBar()
    {
        return bar;
    }


}
