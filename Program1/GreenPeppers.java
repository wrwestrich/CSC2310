class GreenPeppers extends DecoratedPizza{

    private final DecoratedPizza pizza;

    public GreenPeppers(DecoratedPizza pizza){
        this.pizza = pizza;
    }

    public double pizzaCost(){
        return (pizza.pizzaCost() + 0.69);
    }

    public String getImage(){
        return pizza.getImage() + "G";
    }

    public String toString(){
        return pizza.toString() + "Green Peppers\n";
    }
}
