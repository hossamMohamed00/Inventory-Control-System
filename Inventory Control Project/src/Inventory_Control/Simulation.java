package Inventory_Control;


import java.util.Random;

public class Simulation
{
    int i;
    //Data for Demand
    public static int numberOfACEDRILL;

    public int[] demands;
    public int[] demandFrequency;
    public int j, sumOfFrequencies = 0;

    public float[] demandsProbabilities;
    public float[] cumulativeProbabilities;

    public int[] first_interval_demands;
    public int[] end_interval_demands;


    //Fun to set Demands
    public void setDemands() {
        //set the Demands
        for (i = 0; i < numberOfACEDRILL; i++)
        {
            this.demands[i] = i;
        }
    }

    //Fun to set Freq
    public void setDemandFrequency(int[] demandFrequency)
    {
        this.demandFrequency = demandFrequency;

    }

    public void setSumOfFreq()
    {
        for (int value : demandFrequency)
        {
            this.sumOfFrequencies += value;
        }
    }

    //Data for Lead Time
    public static int numberOfLeadTime;

    public int[] leadTimeDays;
    public int[] orderFrequency;
    public int sumOfOrderFrequencies = 0;

    public float[] orderProbabilities;
    public float[] orderCumulative;

    public int[] first_interval_LeadTime;
    public int[] end_interval_LeadTime;

    public void setLeadTimeDays() {
        for (i = 0; i < numberOfLeadTime; i++) {
            this.leadTimeDays[i] = i + 1;
        }
    }

    public void setOrderFrequency(int[] orderFrequency) {
        this.orderFrequency = orderFrequency;
    }

    public void setSumOfLDFreq() {
        for (int value : orderFrequency) {
            this.sumOfOrderFrequencies += value;
        }

    }

    //Here Will be Func To Initialize Demand Data
    public void initializeDemandData(int numberOfACEDRILL)
    {
        demands = new int[numberOfACEDRILL];
        demandFrequency = new int[numberOfACEDRILL];
        demandsProbabilities = new float[numberOfACEDRILL];
        cumulativeProbabilities = new float[numberOfACEDRILL];
        first_interval_demands = new int[numberOfACEDRILL];
        end_interval_demands = new int[numberOfACEDRILL];

    }

    public void initializeLDData(int numberOfLeadTime)
    {
        /*-------------*/
        leadTimeDays = new int[numberOfLeadTime];
        orderFrequency = new int[numberOfLeadTime];
        sumOfOrderFrequencies = 0;

        orderProbabilities = new float[numberOfLeadTime];
        orderCumulative = new float[numberOfLeadTime];

        first_interval_LeadTime = new int[numberOfLeadTime];
        end_interval_LeadTime = new int[numberOfLeadTime];
    }
    /*-------------------------*/

    //process on the data
    public  int orderQuantity,ReOrderPoint,simDays,Biginning;
    /*-------------------------------------*/

    //Declaring The rest of the Arrays to build the table
    public int[] days ;//to save the days simulated in it
    public int[] UNITS_RECEIVED ;
    public int[] BEGINNING ;
    public int[] RANDOM_NUMBER ;
    public int[] simDemands;
    public int[] ENDING ;
    public int[] LOST_SALES ;
    public String[] ORDER ;
    public int[] simLEADTIME ;
    public int[] LEADTIME ;
    public int[] Random_LeadTime ;
    public int[] Ran_Num_for_demand ;
    public int[] Ran_Num_for_leadTime ;
    /*----------------------------------------*/
    //initialize Arrays
    public void initializeTableArrays(int simDays)
    {
         days = new int[simDays];//to save the days simulated in it
         UNITS_RECEIVED = new int[simDays];
         BEGINNING = new int[simDays];
         RANDOM_NUMBER = new int[simDays];
         simDemands = new int[simDays];
         ENDING = new int[simDays];
         LOST_SALES = new int[simDays];
         ORDER = new String[simDays];
         simLEADTIME = new int[simDays];
         LEADTIME = new int[simDays];
         Random_LeadTime = new int[simDays];

         Ran_Num_for_demand = new int[simDays];
         Ran_Num_for_leadTime = new int[simDays];
    }

    //set the days
    public void setDays()
    {
        for (i = 0; i < simDays; i++) {
            days[i] = i + 1;
        }
    }

    //Fun to generate Random Number For Demand && Lead Time
    public void generateRandomNumbers(int days)
    {
        //Generate Random Number Using Random Class

        Random ran = new Random();

        int upperbound = 100;
        //generate random values from 0-99

        for (int i = 0; i < days; i++)
        {
            this.Ran_Num_for_demand[i] = ran.nextInt(upperbound);
            this.Ran_Num_for_leadTime[i] = ran.nextInt(upperbound);
        }

    }

    //it's just like interface to call setSimDemand() on it
    public void setSimulatedDemand()
    {
        //set Simulated Demand and Simulated Lead Time

        setSimDemand(first_interval_demands,end_interval_demands,Ran_Num_for_demand,demands,simDemands);
        setSimDemand(first_interval_LeadTime,end_interval_LeadTime,Ran_Num_for_leadTime,leadTimeDays,simLEADTIME);

    }
    public void Start_Simulation()
    {
        //start the simulation for all these data
        Simulation_Data(UNITS_RECEIVED, BEGINNING, simDemands,
                ENDING, LOST_SALES, ORDER, Random_LeadTime, LEADTIME,
                orderQuantity, ReOrderPoint, Ran_Num_for_leadTime,
                simLEADTIME, Biginning);
    }
    /*---------------------------*/
    //Compute  Average Ending,Lost Sales,Num of Orders

