

class MeatLovers extends PizzaBuilder{

    protected void buildPizza(){

        super.buildPizza();
        addTopping('P');
        addTopping('S');
        addTopping('H');
    }
}
