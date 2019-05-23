class Pineapple extends DecoratedPizza{

    private final DecoratedPizza pizza;

    public Pineapple(DecoratedPizza pizza){
        this.pizza = pizza;
    }

    public double pizzaCost(){
        return (pizza.pizzaCost() + 0.99);
    }

    public String getImage(){
        return pizza.getImage() + "A";
    }

    public String toString(){
        return pizza.toString() + "Pineapple\n";
    }
}
