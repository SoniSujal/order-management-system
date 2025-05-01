package org.example.Order_Management_System.utility;

import lombok.extern.slf4j.Slf4j;
import org.example.Order_Management_System.enums.Constants;

import java.util.Scanner;

@Slf4j
public class InputHelper {

    public static Scanner sc = new Scanner(System.in);

    public static String promptForName(Constants tableName) {
        System.out.println("Enter " + tableName +" Name : ");
        String name = sc.nextLine().trim();
        return ValidationUtils.isInvalidNameFormat(name, tableName + " Name") ? "-1" : name;
    }

    public static String promptForEmail() {
        System.out.println("Enter Customer Email ID : ");
        String email = sc.nextLine().trim();

        if (ValidationUtils.isFieldEmpty(email, "Email ID") ||
                ValidationUtils.isInvalidEmailFormat(email, "Email ID")) {
            log.warn("Invalid email input: {}", email);
            return "-1";
        }
        return email;
    }

    public static int promptForId(Constants tableName) {
        System.out.println("Enter "+ tableName +" ID : ");
        String id = sc.nextLine().trim();

        if(ValidationUtils.isFieldEmpty(id, tableName +" Id ")) return 0;

        try {
            return Integer.parseInt(id);
        } catch (NumberFormatException e) {
            log.error("Invalid ID format. Must be a numeric value. !  ");
            return 0;
        }
    }

    public static int promptForQuantity(Constants tablename) {
        System.out.println("Enter " + tablename + " Quantity ");
        String quantity = sc.nextLine().trim();

        if(ValidationUtils.isFieldEmpty(quantity, tablename+ " Quantity") || ValidationUtils.isNegativeNumber(quantity, tablename + " Quantity")) return 0;
        try {
            return Integer.parseInt(quantity);
        } catch (NumberFormatException e) {
            log.error("Invalid quantity format. Must be a numeric value. ! ");
            return 0;
        }
    }

    public static boolean askToContinue() {
        System.out.println(" -- To finalize orders, press \"0\"\n -- To add more, press \"Y\"");
        String input = sc.nextLine().trim();
        if ("0".equals(input)) {
            return false;
        } else if ("y".equalsIgnoreCase(input)) {
            return true;
        } else {
            log.warn("Invalid input. Expected 'Y' or '0'.");
            return false;
        }
    }

}
