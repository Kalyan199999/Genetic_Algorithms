public class Component {
    
    public static double findSum(double[] X)
    {
        double sum = 0.0;

        for(double v:X)
        {
            sum += v;
        }

        return sum;
        
    }
}