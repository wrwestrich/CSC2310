import java.text.DecimalFormat;

public class Currency
{
   private final static DecimalFormat fmt = new DecimalFormat("#,##0.00");

   public static String formatCurrency(double val)
   {
      String temp = "$" + fmt.format(val);
      return temp;
   }
}
