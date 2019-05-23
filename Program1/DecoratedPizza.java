abstract class DecoratedPizza{
    DecoratedPizza next_pizza_item;

    DecoratedPizza(){
        next_pizza_item = null;
    }

    DecoratedPizza(DecoratedPizza foo){
        next_pizza_item = foo;
    }

    public double pizzaCost(){
        return next_pizza_item.pizzaCost();
    }

    public String getImage(){
        return next_pizza_item.getImage();
    }

    public String toString(){
        return next_pizza_item.toString();
    }

}