    public  double sum_Ending = 0.0, sum_LostSales = 0.0;

    public  int num_Orders = 0;

    public  double Average_ending_inventory, Average_lost_sales, Average_number_of_orders_placed;

    public void ComputeAverages()
    {
        //compute the summation

        //Sum of Ending inventory
        for (int k = 0; k < simDays; k++) {
            sum_Ending += ENDING[k];
        }
        //sum of lost sales
        for (int k = 0; k < simDays; k++) {
            sum_LostSales += LOST_SALES[k];
        }
        //Compute the number of order
        for (int k = 0; k < simDays; k++) {
            if ("YES".equals(ORDER[k])) {
                ++num_Orders;
            }
        }

        Average_ending_inventory = sum_Ending / simDays;
        Average_lost_sales = sum_LostSales / simDays;
        Average_number_of_orders_placed = (double) num_Orders / simDays;
    }

    public int days_open,ordering_Cost,holding_cost,lost_sales;
    public  double Daily_order_cost, Daily_holding_cost, Daily_stockout_cost, Total_daily_inventory_cost;

    public void ComputeDaliesCost()
    {
        //  Daily_order_cost  = (Cost of placing one order) x (Average Number of orders placed per day)
        Daily_order_cost = ordering_Cost * Average_number_of_orders_placed;

        //Daily_holding_cost = (Cost of holding one unit for one day) x (Average ending inventory)
        Daily_holding_cost = ((float) holding_cost / days_open) * Average_ending_inventory;

        // Daily_stockout_cost = (Cost per lost sale) x (Average number of lost sales per day)
        Daily_stockout_cost = lost_sales * Average_lost_sales;

        //Total_daily_inventory_cost = Daily order cost + Daily holding cost + Daily stock out cost
        Total_daily_inventory_cost = Daily_order_cost + Daily_holding_cost + Daily_stockout_cost;

    }

    /*-----------------------------*/

    /*--------------*/
    //Function to Do The Main Thing in this Project
    //Function to take all data and Start to Simulate it
    private static void Simulation_Data(int[] units_received, int[] beginning, int[] simDemands,
                                        int[] ending, int[] lost_sales, String[] order, int[] random_leadTime,
                                        int[] leadtime, int orderQuantity, int reOrderPoint, int[] ran_num_for_leadTime,
                                        int[] simLEADTIME, int begin)
    {
        units_received[0] = 0;
        beginning[0] = begin;

        int j;
        for (int i = 0; i < units_received.length; i++)
        {
            if(i != 0)
            {
                beginning[i] = units_received[i] + ending[i-1];
            }
            ending[i] = beginning[i] - simDemands[i];

            if (ending[i] < reOrderPoint)
            {
                if (ending[i] < 0)
                {
                    lost_sales[i] = ending[i] * -1;
                    ending[i] = 0;
                }
                for (j = i; j >= 0; j--)
                {
                    if(leadtime[j] != 0) {
                        break;
                    }
                }
                // j = 2
                if (j >= 0 && (simLEADTIME[j] + j + 1) >= i)
                {

                    order[i] = "NO";
                    continue;
                }
                order[i] = "YES";

                leadtime[i] = simLEADTIME[i];

                random_leadTime [i] = ran_num_for_leadTime[i];

                if (simLEADTIME[i] + 1 + i < units_received.length)
                {
                    units_received[simLEADTIME[i] + i+ 1] = orderQuantity;
                }
            }
            else {
                order[i] = "NO";
                lost_sales[i] = 0;
            }
        }
    }

    /*-----------------*/

    //Function To Calculate the probabilities

    public void CalcProbabilities(float[] Probabilities, int[] Frequency, int sumOfFrequencies)
    {
        int i;
        for (i = 0; i < Frequency.length; i++)
        {
            Probabilities[i] = (float) Frequency[i] / sumOfFrequencies;
        }
    }

    //Function To Calculate the Cumulative probabilities

    public void CalcCumulative(float[] cumulativeProbabilities, float[] demandsProbabilities)
    {
        //to get the first probability
        cumulativeProbabilities[0] = demandsProbabilities[0];

        for (int i = 1; i < demandsProbabilities.length; i++)
        {
            cumulativeProbabilities[i] = demandsProbabilities[i] + cumulativeProbabilities[i - 1];
            //to make the float number like this (#.##) not (#.#######)-->first tow decimal only
            cumulativeProbabilities[i] = Float.parseFloat(String.format("%.2f", cumulativeProbabilities[i]));
        }
    }

    //Function to Calculate and set the number intervals

    public void setInterval(int[] start_interval, int[] second_interval, float[] cumulativeProbabilities) {
        start_interval[0] = 1;
        second_interval[0] = (int ) (cumulativeProbabilities[0] * 100);

        for (int i = 1; i < cumulativeProbabilities.length; i++) {

            start_interval[i] = second_interval[i - 1] + 1;

            second_interval[i] = (int) (cumulativeProbabilities[i] * 100);
        }
    }

    //to set simulated demands or lead time depends on the random numbers

    public void setSimDemand(int[] first_interval, int[] second_interval, int[] ran_numbers, int[] demands,int[] simArray)
    {
        for (int i = 0; i < ran_numbers.length; i++)
        {
            for (int j = 0; j < first_interval.length; j++)
            {
                if (ran_numbers[i] >= first_interval[j] && ran_numbers[i] <= second_interval[j])
                {
                    simArray[i] = demands[j];
                    break;
                }
            }
        }
    }
}