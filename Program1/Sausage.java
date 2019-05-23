class Sausage extends DecoratedPizza{

    private final DecoratedPizza pizza;

    public Sausage(DecoratedPizza pizza){
        this.pizza = pizza;
    }

    public double pizzaCost(){
        return (pizza.pizzaCost() + 0.99);
    }

    public String getImage(){
        return pizza.getImage() + "S";
    }

    public String toString(){
        return pizza.toString() + "Sausage\n";
    }
}
