// Brandon Tseng
// CS-1400
// Project #1

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Lab2 {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        // Prompt user for number of customers (max of 40)
        int numberOfCustomers = fetchNumberOfCustomers(scnr);
        scnr.close();

        String[] customerNames = new String[numberOfCustomers];
        int[] creditCardDebts = new int[numberOfCustomers];
        String[] states = new String[numberOfCustomers];

        // Read data from file
        try {
            File file = new File("data.txt");
            Scanner scanner = new Scanner(file);

            boolean firstLine = true;
            while (scanner.hasNextLine()) {
                // Skips the first line which has the data labels
                if (firstLine) {
                    scanner.nextLine();
                    firstLine = false;
                }

                String line;
                String[] parts;

                // Assuming the format is consistent (name, debt, state)
                for (int i = 0; i < numberOfCustomers; i++) {
                    line = scanner.nextLine();
                    parts = line.split("\\|");

                    if (parts.length == 3) {
                        customerNames[i] = parts[0];
                        creditCardDebts[i] = Integer.parseInt(parts[1]);
                        states[i] = parts[2];
                    }
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }

        for (int i = 0; i < numberOfCustomers; i++) {
            System.out.println("Customer: " + customerNames[i]);
            System.out.println("Debt: $" + creditCardDebts[i]);
            System.out.println("State: " + states[i]);
            System.out.println();
        }
    }

    public static int fetchNumberOfCustomers(Scanner scnr) {
        int numberOfCustomers = -1;
        boolean validNum = false;

        while (!validNum) {
            System.out.print("Enter the number of customers (Max 40): ");
            numberOfCustomers = scnr.nextInt();
            if (numberOfCustomers > 0 && numberOfCustomers <= 40) {
                validNum = true;
            } else {
                System.out.println("Invalid number of customers. Please enter a number between 1 and 40.");
            }
        }

        return numberOfCustomers;
    }
}