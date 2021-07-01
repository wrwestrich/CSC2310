// Will Westrich and Seth Hunter


public class IceCreamCone{

    private IceCream icecream = new IceCream();
    private Cone cone = new Cone();

    public IceCreamCone(IceCream icecream, Cone cone){
        icecream = this.icecream;
        cone = this.cone;
    }

    public double price(){
        return 1.99 + icecream.price() + cone.price();
    }

    public String toString(){
        String order = "";
        Currency currency = new Currency();

        order += cone.toString() + "\r\n" + icecream.toString() + "\r\n" + "Price: $" + currency.formatCurrency(price());

        return order;
    }
}
