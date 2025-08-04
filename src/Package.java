import java.time.LocalDate;
import java.util.Date;

public class Package {
    private Product product;
    private LocalDate entryDate;
    private LocalDate expirationDate;
    private int stockQuantity;
    private double discount;

    public Package(Product product, LocalDate expirationDate, int stockQuantity) {
        this.product = product;
        this.entryDate = LocalDate.now();
        this.expirationDate = expirationDate;
        if(product.getUnitType().equals("Kg")){
            this.stockQuantity = (int)(product.getQuantity());
        } else {
            this.stockQuantity = stockQuantity;
        }


    }

    public Package(Product product, LocalDate entryDate, LocalDate expirationDate, int stockQuantity) {
        this.product = product;
        this.entryDate = entryDate;
        this.expirationDate = expirationDate;
        this.stockQuantity = stockQuantity;
    }

    public Package() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
        this.product.setPrice(this.product.getPrice() * (discount/100));
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
