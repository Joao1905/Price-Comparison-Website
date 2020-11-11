package facade;
import modelo.Model;
import dao.ModelDao;
import java.io.Serializable;
import java.util.List;

public class ModelFacade implements Serializable {
    
    public ModelFacade(){
        
    }
    
    public static List<Model> selectAll (int product_id) {
           return ModelDao.selectAll(product_id);
     }
    
    public static Model selectModelByID (int product_id, int model_id) {
           return ModelDao.selectModelByID(product_id, model_id);
     }
    
    public static List<Model> selectCheapestTable (int product_id, String designer1, String designer2, int maxPrice) {
        return ModelDao.selectCheapestTable(product_id, designer1, designer2, maxPrice);
    }
    
}
