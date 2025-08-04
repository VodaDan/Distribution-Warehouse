import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws Exception {

        Product apple = new Fruit("Apple", 40,"Fruits",10,15,"Bag");
        Product potatoe =  new Vegetables("Potatoe", 105,"Vegetables",8,50,"Box","Sandel SRL");
        Product orange =  new Vegetables("Orange", 100,"Fruits",10,100,"Pack","Sandel SRL");

        Package applePackage = new Package(apple, LocalDate.now().plus(10, ChronoUnit.WEEKS),15);
        Package orangePackage = new Package(orange, LocalDate.now().plus(2, ChronoUnit.WEEKS),12);
        Package potatoePackage = new Package(potatoe, LocalDate.now().plus(2, ChronoUnit.WEEKS),45);

        Storage warehouse = new Storage();
        warehouse.addPackage(applePackage);
        warehouse.addPackage(potatoePackage);
        warehouse.addPackage(orangePackage);

        warehouse.printPackages();
        warehouse.createReport();
        warehouse.readReport();

        System.out.println(orangePackage.getDiscount());
    }

    public static Package generateRandomProduct () {
        Package generatedPackage = new Package();
        return generatedPackage;
    }
}