package GUI;

import Inventory_Control.Simulation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LeadTimeData extends JFrame implements ActionListener {

        Simulation sim = GetDataFrame.getSavedData();

        JButton nextBtn =  new JButton("Next");
        JButton BackBtn =  new JButton("Back");
        JPanel panel = new JPanel();
        JLabel headerLabel = new JLabel ("Probabilities and Random Number Intervals for Reorder Lead Time\n");

        public LeadTimeData () {

            Show_Table();
        }

        public void Show_Table ()
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
            /*----------------*/

            String[] column = {"LEAD TIME (DAYS)","FREQUENCY (ORDERS)","PROBABILITY","CUMULATIVE PROBABILITY","INTERVAL OF RANDOM NUMBER"};

            JTable table = new JTable ();

            DefaultTableModel tableModel = new DefaultTableModel (column,0);
            setSize(950,700);

            this.setLocation(230, 10);
            setTitle ("Lead Time Table Data ");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setResizable(false);

            panel.setLayout(null);
            panel.setBackground(Color.DARK_GRAY);
            panel.setBounds(0,0,950,700);
            add(panel);

            headerLabel.setBounds (25,20,900,35);
            headerLabel.setForeground (Color.WHITE);
            headerLabel.setFont (new Font(Font.MONOSPACED,Font.BOLD + Font.ITALIC,23));
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

            Object [] rowData = new Object[5];
            {
                rowData[0] = "LEAD TIME (DAYS)";
                rowData[1] = "FREQUENCY (Orders)";
                rowData[2] = "PROBABILITY";
                rowData[3] = "CUMULATIVE PROBABILITY";
                rowData[4] = "INTERVAL OF RANDOM NUMBER";
                tableModel.addRow (rowData);
            }
            for(int i = 0; i < Simulation.numberOfLeadTime; i++)
            {

                rowData[0] = sim.leadTimeDays[i];
                rowData[1] = sim.orderFrequency[i];
                rowData[2] = sim.orderProbabilities[i];
                rowData[3] = sim.orderCumulative[i];
                rowData[4] = sim.first_interval_LeadTime[i];
                rowData[4] += " to " + sim.end_interval_LeadTime[i];
                tableModel.addRow (rowData);
            }
            table.setModel (tableModel);
            table.setBackground (Color.LIGHT_GRAY);
            this.setLocationRelativeTo (null);
            table.setBounds (0,80,940,500);

            panel.add (table);
            nextBtn.addActionListener (this);
            BackBtn.addActionListener (this);
            setVisible (true);
        }
        @Override
        public void actionPerformed (ActionEvent e) {
            if(e.getSource () == nextBtn)
            {
                this.setVisible(false);
                getRestData getRestData = new getRestData();

            }
            else if(e.getSource() == BackBtn) {
                this.setVisible(false);
                DemandData demandData = new DemandData();
            }

        }
}

