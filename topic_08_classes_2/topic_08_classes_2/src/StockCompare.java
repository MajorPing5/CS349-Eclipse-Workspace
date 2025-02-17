/**
   This program uses the Stock class's equals
   method to compare two Stock objects.
*/

public class StockCompare
{
   public static void main(String[] args)
   {
      // Create two Stock objects with the same values.
      Stock2 company1 = new Stock2("XYZ", 9.62);
      Stock2 company2 = new Stock2("XYZ", 9.62);
      
      // Use the equals method to compare the objects.
      if (company1.equals(company2))
         System.out.println("Both objects are the same.");
      else
         System.out.println("The objects are different.");
   }
}