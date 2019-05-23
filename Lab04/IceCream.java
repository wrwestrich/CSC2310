// Will Westrich and Seth Hunter


public class IceCream {

    private int scoops;
    private Flavor flavor;
    private Topping topping;

    public IceCream(){}

    public IceCream(int numScoops, Flavor flavor, Topping topping){
        if(numScoops > 3 || numScoops < 1)
            scoops = 1;
        else
            scoops = numScoops;

        flavor = this.flavor;
        topping = this.topping;
    }

    public double price(){
        if(scoops == 2)
            return topping.price() + 0.75;
        else if(scoops == 3)
            return topping.price() + 1.5;
        else
            return topping.price();
    }

    public String toString(){
        String choices = "";

        choices += "Flavor: " + flavor.toString() + "\r\n" + "Topping: " + topping.toString() + "\r\n" + "Number of Scoops: " + scoops;

        return choices;
    }

}
