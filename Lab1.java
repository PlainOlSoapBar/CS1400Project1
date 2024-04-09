// Brandon Tseng
// CS-1400
// Project #1

import java.util.Scanner;

public class Lab1 {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        // Prompt user for month and day
        System.out.print("Enter month and day (MM/DD): ");
        String[] userInput = fetchInput(scnr);

        // Stores month and day
        int month = fetchDate(userInput[0]);
        int day = fetchDate(userInput[1]);

        // Check if month and day are valid
        boolean validMonth = isValidMonth(month);
        boolean validDay = isValidDay(day);
        while (!validMonth) {
            System.out.print("Invalid month. Please enter a valid month: ");
            month = scnr.nextInt();
            validMonth = isValidMonth(month);
        }
        while (!validDay) {
            System.out.print("Invalid day. Please enter a valid day: ");
            day = scnr.nextInt();
            validDay = isValidDay(day);
        }

        // Determines and prints the season
        if (validMonth && validDay) {
            System.out.printf("Your date of %s/%s takes place in the %s.\n", month, day, determineSeason(month, day));
        }

        // Close scanner
        scnr.close();
    }

    // Reads user input and returns it as a String array
    public static String[] fetchInput(Scanner scnr) {
        return scnr.nextLine().split("/");
    }

    // Fetches date from user input
    public static int fetchDate(String date) {
        return Integer.parseInt(date);
    }

    // Checks if month is valid
    public static boolean isValidMonth(int month) {
        return (1 <= month && month <= 12);
    }

    // Checks if day is valid
    public static boolean isValidDay(int day) {
        return (1 <= day && day <= 31);
    }

    // Determines season based on month and day
    public static String determineSeason(int month, int day) {
        String season = "";
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
        return season;
    }
}