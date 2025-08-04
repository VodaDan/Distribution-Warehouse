import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Storage {
    private ArrayList<Package> packages;
    private int totalFruits;
    private int totalVegetables;
    private int totalOthers;

    public Storage() {
        this.packages = new ArrayList<>();
    }

    public void addPackage(Package pack){
        this.packages.add(pack);
        switch (pack.getProduct().getProductCategory()) {
            case "Fruits":
                this.totalFruits = (int)(this.totalFruits + pack.getStockQuantity() * pack.getProduct().getQuantity());
                break;
            case "Vegetables":
                this.totalVegetables = (int)(this.totalVegetables + pack.getStockQuantity() * pack.getProduct().getQuantity());
                break;
            case "Others":
                this.totalOthers = (int)(this.totalOthers + pack.getStockQuantity() * pack.getProduct().getQuantity());
                break;
        }
    }

    public void removePackage(Package pack) {
        packages.remove(pack);
    }

    public ArrayList<Package> getPackages() {
        return packages;
    }

    public void setPackages(ArrayList<Package> packages) {
        this.packages = packages;
    }

    // method that prints packages for testing purpose
    public void printPackages() {
        int index = 1;
        this.applyDiscount();
        for(Package pack: this.packages) {
            System.out.println(pack.getProduct().getName() + ": "+ pack.getStockQuantity()+" "+pack.getProduct().getUnitType()+
                    "("+(pack.getProduct().getQuantity()*pack.getStockQuantity())+" Kg), Unit Price:"+pack.getProduct().getPrice()+
                    ", Total Price:" + (pack.getStockQuantity() * pack.getProduct().getPrice()) + ", Discount: " + pack.getDiscount() + "%(" +
                    ((pack.getStockQuantity() * pack.getProduct().getPrice()) * (pack.getDiscount())/100) + ")" );
        }
    }

    // method to calculate the quantities for products categories
    public HashMap<String,Integer> calculateTotalQuantities(){
        HashMap<String,Integer> totals = new HashMap<>();
        totals.put("Fruits",0);
        totals.put("Vegetables",0);
        totals.put("Others",0);
        for(Package pack : packages) {
            if(totals.containsKey(pack.getProduct().getProductCategory())) {
                int oldQuantity = totals.get(pack.getProduct().getProductCategory());
                totals.put(pack.getProduct().getProductCategory(), (int)(oldQuantity+(pack.getStockQuantity()*pack.getProduct().getQuantity())));
            }
        }
        return totals;
    }

    // method to calculate the prices for products categories
    public HashMap<String,Integer> calculateTotalPrices(){
        HashMap<String,Integer> totals = new HashMap<>();
        totals.put("Fruits",0);
        totals.put("Vegetables",0);
        totals.put("Others",0);
        for(Package pack : packages) {
            if(totals.containsKey(pack.getProduct().getProductCategory())) {
                int oldQuantity = totals.get(pack.getProduct().getProductCategory());
                totals.put(pack.getProduct().getProductCategory(), (int)(oldQuantity+(pack.getStockQuantity()*pack.getProduct().getPrice())));
            }
        }
        return totals;
    }
    // Calculate ex: Apples: 1025kg, Total price: 5000, Average Price: 34, Average Discount: 12% .
    public HashMap<String,ArrayList<Integer>> calculateAverage() {
        HashMap<String,ArrayList<Integer>> productDetails = new HashMap<>();
        for(Package pack: packages) {
            String category = pack.getProduct().getProductCategory();
            int newPrice = (int)(pack.getStockQuantity()*pack.getProduct().getQuantity());
            int newQuantity = (int)(pack.getStockQuantity()*pack.getProduct().getQuantity());
            int newDiscount = (int)(pack.getDiscount());
            productDetails.get(category).add(0,(int)(productDetails.get(category).get(0)+newQuantity));
            productDetails.get(category).add(1,(int)(productDetails.get(category).get(1)+newPrice));
            productDetails.get(category).add(2,(productDetails.get(category).get(1)/productDetails.get(category).get(0)));
            productDetails.get(category).add(3,(int)(productDetails.get(category).get(2)+(pack.getStockQuantity()*pack.getProduct().getQuantity())));
        }
        return productDetails;
    }


    // method to generate a report
    public void createReport() throws Exception {
        this.applyDiscount();
        LocalDate today = LocalDate.now();
        PrintWriter writer = new PrintWriter("Report_"+today+".txt");
        ArrayList<String> categories = new ArrayList<>();

        HashMap<String,Integer> totalQuantities = calculateTotalQuantities();
        HashMap<String,Integer> totalPrices = calculateTotalPrices();

        categories.add("Fruits");
        categories.add("Vegetables");
        categories.add("Others");
        writer.println("----Warehouse summary----");
        for(String category : categories) {
            writer.println("   ");
            writer.print(category + ": Total: ");
            writer.println(totalQuantities.get(category) + " Kg, Total Price: " + totalPrices.get(category));
            for(Package pack: this.packages) {
                if(category.equals(pack.getProduct().getProductCategory())) {
                    writer.print("  "+pack.getProduct().getName() + ": " + pack.getStockQuantity() + " "  + pack.getProduct().getUnitType() );
                    if(!(pack.getProduct().getUnitType().equals("Kg"))){
                        writer.printf("(%.0f Kg), Unit Price:",(pack.getProduct().getQuantity()* pack.getStockQuantity()));
                    } else {
                        writer.printf(", Unit Price:");
                    }

                    writer.printf("%.0f , Total Price: %.0f",(pack.getProduct().getPrice()),((pack.getStockQuantity() * pack.getProduct().getPrice())));
                    writer.printf(", Discount: %.0f%%(%.0f) ." ,pack.getDiscount(),((pack.getStockQuantity() * pack.getProduct().getPrice()) * (pack.getDiscount()) / 100));
                    writer.println();
                }
            }
        }
        writer.println();
        System.out.println();
        writer.close();
    }

    public void readReport() {
        LocalDate today = LocalDate.now();
        try {
            List<String> lines = Files.readAllLines(Paths.get("Report_"+today+".txt"));
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // method to check the dates and apply discounts - this is called before generating a report
    public void applyDiscount() {
        for(Package pack : this.packages) {
            long weeksUntilExpiration = ChronoUnit.WEEKS.between(LocalDate.now(),pack.getExpirationDate());
            String category = pack.getProduct().getProductCategory();
            if(weeksUntilExpiration == 0) {
                this.removePackage(pack);
                continue;
            }
            switch (category) {
                case "Vegetables":
                    if(weeksUntilExpiration < 5){
                        pack.setDiscount((5-weeksUntilExpiration)*5);
                    }
                    break;
                case "Fruits":
                    if(weeksUntilExpiration < 6){
                        pack.setDiscount((6-weeksUntilExpiration)*10);
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
