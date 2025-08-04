public class Vegetables extends Product {
    private String producer;

    public Vegetables(String name, double price, String productCategory, int nutritionalQuality, double quantity, String unitType, String producer) {
        super(name, price, productCategory, nutritionalQuality, quantity, unitType);
        this.producer = producer;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }
}
