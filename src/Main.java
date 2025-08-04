import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        Product apple = new Fruit("Apple", 40,"Fruit",10,15,"Bag");
        Product potatoe =  new Vegetables("Potatoe", 105,"Vegetable",8,50,"Box","Sandel");

        Package applePackage = new Package(apple, LocalDate.now().plus(50, ChronoUnit.DAYS),15);
        Package potatoePackage = new Package(potatoe, LocalDate.now().plus(100, ChronoUnit.DAYS),45);

        Storage warehouse = new Storage();
        warehouse.addPackage(applePackage);
        warehouse.addPackage(potatoePackage);

        warehouse.printPackages();
    }

    public static void generateRandomProduct () {

    }
}