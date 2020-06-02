package GUI;

import Inventory_Control.Simulation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class getRestData extends JFrame implements ActionListener
{
    Simulation sim = GetDataFrame.getSavedData();
    int simDays = 0;
    int flage  = 0; ////to check if user input all data before press Go

    //Components
    JPanel panel = new JPanel ();
    JLabel headerLabel = new JLabel ("Rest Of Data");

    JLabel orderLabel = new JLabel ("Enter ORDER QUANTITY :");
    JLabel reorderLabel = new JLabel ("Enter REORDER_POINT :");
    JLabel beginningLabel = new JLabel ("Enter Beginning Inventory :");
    JLabel SimDayLabel = new JLabel ("Enter number simulated day :");

    JTextField ordertext = new JTextField ();
    JTextField reorderText = new JTextField ();
    JTextField beginningText = new JTextField ();
    JTextField SimDayText = new JTextField() ;

    JButton submitBtn = new JButton ("Submit");
    JButton NextBtn = new JButton ("Go");
    JButton backbutton = new JButton ("Back");

    public getRestData()
    {
        showFrame();
    }
    public void showFrame()
    {
        //Confirmation Dialog When Close
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent evt){
                int x = JOptionPane.showConfirmDialog(null,"You will lose all data,Are You Sure To Close Program..?",
                        "Exit Program", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                if(x == JOptionPane.YES_OPTION) {
                    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                }else{
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        /*----------------*/
        /*-----------------*/
        this.setSize(950,700);
        this.setTitle("Inventory Control Simulation");
        this.setLocation(230, 10);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        panel.setLayout(null);
        panel.setBounds(0,0,950,700);
        panel.setBackground(Color.DARK_GRAY);
        this.add(panel);

        headerLabel.setBounds (340,40,400,40);
        headerLabel.setForeground (Color.WHITE);
        headerLabel.setFont (new Font(Font.MONOSPACED,Font.BOLD + Font.ITALIC,35));
        panel.add (headerLabel);

        orderLabel.setBounds (50,130,300,30);
        orderLabel.setForeground(Color.WHITE);
        orderLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD+ Font.ITALIC, 20));
        panel.add (orderLabel);

        reorderLabel.setBounds (50,200,300,30);
        reorderLabel.setForeground(Color.WHITE);
        reorderLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD+ Font.ITALIC, 20));
        panel.add (reorderLabel);

        beginningLabel.setBounds (50,270,350,30);
        beginningLabel.setForeground(Color.WHITE);
        beginningLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD+ Font.ITALIC, 20));
        panel.add (beginningLabel);

        SimDayLabel.setBounds (50,340,400,30);
        SimDayLabel.setForeground(Color.WHITE);
        SimDayLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD+ Font.ITALIC, 20));
        panel.add (SimDayLabel);

        ordertext.setBounds (400,132,200,25);
        ordertext.setBackground (Color.lightGray);
        panel.add (ordertext);

        reorderText.setBounds (400,200,200,25);
        reorderText.setBackground (Color.lightGray);
        panel.add (reorderText);

        beginningText.setBounds (400,270,200,25);
        beginningText.setBackground (Color.lightGray);
        panel.add (beginningText);

        SimDayText.setBounds (400,343,200,25);
        SimDayText.setBackground (Color.lightGray);
        panel.add (SimDayText);



        //Buttons
        submitBtn.setBounds(390, 420, 150, 40);
        submitBtn.setBackground(Color.GRAY);
        submitBtn.setForeground(Color.WHITE);
        submitBtn.setFont(new Font(Font.MONOSPACED, Font.BOLD + Font.ITALIC, 22));
        panel.add (submitBtn);

        NextBtn.setBounds(750, 600, 150, 40);
        NextBtn.setBackground(Color.GRAY);
        NextBtn.setForeground(Color.WHITE);
        NextBtn.setFont(new Font(Font.MONOSPACED, Font.BOLD + Font.ITALIC, 22));
        panel.add (NextBtn);

        backbutton.setBounds(30, 600, 150, 40);
        backbutton.setBackground(Color.GRAY);
        backbutton.setForeground(Color.WHITE);
        backbutton.setFont(new Font(Font.MONOSPACED, Font.BOLD + Font.ITALIC, 22));
        panel.add (backbutton);

        NextBtn.addActionListener (this);
        backbutton.addActionListener (this);
        submitBtn.addActionListener (this);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        if(e.getSource ()  == submitBtn)
        {
            if(!ordertext.getText ().equals ("") && ordertext.getText ().matches ("[0-9]+") &&!reorderText.getText ().equals ("") && reorderText.getText ().matches("[0-9]+")
                    &&!beginningText.getText ().equals ("") && beginningText.getText ().matches ("[0-9]+")  && !SimDayText.getText ().equals ("") && SimDayText.getText ().matches ("[0-9]+")   )
            {
                /*-------------**/
                //get Data
                int OrderQuantity = Integer.parseInt(ordertext.getText());
                int ReorderPoint = Integer.parseInt(reorderText.getText());
                int BiginningInventory = Integer.parseInt(beginningText.getText());
                simDays = Integer.parseInt(SimDayText.getText());

                sim.orderQuantity = OrderQuantity;
                sim.ReOrderPoint = ReorderPoint;
                sim.Biginning = BiginningInventory;
                sim.simDays = simDays;
                /*--------------------*/
                sim.initializeTableArrays(simDays);
                sim.setDays();//to set the days column
                /*------------------*/

                //set the Random numbers
                sim.generateRandomNumbers(simDays);
                //this means the user enter all fields successfully
                flage = 1;

                JOptionPane.showMessageDialog (null,"Data Added Successfully","Done!",JOptionPane.INFORMATION_MESSAGE);

            }
            else
            {
                JOptionPane.showMessageDialog (null,"Invalid input or Missing required Fields ... !  Please, complete them before submit ...!","Failed",JOptionPane.ERROR_MESSAGE);
            }
        }

        else if(e.getSource() == NextBtn)
        {
            if(flage == 1 )
            {
                this.setVisible(false);
                sim.setSimulatedDemand();
                sim.Start_Simulation();
                sim.ComputeAverages();

                Final_Table final_table = new Final_Table();
            }
            else
            {
                JOptionPane.showMessageDialog (null,"Please Complete All Inputs First ","Alert",JOptionPane.WARNING_MESSAGE);
            }
        }
        else if(e.getSource() == backbutton)
        {
            this.setVisible(false);
            LeadTimeData leadTimeData = new LeadTimeData();
        }
    }
}
