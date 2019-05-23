
public class Toppings
{
   private static Toppings toppings = new Toppings();
   private Topping[] all_toppings;

   private Toppings()
   {
      all_toppings = Topping.values();
   }

   public static Toppings getToppings()
   {
      return toppings;
   }

   public int numToppings()
   {
      return all_toppings.length;
   }

   public String listToppings()
   {
      int index = 1;

      //loop through the enumeration, listing the flavors
      String topping_str = "";

      for (Topping top : all_toppings)
      {
         topping_str += index + ". " + top.toString() +  "\r\n";
         index++;
      }

      return topping_str;
   }

   public Topping getTopping(int index)  //1-based
   {
      int num_toppings = numToppings();
      if (index < 1 || index > num_toppings)
      {
         index = 1;
      }

      return all_toppings[index - 1];
   }
}
