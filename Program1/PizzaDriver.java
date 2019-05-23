//Will Westrich

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

class PizzaDriver{

    private static double total = 0.0;
    private static int numPizzas = 0;

    private static int menu() throws IOException{

        int input;
        do{
            System.out.println("\n1. Meat Lover's");
            System.out.println("2. Veggie Lover's");
            System.out.println("3. Hawaiian");
            System.out.println("4. Build Your Own");

            try{
                Keyboard reader = Keyboard.getKeyboard();
                input = Integer.parseInt(reader.readString("\nSelect from the above: "));
            }
            catch(NumberFormatException e){
                input = 0;
            }

            if(input < 1 || input > 4)
                System.out.println("Please enter 1-4.");

        } while(input < 1 || input > 4);

        return input;
    }

    private static void requestSize(PizzaBuilder pizza_builder) throws IOException{

        String input;

        do{

            //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            Keyboard reader = Keyboard.getKeyboard();
            input = reader.readString("\nWhat size pizza (S/M/L)? ");

        } while(!(pizza_builder.setSize(input.charAt(0))));
    }

    private static void requestCrust(PizzaBuilder pizza_builder) throws IOException{

        String input;

        do{

            Keyboard reader = Keyboard.getKeyboard();
            input = reader.readString("\nWhat type of crust (Thin/Hand/Pan)? ");

        } while(!(pizza_builder.setCrust(input)));
    }

    private static void requestToppings(PizzaBuilder pizza_builder) throws IOException{

        String input;

        do{

            Keyboard reader = Keyboard.getKeyboard();
            input = reader.readString("\n(P)epperoni, (O)nions, (G)reen Peppers,\n(S)ausage, (M)ushroms, (D)one\n");

            pizza_builder.addTopping(input.charAt(0));

        } while(!input.equalsIgnoreCase("D"));
    }

    private static void showOrder(DecoratedPizza dec_pizza){

        System.out.println("\nYour Pizza:");
        System.out.println(dec_pizza.toString());

        total += BigDecimal.valueOf(dec_pizza.pizzaCost()).setScale(2, RoundingMode.HALF_UP).doubleValue();

        System.out.println("The cost of your pizza is $" + BigDecimal.valueOf(dec_pizza.pizzaCost()).setScale(2, RoundingMode.HALF_UP).doubleValue());
    }

    public static void main(String[] args){

        PizzaBuilder builder;

        while(true){

            try{
                Keyboard reader = Keyboard.getKeyboard();
                String input = reader.readString("\nWould you like to order a pizza (y/n)? ");

                if(input.equalsIgnoreCase("N")){
                    System.out.println("\nYou ordered " + numPizzas + " pizza(s) for a grand total of $" + BigDecimal.valueOf(total).setScale(2, RoundingMode.HALF_UP).doubleValue());
                    break;
                }
                else if(input.equalsIgnoreCase("Y")){
                    int choice = menu();
                    switch(choice){
                        case 1:
                                builder = new MeatLovers();

                                requestSize(builder);
                                requestCrust(builder);
                                builder.buildPizza();
                                break;
                        case 2:
                                builder = new VeggieLovers();

                                requestSize(builder);
                                requestCrust(builder);
                                builder.buildPizza();
                                break;
                        case 3:
                                builder = new Hawaiian();

                                requestSize(builder);
                                requestCrust(builder);
                                builder.buildPizza();
                                break;
                        default:
                                builder = new PizzaBuilder();

                                requestSize(builder);
                                requestCrust(builder);
                                builder.buildPizza();
                                requestToppings(builder);
                                break;
                    }


                    input = reader.readString("\nAre you a senior citizen (y/n)? ");
                    if(input.charAt(0) == 'Y' || input.charAt(0) == 'y')
                        builder.addDiscount();

                    input = reader.readString("\nDo you need this pizza delivered (y/n)? ");
                    if(input.charAt(0) == 'Y' || input.charAt(0) == 'y')
                        builder.addFee();

                    showOrder(builder.pizzaDone());
                    ++numPizzas;
                }
                else{
                    System.out.println("Please enter y/n.");
                }
            }
            catch(IOException e){
                System.out.println("Something Broke");
            }
        }
    }
}
