import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
        heading.setFont(new Font("Serif", Font.BOLD, 30));
//        heading.setBackground(Color.black);
        heading.setForeground(Color.cyan);
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
        framegbc.fill= GridBagConstraints.BOTH;
        framegbc.gridwidth=10;
        framegbc.gridheight=20;
	    framegbc.weightx = 100;
	    framegbc.weighty = 100;
        framegbc.gridx=1;
        framegbc.gridy=1;
        frame.add(pane,framegbc);

       /* bar = new JProgressBar(0, 1523000);
        bar.setValue(0);
        bar.setStringPainted(true);
        framegbc.gridwidth=5;
        framegbc.gridheight=1;
        framegbc.gridx=0;
        framegbc.gridy=2;
        frame.add(bar,framegbc);*/


        frame.getContentPane().setBackground(Color.GRAY);
        frame.setSize(1800,1500);
        frame.setVisible(true);
    }

    public static JProgressBar getBar()
    {
        return bar;
    }


}
