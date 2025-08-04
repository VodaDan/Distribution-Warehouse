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

    public ArrayList<Package> getPackages() {
        return packages;
    }

    public void setPackages(ArrayList<Package> packages) {
        this.packages = packages;
    }

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

    public HashMap<String,Integer> calculateTotalQuantities(){
        HashMap<String,Integer> totals = new HashMap<>();
        totals.put("Fruits",0);
        totals.put("Vegetables",0);
        totals.put("Other",0);
        for(Package pack : packages) {
            if(totals.containsKey(pack.getProduct().getProductCategory())) {
                int oldQuantity = totals.get(pack.getProduct().getProductCategory());
                totals.put(pack.getProduct().getProductCategory(), (int)(oldQuantity+(pack.getStockQuantity()*pack.getProduct().getQuantity())));
            }
        }
        return totals;
    }

    public HashMap<String,Integer> calculateTotalPrices(){
        HashMap<String,Integer> totals = new HashMap<>();
        totals.put("Fruits",0);
        totals.put("Vegetables",0);
        totals.put("Other",0);
        for(Package pack : packages) {
            if(totals.containsKey(pack.getProduct().getProductCategory())) {
                int oldQuantity = totals.get(pack.getProduct().getProductCategory());
                totals.put(pack.getProduct().getProductCategory(), (int)(oldQuantity+(pack.getStockQuantity()*pack.getProduct().getPrice())));
            }
        }
        return totals;
    }



    public void createReport() throws Exception {
        this.applyDiscount();
        PrintWriter writer = new PrintWriter("Report.txt");
        ArrayList<String> categories = new ArrayList<>();
        HashMap<String,Integer> totalQuantities = calculateTotalQuantities();
        HashMap<String,Integer> totalPrices = calculateTotalPrices();
        categories.add("Fruits");
        categories.add("Vegetables");
        categories.add("Other");
        for(String category : categories) {
            writer.println("   ");
            writer.print(category + ": Total: ");
            writer.println(totalQuantities.get(category) + " Kg, Total Price: " + totalPrices.get(category));
            for(Package pack: this.packages) {
                if(category.equals(pack.getProduct().getProductCategory())) {
//                    writer.println("  "+pack.getProduct().getName() + ": " + pack.getStockQuantity() + " " + pack.getProduct().getUnitType() +
//                            "(" + (pack.getProduct().getQuantity() * pack.getStockQuantity()) + " Kg), Unit Price:" + pack.getProduct().getPrice() +
//                            ", Total Price:" + (pack.getStockQuantity() * pack.getProduct().getPrice()) + ", Discount: " + pack.getDiscount() + "%(" +
//                            ((pack.getStockQuantity() * pack.getProduct().getPrice()) * (pack.getDiscount()) / 100) + ")");

                    writer.print("  "+pack.getProduct().getName() + ": " + pack.getStockQuantity() + " "  + pack.getProduct().getUnitType() + "(");
                    writer.printf("%.1f Kg), Unit Price:",(pack.getProduct().getQuantity()* pack.getStockQuantity()));
                    writer.printf("%.1f , Total Price: %.1f",(pack.getProduct().getPrice()),((pack.getStockQuantity() * pack.getProduct().getPrice())));
                    writer.printf(", Discount: %.1f%%(%.1f)" ,pack.getDiscount(),((pack.getStockQuantity() * pack.getProduct().getPrice()) * (pack.getDiscount()) / 100));
                    writer.println();
                }
            }
        }
        writer.println();
        System.out.println();
        writer.close();
    }

    public void readReport() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("Report.txt"));
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void applyDiscount() {
        for(Package pack : this.packages) {
            long weeksUntilExpiration = ChronoUnit.WEEKS.between(LocalDate.now(),pack.getExpirationDate());
            String category = pack.getProduct().getProductCategory();

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
