package GUI;

import Inventory_Control.Simulation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Daily_Cost extends JFrame implements ActionListener {

    Simulation sim = GetDataFrame.getSavedData();

    //Components
    JPanel panel = new JPanel ();
    JPanel Result_Panel = new JPanel ();
    JLabel headerLabel = new JLabel ("Analyzing Inventory Cost");

    JLabel numOpenLabel = new JLabel ("Enter number of Days store is open (per year) :  ");
    JLabel orderCostLabel = new JLabel ("Enter Ordering cost per order :");
    JLabel holdingCostLabel = new JLabel ("Enter Holding Cost per year :");
    JLabel lostSalesLabel = new JLabel ("Enter Lost Sales Cost : ");

    JLabel dailyOrderLabel = new JLabel("Daily order cost  :");
    JLabel dailyOrderCost = new JLabel("1");

    JLabel dailyHoldingLabel = new JLabel("Daily holding cost : ");
    JLabel dailyHolding = new JLabel("1");

    JLabel dailyStockOutLabel = new JLabel("Daily stockout cost : ");
    JLabel dailyStockOut = new JLabel("1");
    JLabel totaDailyCostLAbel = new JLabel("Total daily inventory cost : ");
    JLabel totaDailyCost = new JLabel("1");

    JTextField openDays = new JTextField ();
    JTextField orderCost = new JTextField ();
    JTextField holdingCost = new JTextField ();
    JTextField lostSales = new JTextField() ;



    //Buttons
    JButton submitBtn = new JButton ("Submit");
    JButton FinishBtn = new JButton ("Finish");
    JButton backbutton = new JButton ("Back");

    /*--------------------*/
    public Daily_Cost()
    {
        showFrame();
    }
    public void showFrame() {
        //Confirmation Dialog When Close
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                int x = JOptionPane.showConfirmDialog(null, "You will lose all data,Are You Sure To Close Program..?",
                        "Exit Program", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                if (x == JOptionPane.YES_OPTION) {
                    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                } else {
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });
        /*--------------------*/
        this.setSize(950,700);
        this.setTitle("Inventory Control Simulation");
        this.setLocation(230, 10);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        getContentPane().setBackground(Color.DARK_GRAY);

        panel.setLayout(null);
        panel.setBounds(0,0,950,380);
        panel.setBackground(Color.DARK_GRAY);
        this.add(panel);

        Result_Panel.setLayout(null);
        Result_Panel.setBounds(0,0,950,200);
        Result_Panel.setBackground(Color.DARK_GRAY);
        this.add(Result_Panel);

        /*---------------------*/
        headerLabel.setBounds (210,5,700,40);
        headerLabel.setForeground (Color.WHITE);
        headerLabel.setFont (new Font(Font.MONOSPACED,Font.BOLD,35));
        panel.add (headerLabel);

        numOpenLabel.setBounds (50,70,600,30);
        numOpenLabel.setForeground(Color.WHITE);
        numOpenLabel.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 20));
        panel.add (numOpenLabel);

        orderCostLabel.setBounds (50,140,400,30);
        orderCostLabel.setForeground(Color.WHITE);
        orderCostLabel.setFont(new Font(Font.MONOSPACED,Font.ITALIC, 20));
        panel.add (orderCostLabel);

        holdingCostLabel.setBounds (50,210,420,30);
        holdingCostLabel.setForeground(Color.WHITE);
        holdingCostLabel.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 20));
        panel.add (holdingCostLabel);

        lostSalesLabel.setBounds (50,280,350,30);
        lostSalesLabel.setForeground(Color.WHITE);
        lostSalesLabel.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 20));
        panel.add (lostSalesLabel);
        /*--------*/
        //Text Field
        openDays.setBounds (630,75,200,25);
        openDays.setBackground (Color.lightGray);
        panel.add (openDays);

        orderCost.setBounds (430,143,200,25);
        orderCost.setBackground (Color.lightGray);
        panel.add (orderCost);

        holdingCost.setBounds (430 ,210,200,25);
        holdingCost.setBackground (Color.lightGray);
        panel.add (holdingCost);

        lostSales.setBounds (430,280,200,25);
        lostSales.setBackground (Color.lightGray);
        panel.add (lostSales);
        /*---------------*/

        submitBtn.setBounds(600, 334, 200, 40);
        submitBtn.setBackground(Color.GRAY);
        submitBtn.setForeground(Color.WHITE);
        submitBtn.setFont(new Font(Font.MONOSPACED, Font.BOLD + Font.ITALIC, 24));
        panel.add (submitBtn);

        backbutton.setBounds(150, 334, 200, 40);
        backbutton.setBackground(Color.GRAY);
        backbutton.setForeground(Color.WHITE);

        backbutton.setFont(new Font(Font.MONOSPACED, Font.BOLD + Font.ITALIC, 24));
        panel.add (backbutton);


        //actions
        FinishBtn.addActionListener (this);
        backbutton.addActionListener (this);
        submitBtn.addActionListener(this);

        //set the Result_Panel visible false and show it when press submit
        Result_Panel.setVisible(false);

        this.setVisible(true);
    }
    public void setResultPanel()
    {
        dailyOrderLabel.setBounds (30,390,400,30);
        dailyOrderLabel.setForeground(Color.WHITE);
        dailyOrderLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD + Font.ITALIC, 23));
        Result_Panel.add (dailyOrderLabel);

        dailyOrderCost.setBounds (330,390,300,30);
        dailyOrderCost.setForeground(Color.WHITE);
        dailyOrderCost.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        dailyOrderCost.setText(String.valueOf(sim.Daily_order_cost));
        Result_Panel.add (dailyOrderCost);

        dailyHoldingLabel.setBounds (30,445,300,30);
        dailyHoldingLabel.setForeground(Color.WHITE);
        dailyHoldingLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD+ Font.ITALIC, 23));
        Result_Panel.add (dailyHoldingLabel);

        dailyHolding.setBounds (330,445,300,30);
        dailyHolding.setForeground(Color.WHITE);
        dailyHolding.setFont(new Font("Arial", Font.BOLD, 20));
        dailyHolding.setText(String.valueOf(sim.Daily_holding_cost));
        Result_Panel.add (dailyHolding);


        dailyStockOutLabel.setBounds (30,505,400,30);
        dailyStockOutLabel.setForeground(Color.WHITE);
        dailyStockOutLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD+ Font.ITALIC, 23));
        Result_Panel.add (dailyStockOutLabel);

        dailyStockOut.setBounds (340,505,400,30);
        dailyStockOut.setForeground(Color.WHITE);
        dailyStockOut.setFont(new Font("Arial", Font.BOLD, 20));
        dailyStockOut.setText(String.valueOf(sim.Daily_stockout_cost));
        Result_Panel.add (dailyStockOut);

        totaDailyCostLAbel.setBounds (30,555,400,30);
        totaDailyCostLAbel.setForeground(Color.WHITE);
        totaDailyCostLAbel.setFont(new Font(Font.MONOSPACED, Font.BOLD+ Font.ITALIC, 23));
        Result_Panel.add (totaDailyCostLAbel);

        totaDailyCost.setBounds (430,557,400,30);
        totaDailyCost.setForeground(Color.WHITE);
        totaDailyCost.setFont(new Font("Arial", Font.BOLD, 20));
        totaDailyCost.setText(String.valueOf(sim.Total_daily_inventory_cost));
        Result_Panel.add (totaDailyCost);


        FinishBtn.setBounds(350, 610, 150, 40);
        FinishBtn.setBackground(Color.GRAY);
        FinishBtn.setForeground(Color.WHITE);
        FinishBtn.setFont(new Font(Font.MONOSPACED, Font.BOLD + Font.ITALIC, 24));
        Result_Panel.add (FinishBtn);

    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource () == submitBtn) {

                if(!openDays.getText ().equals ("") && openDays.getText ().matches ("[0-9]+") &&!orderCost.getText ().equals ("") && orderCost.getText ().matches("[0-9]+")
                    &&!holdingCost.getText ().equals ("") && holdingCost.getText ().matches ("[0-9]+")  && !lostSales.getText ().equals ("") && lostSales.getText ().matches ("[0-9]+")   )
            {
                /*-------------**/
                //get Data

                sim.days_open = Integer.parseInt(holdingCost.getText());
                sim.ordering_Cost = Integer.parseInt(orderCost.getText());
                sim.holding_cost = Integer.parseInt(holdingCost.getText());
                sim.lost_sales = Integer.parseInt(lostSales.getText());
                /*--------------------*/
                sim.ComputeDaliesCost();

                /*------------------*/
                JOptionPane.showMessageDialog (null,"Great Job..,We almost Finish","Information",JOptionPane.INFORMATION_MESSAGE);
                setResultPanel();//call this func to set the Daily cost on the panel
                Result_Panel.setVisible(true);//to show this panel
            }
            else {
                JOptionPane.showMessageDialog (null,"Invalid input or Missing required Fields ... !  Please, complete them before submit ...!","Failed",JOptionPane.ERROR_MESSAGE);
            }

        }
        else if(e.getSource () == FinishBtn)
        {
            this.setVisible(false);
            Final_Frame final_frame = new Final_Frame();

        }
        else if (e.getSource() == backbutton)
        {
            int x = JOptionPane.showConfirmDialog(null,"You will lose this data if you back ,Are You Sure To Back?",
                    "Alert", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if(x == JOptionPane.YES_OPTION)
            {
                this.setVisible(false);
                Final_Table final_table = new Final_Table();
            }
        }
    }
}
