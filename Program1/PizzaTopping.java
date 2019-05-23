class PizzaTopping extends DecoratedPizza{

    private final DecoratedPizza pizza;
    private final double cost;
    private final String image;
    private final String string;

    public PizzaTopping(DecoratedPizza pizza, String topping_string, String topping_char, double toppingCost){
    
        this.pizza = pizza;
        cost = toppingCost;
        image = topping_char;
        string = topping_string;
    }

    public double pizzaCost(){
        return (pizza.pizzaCost() + cost);
    }

    public String getImage(){
        return (pizza.getImage() + image);
    }

    public String toString(){
        return (pizza.toString() + string);
    }
}
