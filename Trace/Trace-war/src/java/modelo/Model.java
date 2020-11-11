package modelo;
import java.io.Serializable;

public class Model implements Serializable {
    
    private int model_id;
    private int product_id;
    private String name;
    private String unique_name;
    private String designer;
    private String manufacturer;
    private String image;
    
    //Enhanced attribute
    private float cheapest_price;
    
    public Model() {
    }

    public int getModel_id() {
        return model_id;
    }

    public void setModel_id(int model_id) {
        this.model_id = model_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesigner() {
        return designer;
    }

    public void setDesigner(String designer) {
        this.designer = designer;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getUnique_name() {
        return unique_name;
    }

    public void setUnique_name(String unique_name) {
        this.unique_name = unique_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getCheapest_price() {
        return cheapest_price;
    }

    public void setCheapest_price(float cheapest_price) {
        this.cheapest_price = cheapest_price;
    }
}
