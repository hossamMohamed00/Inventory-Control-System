package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class First_Frame extends JFrame implements ActionListener
{
    private JButton letsBegin = new JButton("Let's Begin");
    private JLabel headerLabel = new JLabel("Inventory Control System");
    private JLabel welcome_to_our_Project = new JLabel("Welcome To Our Simulation Project");
    private JPanel panel = new JPanel();

    public First_Frame()
    {
        //Confirmation Dialog When Close
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent evt){
                int x = JOptionPane.showConfirmDialog(null,"Are You Sure to Close the Program..?",
                        "Exit Program", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                if(x == JOptionPane.YES_OPTION) {
                    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                }else{
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        /*----------------*/

        this.setTitle("Inventory Control System"); ///jframe
        this.setSize(950, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocation(230, 10);
        this.setLayout(null);

        ////jpanel
        panel.setSize(950, 700);
        panel.setLayout(null);
        panel.setBackground(Color.darkGray);
        add(panel);

        ////jlabel
        headerLabel.setBounds(180, 80, 700, 50);
        headerLabel.setForeground (Color.WHITE);
        headerLabel.setFont (new Font(Font.MONOSPACED,Font.BOLD + Font.ITALIC,37));
        panel.add(headerLabel);

        ///JButton
        letsBegin.setBounds(320, 300, 270, 50);
        letsBegin.setBackground(Color.GRAY);
        letsBegin.setForeground(Color.WHITE);
        letsBegin.setFont(new Font(Font.MONOSPACED, Font.BOLD + Font.ITALIC, 29));

        panel.add(letsBegin);


        ///label welcome
        welcome_to_our_Project.setBounds(185, 500, 700, 40);
        welcome_to_our_Project.setForeground(Color.white);
        welcome_to_our_Project.setFont(new Font(Font.MONOSPACED,Font.BOLD + Font.ITALIC, 28));
        panel.add(welcome_to_our_Project);

        ///add actions
        letsBegin.addActionListener(this);
        this.setVisible(true);
    }

    //actions

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == letsBegin) {

            this.setVisible(false);
            GetDataFrame getDataFrame = new GetDataFrame();
        }

    }
}





