import java.util.Date;

public class Package {
    private Product product;
    private Date entryDate;
    private Date expirationDate;
    private double quantity;
    private String unitType;
    private int stockQuantity;

    public Package(Product product, Date entryDate, Date expirationDate, double quantity, String unitType, int stockQuantity) {
        this.product = product;
        this.entryDate = entryDate;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
        this.unitType = unitType;
        this.stockQuantity = stockQuantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

//    private void allocateBaggageType (int quantity) {
//        if(quantity >= 15 && quantity <= 25) {
//            this.unitType = "Bag";
//        } else if (quantity >= 30 && quantity <= 60) {
//            this.unitType = "Box";
//        } else if (quantity >=50 && quantity <= 250) {
//            this.unitType = "Kg";
//        } else if (quantity >= 100 && quantity <= 500) {
//            this.unitType = "Pack";
//        }
//    }
}
