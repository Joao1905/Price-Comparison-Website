package modelo;
import java.io.Serializable;

public class PriceHistory implements Serializable {
    private int price_entry_id;
    private String date;
    private int product_id;
    private int model_id;
    private Model model;
    private int shop_id;
    private String shop_name;
    private float price;
    private String product_url;
    private int page_visits;
    private int page_clicks;


    public PriceHistory() {    
    }
    
    public int getPrice_entry_id() {
        return price_entry_id;
    }

    public void setPrice_entry_id(int price_entry_id) {
        this.price_entry_id = price_entry_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getModel_id() {
        return model_id;
    }

    public void setModel_id(int model_id) {
        this.model_id = model_id;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getProduct_url() {
        return product_url;
    }

    public void setProduct_url(String product_url) {
        this.product_url = product_url;
    }

    public int getPage_visits() {
        return page_visits;
    }

    public void setPage_visits(int page_visits) {
        this.page_visits = page_visits;
    }

    public int getPage_clicks() {
        return page_clicks;
    }

    public void setPage_clicks(int page_clicks) {
        this.page_clicks = page_clicks;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }
}
