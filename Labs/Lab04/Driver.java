// Will Westrich and Seth Hunter


public class Driver{

    private static Keyboard input = Keyboard.getKeyboard();

    public static Flavor getFlavorChoice(){
        Flavors flavors = Flavors.getFlavors();
        int choice;

        System.out.println(flavors.listFlavors());

        choice = input.readInt("Enter your desired flavor: ");

        while(true){
            if(choice < 1 || choice > flavors.numFlavors())
                System.out.println("Please select 1-" + flavors.numFlavors() + " for the flavor.");
            else
                return flavors.getFlavor(choice);
        }
    }

    public static Topping getToppingChoice(){
        Toppings toppings = Toppings.getToppings();
        int choice;

        System.out.println(toppings.listToppings());

        while(true){
            choice = input.readInt("Enter you desired topping: ");

            if(choice < 1 || choice > toppings.numToppings())
                System.out.println("Please select 1-" + toppings.numToppings() + " for the topping.");
            else
                return toppings.getTopping(choice);
        }
    }

    public static int getScoopsChoice(){
        int choice;

        while(true){
            choice = input.readInt("How many scoops (1, 2, or 3) would you like? ");

            if(choice < 1 || choice > 3)
                System.out.println("Please select 1, 2, or 3 for the number of scoops.");
            else
                return choice;
        }
    }

    public static Cone getConeChoice(){
        int choice;

        while(true){
            choice = input.readInt("Would you like a 1:  Sugar cone, 2:  Waffle cone, 3:  Cup? ");

            if(choice < 1 || choice > 3)
                System.out.println("Please select 1, 2, or 3 for cone choice.");
            else{
                Cone cone = new Cone(choice);
                return cone;
            }
        }
    }

    public static void main(String args[]){
        int numOrders = 0;
        double totalPrice = 0;
        String answer = "";

        while(true){
            answer = input.readString("Would you like to order an ice cream cone? (y/n) ");

            if(answer.equals("n") || answer.equals("N")){
                System.out.println("Your total order for " + numOrders + " orders of ice cream is: $" + totalPrice);
                return;

            }else if(answer.equals("y") || answer.equals("Y")){
                Flavor flavor = getFlavorChoice();
                Topping topping = getToppingChoice();
                int scoops = getScoopsChoice();
                Cone cone = getConeChoice();

                IceCream icecream = new IceCream(scoops, flavor, topping);

                IceCreamCone icecreamcone = new IceCreamCone(icecream, cone);

                System.out.println("Your order:");
                System.out.println(icecreamcone.toString());

                totalPrice += icecreamcone.price();
                ++numOrders;

            }else
                System.out.println("Please enter a valid answer.");
        }
    }

}
