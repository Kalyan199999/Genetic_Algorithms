
class Input{

    public Input(){
        System.out.println("Inputs Are set");
    }
    // All are in megha watts(MWatt)

    double CAPACITY = 100.0;

    double[] DEMAND = {35.4 , 35.90,30.08};
    
    double[] BATTERIE_CAPACITY = {100,70.4,85.76};
    
    double[] SATISFACTION = {2,1,1.5};

    // Price is in USD
    double PRICE = 17.0;

    public double[] getDemands()
    {
        return DEMAND;
    }

    public double getCapacity()
    {
        return CAPACITY;
    }

    public double getPrice()
    {
        return PRICE;
    }

    public double[] getSatisfaction()
    {
        return SATISFACTION;
    }

    public double[] getBatterCapacity()
    {
        return BATTERIE_CAPACITY;
    }

    // public Input(int n,double[] X,double [] bc,double[] sat,double  capacity)
    // {
    //     DEMAND = new double[n];
    //     BATTERIE_CAPACITY = new  double[n];
    //     SATISFACTION = new double[n];
    //     CAPACITY = capacity;

    //     for(int i=0;i<n;i++)
    //     {
    //         DEMAND[i] = X[i];
    //         BATTERIE_CAPACITY[i] = bc[i];
    //         SATISFACTION[i] = sat[i];
    //     }
    // }

}