
public enum Topping
{
   nuts(0.15), m_and_ms(0.15), hot_fudge(0.15), oreo_cookies(0.15), no_topping(0.00);

   private double toppingPrice;  

   private Topping(double price) 
   {
      toppingPrice = price;
   }

   public double price()
   {
      return toppingPrice;
   }
}
