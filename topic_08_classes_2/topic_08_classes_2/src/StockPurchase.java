/**
   The StockPurchase class represents a stock purchase.
*/

public class StockPurchase
{
   private Stock4 stock;  // The stock that was purchased
   private int shares;   // Number of shares owned

   /**
      Constructor
      @param stockObject The stock to purchase.
      @param numShares The number of shares.
   */

   public StockPurchase(Stock4 stockObject, int numShares)
   {
      // Create a copy of the object referenced by
      // stockObject.
      stock = new Stock4 (stockObject);
      shares = numShares;
   }
   
   /**
      getStock method
      @return A copy of the Stock object for the stock
              being purchased.
   */
   
   public Stock4 getStock()
   {
      // Return a copy of the object referenced by stock.
      return new Stock4(stock);
   }
   
   /**
      getShares method
      @return The number of shares being purchased.
   */
   
   public int getShares()
   {
      return shares;
   }

   /**
      getCost method
      @return The cost of the stock purchase.
   */
   
   public double getCost()
   {
      return shares * stock.getSharePrice();
   }
}
