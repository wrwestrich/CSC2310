
public class Flavors
{
   private static Flavors flavors = new Flavors();
   private Flavor[] all_flavors;

   private Flavors()
   {
      all_flavors = Flavor.values();
   }

   public static Flavors getFlavors()
   {
      return flavors;
   }

   public int numFlavors()
   {
      return all_flavors.length;
   }

   public String listFlavors()
   {
      int index = 1;

      //loop through the enumeration, listing the flavors
      String flavor_str = "";

      for (Flavor flavor : all_flavors)
      {
         flavor_str += index + ". " + flavor.toString() +  "\r\n";
         index++;
      }

      return flavor_str;
   }

   public Flavor getFlavor(int index)
   {
      int num_flavors = numFlavors();
      if (index < 1 || index > num_flavors)
      {
         index = 1;
      }

      return all_flavors[index - 1];
   }
}
