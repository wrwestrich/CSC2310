class Onions extends DecoratedPizza{

    private final DecoratedPizza pizza;

    public Onions(DecoratedPizza pizza){
        this.pizza = pizza;
    }

    public double pizzaCost(){
        return (pizza.pizzaCost() + 0.79);
    }

    public String getImage(){
        return pizza.getImage() + "O";
    }

    public String toString(){
        return pizza.toString() + "Onions\n";
    }
}
