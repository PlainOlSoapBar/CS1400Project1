// Brandon Tseng
// Project #1 Lab 1
// April 13th, 2024

import java.util.Scanner;

public class Lab1 {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        // Prompt user for month and day
        System.out.print("Enter month and day (MM/DD): ");
        String[] userInput = scnr.nextLine().split("/");

        // Check if month and day are valid
        boolean validMonth = false;
        boolean validDay = false;
        int month = Integer.parseInt(userInput[0]);
        int day = Integer.parseInt(userInput[1]);
        while (!validMonth || !validDay) {
            if (month < 1 || month > 12) {
                System.out.print("Invalid month. Please enter a valid month: ");
                month = scnr.nextInt();
                continue;
            } else {
                validMonth = true;
            }

            if (day < 1 || day > 31) {
                System.out.print("Invalid day. Please enter a valid day: ");
                day = scnr.nextInt();
                continue;
            } else {
                validDay = true;
            }
        }

        // Determines season
        String season;
        if (month >= 3 && month <= 6) {
            if (month == 3 && day <= 20) {
                season = "Winter";
            } else if (month == 6 && day >= 21) {
                season = "Summer";
            } else {
                season = "Spring";
            }
        } else if (month >= 9 && month <= 12) {
            if (month == 9 && day <= 20) {
                season = "Summer";
            } else if (month == 12 && day >= 21) {
                season = "Winter";
            } else {
                season = "Fall";
            }
        } else {
            if (month >= 6 && month <= 9) {
                season = "Summer";
            } else {
                season = "Winter";
            }
        }

        // Print results
        System.out.printf("Season: %s\n", season);

        // Close scanner
        scnr.close();
    }
}