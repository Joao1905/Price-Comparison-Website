package facade;

import java.io.Serializable;
import java.util.List;
import modelo.PriceHistory;
import dao.PriceHistoryDao;

public class PriceHistoryFacade implements Serializable {
    
    public PriceHistoryFacade(){
        
    }
    
    public static List<PriceHistory> SelectCheapestByShop (int product_id, String unique_name) {
        return PriceHistoryDao.SelectCheapestByShop(product_id, unique_name);
    }
    
    public static void UpdatePCColumnByID (int price_entry_id, PriceHistory pcobj, String column){
        PriceHistoryDao.UpdatePCColumnByID(price_entry_id, pcobj, column);
    }
    
     public static List<PriceHistory> SelectHistoricalPrice (int product_id, int model_id, int shop_id, int interval) {
         return PriceHistoryDao.SelectHistoricalPrice(product_id, model_id, shop_id, interval);
     }
    
}
