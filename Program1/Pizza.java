class Pizza extends DecoratedPizza{

    private Crust crust;

    public Pizza(Crust crust){
        super();
        this.crust = crust;
    }

    public double pizzaCost(){
        return crust.crustCost();
    }

    public String toString(){
        return crust.toString() + "Toppings:\n";
    }

    public String getImage(){
        return String.valueOf(crust.getCrust());
    }

    /*public void setCrust(Crust crust){
        this.crust = crust;
    }*/
}
