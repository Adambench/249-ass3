import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Driver {

    
    public static void main(String[] args) {


        Scanner sc = null;
        PrintWriter pw = null;

        ArrayList<Product> products = new ArrayList<>(10); // List to store Trade objects

        try{
            sc= new Scanner(new FileInputStream("TradeData.txt"));
            pw = new PrintWriter(new FileOutputStream("UpdatedTradeData.txt"));
            
        }catch(FileNotFoundException e){
            System.out.println("File not found.");
        }

        while(sc.hasNextLine()){
            String line = sc.nextLine();
            String[] parts = line.split(",");
            String productName = parts[0].trim();
            String productCountry = parts[1].trim();
            String productCategory = parts[2].trim();
            double productInitialPrice = Double.parseDouble(parts[3].trim());

            Product product = new Product(productName, productCountry, productCategory, productInitialPrice);
            products.add(product); // Add the product to the list

        }

        // Sort the product alphabetically by name
        products.sort((p1, p2) -> p1.getName().compareTo(p2.getName()));

        for(Product product : products){
            pw.println(product.getName() + "," + product.getCountry() + "," + product.getCategory() + "," + product.getPrice());
        }
        
        pw.close();
        sc.close();
    }
}
