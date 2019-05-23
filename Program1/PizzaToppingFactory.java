class PizzaToppingFactory{

    public static DecoratedPizza addPepperoni(DecoratedPizza pizza){

        pizza = new PizzaTopping(pizza, "Pepperoni\n", "P", 0.99);

        return pizza;
    }

    public static DecoratedPizza addSausage(DecoratedPizza pizza){

        pizza = new PizzaTopping(pizza, "Sausage\n", "S", 0.99);

        return pizza;
    }

    public static DecoratedPizza addOnions(DecoratedPizza pizza){

        pizza = new PizzaTopping(pizza, "Onions\n", "O", 0.79);

        return pizza;
    }

    public static DecoratedPizza addGreenPeppers(DecoratedPizza pizza){

        pizza = new PizzaTopping(pizza, "Green Peppers\n", "G", 0.69);

        return pizza;
    }

    public static DecoratedPizza addMushrooms(DecoratedPizza pizza){
 
        pizza = new PizzaTopping(pizza, "Mushrooms\n", "M", 0.79);

        return pizza;
    }

    public static DecoratedPizza addHam(DecoratedPizza pizza){
 
        pizza = new PizzaTopping(pizza, "Ham\n", "H", 0.89);
   
        return pizza;
    }

    public static DecoratedPizza addPineapple(DecoratedPizza pizza){
 
        pizza = new PizzaTopping(pizza, "Pineapple\n", "A", 0.89);

        return pizza;
    }

}
