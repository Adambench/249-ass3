import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.NoSuchElementException;

public class TradeManager {

    public static void main(String[] args) {
        TariffList tariffList1 = new TariffList();
        TariffList tariffList2;

        // Step 1: Read from Tariffs.txt and populate tariffList1 (no duplicates)
        try (BufferedReader br = new BufferedReader(new FileReader("../../Tariffs.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split(" ");
                String destinationCountry = parts[0];
                String originCountry = parts[1];
                String productCategory = parts[2];
                double minimumTariff = Double.parseDouble(parts[3]);

                if (!tariffList1.contains(originCountry, destinationCountry, productCategory)) {
                    Tariff tariff = new Tariff(destinationCountry, originCountry, productCategory, minimumTariff);
                    tariffList1.addToStart(tariff);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading Tariffs.txt: " + e.getMessage());
        }

        // Step 2: Copy the list using copy constructor
        tariffList2 = new TariffList(tariffList1);

        // Step 3: Read TradeRequests.txt into ArrayList
        ArrayList<String> tradeRequests = new ArrayList<>();
        try (BufferedReader br2 = new BufferedReader(new FileReader("../../TradeRequest.txt"))) {
            String line;
            while ((line = br2.readLine()) != null) {
                tradeRequests.add(line.trim());
            }
        } catch (IOException e) {
            System.err.println("Error reading TradeRequest.txt: " + e.getMessage());
        }

        // Step 4: Process each trade request
        for (String request : tradeRequests) {
            String[] parts = request.split(" ");
            String requestID = parts[0];
            String origin = parts[1];
            String destination = parts[2];
            String category = parts[3];
            double tradeValue = Double.parseDouble(parts[4]);
            double proposedTariff = Double.parseDouble(parts[5]);

            TariffList.TariffNode node = tariffList1.find(origin, destination, category);

            if (node == null) {
                System.out.println(requestID + " - Rejected (No matching tariff found)");
                continue;
            }

            double minimumTariff = node.getTariff().getMinimumTariff();
            String outcome = tariffList1.evaluateTrade(proposedTariff, minimumTariff);

            switch (outcome) {
                case "accepted":
                    System.out.println(requestID + " - Accepted.");
                    break;
                case "conditionally accepted":
                    double surcharge = tradeValue * ((minimumTariff - proposedTariff) / 100.0);
                    System.out.println(requestID + " - Conditionally Accepted.");
                    System.out.println("Proposed tariff " + proposedTariff + "% is within 20% of the required minimum tariff " + minimumTariff + "%.");
                    System.out.printf("A surcharge of $%.2f is applied.\n", surcharge);
                    break;
                case "rejected":
                    System.out.println(requestID + " - Rejected");
                    System.out.println("Proposed tariff " + proposedTariff + "% is more than 20% below the required minimum tariff " + minimumTariff + "%.");
                    break;
            }
        }

        // Step 5: Prompt user to search tariffs
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter a few tariffs to search (or type 'exit' to quit):");
        while (true) {
            System.out.print("\nEnter Origin Country: ");
            String origin = sc.next();
            if (origin.equalsIgnoreCase("exit")) break;

            System.out.print("Enter Destination Country: ");
            String destination = sc.next();
            if (destination.equalsIgnoreCase("exit")) break;

            System.out.print("Enter Product Category: ");
            String category = sc.next();
            if (category.equalsIgnoreCase("exit")) break;

            TariffList.TariffNode node = tariffList1.find(origin, destination, category);
            if (node != null) {
                System.out.println("Tariff found: " + node.getTariff());
            } else {
                System.out.println("No matching tariff found.");
            }
        }

        // Step 6: Test methods and constructors
        System.out.println("\nTesting TariffList methods...");

        try {
            Tariff testTariff = new Tariff("Germany", "Canada", "Automobile", 8.0);
            tariffList1.insertAtIndex(testTariff, 1);
            System.out.println("Inserted at index 1: " + testTariff);

            tariffList1.deleteFromIndex(1);
            System.out.println("Deleted from index 1.");

            tariffList1.deleteFromStart();
            System.out.println("Deleted from start.");

            Tariff replaceTariff = new Tariff("Brazil", "USA", "Agriculture", 5.0);
            tariffList1.replaceAtIndex(0, replaceTariff);
            System.out.println("Replaced at index 0 with: " + replaceTariff);
        } catch (NoSuchElementException e) {
            System.out.println("Error with index-based operation: " + e.getMessage());
        }

        System.out.println("\nAll tests complete.");
    }
}
