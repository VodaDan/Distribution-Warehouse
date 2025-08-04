import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Storage {
    private ArrayList<Package> packages;

    public Storage() {
        this.packages = new ArrayList<>();
    }

    public void addPackage(Package pack){
        this.packages.add(pack);
    }

    public ArrayList<Package> getPackages() {
        return packages;
    }

    public void setPackages(ArrayList<Package> packages) {
        this.packages = packages;
    }

    public void printPackages() {
        int index = 1;
        for(Package pack: this.packages) {
            System.out.println(pack.getProduct().getName() + ": "+ pack.getStockQuantity()+" "+pack.getProduct().getUnitType()+
                    "("+(pack.getProduct().getQuantity()*pack.getStockQuantity())+" Kg), Unit Price:"+pack.getProduct().getPrice()+
            ", Total Price:" + (pack.getStockQuantity() * pack.getProduct().getPrice()) + ", Discount: " + pack.getDiscount() + "%(" +
                    ((pack.getStockQuantity() * pack.getProduct().getPrice()) * (pack.getDiscount())/100) + ")" );
        }
    }

    public void applyDiscount() {
        for(Package pack : this.packages) {
            long weeksUntilExpiration = ChronoUnit.WEEKS.between(LocalDate.now(),pack.getExpirationDate());
        }
    }
}
