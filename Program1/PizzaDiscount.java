class PizzaDiscount extends DecoratedPizza{
    
    private final DecoratedPizza pizza;
    private final String string;
    private final double discount;

    public PizzaDiscount(DecoratedPizza pizza_component, String msg, double discount){
        
        pizza = pizza_component;
        string = msg;
        this.discount = discount;
    }

    public double pizzaCost(){
        return (pizza.pizzaCost() - (pizza.pizzaCost() * discount));
    }

    public String toString(){
        return (pizza.toString() + string);
    }
}
