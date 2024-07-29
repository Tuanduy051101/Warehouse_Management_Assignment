package com.warehouse_management_system.util.io;

import java.util.List;
import java.util.Scanner;

public class InputValidator {
    private static final Scanner scanner = new Scanner(System.in);

    public static int getValidInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid integer.");
            }
        }
    }

    public static double getValidDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid double.");
            }
        }
    }

    public static String getValidString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            } else {
                System.out.println("Error: Please enter a valid string.");
            }
        }
    }

    public static String getValidSelect(String prompt, List<String> selectList) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (selectList.stream().anyMatch(e -> e.equals(input))) {
                return input;
            } else {
                System.out.println("Error: Please select a valid value.");
            }
        }
    }
}
