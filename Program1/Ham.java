class Ham extends DecoratedPizza{

    private final DecoratedPizza pizza;

    public Ham(DecoratedPizza pizza){
        this.pizza = pizza;
    }

    public double pizzaCost(){
        return (pizza.pizzaCost() + 0.99);
    }

    public String getImage(){
        return pizza.getImage() + "H";
    }

    public String toString(){
        return pizza.toString() + "Ham\n";
    }
}
