public class Main {

    public static void main(String[] args) {

        Input input = new Input();

        double[] X = input.getDemands();

        double C = input.getCapacity();
        
        double price = input.getPrice();
        
        double sum = Component.findSum(X);

        // If the demands are under available 
        if(sum <= C)
        {
            System.out.println("Total revenue generated:$"+sum*price);
        }

        System.out.println(sum);
    }
    
}