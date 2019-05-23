class Mushrooms extends DecoratedPizza{

    private final DecoratedPizza pizza;

    public Mushrooms(DecoratedPizza pizza){
        this.pizza = pizza;
    }

    public double pizzaCost(){
        return (pizza.pizzaCost() + 0.79);
    }

    public String getImage(){
        return pizza.getImage() + "M";
    }

    public String toString(){
        return pizza.toString() + "Mushrooms\n";
    }
}
