package GUI;

import Inventory_Control.Simulation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Final_Table extends JFrame implements ActionListener {
    Simulation sim = GetDataFrame.getSavedData();

    JButton nextBtn = new JButton("Next");
    JButton BackBtn = new JButton("Back");
    JPanel panel = new JPanel();
    JLabel headerLabel = new JLabel("Inventory Simulation - Final Table");

    JLabel InformationLabel = new JLabel("* Important Information *");

    JLabel AverageEndingLabel = new JLabel("Average ending inventory  :");
    JLabel AverageEnding = new JLabel("1");
    JLabel AverageLostSalesLabel = new JLabel("Average of the  lost sales : ");
    JLabel AverageLostSales = new JLabel("1");
    JLabel AverageNumOrderLabel = new JLabel("Average number of orders placed : ");
    JLabel AverageNumOrder = new JLabel("1");


    public Final_Table() {

        Show_Table();
    }

    public void Show_Table() {

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
        String[] column = {"Day","UNITS RECEIVED","BEGINNING INVENTORY","RANDOM NUMBER","DEMAND","ENDING INVENTORY", "LOST SALES", "ORDER", "RANDOM NUMBER" , "LEAD TIME"};

        JTable table = new JTable ();

        DefaultTableModel tableModel = new DefaultTableModel (column,0);
        setSize(950,700);

        setLocation(230,10);
        setTitle ("Final Table Data ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        panel.setLayout(null);
        panel.setBackground(Color.DARK_GRAY);
        panel.setBounds(0,0,950,700);
        add(panel);

        headerLabel.setBounds (180,20,850,35);
        headerLabel.setForeground (Color.WHITE);
        headerLabel.setFont (new Font(Font.MONOSPACED,Font.BOLD,28));
        panel.add (headerLabel);

        nextBtn.setBounds(750, 600, 150, 40);
        nextBtn.setBackground(Color.GRAY);
        nextBtn.setForeground(Color.WHITE);
        nextBtn.setFont(new Font(Font.MONOSPACED, Font.BOLD + Font.ITALIC, 22));
        panel.add (nextBtn);

        BackBtn.setBounds(30, 600, 150, 40);
        BackBtn.setBackground(Color.GRAY);
        BackBtn.setForeground(Color.WHITE);
        BackBtn.setFont(new Font(Font.MONOSPACED, Font.BOLD + Font.ITALIC, 22));
        panel.add (BackBtn);

        Object [] rowData = new Object[10];
        {

            rowData[0] = "Day";
            rowData[1] = "UNITS RECEIVED";
            rowData[2] = "BEGINNING INVENTORY";
            rowData[3] = "RANDOM NUMBER";
            rowData[4] = "DEMAND";
            rowData[5] = "ENDING INVENTORY";
            rowData[6] = "LOST SALES";
            rowData[7] = "ORDER";
            rowData[8] = "RANDOM NUMBER";
            rowData[9] = "LEAD TIME";
            tableModel.addRow (rowData);
        }

        for(int i = 0; i < sim.simDays; i++)
        {

            rowData[0] = sim.days[i];
            rowData[1] = sim.UNITS_RECEIVED[i];
            rowData[2] = sim.BEGINNING[i];;
            rowData[3] = sim.Ran_Num_for_demand[i];
            rowData[4] = sim.simDemands[i];
            rowData[5] = sim.ENDING[i];
            rowData[6] = sim.LOST_SALES[i];
            rowData[7] = sim.ORDER[i];
            rowData[8] = sim.Random_LeadTime[i];
            rowData[9] = sim.LEADTIME[i];
            tableModel.addRow (rowData);
        }

        table.setModel (tableModel);
        table.setBackground (Color.LIGHT_GRAY);
        this.setLocationRelativeTo (null);
        table.setBounds (0,80,940,275);
        panel.add (table);
        /*-------------------*/
        //Averages
        InformationLabel.setBounds (260,370,450,30);
        InformationLabel.setForeground(Color.WHITE);
        InformationLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD + Font.ITALIC, 28));
        panel.add (InformationLabel);

        AverageEndingLabel.setBounds (30,410,500,30);
        AverageEndingLabel.setForeground(Color.WHITE);
        AverageEndingLabel.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 20));
        panel.add (AverageEndingLabel);

        AverageEnding.setBounds (390,410,300,30);
        AverageEnding.setForeground(Color.WHITE);
        AverageEnding.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        AverageEnding.setText(String.valueOf(sim.Average_ending_inventory));
        panel.add (AverageEnding);

        AverageLostSalesLabel.setBounds (30,470,500,30);
        AverageLostSalesLabel.setForeground(Color.white);
        AverageLostSalesLabel.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 20));
        panel.add (AverageLostSalesLabel);

        AverageLostSales.setBounds (390,470,300,30);
        AverageLostSales.setForeground(Color.WHITE);
        AverageLostSales.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        AverageLostSales.setText(String.valueOf(sim.Average_lost_sales));
        panel.add (AverageLostSales);


        AverageNumOrderLabel.setBounds (30,530,500,30);
        AverageNumOrderLabel.setForeground(Color.WHITE);
        AverageNumOrderLabel.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 20));
        panel.add (AverageNumOrderLabel);

        AverageNumOrder.setBounds (450,530,400,30);
        AverageNumOrder.setForeground(Color.WHITE);
        AverageNumOrder.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        AverageNumOrder.setText(String.valueOf(sim.Average_number_of_orders_placed));
        panel.add (AverageNumOrder);


        //actions
        nextBtn.addActionListener (this);
        BackBtn.addActionListener (this);
        setVisible (true);
    }
    @Override
    public void actionPerformed (ActionEvent e) {
        if(e.getSource () == nextBtn)
        {
            this.setVisible(false);
            Daily_Cost daily_cost = new Daily_Cost();

        }
        else if (e.getSource() == BackBtn)
        {
            int x = JOptionPane.showConfirmDialog(null,"You will lose this data if you back ,Are You Sure To Back?",
                    "Alert", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if(x == JOptionPane.YES_OPTION)
            {
                getContentPane().setVisible(false);
                getRestData getRestData = new getRestData();
            }

        }
    }

}