package GUI;

import Inventory_Control.Simulation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class GetDataFrame extends JFrame implements ActionListener
{
    static  Simulation sim = new Simulation();

    int DemandCount = 0, LDcount = 0;//it's just flags
    int DemandNum = 0;
    int leadTimeNum = 0;
    int[] demandFreq;//array to save on it the freqs
    int[] Orderfreq;//array to save on it the freqs
    int flage = 0;//to check if user input all data before press next
/*---------------------------------------*/

    private JLabel DataForDemand = new JLabel("* First Enter Data For Demand : ");

    private JLabel enterNumberDemand = new JLabel("* Enter the number of demand : ");
    private JTextField NumberOfDemand = new JTextField ();

    private JLabel enterDemandFreq = new JLabel("* Enter Demand Frequencies : ");
    JTextField DemandFreq = new JTextField ();
/*-------------*/

    private JLabel DataForLeadTime = new JLabel("* Second Enter Data For  Lead Time : ");

    private JLabel enterNumberLeadTime = new JLabel("* Enter number of Lead Time (Days) :");
    private JTextField NumbterOFLeadTime = new JTextField ();

    private JLabel enterleadTimeFreq = new JLabel("* Enter LD Frequencies (Orders) :");
    JTextField LeadTimeFreq = new JTextField ();
/*--------*/

    private JButton demandSubmit = new JButton("Submit ");
    private JButton AddDemandFreq = new JButton("Add ");
    private JButton AddLDFreq = new JButton("Add ");
    private JButton leadTimeSubmit = new JButton("Submit ");
    private JButton NextBtn = new JButton("Next ");

    private JPanel panel = new JPanel();

    public GetDataFrame()
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

        /*------------------------------------------------*/

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
        DataForDemand.setBounds(10, 50, 800, 50);
        DataForDemand.setFont(new Font(Font.MONOSPACED, Font.BOLD + Font.ITALIC, 27));
        DataForDemand.setForeground(Color.WHITE);
        panel.add(DataForDemand);

        enterNumberDemand.setBounds(30, 95, 600, 50);
        enterNumberDemand.setForeground(Color.WHITE);
        enterNumberDemand.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 23));
        panel.add(enterNumberDemand);

        NumberOfDemand.setBounds(450,106,100,28);
        NumberOfDemand.setBackground(Color.lightGray);
        panel.add(NumberOfDemand);

        ///JButton
        demandSubmit.setBounds(600, 106, 220, 30);
        demandSubmit.setBackground(Color.GRAY);
        demandSubmit.setForeground(Color.WHITE);
        demandSubmit.setFont(new Font(Font.MONOSPACED, Font.BOLD + Font.ITALIC, 22));

        panel.add(demandSubmit);

        enterDemandFreq.setBounds(30, 160, 600, 50);
        enterDemandFreq.setForeground(Color.WHITE);
        enterDemandFreq.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 23));
        panel.add(enterDemandFreq);

        DemandFreq.setBounds(450,170,100,28);
        DemandFreq.setBackground(Color.lightGray);
        panel.add(DemandFreq);

        ///JButton
        AddDemandFreq.setBounds(600, 170, 220, 30);
        AddDemandFreq.setBackground(Color.GRAY);
        AddDemandFreq.setForeground(Color.WHITE);
        AddDemandFreq.setFont(new Font(Font.MONOSPACED, Font.BOLD + Font.ITALIC, 22));

        panel.add(AddDemandFreq);
        /*-----------------------------------*/
        //SecondData
        DataForLeadTime.setBounds(10, 290, 800, 50);
        DataForLeadTime.setFont(new Font(Font.MONOSPACED, Font.BOLD + Font.ITALIC, 24));
        DataForLeadTime.setForeground(Color.WHITE);
        panel.add(DataForLeadTime);

        enterNumberLeadTime.setBounds(30, 335, 600, 50);
        enterNumberLeadTime.setForeground(Color.WHITE);
        enterNumberLeadTime.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 23));
        panel.add(enterNumberLeadTime);

        NumbterOFLeadTime.setBounds(550,352,100,28);
        NumbterOFLeadTime.setBackground(Color.lightGray);
        panel.add(NumbterOFLeadTime);

        ///JButton
        leadTimeSubmit.setBounds(670, 352, 220, 30);
        leadTimeSubmit.setBackground(Color.GRAY);
        leadTimeSubmit.setForeground(Color.WHITE);
        leadTimeSubmit.setFont(new Font(Font.MONOSPACED, Font.BOLD + Font.ITALIC, 22));
        panel.add(leadTimeSubmit);
        /*-------*/
        enterleadTimeFreq.setBounds(30, 385, 600, 50);
        enterleadTimeFreq.setForeground(Color.WHITE);
        enterleadTimeFreq.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 23));
        panel.add(enterleadTimeFreq);

        LeadTimeFreq.setBounds(550,398,100,28);
        LeadTimeFreq.setBackground(Color.lightGray);
        panel.add(LeadTimeFreq);

        //JButton

        AddLDFreq.setBounds(670, 398, 220, 30);
        AddLDFreq.setBackground(Color.GRAY);
        AddLDFreq.setForeground(Color.WHITE);
        AddLDFreq.setFont(new Font(Font.MONOSPACED, Font.BOLD + Font.ITALIC, 22));
        panel.add(AddLDFreq);

        NextBtn.setBounds(340, 530, 220, 40);
        NextBtn.setBackground(Color.GRAY);
        NextBtn.setForeground(Color.WHITE);
        NextBtn.setFont(new Font(Font.MONOSPACED, Font.BOLD + Font.ITALIC, 22));
        panel.add(NextBtn);

        ///add actions
        demandSubmit.addActionListener(this);
        AddDemandFreq.addActionListener(this);
        leadTimeSubmit.addActionListener(this);
        AddLDFreq.addActionListener(this);
        NextBtn.addActionListener(this);

        this.setVisible(true);
    }

    //actions

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == demandSubmit)
        {
            if(!NumberOfDemand.getText ().equals ("") && NumberOfDemand.getText ().matches ("[0-9]+"))
            {
                 DemandNum = Integer.parseInt (NumberOfDemand.getText ());
                 Simulation.numberOfACEDRILL = DemandNum;
                 sim.initializeDemandData(DemandNum);
                 sim.setDemands();
                 JOptionPane.showMessageDialog (null,"Now,You Must Enter " + DemandNum + " Frequencies Only","Information",JOptionPane.INFORMATION_MESSAGE);

                demandFreq = new int[DemandNum];

                flage = 1;
            }
            else
            {
                JOptionPane.showMessageDialog (null,"Please Enter Valid Input ","Failed",JOptionPane.ERROR_MESSAGE);

            }
        }
        else if(e.getSource () == AddDemandFreq)
        {
            if(!DemandFreq.getText ().equals ("") && DemandFreq.getText ().matches ("[0-9]+"))
            {
                if(DemandCount < DemandNum )
                {

                     demandFreq[DemandCount] =  Integer.parseInt(DemandFreq.getText());

                    if(DemandCount == DemandNum-1)
                    {
                        sim.setDemandFrequency(demandFreq);
                        JOptionPane.showMessageDialog (null,"You Entered All The Frequencies ","Information",JOptionPane.INFORMATION_MESSAGE);
                        flage += 1;
                    }
                    DemandCount++;
                    DemandFreq.setText("");
                }
                else
                {
                    JOptionPane.showMessageDialog (null,"You already Entered All The Frequencies ","Information",JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else
            {
                JOptionPane.showMessageDialog (null,"Please Enter Valid Input ","Failed",JOptionPane.ERROR_MESSAGE);

            }
        }

        else if(e.getSource () == leadTimeSubmit)
        {
            if(!NumbterOFLeadTime.getText ().equals ("") && NumbterOFLeadTime.getText ().matches ("[0-9]+"))
            {
                leadTimeNum = Integer.parseInt (NumbterOFLeadTime.getText ());
                Simulation.numberOfLeadTime = leadTimeNum;
                sim.initializeLDData(leadTimeNum);
                sim.setLeadTimeDays();
                JOptionPane.showMessageDialog (null,"Now,You Must Enter " + leadTimeNum + " Frequencies Only","Information",JOptionPane.INFORMATION_MESSAGE);

                Orderfreq = new int[leadTimeNum];

                flage += 1;
            }
            else
            {
                JOptionPane.showMessageDialog (null,"Please Enter Valid Input ","Failed",JOptionPane.ERROR_MESSAGE);

            }
        }
        else if(e.getSource () == AddLDFreq)
        {
            if(!LeadTimeFreq.getText ().equals ("") && LeadTimeFreq.getText ().matches ("[0-9]+"))
            {
                if(LDcount < leadTimeNum )
                {
                    Orderfreq[LDcount] =  Integer.parseInt(LeadTimeFreq.getText());

                    if(LDcount == leadTimeNum-1)
                    {
                        sim.setOrderFrequency(Orderfreq);
                        JOptionPane.showMessageDialog (null,"You Entered All The Frequencies ","Information",JOptionPane.INFORMATION_MESSAGE);
                        flage += 1;
                    }
                    LDcount++;
                    LeadTimeFreq.setText("");
                }
                else
                {
                    JOptionPane.showMessageDialog (null,"You already Entered All The Frequencies ","Information",JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else
            {
                JOptionPane.showMessageDialog (null,"Please Enter Valid Input ","Failed",JOptionPane.ERROR_MESSAGE);

            }
        }
        else if (e.getSource() == NextBtn)
        {
            if(flage == 4) {


                //set the Data of the Demands
                sim.setSumOfFreq();//to Calculate the sum of freq
                sim.CalcProbabilities(sim.demandsProbabilities, sim.demandFrequency, sim.sumOfFrequencies);
                sim.CalcCumulative(sim.cumulativeProbabilities, sim.demandsProbabilities);
                sim.setInterval(sim.first_interval_demands, sim.end_interval_demands, sim.cumulativeProbabilities);

                //set  the data of Lead Time
                sim.setSumOfLDFreq();//to Calculate the sum of freq
                sim.CalcProbabilities(sim.orderProbabilities, sim.orderFrequency, sim.sumOfOrderFrequencies);
                sim.CalcCumulative(sim.orderCumulative, sim.orderProbabilities);
                sim.setInterval(sim.first_interval_LeadTime, sim.end_interval_LeadTime, sim.orderCumulative);

                //navigate
                this.setVisible(false);
                DemandData demandData = new DemandData();
            }
            else
            {
                JOptionPane.showMessageDialog (null,"Please Complete All Inputs First.. ","Alert",JOptionPane.WARNING_MESSAGE);

            }
        }
    }
    public static Simulation getSavedData()
    {
        return sim;//to return the object with all data
    }
}