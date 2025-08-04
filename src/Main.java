import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception {


        Product apple = new Fruit("Apple", 40,"Fruits",10,15,"Bag");
        Product potatoe =  new Vegetables("Potatoe", 105,"Vegetables",8,50,"Box","Sandel SRL");
        Product orange =  new Vegetables("Orange", 100,"Fruits",10,100,"Pack","Sandel SRL");

        Package applePackage = new Package(apple, LocalDate.now().plus(10, ChronoUnit.WEEKS),15);
        Package orangePackage = new Package(orange, LocalDate.now().plus(2, ChronoUnit.WEEKS),12);
        Package potatoePackage = new Package(potatoe, LocalDate.now().plus(2, ChronoUnit.WEEKS),45);

        Storage warehouse = new Storage();
        generateRandomProduct(warehouse,5);
//        warehouse.addPackage(applePackage);
//        warehouse.addPackage(potatoePackage);
//        warehouse.addPackage(orangePackage);

//        warehouse.printPackages();
        warehouse.createReport();
        warehouse.readReport();
    }

    public static void generateRandomProduct (Storage warehouse, int numberOfProducts) {
            // Creating lists for random elements
            ArrayList<String> fruits = new ArrayList<>();
            fruits.add("Apple");
            fruits.add("Orange");
            fruits.add("Peaches");
            fruits.add("Kiwi");

            ArrayList<String> vegetables = new ArrayList<>();
            vegetables.add("Potato");
            vegetables.add("Tomato");
            vegetables.add("Onions");

            ArrayList<String> others = new ArrayList<>();
            others.add("Crackers");
            others.add("Milk");
            others.add("Cocoa");

            String productCategory;

        for(int i = 0; i< numberOfProducts;i++) {

            Random random = new Random();
            double quantity = random.nextInt(300) + 1;
            double price = random.nextInt(50) + 1;
            int quality = random.nextInt(6) + 4;
            String unitType;

            if (quantity >= 15 && quantity <= 25) {
                unitType = "Bag";
            } else if (quantity >= 30 && quantity <= 60) {
                unitType = "Box";
            } else if (quantity >= 100 && quantity <= 500) {
                unitType = "Pack";
            } else {
                unitType = "Kg";
            }

            String randomElement = "Others";

            // for case 0 a fruit will be generated, for 1 a vegetable, and for 2 "other" products
            int path = random.nextInt(3);
            switch (path) {
                case 0:
                    //Generating a fruit
                    randomElement = fruits.get(random.nextInt(fruits.size()));
                    productCategory = "Fruits";
                    break;
                case 1:
                    //Generating a vegetable
                    randomElement = vegetables.get(random.nextInt(vegetables.size()));
                    productCategory = "Vegetables";
                    break;
                default:
                    //Generating Others
                    randomElement = others.get(random.nextInt(vegetables.size()));
                    productCategory = "Others";
                    break;
            }

            Product newProduct = new Product(randomElement, price, productCategory, quality, quantity, unitType);
            int randomDate = random.nextInt(6) + 1;
            LocalDate entryDate = LocalDate.now().plus(randomDate, ChronoUnit.WEEKS);
            LocalDate expirationDate = entryDate.plus(randomDate, ChronoUnit.WEEKS);

            int stockQuantity = random.nextInt(10) + 1;

            Package generatedPackage = new Package(newProduct, entryDate, expirationDate, stockQuantity);
            warehouse.addPackage(generatedPackage);
        }

    }
}