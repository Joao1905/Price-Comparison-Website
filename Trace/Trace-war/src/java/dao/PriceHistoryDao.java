package dao;

import modelo.PriceHistory;
import modelo.Model;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PriceHistoryDao implements Serializable{
    
    public PriceHistoryDao(){
        
    }
    
    //Selects historical prices for a period of X days to generate javascript charts
    public static List<PriceHistory> SelectHistoricalPrice (int product_id, int model_id, int shop_id, int interval) {
        List<PriceHistory> historical_price = new ArrayList<>();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            con = ConnectionFactory.getConnection();            
            st = con.prepareStatement("select * \n" +
            "from (\n" +
            "SELECT price, shop_id, DATE_FORMAT(`date`, '%d/%m/%Y') as 'date_format', date from price_history \n" +
            "	where model_id = ? and product_id=? and shop_id=? and date > now() - interval ? day\n" +
            "    group by date\n" +
            "    order by date DESC) t1 where not exists (select * from(\n" +
            "SELECT price, shop_id, DATE_FORMAT(`date`, '%d/%m/%Y') as 'date_format', date from price_history \n" +
            "	where model_id = ? and product_id=? and shop_id=? and date > now() - interval ? day\n" +
            "    group by date\n" +
            "    order by date DESC) t2 where t2.date_format = t1.date_format and t2.date > t1.date)");
            st.setInt(1, model_id);
            st.setInt(2, product_id);
            st.setInt(3, shop_id);
            st.setInt(4, interval);
            st.setInt(5, model_id);
            st.setInt(6, product_id);
            st.setInt(7, shop_id);
            st.setInt(8, interval);
            rs = st.executeQuery();
            
            while (rs.next()) {
                PriceHistory pc = new PriceHistory();
                pc.setPrice( rs.getFloat("price"));
                pc.setShop_id( rs.getInt("shop_id"));
                pc.setDate( rs.getString("date_format"));
                
                historical_price.add (pc);
            }
            
            return historical_price;
            
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
    
    public static List<PriceHistory> SelectCheapestByShop (int product_id, String unique_name) {
        List<PriceHistory> price_history = new ArrayList<>();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            con = ConnectionFactory.getConnection();
            
            st = con.prepareStatement("select * \n" +
            "from (\n" +
            "SELECT price_history.price_entry_id, price_history.date, models.model_id, models.product_id, shops.shop_id, shops.name as 'shop_name', models.name, unique_name, designer, manufacturer, DATE_FORMAT(`date`, '%Y-%m-%d %H') as 'formated_date', price, image, product_url, page_visits, page_clicks FROM price_history\n" +
            "	join models\n" +
            "    on price_history.model_id = models.model_id \n" +
            "    join shops\n" +
            "   on shops.shop_id = price_history.shop_id\n"+
            "	where models.unique_name = ? and models.product_id=? and date BETWEEN NOW()-INTERVAL 2 DAY-interval 5 minute and NOW()\n" +
            "	 order by price\n"+
            ") t1\n" +
            "where not exists (select * from (\n" +
            "SELECT price_history.price_entry_id, price_history.date, models.model_id, models.product_id, shops.shop_id, shops.name as 'shop_name', models.name, unique_name, designer, manufacturer, DATE_FORMAT(`date`, '%Y-%m-%d %H') as 'formated_date', price, image, product_url, page_visits, page_clicks FROM price_history\n" +
            "	join models\n" +
            "    on price_history.model_id = models.model_id \n" +
            "    join shops\n" +
            "   on shops.shop_id = price_history.shop_id\n"+
            "	where models.unique_name = ? and models.product_id=? and date BETWEEN NOW()-INTERVAL 2 DAY-interval 5 minute and NOW()\n" +
            "	 order by price\n"+
            ") t2 where t2.shop_id = t1.shop_id and (t2.formated_date > t1.formated_date or (t2.formated_date = t1.formated_date and t2.price < t1.price)))");
            st.setString(1, unique_name);
            st.setInt(2, product_id);
            st.setString(3, unique_name);
            st.setInt(4, product_id);
            rs = st.executeQuery();
            
            while (rs.next()) {
                PriceHistory pc = new PriceHistory();
                pc.setPrice_entry_id( rs.getInt("price_entry_id"));
                pc.setDate( rs.getString("date"));
                pc.setProduct_id( rs.getInt("product_id"));
                pc.setModel_id( rs.getInt("model_id"));
                pc.setShop_id( rs.getInt("shop_id"));
                pc.setShop_name( rs.getString("shop_name"));
                pc.setPrice( rs.getFloat("price"));
                pc.setProduct_url( rs.getString("product_url"));
                pc.setPage_visits( rs.getInt("page_visits"));
                pc.setPage_clicks( rs.getInt("page_clicks"));
                
                Model m = new Model();
                m.setModel_id( rs.getInt("model_id"));
                m.setName( rs.getString("name"));
                m.setUnique_name( rs.getString("unique_name"));
                m.setDesigner( rs.getString("designer"));
                m.setManufacturer( rs.getString("manufacturer"));
                m.setImage( rs.getString("image"));
                
                pc.setModel(m);
                price_history.add (pc);
            }
                return price_history;

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
    
    public static void UpdatePCColumnByID (int price_entry_id, PriceHistory pcobj, String column) {
        Connection con = null;
        PreparedStatement st = null;
        
        try {
            con = ConnectionFactory.getConnection();
            switch (column) {
                case "page_visits":
                    st = con.prepareStatement("UPDATE price_history SET page_visits=? WHERE price_entry_id=?");
                    st.setInt(1, pcobj.getPage_visits());
                    st.setInt(2, price_entry_id);
                    st.executeUpdate();
                    break;
                case "page_clicks":
                    st = con.prepareStatement("UPDATE price_history SET page_clicks=? WHERE price_entry_id=?");
                    st.setInt(1, pcobj.getPage_clicks());
                    st.setInt(2, price_entry_id);
                    st.executeUpdate();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid column: " + column);
            }
        } catch (Exception e) {
            throw new RuntimeException (e);
        } finally {
            if (st != null)
                try {st.close();} catch (Exception e){}
            if (con!=null)
                try {con.close();} catch (Exception e){}
        }
    }
    
}
