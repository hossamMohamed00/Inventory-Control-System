package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Final_Frame   extends JFrame implements ActionListener
{
    JLabel headerLabel = new JLabel("The End");
    JLabel Message = new JLabel("- Now you have done your Simulation..Choose one of this actions : ");
    JLabel gotoFinalTable = new JLabel("- Go To Final Table press this button");
    JLabel simulate_another = new JLabel("- Simulate another problem press this button");
    JLabel ExitLabel = new JLabel("- Exit.");
    JLabel ThanksLabel = new JLabel(" Thanks For Using Our Simple Program");
    JButton finalTableBtn = new JButton ("Final table");
    JButton simAnother = new JButton ("Simulate Now");
    JButton ExitBTN = new JButton ("Exit");

    private JPanel panel = new JPanel();

    public Final_Frame(){

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
        headerLabel.setBounds(350, 60, 700, 50);
        headerLabel.setForeground (Color.WHITE);
        headerLabel.setFont (new Font(Font.MONOSPACED,Font.BOLD + Font.ITALIC,40));
        panel.add(headerLabel);

        Message.setBounds(20, 150, 900, 50);
        Message.setForeground (Color.WHITE);
        Message.setFont (new Font(Font.MONOSPACED,Font.BOLD + Font.ITALIC,20));
        panel.add(Message);

        gotoFinalTable.setBounds(40, 200, 700, 50);
        gotoFinalTable.setForeground (Color.WHITE);
        gotoFinalTable.setFont (new Font(Font.MONOSPACED,Font.BOLD + Font.ITALIC,20));
        panel.add(gotoFinalTable);


        simulate_another.setBounds(40, 250, 700, 50);
        simulate_another.setForeground (Color.WHITE);
        simulate_another.setFont (new Font(Font.MONOSPACED,Font.BOLD + Font.ITALIC,20));
        panel.add(simulate_another);

        ExitLabel.setBounds(40, 300, 700, 50);
        ExitLabel.setForeground (Color.WHITE);
        ExitLabel.setFont (new Font(Font.MONOSPACED,Font.BOLD + Font.ITALIC,20));
        panel.add(ExitLabel);

        finalTableBtn.setBounds(600, 210, 220, 32);
        finalTableBtn.setBackground(Color.GRAY);
        finalTableBtn.setForeground(Color.WHITE);
        finalTableBtn.setFont(new Font(Font.MONOSPACED, Font.BOLD + Font.ITALIC, 22));
        panel.add(finalTableBtn);

        simAnother.setBounds(600, 260, 220, 32);
        simAnother.setBackground(Color.GRAY);
        simAnother.setForeground(Color.WHITE);
        simAnother.setFont(new Font(Font.MONOSPACED, Font.BOLD + Font.ITALIC, 22));
        panel.add(simAnother);

        ExitBTN.setBounds(200, 310, 220, 32);
        ExitBTN.setBackground(Color.GRAY);
        ExitBTN.setForeground(Color.WHITE);
        ExitBTN.setFont(new Font(Font.MONOSPACED, Font.BOLD + Font.ITALIC, 22));
        panel.add(ExitBTN);

        ThanksLabel.setBounds(215, 500, 600, 32);
        ThanksLabel.setBackground(Color.GRAY);
        ThanksLabel.setForeground(Color.WHITE);
        ThanksLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD + Font.ITALIC, 23));
        panel.add(ThanksLabel);


        ExitBTN.addActionListener(this);
        simAnother.addActionListener(this);
        finalTableBtn.addActionListener(this);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        if(actionEvent.getSource() == finalTableBtn)
        {
            this.setVisible(false);
            Final_Table final_table = new Final_Table();
        }
        else if(actionEvent.getSource()  == simAnother)
        {
            this.setVisible(false);
            First_Frame first_frame = new First_Frame();
        }
        else if(actionEvent.getSource()  == ExitBTN)
        {
            int x = JOptionPane.showConfirmDialog(null,"You will lose your last simulation,Are You Sure to Close the Program..?",
                    "Exit Program", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if(x == JOptionPane.YES_OPTION)
            {
                System.exit(0); // stop program
                this.dispose(); // close window
                this.setVisible(false); // hide window
            }

        }

    }
}
