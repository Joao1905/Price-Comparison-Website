package dao;

import modelo.Model;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ModelDao implements Serializable {
    
    public ModelDao(){
        
    }
    
    public static Model selectModelByID (int product_id, int model_id){
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            con = ConnectionFactory.getConnection();
            st = con.prepareStatement("SELECT * FROM models WHERE product_id=? and model_id=?");
            st.setInt(1, product_id);
            st.setInt(2, model_id);
            rs = st.executeQuery();
             if (rs.next()) {
                Model m = new Model();
                m.setModel_id( rs.getInt("model_id"));
                m.setProduct_id( rs.getInt("product_id"));
                m.setName( rs.getString("name"));
                m.setUnique_name( rs.getString("unique_name"));
                m.setDesigner( rs.getString("designer"));
                m.setManufacturer( rs.getString("manufacturer"));
                m.setImage( rs.getString("image"));
                return m;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException (e);
        } finally {
            if (st != null)
                try {st.close();} catch (Exception e){}
            if (con!=null)
                try {con.close();} catch (Exception e){}
            if (rs!=null)
                try {rs.close();} catch (Exception e){}
        }
    }
    
    public static List<Model> selectCheapestTable (int product_id, String designer1, String designer2, int maxPrice) {
        List<Model> model = new ArrayList<>();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            con = ConnectionFactory.getConnection();
            st = con.prepareStatement("select * \n" +
            "from (\n" +
            "SELECT models.model_id, models.product_id, shop_id, name, unique_name, designer, manufacturer, DATE_FORMAT(`date`, '%Y-%m-%d %H') as 'formated_date', price, image FROM price_history\n" +
            "	join models\n" +
            "    on price_history.model_id = models.model_id \n" +
            "	where (designer=? or designer=?) and price < ? and models.product_id=? and date BETWEEN NOW()-INTERVAL 2 DAY-interval 5 minute and NOW()\n" +
            ") t1\n" +
            "where not exists (select * from (\n" +
            "SELECT models.model_id, models.product_id, shop_id, name, unique_name, designer, manufacturer, DATE_FORMAT(`date`, '%Y-%m-%d %H') as 'formated_date', price, image FROM price_history\n" +
            "	join models\n" +
            "    on price_history.model_id = models.model_id \n" +
            "	where (designer=? or designer=?) and price < ? and models.product_id=? and date BETWEEN NOW()-INTERVAL 2 DAY-interval 5 minute and NOW()\n" +
            ") t2 where t2.model_id = t1.model_id and (t2.formated_date > t1.formated_date or (t2.formated_date = t1.formated_date and t2.price < t1.price)))");
            st.setString(1, designer1);
            st.setString(2, designer2);
            st.setInt(3, maxPrice);
            st.setInt(4, product_id);
            st.setString(5, designer1);
            st.setString(6, designer2);
            st.setInt(7, maxPrice);
            st.setInt(8, product_id);
            rs = st.executeQuery();
            
            while (rs.next()) {
                Model m = new Model();
                m.setModel_id( rs.getInt("model_id"));
                m.setProduct_id( rs.getInt("product_id"));
                m.setName( rs.getString("name"));
                m.setUnique_name( rs.getString("unique_name"));
                m.setDesigner( rs.getString("designer"));
                m.setManufacturer( rs.getString("manufacturer"));
                m.setCheapest_price( rs.getFloat("price"));
                m.setImage( rs.getString("image"));
                model.add (m);
            }
                return model;

        } catch (Exception e) {
            throw new RuntimeException (e);
        } finally {
            if (st != null)
                try {st.close();} catch (Exception e){}
            if (con!=null)
                try {con.close();} catch (Exception e){}
            if (rs!=null)
                try {rs.close();} catch (Exception e){}
        }
        
    }
    
    public static List<Model> selectAll(int product_id) {
        List<Model> model = new ArrayList<>();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            con = ConnectionFactory.getConnection();
            st = con.prepareStatement("SELECT * FROM models WHERE product_id=?");
            st.setInt(1, product_id);
            rs = st.executeQuery();
            while (rs.next()) {
                    Model model2 = new Model();
                    model2.setModel_id( rs.getInt("model_id"));
                    model2.setProduct_id( rs.getInt("product_id"));
                    model2.setName( rs.getString("name"));
                    model2.setUnique_name( rs.getString("unique_name"));
                    model2.setDesigner( rs.getString("designer"));
                    model2.setManufacturer( rs.getString("manufacturer"));
                    model2.setImage( rs.getString("image"));
                    model.add(model2);
                }
                return model;
        } catch (Exception e) {
            throw new RuntimeException (e);
        } finally {
            if (st != null)
                try {st.close();} catch (Exception e){}
            if (con!=null)
                try {con.close();} catch (Exception e){}
            if (rs!=null)
                try {rs.close();} catch (Exception e){}
        }
        
    }
    
}
