public class Product {

    private String name;
    private double price;
    private String productCategory;
    private int nutritionalQuality;
    private double quantity;
    private String unitType;

    public Product(String name, double price, String productCategory, int nutritionalQuality, double quantity, String unitType) {
        this.name = name;
        this.price = price;
        this.productCategory = productCategory;
        this.nutritionalQuality = nutritionalQuality;
        this.quantity = quantity;
        this.unitType = unitType;
    }

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public int getNutritionalQuality() {
        return nutritionalQuality;
    }

    public void setNutritionalQuality(int nutritionalQuality) {
        this.nutritionalQuality = nutritionalQuality;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }
}
