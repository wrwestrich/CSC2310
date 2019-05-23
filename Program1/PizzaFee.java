class PizzaFee extends DecoratedPizza{

    private final DecoratedPizza pizza;
    private final String string;
    private final double fee;

    public PizzaFee(DecoratedPizza pizza_component, String msg, double fee){

        pizza = pizza_component;
        string = msg;
        this.fee = fee;
    }

    public double pizzaCost(){
        return (pizza.pizzaCost() + fee);
    }

    public String toString(){
        return (pizza.toString() + string);
    }
}
