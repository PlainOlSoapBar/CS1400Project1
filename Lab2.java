// Brandon Tseng
// CS-1400
// Project #1

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Lab2 {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        // Prompt user for inputs
        int numberOfCustomers = fetchNumberOfCustomers(scnr); // Max 40
        int debtLimit = fetchDebtLimit(scnr);
        String searchPhrase = fetchSearchPhrase(scnr);
        String stateAbbreviation = fetchStateAbbreviation(scnr);
        scnr.close();

        String[] customerNames = new String[numberOfCustomers];
        int[] creditCardDebts = new int[numberOfCustomers];
        String[] states = new String[numberOfCustomers];

        // Read data from file
        try {
            File file = new File("data.txt");
            Scanner scanner = new Scanner(file);

            boolean firstLine = true;
            // Skips the first line which has the data labels
            if (firstLine) {
                scanner.nextLine();
                firstLine = false;
            }

            String line;
            String[] parts;

            // Assign data to arrays
            for (int i = 0; i < numberOfCustomers; i++) {
                line = scanner.nextLine();
                parts = line.split("\\|");

                if (parts.length == 3) {
                    customerNames[i] = parts[0];
                    creditCardDebts[i] = Integer.parseInt(parts[1]);
                    states[i] = parts[2];
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }

        System.out.println("U.S. Report");
        System.out.printf("Customers: %d\n", numberOfCustomers);
        System.out.printf("Highest debt: %s\n", findHighestDebt(customerNames, creditCardDebts));
        System.out.printf("Number of customers whose names that start with \"%s\": %d\n", searchPhrase,
                findNumberOfCustomersWithSearchPhrase(customerNames, searchPhrase));
    }

    // Fetches the number of customers from the user
    public static int fetchNumberOfCustomers(Scanner scnr) {
        int numberOfCustomers = 0;
        System.out.print("Enter the number of customers (Max 40): ");
        numberOfCustomers = scnr.nextInt();
        if (numberOfCustomers < 1 || 40 < numberOfCustomers) {
            System.out.println("Invalid number of customers. Please enter a number between 1 and 40.");
            return fetchNumberOfCustomers(scnr);
        }

        return numberOfCustomers;
    }

    // Fetches the debt limit from the user
    public static int fetchDebtLimit(Scanner scnr) {
        int debtLimit = 0;

        System.out.print("Enter the debt limit: ");
        debtLimit = scnr.nextInt();
        if (debtLimit < 1) {
            System.out.println("Invalid debt limit. Please enter a number greater than 0.");
            return fetchDebtLimit(scnr);
        }

        return debtLimit;
    }

    // Fetches the search phrase from the user
    public static String fetchSearchPhrase(Scanner scnr) {
        System.out.print("Enter the search phrase: ");
        String searchPhrase = scnr.next();
        if (!searchPhrase.matches("[a-zA-Z]+")) {
            System.out.println("Invalid search phrase. Please enter only letters.");
            return fetchSearchPhrase(scnr);
        }
        return capitalize(searchPhrase);
    }

    // Fetches the state abbreviation from the user
    public static String fetchStateAbbreviation(Scanner scnr) {
        System.out.print("Enter the state abbreviation: ");
        String stateAbbreviation = scnr.next();
        if (!stateAbbreviation.matches("[a-zA-Z]+") || stateAbbreviation.length() != 2) {
            System.out.println("State abbreviation should only contain two letters.");
            return fetchStateAbbreviation(scnr);
        }

        return stateAbbreviation.toUpperCase();
    }

    // Capitalizes the first letter of a string
    public static String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    // Returns the name of the customer with the highest debt
    public static String findHighestDebt(String[] customerNames, int[] creditCardDebts) {
        int highestDebt = 0;
        String highestDebtCustomer = "";

        for (int i = 0; i < creditCardDebts.length; i++) {
            if (creditCardDebts[i] > highestDebt) {
                highestDebt = creditCardDebts[i];
                highestDebtCustomer = customerNames[i];
            }
        }

        return highestDebtCustomer;
    }

    // Returns the number of customers whose names start with the search phrase
    public static int findNumberOfCustomersWithSearchPhrase(String[] customerNames, String searchPhrase) {
        int numberOfCustomersWithSearchPhrase = 0;

        for (int i = 0; i < customerNames.length; i++) {
            if (customerNames[i].startsWith(searchPhrase)) {
                numberOfCustomersWithSearchPhrase++;
            }
        }

        return numberOfCustomersWithSearchPhrase;
    }
}