class PizzaBuilder{
    
    private Crust crust;
    private DecoratedPizza pizza;
    private char size;
    private String type;

    public PizzaBuilder(){
        
        size = 'S';
        type = "THIN";
        crust = new Crust(type, size);
        pizza = new Pizza(crust);
        //pizza.setCrust(crust);
    }

    protected void buildPizza(){
       
        crust = new Crust(type, size);
        pizza = new Pizza(crust);
        //pizza.setCrust(crust);
    }

    public boolean setSize(char size){

        if(size == 'S' || size == 's'){
            //crust.setCrust('S');
            this.size = Character.toUpperCase(size);
            return true;
        }
        else if(size == 'M' || size == 'm'){
            //crust.setCrust('M');
            this.size = Character.toUpperCase(size);
            return true;
        }
        else if(size == 'L' || size == 'l'){
            //crust.setCrust('L');
            this.size = Character.toUpperCase(size);
            return true;
        }

        return false;
    }

    public boolean setCrust(String crust){

        if(crust.compareToIgnoreCase("Thin") == 0){
            //this.crust.setType("THIN");
            type = crust.toUpperCase();
            return true;
        }
        else if(crust.compareToIgnoreCase("Hand") == 0){
            //this.crust.setType("HAND");
            type = crust.toUpperCase();
            return true;
        }
        else if(crust.compareToIgnoreCase("Pan") == 0){
            //this.crust.setType("PAN");
            type = crust.toUpperCase();
            return true;
        }

        return false;
    }

    public void addTopping(char topping){

        if(!(pizza instanceof PizzaDiscount)){
            if(topping == 'P' || topping == 'p')
                //pizza = new Pepperoni(pizza);
                pizza = PizzaToppingFactory.addPepperoni(pizza);
            else if(topping == 'S' || topping == 's')
                //pizza = new Sausage(pizza);
                pizza = PizzaToppingFactory.addSausage(pizza);
            else if(topping == 'O' || topping == 'o')
                //pizza = new Onions(pizza);
                pizza = PizzaToppingFactory.addOnions(pizza);
            else if(topping == 'G' || topping == 'g')
                //pizza = new GreenPeppers(pizza);
                pizza = PizzaToppingFactory.addGreenPeppers(pizza);
            else if(topping == 'M' || topping == 'm')
                //pizza = new Mushrooms(pizza);
                pizza = PizzaToppingFactory.addMushrooms(pizza);
            else if(topping == 'H' || topping == 'h')
                //pizza = new Ham(pizza);
                pizza = PizzaToppingFactory.addHam(pizza);
            else if(topping == 'A' || topping == 'a')
                //pizza = new Pineapple(pizza);
                pizza = PizzaToppingFactory.addPineapple(pizza);
        }
    }

    public void addDiscount(){

        if(!(pizza instanceof PizzaFee))
            pizza = new PizzaDiscount(pizza, "Senior Discount\n", 0.1);
    }

    public void addFee(){

        pizza = new PizzaFee(pizza, "Delivery\n", 2.5);
    }

    public DecoratedPizza pizzaDone(){
        
        DecoratedPizza done = pizza;

        size = 'S';
        type = "THIN";
        crust = new Crust(type, size);
        pizza = new Pizza(crust);

        return done;
    }
}
